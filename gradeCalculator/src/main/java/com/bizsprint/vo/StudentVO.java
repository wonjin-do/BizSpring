package com.bizsprint.vo;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentVO {
	private String name;//학생이름 - 고유한 정보
	private Map<String, ScoreVO> scoreMap = new LinkedHashMap<String, ScoreVO>(); // 과목별 - 점수정보
	private int total=0;
	private double avg;
	private int rankByTotalScore;
	
	public StudentVO(String name, String subject, int score) {
		this.name = name;
		this.scoreMap.put(subject, new ScoreVO(score));
		this.total += score;
	}

	@Override
	public String toString() {
		return "StudentVO [name=" + name + ", scoreMap=" + scoreMap + ", total=" + total + ", avg=" + Math.round(avg*100)/100.0
				+ ", rankByTotalScore=" + rankByTotalScore + "]";
	}
}
