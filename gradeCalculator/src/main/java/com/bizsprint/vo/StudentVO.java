package com.bizsprint.vo;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentVO {
	private int rankByAll;
	private String name;//학생이름 - 고유한 정보
	private int total=0;
	private double avg;
	
	private Map<String, ScoreVO> scoreMap = new HashMap<>(); // 과목별 - 점수정보
	public StudentVO(String name, String subject, int score) {
		this.name = name;
		this.scoreMap.put(subject, new ScoreVO(score));
		this.total += score;
	}
	@Override
	public String toString() {
		return "StudentVO [rankByAll=" + rankByAll + ", name=" + name + ", total=" + total + ", avg=" + Math.round(avg*100)/100.0
				+ ", scoreMap=" + scoreMap + "]";
	}


	
	
}
