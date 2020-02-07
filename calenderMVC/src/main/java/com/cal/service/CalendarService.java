package com.cal.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.cal.vo.CalendarVO;
import com.cal.vo.MemberVO;

public interface CalendarService {
	public CalendarVO getHome(CalendarVO cldVO) throws ParseException;
	
	public List<Date> getHodliday(int year, int month);
	
	
	public void register(MemberVO member);

	public boolean login(MemberVO member);
}
