package com.mycompany.a1;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class Flag extends Fixed{
	private int sequenceNumber;
	
	public Flag(Point location, int sequenceNumber) {
		super(10, location, ColorUtil.rgb(0, 0, 255));
		this.sequenceNumber = sequenceNumber;
	}
	//Flags are not allowed to change color once they are created. 
	public void setColor(int red, int green, int blue) {
		
	}
	public int getSequenceNumber() {
		return sequenceNumber;
	}
}
