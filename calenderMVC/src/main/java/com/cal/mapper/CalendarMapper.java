package com.cal.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cal.vo.MemberVO;

public interface CalendarMapper {
	
	@Select("SELECT date FROM tbl_holiday WHERE date LIKE CONCAT(#{year},'-',#{month},'%')")
	public List<Date> getHoliday(@Param("year")int year, @Param("month")String month);
	
	@Insert("INSERT INTO tbl_member (id, password) values (#{id}, #{password})")
	public int register(MemberVO memberVO);
	
	@Select("SELECT *FROM tbl_member WHERE id=#{id} AND password=#{password}")
	public MemberVO getMember(MemberVO memberVO);
}
