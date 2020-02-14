package com.cal.etc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cal.vo.CalDTO;
import com.cal.vo.HolidayVO;

public class HolidayTextRead {

	List<String> rows;
	
	
	
	public HolidayTextRead() {
		super();
		this.rows = new ArrayList<String>();
	}

	public List<String> readTextFile(String filePath)  {
		try{
			//파일 객체 생성
	        File file = new File(filePath);
	        FileReader filereader = new FileReader(file);//입력 스트림 생성
	        BufferedReader bufReader = new BufferedReader(filereader);//입력 버퍼 생성			
	        String row;
	        while((row = bufReader.readLine()) != null){
	        	rows.add(row);//라인단위로 읽는 함수는 \n 을 읽어오진 않음
	        }
		 }catch (FileNotFoundException e) {
	            // TODO: handle exception
        }catch(IOException e){
            System.out.println(e);
        }
        return rows;
	}
	
	public Map<String, List<HolidayVO>> makeHolidayMap(CalDTO calendar){
		Map<String, List<HolidayVO>> holidayMap = new HashMap<>();
		List <HolidayVO> value;
		for(String row : rows) {
			String[] rowParts = row.split("\t"); // "2020-01-26"  "설연휴"   "Y"
			HolidayVO holidayVO = new HolidayVO(rowParts[1], rowParts[2]);
			String key = rowParts[0];
			if(!holidayMap.containsKey(rowParts[0])) {
				value = new ArrayList<HolidayVO>();
				value.add(holidayVO);
				holidayMap.put(key, value);
			}else {
				value = holidayMap.get(key);
				value.add(holidayVO);
			}
		}
		return holidayMap;
	}
	
	
}
