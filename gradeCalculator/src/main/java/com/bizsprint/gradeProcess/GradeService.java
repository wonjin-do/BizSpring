package com.bizsprint.gradeProcess;

import java.util.List;
import java.util.Map;

import com.bizsprint.vo.ScoreResultBySubjectVO;
import com.bizsprint.vo.StudentVO;

public interface GradeService {

	
	public Map<String, StudentVO> makeScoreListBySTU();
	
	public Map<String, ScoreResultBySubjectVO> makeResultBySubject();
	
	public List<Map.Entry<String, StudentVO>> sortedScoreListBySTU(char option);
	
}
