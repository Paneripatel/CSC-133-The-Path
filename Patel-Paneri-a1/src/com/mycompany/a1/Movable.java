package com.mycompany.a1;

import com.codename1.charts.models.Point;

public abstract class Movable extends GameObject{
	
	private int heading;
	private int speed;
	
	public Movable(int size, Point location, int color) {
		super(size, location, color);
	}
	//Ensuring all movable objects does not move out of limitations
	public void move() {
		double deltaX = Math.cos(Math.toRadians(90-heading))*speed;
		double deltaY = Math.sin(Math.toRadians(90-heading))*speed;
		Point newLocation = new Point(getLocation().getX()+(float)deltaX, getLocation().getY()+(float)deltaY);
		if(newLocation.getX()>1000f) {
			newLocation.setX(1000f);
		}
		else if(newLocation.getX()<0f) {
			newLocation.setX(0f);
		}
		if(newLocation.getY()>1000f) {
			newLocation.setY(1000f);
		}
		else if(newLocation.getY()<0f) {
			newLocation.setY(0f);
		}
		setLocation(newLocation);
	}
	//Getters and setters
	public int getHeading() {
		return heading;
	}
	public int getSpeed() {
		return speed;
	}
	protected void setHeading(int heading) {
		this.heading = heading;
	}
	protected void setSpeed(int speed) {
		this.speed = speed;
	}

}
