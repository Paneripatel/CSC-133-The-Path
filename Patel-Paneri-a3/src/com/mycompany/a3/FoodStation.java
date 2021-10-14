package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
//Food station class is for ant, if it collides with food station then it will increase its food level.
public class FoodStation extends Fixed {
	private int capacity;
	private static final int MAX_SIZE = 100;
	private static final int MIN_SIZE = 40;

	
	public FoodStation(GameWorld gameWorld) {
		super(MIN_SIZE + new Random().nextInt(MAX_SIZE - MIN_SIZE + 1), new Point(new Random().nextFloat() * gameWorld.getSizeX(), new Random().nextFloat() * gameWorld.getSizeY()), ColorUtil.rgb(0, 255, 0), gameWorld);
		capacity = getSize();
	}
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(getColor());
		if(isSelected()) {
			g.drawArc((int)(pCmpRelPrnt.getX() + getLocation().getX() - getSize() / 2f), (int)(pCmpRelPrnt.getY() + getLocation().getY() - getSize() / 2f), getSize(), getSize(), 0, 360);
		}
		else {
			g.fillArc((int)(pCmpRelPrnt.getX() + getLocation().getX() - getSize() / 2f), (int)(pCmpRelPrnt.getY() + getLocation().getY() - getSize() / 2f), getSize(), getSize(), 0, 360);
		}
		g.setColor(ColorUtil.BLACK);
		g.drawString(Integer.toString(capacity), (int)(pCmpRelPrnt.getX() + getLocation().getX()), (int)(pCmpRelPrnt.getY() + getLocation().getY()));
	}
	
	
	public void handleCollision(ICollider other) {
		super.handleCollision(other);
		if(other instanceof Ant && capacity > 0) {
			getGameWorld().collideWithFoodStation((Ant)other, this);
		}
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
