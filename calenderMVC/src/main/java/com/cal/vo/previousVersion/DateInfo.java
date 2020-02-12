package com.cal.vo.previousVersion;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateInfo {
	private int year;
	private int month;
	private int endDay;// 28, 30, 31 중 하나
	private int dayOfWeek;
	
	private String strDate;
}
