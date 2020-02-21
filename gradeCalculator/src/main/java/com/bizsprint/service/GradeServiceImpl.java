package com.bizsprint.service;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bizsprint.vo.ScoreResultBySubjectVO;
import com.bizsprint.vo.ScoreVO;
import com.bizsprint.vo.StudentVO;
import com.opencsv.CSVReader;
@Service 
public class GradeServiceImpl implements GradeService {
	public static final String filenames[] = {"korean", "english", "math"};
	public static final String ext = "dat";
	public static final String filedir = "C:\\Users\\Bizspring\\Desktop\\";
	public static int NUM_OF_SUBJECT = 3;
	public static int num_of_stu;
	public static List<List<String[]>> allSubjectInfo;
	public static final Map<String, ScoreResultBySubjectVO> resultBySubject = new HashMap<>();
	
	public static Map<String, String> maxMin = new HashMap<String, String>();
	
	@Override//학생별
	public Map<String, StudentVO> makeScoreListBySTU(){
		readFromFiles();
		return scoreBySTU(); //가로로 vo
	}

	@Override//아래 통계
	public Map<String, ScoreResultBySubjectVO> makeResultBySubject() {//세로로 vo
		// TODO Auto-generated method stub
		readFromFiles();
		Map<String, ScoreResultBySubjectVO> resultBySubject = new LinkedHashMap<String, ScoreResultBySubjectVO>();  
		for(int i=0; i<filenames.length; i++) {
			String subject = filenames[i];
			putScoreBySubject(resultBySubject, subject, allSubjectInfo.get(i));
		}// scoreList ,resultBySubject
		setAvgEachSub(resultBySubject);
		return resultBySubject;
	}

	@Override//과목별-정렬
	public List<Map.Entry<String, StudentVO>> sortedScoreListBySTU(char option) {
		// TODO Auto-generated method stub
		readFromFiles();
		Map<String, StudentVO> unSorted = scoreBySTU();
        List<Map.Entry<String, StudentVO>> list = new LinkedList<>(unSorted.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, StudentVO>>() {
            @Override
            public int compare(Map.Entry<String, StudentVO> o1, Map.Entry<String, StudentVO> o2) {
            	int idx = -1;
            	 if(option=='k') {
                 	idx =  0;
                 }else if(option=='e') {
                	 idx = 1;
                 }else if(option=='m') {
                 	idx = 2;
                 }else {
                	 return o2.getValue().getTotal() - o1.getValue().getTotal();  
                 }
            	 return o2.getValue().getScoreMap().get(filenames[idx]).getScore() - o1.getValue().getScoreMap().get(filenames[idx]).getScore();
            }
        });
        int rankByTotalScore = 1;
        for(Map.Entry<String, StudentVO> entry : list) {
        	entry.getValue().setRankByTotalScore(rankByTotalScore++);
        }
        
		return list;
	}
	
	private Map<String, StudentVO> scoreBySTU() {
		Map<String, StudentVO> studentInfoMap = new HashMap<String, StudentVO>();  
		for(int i=0; i<filenames.length; i++) {
			String subject = filenames[i];
			putScoreBySTU(studentInfoMap, subject, allSubjectInfo.get(i));
		}
		setAvgEachStu(studentInfoMap);
		return studentInfoMap;
	}
	
	private void setAvgEachStu(Map<String, StudentVO> studentInfoMap) {
		for(Map.Entry<String, StudentVO> elem : studentInfoMap.entrySet()) {
			StudentVO stu = elem.getValue();
			stu.setAvg(stu.getTotal() / NUM_OF_SUBJECT );
		}
	}
	 
	private void setAvgEachSub(Map<String, ScoreResultBySubjectVO> resultBySubject) {
		// TODO Auto-generated method stub
		for(Map.Entry<String, ScoreResultBySubjectVO> elem : resultBySubject.entrySet()) {
			ScoreResultBySubjectVO stu = elem.getValue();
			stu.setAvg(stu.getTotal() / (double)stu.getNumOfStu());
		}
	}
	
	public void readFromFiles() {
		allSubjectInfo = new ArrayList<List<String[]>>();
		for(String subject : filenames) {
			StringBuffer subfiledir = new StringBuffer();
			subfiledir.append(filedir);
			subfiledir.append(subject );
			subfiledir.append(".");
			subfiledir.append(ext);
			allSubjectInfo.add(readFromOneFile(subfiledir.toString())) ; 
		}
	}
	
