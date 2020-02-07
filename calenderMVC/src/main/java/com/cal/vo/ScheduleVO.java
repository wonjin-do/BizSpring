package com.cal.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleVO {
	private int sno;
	private int mno;
	private Date startdate;
	private Date enddate;
	private String title;
	private String content;
}
