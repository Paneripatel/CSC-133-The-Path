package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
//Singleton design pattern
//extends from ant 
public class PlayerAnt extends Ant {

	//the static instance
	private static PlayerAnt instance;
	
	//private so no one outside this class can make another
	private PlayerAnt(Point location, int maximumSpeed, GameWorld gameWorld) {
		super(location, maximumSpeed, gameWorld);
	}
	
	//can create object if it does not exist yet
	public static PlayerAnt getInstance() {
		if(instance == null)
			instance = new PlayerAnt(new Point(), 250, null);
		return instance;
	}
	
	public void setGameWorld(GameWorld gameWorld) {
		super.setGameWorld(gameWorld);
	}

	public String toString() {
		int color = getColor();
		return ("PlayerAnt: loc=" + Math.round(this.getLocation().getX() * 10.0) / 10.0 + "," + Math.round(this.getLocation().getY() * 10.0) / 10.0 + " color=[" + ColorUtil.red(color) + "," + ColorUtil.green(color) + "," + ColorUtil.blue(color) + "] heading=" + this.getHeading() + " speed=" + getSpeed() + " size=" + this.getSize() + " maxSpeed=" + getMaximumSpeed() + " Direction=" + getDirection() + " Foodlevel=" + getFoodLevel() + " damageLevel=" + getMaxDamage());
	}
	
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(getColor());
		g.fillRect((int)(pCmpRelPrnt.getX() + getLocation().getX() - getSize() / 2), (int)(pCmpRelPrnt.getY() + getLocation().getY() - getSize() / 2), getSize(), getSize());
	}
	
}