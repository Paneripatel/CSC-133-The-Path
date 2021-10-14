package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Spider extends Movable{
	//Spider moves in random location.
	
	private static final int MAX_SIZE = 100;
	private static final int MIN_SIZE = 40;
	
	public Spider(Point location, GameWorld gameWorld) {
		super(MIN_SIZE + new Random().nextInt(MAX_SIZE - MIN_SIZE + 1), location, ColorUtil.rgb(255, 0, 255), gameWorld);
		setHeading(new Random().nextInt(360));
		setSpeed(new Random().nextInt(101)+50);
		
	}
	public void draw(Graphics g, Point pCmpRelPrnt) {
		Point center = new Point(pCmpRelPrnt.getX() + getLocation().getX(), pCmpRelPrnt.getY() + getLocation().getY());
		int[] xPoints = new int[] {(int)center.getX(), (int)(center.getX() - getSize() / 2f), (int)(center.getX() + getSize() / 2f)};
		int[] yPoints = new int[] {(int)(center.getY() + getSize() / 2f), (int)(center.getY() - getSize() / 2f), (int)(center.getY() - getSize() / 2f)};
		g.setColor(getColor());
		g.fillPolygon(xPoints, yPoints, 3);
	}
	
	
	public void handleCollision(ICollider other) {
		super.handleCollision(other);
		if(other instanceof Ant) {
			Ant ant = (Ant)other;
			getGameWorld().collideWithSpider(ant);
		}
	}
	
	

	public void move(int ms) {
		setHeading(getHeading()+(new Random().nextInt(11)-5));
		super.move(ms);
		if(getLocation().getX()==getWorldSizeX() || getLocation().getX()==0f || getLocation().getY()==getWorldSizeY()||getLocation().getY()==0f) {
			setHeading(getHeading()+180);
		}
	}
	//Spider are not allowed to change color once created.
	public void setColor(int red, int green, int blue) {
		
	}
	public String toString() {
		int color = getColor();
		return("Spider: loc="+Math.round(this.getLocation().getX() * 10.0) / 10.0 + "," + Math.round(this.getLocation().getY() * 10.0) / 10.0 + " color=[" + ColorUtil.red(color) + "," + ColorUtil.green(color) + "," + ColorUtil.blue(color) + "] heading=" + this.getHeading() + " speed=" + getSpeed() + " size=" + this.getSize());
	}
}
