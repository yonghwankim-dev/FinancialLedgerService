package entity;

import java.util.Date;

public class Expense {
	private Date regdate;
	private int price;
	private String content;
	
	public Expense()
	{
		
	}
	
	public Expense(Date regdate, int price, String content) {
		this.regdate = regdate;
		this.price = price;
		this.content = content;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return regdate + ", " + price + ", " + content;
	}
	
	
	
	
}
