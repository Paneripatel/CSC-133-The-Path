package com.mycompany.a2;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class Flag extends Fixed{
	private int sequenceNumber;
	private static final int SIZE = 10;
	
	public Flag(int worldSizeX, int worldSizeY, Point location, int sequenceNumber) {
		super(worldSizeX, worldSizeY, SIZE, location, ColorUtil.rgb(0, 0, 255));
		this.sequenceNumber = sequenceNumber;
	}
	//Flags are not allowed to change color once they are created. 
	public void setColor(int red, int green, int blue) {
		
	}
	public int getSequenceNumber() {
		return sequenceNumber;
	}
	public String toString() {
		int color = getColor();
		return("Flag: loc=" + Math.round(this.getLocation().getX()*10.0)/10.0 + ","+Math.round(this.getLocation().getY()*10.0)/10.0+" color=["+ColorUtil.red(color)+","+ColorUtil.green(color)+","+ColorUtil.blue(color)+"] size="+this.getSize()+" seqNum=" + this.getSequenceNumber());	
	}
}