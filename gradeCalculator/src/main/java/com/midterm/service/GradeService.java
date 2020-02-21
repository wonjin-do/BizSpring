package com.midterm.service;

import java.util.List;
import java.util.Map;

public interface GradeService {
	
	public List<String> getTitle_Keys();
	
	public Map<String, String> getTranslateMap();
	
	public List<Map.Entry<String, Map<String, Object>>> scoreBoard(String option);
	
	public Map<String, Map<String, Object>> resultBoard();

}
