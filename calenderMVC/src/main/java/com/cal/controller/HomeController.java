package com.cal.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.cal.service.CalendarService;
import com.cal.vo.CalendarVO;
import com.cal.vo.HolidayVO;
import com.cal.vo.MemberVO;
import com.cal.vo.ScheduleVO;
import com.cal.vo.newVersion.CalDTO;

import lombok.extern.java.Log;
/**
 * Handles requests for the application home page.
 */
@Controller
@Log
public class HomeController {
	
	@Autowired
	CalendarService calendarService; 
	
	@GetMapping("/")
	public void calendar(@RequestParam("year")int year, @RequestParam("month")int month, HttpServletRequest request, Model model) throws ParseException {
		log.info("접속");
		//달력 범위 예외 처리
		Date d = new Date();
		if(year == 0) {
			year = d.getYear();
			if(month == 0) {
				month = d.getMonth();
			}
		}
		else {
			if(month == 0) {
				year--;
				month = 12;
			}else if(month == 13) {
				year++;
				month = 1;
			}
		}
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("customer");
		CalDTO res = calendarService.showCalendar(year, month, id);
		model.addAttribute("calDTO", res);
		
		//Map<String, List<HolidayVO>> holidayList = calendarService.getHoliday(year, month);
		//model.addAttribute("holidayList",holidayList);
		
		//model.addAttribute("scheduleList",scheduleList);
		
	}
	
	//@GetMapping(value = "/")
	public String home(CalendarVO cldVO, HttpServletRequest request, Model model) throws ParseException {
		log.info("접속");
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("customer");
		CalendarVO res = calendarService.getHome(cldVO, id);
		model.addAttribute("cldVO", res);
		return "home";
		
		
		//Formatter fmt = new Formatter(Locale.US);
		//fmt.format("      %tB %tY", cal, cal); // January 2020 출력 
		//System.out.println(fmt);
	}
	
	
	
	
	@GetMapping(value = "/join")
	public String joinPage() {
		return "join";
	}
	
	@PostMapping(value = "/join")
	public String join(MemberVO member) {
		
		calendarService.register(member);
		
		return "redirect:/";
	}
	
	@PostMapping(value = "/login")
	public String login(MemberVO member,  HttpServletRequest request ,RedirectAttributes rttr) {
		HttpSession session = request.getSession();
		
		if(calendarService.login(member)) {
			session.setAttribute("customer", member.getId());
			return "redirect:/";
		}
		
		return "redirect:login";
		
	}
	
	@GetMapping(value="logout")
	public String logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
		return "redirect:/";
	}
	
	@PostMapping(value="/schedule/new")
	public ResponseEntity<String> create(@RequestBody ScheduleVO vo, HttpServletRequest req ) {
		HttpSession session = req.getSession();
		log.info("ScheduleVO: " + vo);
		String id = (String)session.getAttribute("customer");
		vo.setUserid(id);
		int insertCount = calendarService.addSchedule(vo);
		log.info("createSchedule Count: " + insertCount);

		return insertCount == 1  
				? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
		
	
}


 