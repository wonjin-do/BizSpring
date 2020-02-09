package com.cal.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalendarVO {
	private int year;
	private int month;
	private int endDay;// 28, 30, 31 중 하나
	private int dayOfWeek;
	private int numOfRows;
	private int days[][];
	private String meaning[];
	private List<ScheduleVO> scheduleList;
}
