package com.cal.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import com.cal.vo.MemberVO;
import com.cal.vo.ScheduleVO;

public interface CalendarMapper {
	
	@Insert("INSERT INTO tbl_member (id, password) values (#{id}, #{password})")
	public int register(MemberVO memberVO);
	
	@Select("SELECT *FROM tbl_member WHERE id=#{id} AND password=#{password}")
	public MemberVO getMember(MemberVO memberVO);
	
	@Select("SELECT * FROM tbl_schedule WHERE userid=#{id} AND startdate between #{firstdate} AND #{lastdate}")
	public List<ScheduleVO> getSchedule(@Param("firstdate")String firstdate, @Param("lastdate")String lastdate, @Param("id")String id);
	
	@Insert("INSERT INTO tbl_schedule (userid, startdate, enddate, title, content) values (#{userid},#{startdate},#{enddate},#{title},#{content})")
	public int setSchedule(ScheduleVO vo);
	

	
	
	
}
