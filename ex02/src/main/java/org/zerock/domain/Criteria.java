package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter
public class Criteria {

  private int pageNum;
  private int amount;
  private int start;
  
  private String type;
  private String keyword;

  public Criteria() {
    this(1, 10);
  }

  
  public Criteria(int pageNum, int amount) {
	  this.pageNum = pageNum;
	  this.amount = amount;
  }
  
  public void setStartAndEnd(int pageNum, int amount) {
	  this.pageNum = pageNum;
	  this.amount = amount;
	  
	  this.start = (pageNum-1) * amount;
  }
  
  
  public String[] getTypeArr() {
    
    return type == null? new String[] {}: type.split("");
  }

	public void setPageNum(int pageNum) {
		setStartAndEnd(pageNum, this.amount);
	}
	
	public void setAmount(int amount) {
		setStartAndEnd(this.pageNum, amount);
	}
}
