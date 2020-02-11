package com.cal.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cal.vo.CalendarVO;
import com.cal.vo.HolidayVO;
import com.cal.vo.MemberVO;
import com.cal.vo.ScheduleVO;
import com.cal.vo.newVersion.CalDTO;

public interface CalendarService {
	public CalendarVO getHome(CalendarVO cldVO, String id);
	
	public CalDTO showCalendar(int year, int month, String id);

	public Map<String, List<HolidayVO>> getHoliday(int year, int month);
	
	public void register(MemberVO member);

	public boolean login(MemberVO member);
	
	public int addSchedule(ScheduleVO vo);

	
}
