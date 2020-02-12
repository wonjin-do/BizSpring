package com.cal.vo;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DayInfo {
	//생성자로 받을 인자
	private int year;
	private int month;
	private int day;
	
	
	private int dayOfWeek;
	private DayOfWeek strDayofWeek;
	private String date;

	//holiday정보는 다른 DTO가 담당
	//db에서 가져오기
	private List<ScheduleVO> todoList;
	
	public DayInfo(int year, int month, int day) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
		 
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, day);
		
		dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		date = sdf.format(cal.getTime());
	}
	
	
}