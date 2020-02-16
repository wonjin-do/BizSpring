package com.bizsprint.vo;

import lombok.Data;

@Data
public class ScoreVO{
	private int score;
	private int rankBySubject;//해당 과목에서 몇등했는지
	public ScoreVO(int score) {
		super();
		this.score = score;
	}
}
