package com.cal.vo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CalDTO {
	public final static int TOTAL_CELL = 42 + 1;
	public final static int PREIOD_int = 7;
	public final static double PREIOD_double = 7.0;
	public final static int DECEMBER = 12;
	public final static int JANUARY = 1;

	
	//생성자함수로 입력받는 값
	private int year;
	private int month;

	//생성자에서 계산할 값
	private int dayOfweek;
	private int lastDay;
	private int numOfweeks;
	
	
	private List<DayInfo> days1D;
	private List<List<DayInfo>> days2D;
//	private Map<String, List<String>> schedule;
//	private Map<String, List<String>> holidayInfo;
	
	//생성자에서 계산할 값
	private String today;
	
	private int prevYear;
	private int prevMonth;
	private int prevEndDay;
	
	private int nextYear;
	private int nextMonth;
	 	

	
	public CalDTO(int year, int month) {
		super();
		days1D = new ArrayList<DayInfo>(TOTAL_CELL);
		days2D = new ArrayList<List<DayInfo>>();
		refineYearAndMomth(year, month); // 0 년, 13월 , 0월 처리
		Calendar cal = setThisMonthFeild(year, month);
		setPrevYearNmonth(year, month, cal);
	}



	private Calendar setThisMonthFeild(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, 1);
		dayOfweek 	= cal.get(Calendar.DAY_OF_WEEK);		//첫날의 요일
		lastDay	    = cal.getActualMaximum(Calendar.DATE);  //해당 월의 마지막 일(date)를 반환
		numOfweeks =(int)Math.ceil((dayOfweek + lastDay - 1)/PREIOD_double);
		today = LocalDate.now().toString();
		return cal;
	}



	private void setPrevYearNmonth(int year, int month, Calendar cal) {
		prevYear = (month == JANUARY) ? year - 1 : year;
		prevMonth = (month == JANUARY) ? DECEMBER : month -1;
		cal.set(year, prevMonth - 1, 1); 				  //동시 세팅 연,월,일 / month는 0이 1월이므로 -1을 해준다
		prevEndDay = cal.getActualMaximum(Calendar.DATE); // 해당 월 마지막 날짜 28, 30, 31
		nextYear = (month == DECEMBER) ? year + 1 : year;
		nextMonth = (month == DECEMBER) ? JANUARY : month + 1;
	}



	private void refineYearAndMomth(int year, int month) {
		LocalDate d = LocalDate.now();
		this.year = year;
		this.month = month;
		if(this.year == 0) {
			this.year = d.getYear();
			if(this.month == 0) {
				this.month = d.getMonthValue();
			}
		}
		else {
			if(this.month == 0) {
				this.year--;
				this.month = DECEMBER;
			}else if(month == DECEMBER + 1) {
				this.year++;
				this.month = JANUARY;
			}
		}
	}
	
	
	
	
}
