package com.mycompany.a2;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
//Food station class is for ant, if it collides with food station then it will increase its food level.
public class FoodStation extends Fixed {
	private int capacity;
	private static final int MAX_SIZE = 40;
	private static final int MIN_SIZE = 10;

	
	public FoodStation(int worldSizeX, int worldSizeY) {
		super(worldSizeX, worldSizeY, MIN_SIZE + new Random().nextInt(MAX_SIZE - MIN_SIZE + 1), new Point(new Random().nextFloat() * worldSizeX, new Random().nextFloat() * worldSizeY), ColorUtil.rgb(0, 255, 0));
		capacity = getSize();
	}
	//Setters and Getters
	public int getCapacity() {
		return capacity;
	}
	
	public void setCapacity(int capacity) {
		this.capacity=capacity;
		if(capacity==0) {
			setColor(0, 0, 0);
		}
	}
	public String toString() {
		int color = getColor();
		return("Food Station: loc=" +  Math.round(this.getLocation().getX() * 10.0) / 10.0 + "," + Math.round(this.getLocation().getY() * 10.0) / 10.0 + " color=[" + ColorUtil.red(color) + "," + ColorUtil.green(color) + "," + ColorUtil.blue(color) + "] size=" + this.getSize() + " capacity=" + this.getCapacity());
	}
}
