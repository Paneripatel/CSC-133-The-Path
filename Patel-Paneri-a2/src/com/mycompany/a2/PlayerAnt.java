package com.mycompany.a2;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
//Singleton design pattern
//extends from ant 
public class PlayerAnt extends Ant {

	//the static instance
	private static PlayerAnt instance;
	
	//private so no one outside this class can make another
	private PlayerAnt(int worldSizeX, int worldSizeY, Point location, int maximumSpeed) {
		super(worldSizeX, worldSizeY, location, maximumSpeed);
	}
	
	//can create object if it does not exist yet
	public static PlayerAnt getInstance() {
		if(instance == null)
			instance = new PlayerAnt(0, 0, new Point(), 50);
		return instance;
	}
	
	public void setWorldSize(int x, int y) {
		setWorldSizeX(x);
		setWorldSizeY(y);
	}

	public String toString() {
		int color = getColor();
		return ("PlayerAnt: loc=" + Math.round(this.getLocation().getX() * 10.0) / 10.0 + "," + Math.round(this.getLocation().getY() * 10.0) / 10.0 + " color=[" + ColorUtil.red(color) + "," + ColorUtil.green(color) + "," + ColorUtil.blue(color) + "] heading=" + this.getHeading() + " speed=" + getSpeed() + " size=" + this.getSize() + " maxSpeed=" + getMaximumSpeed() + " Direction=" + getDirection() + " Foodlevel=" + getFoodLevel() + " healthLevel=" + getHealthLevel());
	}

	
		
	
	
}