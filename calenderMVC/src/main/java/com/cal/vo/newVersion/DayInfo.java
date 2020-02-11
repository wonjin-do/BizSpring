package com.cal.vo.newVersion;

import java.util.Date;
import java.util.List;

import com.cal.vo.ScheduleVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DayInfo {
	
	private int year;
	private int month;
	private int day;
	private String date;

	//holiday정보는 다른 DTO가 담당
	
	//db에서 가져오기
	private List<ScheduleVO> todoList;
	
	public DayInfo(int year, int month, int day) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
		
		Date d = new Date(year, month, day);
		date = d.toString();
	}
	
	
}