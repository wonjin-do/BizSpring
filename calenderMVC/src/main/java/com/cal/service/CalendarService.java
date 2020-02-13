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
	public CalendarVO getHome(CalendarVO cldVO, String id);
	
	public CalDTO  showCalendar( int year, int month, String id);

	public Map<String, List<HolidayVO>> getHoliday(int year, int month);
	
	public Map<String, List<ScheduleVO>> getScheduleMap(int year, int month, String id);
	
	public ScheduleVO getSchedule(int idx);

	public int getScheduleByDate(String date, String id);
	
	
	public int addSchedule(ScheduleVO vo);
	
	public int modifySchedule(ScheduleVO vo);

	public int deleteSchedule(int idx);
	
	public void register(MemberVO member);

	public boolean login(MemberVO member);
	
	
	
	
}
