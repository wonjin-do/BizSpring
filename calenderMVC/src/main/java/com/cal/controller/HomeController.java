package com.cal.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cal.service.CalendarService;
import com.cal.vo.CalDTO;
import com.cal.vo.HolidayVO;
import com.cal.vo.MemberVO;
import com.cal.vo.ScheduleVO;
import com.cal.vo.previousVersion.CalendarVO;

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
	public String calendar(@RequestParam(value="year", defaultValue="0")int year, @RequestParam(value="month", defaultValue="0")int month, HttpServletRequest request, Model model)  {
		log.info("접속");
		//달력 범위 예외 처리
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("customer");
		
		CalDTO calendar = calendarService.showCalendar(year, month, id);
		model.addAttribute("calendar", calendar);
		
		//휴일
		Map<String, List<HolidayVO>> holidayMap = calendarService.getHoliday(calendar.getYear(), calendar.getMonth());
		model.addAttribute("holidayMap",holidayMap);

		//if(id == null) return "calendar";
		
		//일정
		Map<String, List<ScheduleVO>> scheduleMap = calendarService.getScheduleMap(calendar.getYear(), calendar.getMonth(), id);
		model.addAttribute("scheduleMap",scheduleMap);
		
		return "calendar";
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
	public String login(MemberVO member, @RequestParam("currMonth")String currMonth,  HttpServletRequest request ,RedirectAttributes rttr) {
		HttpSession session = request.getSession();
		int month = Integer.parseInt(currMonth);
		if(calendarService.login(member)) {
			session.setAttribute("customer", member.getId());
			rttr.addFlashAttribute("month" ,month);
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
	
	@PostMapping(value="/schedule/new", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ScheduleVO> addSchedule(@RequestBody ScheduleVO vo, HttpServletRequest req ) {
		HttpSession session = req.getSession();
		log.info("ScheduleVO: " + vo);
		String id = (String)session.getAttribute("customer");
		vo.setUserid(id);
		int insertCount = calendarService.addSchedule(vo);
		
		log.info("createSchedule Count: " + insertCount);
		System.out.println("추가된 schedule정보: " + vo);
		return insertCount == 1  
				? new ResponseEntity<>(vo, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="/schedule/{idx}")
	public ResponseEntity<ScheduleVO> getSchedule(@PathVariable("idx") int idx, HttpServletRequest req ) {
		System.out.println("요청옴");
		HttpSession session = req.getSession();
		String sessionId = (String)session.getAttribute("customer");
		if(sessionId == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		ScheduleVO scheduleVO = calendarService.getSchedule(idx);
		System.out.println(scheduleVO);
		return new ResponseEntity<ScheduleVO>(scheduleVO, HttpStatus.OK);
	}
	
	@GetMapping(value="/schedule/date/{date}")
	public ResponseEntity<Integer> getScheduleByDate(@PathVariable("date") String date, HttpServletRequest req ) {
		System.out.println("요청옴");
		HttpSession session = req.getSession();
		String sessionId = (String)session.getAttribute("customer");
		if(sessionId == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		int idx = calendarService.getScheduleByDate(date, sessionId);
		return new ResponseEntity<>(idx, HttpStatus.OK);
	}
	
	@PutMapping(value="/schedule/modify.json")
	public ResponseEntity<ScheduleVO> modifySchedule(@RequestBody ScheduleVO vo, HttpServletRequest req ) {
		System.out.println("수정요청 진입");
		HttpSession session = req.getSession();
		String sessionId = (String)session.getAttribute("customer");
		vo.setUserid(sessionId);
		if(sessionId == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return calendarService.modifySchedule(vo) == 1  
				? new ResponseEntity<>(vo, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping(value="/schedule/delete/{idx}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> cancelSchedule( @PathVariable("idx") int idx, HttpServletRequest req){
		System.out.println("삭제 요청");
		HttpSession session = req.getSession();
		String sessionId = (String)session.getAttribute("customer");
		if(sessionId == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return calendarService.deleteSchedule(idx) == 1
				? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
		
}


 