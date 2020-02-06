package com.cal.controller;

import java.util.Calendar;
import java.util.Formatter;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cal.vo.CalendarVO;

import lombok.extern.java.Log;

/**
 * Handles requests for the application home page.
 */
@Controller
@Log
public class HomeController {
	
	@GetMapping(value = "/")
	public String home(CalendarVO cldVO, Locale locale, Model model) {
		
		int year = cldVO.getYear();
		int month = cldVO.getMonth();
		int endDay;
		int dayOfWeek;
		
		int prevMonth = (month == 1) ? 12 : month -1;
		int prevEndDay;
		
		
		
		Calendar cal = Calendar.getInstance();
		// cal.set(Calendar.YEAR, year); // 입력받은 년도로 세팅
		// cal.set(Calendar.MONTH, month); // 입력받은 월로 세팅
		
		//이전 달
		cal.set(year, prevMonth - 1, 1); //동시 세팅 연,월,일 / month는 0이 1월이므로 -1을 해준다
		prevEndDay = cal.getActualMaximum(Calendar.DATE); // 해당 월 마지막 날짜 28, 30, 31
		
		//이번 달
		cal.set(year, month - 1, 1); //동시 세팅 연,월,일 / month는 0이 1월이므로 -1을 해준다
		endDay = cal.getActualMaximum(Calendar.DATE); // 해당 월 마지막 날짜 28, 30, 31
		dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 해당 날짜의 요일 숫자값(1:일요일 … 7:토요일)
		int cell = 1;
		int days[] = new int[42 + 1];
		//지난 달 일수 채우기
		for(;cell<dayOfWeek;cell++) {
			days[cell] = prevEndDay - dayOfWeek + cell;
		}
		
		//현재 달 일수 채우기
		for(int day=1; day <= endDay; cell++, day++) {
			days[cell] =  day;
		}
		
	

		//다음 달 일수 채우기
		for(int day=1;cell<=days.length-1;cell++,day++) {
			days[cell] = day;
		}
				
		Formatter fmt = new Formatter(Locale.US);
		fmt.format("      %tB %tY", cal, cal); // January 2020 출력 
		//System.out.println(fmt);
		//System.out.println(" Su Mo Tu We Th Fr Sa");
//		for (int i = 1; i <= endDay; i++) {
//			if (i == 1) {
//				for (int j = 1; j < dayOfWeek; j++) { //dayOfWeek 전날 까지 공백출력
//					System.out.print("   ");
//				}
//			}
//			if (i < 10) {
//				// 한자릿수일 경우 공백을 추가해서 줄맞추기
//				System.out.print(" ");
//			}
//			System.out.print(" " + i);
//			if (dayOfWeek % 7 == 0) {
//				// 한줄에 7일씩 출력
//				System.out.println();
//			}
//			dayOfWeek++;
//		}
		
		cldVO = new CalendarVO(year, month, endDay, dayOfWeek, days);
		model.addAttribute("cldVO", cldVO);
		return "home";
	}
	
}
