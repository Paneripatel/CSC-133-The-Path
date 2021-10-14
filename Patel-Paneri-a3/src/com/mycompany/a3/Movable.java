package com.mycompany.a3;

import com.codename1.charts.models.Point;

public abstract class Movable extends GameObject{
	
	private int heading;
	private int speed;
	
	public Movable(int size, Point location, int color, GameWorld gameWorld) {
		super(size, location, color, gameWorld);
	}
	//Ensuring all movable objects does not move out of limitations
	public void move(int ms) {
		double deltaX = Math.cos(Math.toRadians(90-heading))*speed*ms/1000f;
		double deltaY = Math.sin(Math.toRadians(90-heading))*speed*ms/1000f;
		Point newLocation = new Point(getLocation().getX()+(float)deltaX, getLocation().getY()+(float)deltaY);
		if(newLocation.getX()>getWorldSizeX()) {
			newLocation.setX(getWorldSizeX());
		}
		else if(newLocation.getX()<0f) {
			newLocation.setX(0f);
		}
		if(newLocation.getY()>getWorldSizeY()) {
			newLocation.setY(getWorldSizeY());
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
