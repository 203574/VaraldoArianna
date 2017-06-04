package it.polito.tdp.ariannavaraldo.model;

import java.sql.Time;

public class PersonArea {

	private int area;
	private Time start;
	private Time end;

	public Time getStart() {
		return start;
	}
	public void setStart(Time start) {
		this.start = start;
	}
	public Time getEnd() {
		return end;
	}
	public void setEnd(Time end) {
		this.end = end;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}

}
