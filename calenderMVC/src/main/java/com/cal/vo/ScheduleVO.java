package com.cal.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleVO {
	private String idx;
	private String userid;
	private String startdate;
	private String enddate;
	private String title;
	private String content;
}
