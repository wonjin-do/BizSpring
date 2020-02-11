package com.cal.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.cal.vo.CalendarVO;
import com.cal.vo.MemberVO;
import com.cal.vo.ScheduleVO;
import com.cal.vo.newVersion.CalDTO;

public interface CalService {
	public CalendarVO getHome(CalendarVO cldVO, String id) throws ParseException;
	
	public CalDTO getHome_ver2(CalDTO calDTO, String id);
	
	public void register(MemberVO member);

	public boolean login(MemberVO member);
	
	public int addSchedule(ScheduleVO vo);
}
