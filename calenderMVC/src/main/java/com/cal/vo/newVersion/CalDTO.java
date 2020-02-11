package com.cal.vo.newVersion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CalDTO {
	private final static int TOTAL_CELL = 42 + 1;
	private final static int PREIOD_int = 7;
	private final static double PREIOD_double = 7.0;
	
	//생성자함수로 입력받는 값
	private int year;
	private int month;

	//생성자에서 계산할 값
	private int dayOfweek;
	private int lastDay;
	private int numOfweeks;
	
	
	private List<DayInfo> calendar;
//	private Map<String, List<String>> schedule;
//	private Map<String, List<String>> holidayInfo;
	
	//생성자에서 계산할 값
	private Date today;
	private int prevMonth;
	private int prevEndDay;
	private int nextMonth;
	 	

	
	public CalDTO(int year, int month) {
		super();
		
		this.year = year;
		this.month = month;
		
		calendar = new ArrayList<DayInfo>(TOTAL_CELL);
		
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, 1);
		dayOfweek 	= cal.get(Calendar.DAY_OF_WEEK);		//첫날의 요일
		lastDay	    = cal.getActualMaximum(Calendar.DATE);  //해당 월의 마지막 일(date)를 반환
		
		numOfweeks =(int)Math.ceil((dayOfweek + lastDay - 1)/PREIOD_double);
		
		today = new Date();
	
		prevMonth = (month == 1) ? 12 : month -1;
		cal.set(year, prevMonth - 1, 1); 				  //동시 세팅 연,월,일 / month는 0이 1월이므로 -1을 해준다
		prevEndDay = cal.getActualMaximum(Calendar.DATE); // 해당 월 마지막 날짜 28, 30, 31
		
		
	}
	
	
	
	
}
