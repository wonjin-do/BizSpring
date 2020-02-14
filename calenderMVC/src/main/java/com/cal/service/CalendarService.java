package com.cal.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cal.vo.CalDTO;
import com.cal.vo.DayInfo;
import com.cal.vo.HolidayVO;
import com.cal.vo.MemberVO;
import com.cal.vo.ScheduleVO;
import com.cal.vo.previousVersion.CalendarVO;

public interface CalendarService {
	//달력
	public CalDTO  getCalendar( int year, int month, String id);
	
	//휴일
	public Map<String, List<HolidayVO>> getHoliday(CalDTO calendar);
	
	
	//스케줄
	public Map<String, List<ScheduleVO>> getScheduleMap(CalDTO calendar, String id);
	
	public ScheduleVO getSchedule(int idx);

	public int getOneDayScheduleByDate(String date, String id);
	
	public int addSchedule(ScheduleVO vo);
	
	public int modifySchedule(ScheduleVO vo);

	public int deleteSchedule(int idx);
	
	//로그인
	public void register(MemberVO member);

	public boolean login(MemberVO member);
	
	
	
	
}
