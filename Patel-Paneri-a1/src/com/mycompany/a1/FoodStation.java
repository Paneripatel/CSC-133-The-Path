package com.mycompany.a1;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
//Food station class is for ant, if it collides with food station then it will increase its food level.
public class FoodStation extends Fixed {
	private int capacity;
	
	public FoodStation() {
		super(10+new Random().nextInt(40-10+1), new Point(new Random().nextFloat()*1000f, new Random().nextFloat()*1000f), ColorUtil.rgb(0, 255, 0));
		capacity = getSize();
	}
	//Setters and Getters
	public int getCapacity() {
		return capacity;
	}
	
	public void setCapacity(int capacity) {
		this.capacity=capacity;
	}
}
