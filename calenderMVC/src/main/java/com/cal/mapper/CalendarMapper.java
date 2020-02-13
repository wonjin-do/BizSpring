package com.cal.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cal.vo.MemberVO;
import com.cal.vo.ScheduleVO;

public interface CalendarMapper {
	
	@Insert("INSERT INTO tbl_member (id, password) values (#{id}, #{password})")
	public int register(MemberVO memberVO);
	
	@Select("SELECT *FROM tbl_member WHERE id=#{id} AND password=#{password}")
	public MemberVO getMember(MemberVO memberVO);
	
	@Select("SELECT * FROM tbl_schedule WHERE idx=#{idx}")
	public ScheduleVO getSchedule(int idx);
	
	@Select("SELECT * FROM tbl_schedule WHERE startdate=#{date} AND userid=#{id} order by idx desc")
	public List<ScheduleVO> getScheduleByDate(@Param("date")String date, @Param("id")String id);
	
	
	@Select("SELECT * FROM tbl_schedule WHERE userid=#{id} AND startdate between #{firstdate} AND #{lastdate}")
	public List<ScheduleVO> getScheduleList(@Param("firstdate")String firstdate, @Param("lastdate")String lastdate, @Param("id")String id);
	
	
	@Update("UPDATE tbl_schedule SET startdate=#{startdate}, enddate=#{enddate}, title=#{title}, content=#{content} WHERE idx=#{idx}")
	public int updateSchedule(ScheduleVO vo);
	
	@Delete("DELETE FROM tbl_schedule WHERE idx=#{idx}")
	public int deleteSchedule(int idx);
	
	@Insert("INSERT INTO tbl_schedule (userid, startdate, enddate, title, content) values (#{userid},#{startdate},#{enddate},#{title},#{content})")
	public int insertSchedule(ScheduleVO vo);
	

	
	
	
}
