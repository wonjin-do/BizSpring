package com.cafeMenu.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafeMenu.service.MenuService;
import com.cafeMenu.vo.MenuVO;
import com.cafeMenu.vo.Node;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	private MenuService menuService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		logger.info("Welcome home! The client locale is {}.");
		
//		Map<Integer, List<MenuVO>> menuMap =  menuService.getMenuVOList();
//		model.addAttribute("menuMap",menuMap);
		
		List<Node>  nonRecursiveList = menuService.getnodeList();
		String res = menuService.getMenu();
		
		model.addAttribute("res", res);
		model.addAttribute("nonRecursiveList", nonRecursiveList);
		return "home";
	}
	
}
