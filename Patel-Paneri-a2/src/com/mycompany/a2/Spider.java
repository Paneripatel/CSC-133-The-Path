package com.mycompany.a2;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class Spider extends Movable{
	//Spider moves in random location.
	
	private static final int MAX_SIZE = 40;
	private static final int MIN_SIZE = 10;
	
	public Spider(int worldSizeX, int worldSizeY, Point location) {
		super(worldSizeX, worldSizeY, MIN_SIZE + new Random().nextInt(MAX_SIZE - MIN_SIZE + 1), location, ColorUtil.rgb(255, 0, 255));
		setHeading(new Random().nextInt(360));
		setSpeed(new Random().nextInt(6)+5);
		
	}

	public void move() {
		setHeading(getHeading()+(new Random().nextInt(11)-5));
		super.move();
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
