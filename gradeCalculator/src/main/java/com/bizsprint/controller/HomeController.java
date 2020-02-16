package com.bizsprint.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bizsprint.gradeProcess.GradeServiceImpl;
import com.bizsprint.vo.ScoreResultBySubjectVO;
import com.bizsprint.vo.StudentVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request ,Model model) {
		System.out.println("접속");
		GradeServiceImpl csv = new GradeServiceImpl();
		Map<String, ScoreResultBySubjectVO> resBySub = csv.makeResultBySubject();
		Map<String, StudentVO> 				resBySTU = csv.makeScoreListBySTU();
		char option = 'm';//'e','m','t'
		List<Map.Entry<String, StudentVO>> sortedResBySTU = csv.sortedScoreListBySTU(option);
		model.addAttribute("resBySub", resBySub);
		model.addAttribute("resBySTU", resBySTU);
		model.addAttribute("sortedResBySTU", sortedResBySTU);
		model.addAttribute("option",option);
		return "home";
	}
}
