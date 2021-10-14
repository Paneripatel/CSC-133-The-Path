package com.mycompany.a1;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public abstract class GameObject {
	
	private int size;
	private Point location;
	private int color;
	
	//checking the location
	public GameObject(int size, Point location, int color) {
		this.size = size;
		
		if(location.getX()<0) {
			location.setX(0);
		}
		else if(location.getX()>1000) {
			location.setX(1000);
		}
		if(location.getY()<0) {
			location.setY(0);
		}
		else if(location.getY()>1000) {
			location.setY(1000);
		}
		
		this.location = location;
		this.color = color;
	}
	//Getters and Setters
	public int getSize() {
		return size;
	}
	
	public Point getLocation() {
		return location;
	}
	
	public void setLocation(Point location) {
		this.location = location;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int red, int green, int blue) {
		color = ColorUtil.rgb(red,  green, blue);
	}
}
