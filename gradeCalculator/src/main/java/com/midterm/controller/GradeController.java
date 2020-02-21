package com.midterm.controller;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.midterm.service.GradeService;
@Controller
public class GradeController {
	@Autowired
	GradeService gradeService;
	
	@GetMapping(value= {"/score"})
	public String getScores(@RequestParam("option")String option ,Model model) {
			
		List<Map.Entry<String, Map<String, Object>>> scoreBoard = gradeService.scoreBoard(option);
		Map<String, Map<String, Object>> resultBoard = gradeService.resultBoard();
		List<String> titleKeys = gradeService.getTitle_Keys();
		Map<String, String> translateMap = gradeService.getTranslateMap();
		
		model.addAttribute("option",option);
		
		model.addAttribute("translateMap",translateMap);
		model.addAttribute("titleKeys", titleKeys);
		
		model.addAttribute("scoreBoard", scoreBoard);
		model.addAttribute("resultBoard", resultBoard);
		
		
		
		return "score";
	}
	
}
