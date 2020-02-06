package com.cal.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalendarVO {
	private int year;
	private int month;
	private int endDay;
	private int dayOfWeek;
	
	private int days[];
	
}
