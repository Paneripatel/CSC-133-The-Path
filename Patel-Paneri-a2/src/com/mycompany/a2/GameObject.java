package com.mycompany.a2;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public abstract class GameObject {
	
	private int size;
	private Point location;
	private int color;
	private int worldSizeX;
	private int worldSizeY;
	
	//checking the location
	public GameObject(int worldSizeX, int worldSizeY, int size, Point location, int color) {
		this.size = size;
		this.worldSizeX = worldSizeX;
		this.worldSizeY = worldSizeY;
		
		if(location.getX()<0) {
			location.setX(0);
		}
		else if(location.getX()>worldSizeX) {
			location.setX(worldSizeX);
		}
		if(location.getY()<0) {
			location.setY(0);
		}
		else if(location.getY()>worldSizeY) {
			location.setY(worldSizeY);
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
	public String toString() {
		return null;
	}
	protected int getWorldSizeX() {
		return worldSizeX;
	}
	protected int getWorldSizeY() {
		return worldSizeY;
	}
	protected void setWorldSizeX(int x) {
		worldSizeX = x;
	}
	protected void setWorldSizeY(int y) {
		worldSizeY = y;
	}
}
