package com.midterm.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;

import lombok.extern.java.Log;
@Service
@Log
public class GradeServiceImpl implements GradeService{
	private static final String dirPath = "C:\\Users\\Bizspring\\Desktop\\score\\";
	private static final String TOTAL 	= "tot";
	private static final String AVG 	= "avg";
	
	private List<String> student_Keys;	//세로 키 
	private List<String> subject_Keys; 	//과목 키
	private List<String> titleKeys;		//가로 전체 키
	
	private Map<String, String> translate; 		//번역
	
	private Map<String, Map<String, Object>> scoreBoard;  //상단) 점수표
	private Map<String, Map<String, Object>> resultBoard; //하단) 합계/평균 표
	
	private List<Map.Entry<String, Map<String, Object>>> entryList; //view에 전달될 결과
	@Override
	public List<String> getTitle_Keys() {
		// TODO Auto-generated method stub
		return titleKeys;
	}
	@Override
	public Map<String, String> getTranslateMap(){
		return translate;
	}
	
	@Override
	public Map<String, Map<String, Object>> resultBoard(){
		return resultBoard;
	}

	@Override
	public List<Map.Entry<String, Map<String, Object>>> scoreBoard(String option) {
		// TODO Auto-generated method stub
		
		//필드 초기화(번역, 객체생성)
		init();
		
		
		
		//정보읽기
		readFromFiles();
		
		//subject 정렬하기
		sortsubjectName();
		entryList = new ArrayList<>(scoreBoard.entrySet()); 

		//성적표 상단의 모든 key값들 세팅완료(subject + tot + avg)
		completeAllKeys();
		
		//통계내기
		personalResult();
		subjectResult();
		
		//jsp에서 쓸 수 있는 색깔기록하기
		setBackgroundColor();
		
		// 등수 기록
		setRankByScore(entryList);
		
		//옵션에 따른 정렬
		sortedByOption(option, entryList);
		
		//console Test
		printAll(entryList);	

		return entryList;
	}

	private void registerLanguage() {
		translate.put("korean","국어");
		translate.put("english","영어");
		translate.put("math","수학");
		
		translate.put("tot","합계");
		translate.put("avg","평균");
		
		
	}

