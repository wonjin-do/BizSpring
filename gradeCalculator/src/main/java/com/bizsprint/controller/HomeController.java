package com.bizsprint.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bizsprint.service.GradeService;
import com.bizsprint.vo.ScoreResultBySubjectVO;
import com.bizsprint.vo.StudentVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	GradeService csv;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request ,Model model) {
		System.out.println("접속");
		
		//StudentVO에 있는 List가 넣어진 순서대로 출력됨
		Map<String, StudentVO> 				unsortedBySTU = csv.makeScoreListBySTU();
		
		//HashMap의 해쉬값에 따라 출력됨.(문제발생)
		Map<String, ScoreResultBySubjectVO> unsortedBySub = csv.makeResultBySubject();
		char option = 'm';//'e','m','t'

		//List에 넣어진  순서대로 출력됨
		List<Map.Entry<String, StudentVO>> sortedResBySTU = csv.sortedScoreListBySTU(option);
		
		model.addAttribute("unsortedBySub", unsortedBySub);
		model.addAttribute("unsortedBySTU", unsortedBySTU);
		model.addAttribute("sortedResBySTU", sortedResBySTU);
		model.addAttribute("option",option);
		
		return "home";
	}
}
