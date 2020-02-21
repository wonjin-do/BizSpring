package com.bizsprint.vo;

import lombok.Data;

@Data
public class ScoreResultBySubjectVO {
	private String subjectName;
	private int total;
	private double avg;
	private int numOfStu;
	@Override
	public String toString() {
		return "ScoreResultBySubjectVO [subjectName=" + subjectName + " total=" + total + ", avg=" + Math.round(avg*100)/100.0 + ", numOfStu=" + numOfStu + "]";
	}
	
}
