package com.cafeMenu.service;

import java.util.List;
import java.util.Map;

import com.cafeMenu.vo.MenuVO;
import com.cafeMenu.vo.Node;

public interface MenuService {
	
	public List<Node> getnodeList();
	
	public String getMenu();
}