	private void completeAllKeys() {
		titleKeys = new ArrayList<String>();
		titleKeys.addAll(subject_Keys);
		titleKeys.add("tot");
		titleKeys.add("avg");
	}
	private void sortsubjectName() {
		Collections.sort(subject_Keys,new Comparator<String>(){
			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				if(o2.equals("korean") || o1.equals("math")) {
					return 1;
				}
				return -1;
			}
		});
	}
	private void setBackgroundColor() {
		for(Map.Entry<String, Map<String, Object>> entry : scoreBoard.entrySet()) {
			String stuName = entry.getKey();
			Map<String, Object> stuScores = entry.getValue();
			
			//개인별 과목
			for(String title : subject_Keys) {
				if((int)stuScores.get(title) > (double)resultBoard.get(AVG).get(title)) {
					stuScores.put(stuName+title, "up");
				}else
					stuScores.put(stuName+title, "down");
			}
			
			//개인별 결과
			if((int)stuScores.get(TOTAL) > (double)resultBoard.get(AVG).get(TOTAL))//합계 
				stuScores.put(stuName+TOTAL, "up");
			else
				stuScores.put(stuName+TOTAL, "down");
			
			if((double)stuScores.get(AVG) > (double)resultBoard.get(AVG).get(AVG))//평균
				stuScores.put(stuName+AVG, "up");
			else
				stuScores.put(stuName+AVG, "down");
		}
	}

	private void sortedByOption(String option, List<Map.Entry<String, Map<String, Object>>> entryList) {
		System.out.println("option: " + option);
		Collections.sort(entryList, new Comparator<Map.Entry<String, Map<String, Object>>>() {
			@Override
			public int compare(Entry<String, Map<String, Object>> o1, Entry<String, Map<String, Object>> o2) {
				// TODO Auto-generated method stub
				System.out.println((int)o2.getValue().get(option) + " " + (int)o1.getValue().get(option));
				return (int)o2.getValue().get(option) - (int)o1.getValue().get(option);
			}
		});
	}

	private void init() {
		translate = new HashMap<String, String>();
		//번역
		registerLanguage();
		student_Keys = new ArrayList<String>();
		resultBoard = new LinkedHashMap<String, Map<String,Object>>();
		scoreBoard = new HashMap<String, Map<String,Object>>();
		subject_Keys = new ArrayList<String>();
	}

	private void setRankByScore(List<Map.Entry<String, Map<String, Object>>> entryList) {
		for(String title: titleKeys) {
			if(title.equals("avg"))continue;
			Collections.sort(entryList, new Comparator<Map.Entry<String, Map<String, Object>>>() {
				@Override
				public int compare(Entry<String, Map<String, Object>> o1, Entry<String, Map<String, Object>> o2) {
					// TODO Auto-generated method stub
					//과목(sub)점수를 통해 비교
					return (int)o2.getValue().get(title) - (int)o1.getValue().get(title);
				}
			});
			int rank=1;
			for(Map.Entry<String, Map<String, Object>> entry : entryList) {
				String name = entry.getKey();
				if(rank==1) {
					scoreBoard.get(name).put(title+"rank", "+");
				}else if(rank==10) {
					scoreBoard.get(name).put(title+"rank", "-");
				}
				
				rank++;
			}
		}
	}

	//과목별 합계, 평균 (표에서 아래 두줄)
	private void subjectResult() {
		//별도의 맵에 담아야 겠다. sort할 때 걸리적거림
		Map<String, Object> rowTotal = new HashMap<String, Object>();
		Map<String, Object> rowAvg = new HashMap<String, Object>();
		//하단의 과목별 결과
		resultBySubject(rowTotal, rowAvg);

		//하단의 합계의 합계/평균 , 평균의 합계/평균
		resultFinalParts(rowTotal, rowAvg);
		
		resultBoard.put(TOTAL, rowTotal);
		resultBoard.put(AVG, rowAvg);
	}

	private void resultFinalParts(Map<String, Object> rowTotal, Map<String, Object> rowAvg) {
		int finalTot=0; double finalTotAvg;
		double finalAvgTot=0; double finalAvgTotAvg;
		
		//오른쪽 두개 열의 총계 및 평균
		for(Map.Entry<String,Map<String, Object>> entry : scoreBoard.entrySet()) {
			String stuName = entry.getKey();
			Map<String, Object> scores = entry.getValue();
			//오른쪽 두번째 합계
			finalTot += (int)scores.get(TOTAL);
			//오른쪽 첫번째 합계
			finalAvgTot += (double)scores.get(AVG);
		}
		finalAvgTot = Math.round(finalAvgTot*100)/100.0;
		//합계의 평균
		finalTotAvg = finalTot/(double)student_Keys.size(); 
		finalTotAvg = Math.round(finalTotAvg*100)/100.0;
		//평균의 평균 
		finalAvgTotAvg = finalAvgTot/(double)student_Keys.size();
		finalAvgTotAvg = Math.round(finalAvgTotAvg*100)/100.0;

		rowTotal.put(TOTAL, finalTot);
		rowAvg.put(TOTAL, finalTotAvg);
		
		rowTotal.put(AVG, finalAvgTot);
		rowAvg.put(AVG, finalAvgTotAvg);
	}

	private void resultBySubject(Map<String, Object> rowTotal, Map<String, Object> rowAvg) {
		for(String sub : subject_Keys) {
			int tot=0;double avg=0;
			for(Map<String, Object> elem : scoreBoard.values()) {
				tot += (int)elem.get(sub);
			}
			avg = tot/(double)student_Keys.size();
			avg = Math.round(avg*100)/100.0;
			rowTotal.put(sub, tot);
			rowAvg.put(sub, avg);
		}
	}
	
	private void personalResult() {
		//학생별 통계
		for(Map<String, Object> elem : scoreBoard.values()) {
			int tot=0;double avg=0;
			for(String sub : subject_Keys) {
				tot += (int)elem.get(sub);
			}
			avg = tot/(double)subject_Keys.size();
			avg = Math.round(avg*100)/100.0;
			elem.put(TOTAL, tot);
			elem.put(AVG, avg);
		}
	}

	public void readFromFiles() {
		File path = new File(dirPath);
		File[] fileList = path.listFiles();
		if(fileList.length > 0){
			List<String[]> scoresFromAfile;
		    for(int i=0; i < fileList.length; i++){
		  		String filename = fileList[i].getName();
		  		
		  		log.info(filename);
		  		
		  		String subjectName = filename.substring(0, filename.indexOf('.'));
		  		subject_Keys.add(subjectName);
		  		scoresFromAfile = readFromOneFile(dirPath + filename);
		  		writeScore(subjectName, scoresFromAfile);
		    }
		}else {
			System.out.println("성적파일이 없다");
		}
	}
	
	
	
	public List<String[]> readFromOneFile(String subfiledir) {
		// TODO Auto-generated constructor stub\
		List<String[]> strSoreList = new ArrayList<String[]>();
		try(InputStreamReader is = new InputStreamReader(new FileInputStream(subfiledir), "utf-8");
	            CSVReader reader = new CSVReader(is,'\t');){
			strSoreList = reader.readAll();
			//strSoreList = myReadAll(subfiledir);
		}catch(Exception e){
            e.printStackTrace();
        }
		return strSoreList;// 리턴타입  배열 [] 로 한 이유. 과목 수는 정해져 있기 때문
	}
	
	private void writeScore(String subject, List<String[]> scoresFromAfile) {
		for(String scoreInfo[] : scoresFromAfile) {
			String stuName 	= scoreInfo[0];
			int    score 	= Integer.parseInt(scoreInfo[1]);
			Map<String, Object> value;
			if(scoreBoard.containsKey(stuName)) {
				value = scoreBoard.get(stuName);
				value.put(subject, score);
			}else {
				student_Keys.add(stuName);
				Map<String, Object> personalScore = new HashMap<String, Object>();
				personalScore.put(subject, score);
				scoreBoard.put(stuName, personalScore);
			}
		}
	}
	
	private void printAll(List<Map.Entry<String, Map<String, Object>>> entryList) {
		
		for(Map.Entry<String, Map<String, Object>> entry : entryList) {
			String k = entry.getKey();
			Map<String, Object> v = entry.getValue();
			System.out.print(entry.getKey() + "\t");
			for(String title : subject_Keys) {
				//System.out.print(v.get(title) + " " + v.get(k+title) + "\t");
				System.out.print(v.get(title) + "\t");
			}
			//System.out.print(v.get("tot") + " " + v.get(k+"tot") + "  \t" + v.get("avg") + "\t");
			System.out.print(v.get("tot") + "\t" + v.get("avg") + "\t");
			
//			for(String title : v.keySet()) {
//				if(!subject_Keys.contains(title))
//					System.out.print(title + ":    \t" + v.get(title) + "\t");
//			}
			System.out.println();
		}
		
		
		System.out.println("---------------------------------------------");
		for(Map.Entry<String, Map<String, Object>> elem : resultBoard.entrySet()) {
			String k = elem.getKey();
			Map<String, Object> v = elem.getValue();
			System.out.print(elem.getKey() + "\t");
			for(String sub : subject_Keys) {
				System.out.print(v.get(sub) + "\t");
			}
			/*
			 * for(String title : v.keySet()) { System.out.print(title + ":\t" +
			 * v.get(title)+ "\t"); }
			 */
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		GradeService gs = new GradeServiceImpl();
		gs.scoreBoard("math");
	}
}
