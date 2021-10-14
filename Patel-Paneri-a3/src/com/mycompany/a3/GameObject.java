package com.mycompany.a3;

import java.util.ArrayList;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public abstract class GameObject implements IDrawable, ICollider{
	
	private int size;
	private Point location;
	private int color;
	private GameWorld gameWorld;
	
	public abstract void draw(Graphics g, Point pCmpRelPrnt);
	
	public boolean collidesWith(ICollider other) {
		boolean result = false;
		if(!(other instanceof GameObject)) {
			result = false;
		}
		else{
				Point loc = getLocation();
				int size = getSize();
				GameObject otherGO = (GameObject)other;
				Point otherLoc = otherGO.getLocation();
				int otherSize = otherGO.getSize();
			
				float R1 = loc.getX() + size / 2f;
				float L1 = loc.getX() - size / 2f;
				float T1 = loc.getY() + size / 2f;
				float B1 = loc.getY() - size / 2f;
			
				float R2 = otherLoc.getX() + otherSize / 2f;
				float L2 = otherLoc.getX() - otherSize / 2f;
				float T2 = otherLoc.getY() + otherSize / 2f;
				float B2 = otherLoc.getY() - otherSize / 2f;
					
				if(R1 < L2 || L1 > R2) {
					result = false;
				}
				else if(T2 < B1 || T1 < B2) {
				result = false;
				}
				else {
				result = true;
				}
			}
		
		if(result == false) {
			if(collisions.contains(other)) {
				collisions.remove(other);
			}
			if(other.getCollisions().contains(this)) {
				other.getCollisions().remove(this);
			}
		}
		else {
			if(collisions.contains(other)) {
				result = false;
				if(!other.getCollisions().contains(this)) {
					other.getCollisions().add(this);
				}
			}
			else if (other.getCollisions().contains(this)) {
				result = false;
				collisions.add(other);
			}
		}
		
		if(result) {
			System.out.println("Collision: " + toString() + " | " + other.toString());
		}
		
		return result;
	}
	
	private ArrayList<ICollider> collisions = new ArrayList<ICollider>();
	
	public ArrayList<ICollider> getCollisions(){
		return collisions;
	}
	
	public void handleCollision(ICollider other) { 
		if(collisions.contains(other)) {
			if(!other.getCollisions().contains(this)) {
				other.getCollisions().add(this);
			}
			return;
		}
		if(other.getCollisions().contains(this)) {
			if(!collisions.contains(other)) {
				collisions.add(other);
			}
			return;
		}
		collisions.add(other);
		other.getCollisions().add(this);
	}
	
	
	//checking the location
	public GameObject(int size, Point location, int color, GameWorld gameWorld) {
		this.size = size;
		if(gameWorld == null) {
			gameWorld = new GameWorld();
		}
		if(location.getX()<0) {
			location.setX(0);
		}
		else if(location.getX()>gameWorld.getSizeX()) {
			location.setX(gameWorld.getSizeX());
		}
		if(location.getY()<0) {
			location.setY(0);
		}
		else if(location.getY()>gameWorld.getSizeY()) {
			location.setY(gameWorld.getSizeY());
		}
		
		this.location = location;
		this.color = color;
		this.gameWorld = gameWorld;
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
		return gameWorld.getSizeX();
	}
	protected int getWorldSizeY() {
		return gameWorld.getSizeY();
	}
	protected GameWorld getGameWorld(){
		return gameWorld;
	}
	protected void setGameWorld(GameWorld gameWorld){
		this.gameWorld = gameWorld;
	}
}