	public List<String[]> readFromOneFile(String subfiledir) {
		// TODO Auto-generated constructor stub\
		List<String[]> strSoreList = new ArrayList<String[]>();
		try(InputStreamReader is = new InputStreamReader(new FileInputStream(subfiledir), "utf-8");
	            CSVReader reader = new CSVReader(is,'\t');){
			//strSoreList = reader.readAll();
			strSoreList = myReadAll(subfiledir);
		}catch(Exception e){
            e.printStackTrace();
        }
		return strSoreList;// 리턴타입  배열 [] 로 한 이유. 과목 수는 정해져 있기 때문
	}
	
	public List<String[]> myReadAll(String subfiledir){
		List<String[]> res = new ArrayList<String[]>();
		try{
			//파일 객체 생성
	        File file = new File(subfiledir);
	        FileReader filereader = new FileReader(file);//입력 스트림 생성
	        BufferedReader bufReader = new BufferedReader(filereader);//입력 버퍼 생성			
	        String row;
	        String[] rowArray;
	        while((row = bufReader.readLine()) != null){
	        	rowArray = row.split("\t");//라인단위로 읽는 함수는 \n 을 읽어오진 않음
	        	res.add(rowArray);
	        }
		 }catch (FileNotFoundException e) {
	            // TODO: handle exception
        }catch(IOException e){
            System.out.println(e);
        }
		
		return res;
	}
	
	private void putScoreBySTU(Map<String, StudentVO> studentInfoMap, String subject, List<String[]> ScoresFromFile) {
		String key;			//학생 이름	
		StudentVO value;	//점수 정보
		for(String scoreInfo[] : ScoresFromFile) {
			String stuName = scoreInfo[0];
			int score = Integer.parseInt(scoreInfo[1]);
			if(studentInfoMap.containsKey(stuName)) {
				//이미 있는 학생정보
				key 	= stuName;
				value	= studentInfoMap.get(key);
				value.getScoreMap().put(subject, new ScoreVO(score)); // StudentVO의 Map 에 put
				value.setTotal(value.getTotal() + score);
			}else {
				value = new StudentVO(stuName, subject, score);
				studentInfoMap.put(stuName, value);						// StudentInfoMap 에 put
			}
		}
	}
	
	private void putScoreBySubject(Map<String, ScoreResultBySubjectVO> resultBySubject, String subject, List<String[]> ScoresFromFile) {
		// TODO Auto-generated method stub
		String key = subject;					//과목이름	
		ScoreResultBySubjectVO value;	//점수 정보
		for(String scoreInfo[] : ScoresFromFile) {
			int score = Integer.parseInt(scoreInfo[1]);
			if(resultBySubject.containsKey(key)) {
				//이미 있는 과목정보
				value	= resultBySubject.get(key);
				value.setTotal(value.getTotal() + score);
			}else {
				value = new ScoreResultBySubjectVO();
				value.setTotal(score);
				value.setSubjectName(key);
				resultBySubject.put(key, value);
			}
			value.setNumOfStu(value.getNumOfStu() + 1); //과목별 응시 인원수 체크
		}
	}
			
	
	public static void main(String[] args) {
		GradeServiceImpl csv = new GradeServiceImpl();
		Map<String, ScoreResultBySubjectVO> map1 = csv.makeResultBySubject();
		Map<String, StudentVO> map2 = csv.makeScoreListBySTU();
		char option = 'k';//'e','m','t'
		List<Map.Entry<String, StudentVO>> list = csv.sortedScoreListBySTU(option);
		
		System.out.println("makeScoreListBySTU()");
		for(Map.Entry<String, StudentVO> entry : map2.entrySet()) {
			System.out.println(entry);
		}
		System.out.println();

		System.out.println("makeResultBySubject()");
		for(Map.Entry<String, ScoreResultBySubjectVO> entry : map1.entrySet()) {
			System.out.println(entry);
		}
		System.out.println();
		
		
		System.out.printf("sortedScoreListBySTU( %c )\n",option);
		for(Map.Entry<String, StudentVO> entry : list) {
			System.out.println(entry);
		}
		System.out.println();
		
		System.out.println("makeResultBySubject()");
		for(Map.Entry<String, ScoreResultBySubjectVO> entry : map1.entrySet()) {
			System.out.println(entry);
		}
		System.out.println();
		
	}

}
