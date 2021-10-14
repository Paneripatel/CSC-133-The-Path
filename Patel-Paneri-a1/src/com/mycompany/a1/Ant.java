package com.mycompany.a1;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;


public class Ant extends Movable implements ISteerable{
	//As food consumption rate is same for all ant so I created static variable for it.
	private static int foodConsumptionRate = 1;
	
	private int maximumSpeed;
	private int foodLevel;
	private int healthLevel;
	private int lastFlagReached;
	private int damageLevel;
	private int maxDamage;
	private int direction;
	
	
	public Ant(Point location, int maximumSpeed) {
		super(40, location, ColorUtil.rgb(255, 0, 0));
		setHeading(0);
		foodLevel = 100;
		lastFlagReached = 1;
		maxDamage = 5;
		this.maximumSpeed = maximumSpeed;
		setSpeed(10);
	}
	//Accelerate method for ant
	public void accelerate(int amount) {
		//Calling setSpeed to ensure the speed stays within limitations.
		setSpeed(getSpeed()+amount);
	}
	//Brake method for ant.
	public void brake(int amount) {
		if(getSpeed()-amount<0) {
			//Calling setSpeed to ensure the speed stays within limitations.
			setSpeed(0);
		}else {
			setSpeed(getSpeed()-amount);
		}
	}
	//setSpeed method to set the speed for ant 
	protected void setSpeed(int speed) {
		float adjustedMaxSpeed = maximumSpeed * (maxDamage - damageLevel)/maxDamage;
		if(speed <= adjustedMaxSpeed) {
			super.setSpeed(speed);
			
		}else {
			super.setSpeed((int)adjustedMaxSpeed);
		}
	}
	//Method left for changing ant's direction towards left.
	public void left() {
		direction -= 5;
		if(direction > 50) {
			direction = 50;
		}else if(direction < -50) {
			direction = -50;
			
		}
	}
	//Method right for changing ant's direction towards right.
	public void right() {
		direction +=5;
		if(direction > 50) {
			direction = 50;
		}else if(direction < -50) {
			direction = -50;
		}
	}
	
	public void setHeading(int heading) {
		super.setHeading(heading);
	}
	//Method imposingDamage adds damage to method damageLevel and the color goes lighter as ant takes the damage.
	public void imposingDamage(int amount) {
		damageLevel += amount;
		if(damageLevel > maxDamage) {
			damageLevel = maxDamage;
		}
		setSpeed(getSpeed());
		int color = getColor();
		int red = ColorUtil.red(color)*(maxDamage - damageLevel)/maxDamage;
		int green = ColorUtil.green(color)*(maxDamage - damageLevel)/maxDamage;
		int blue = ColorUtil.blue(color)*(maxDamage - damageLevel)/maxDamage;
		setColor(red, green, blue);
		
	}
	//If ant is out of life or foodlevel then it will die.
	public boolean isDead() {
		if(damageLevel >= maxDamage || foodLevel <= 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void consumeFood() {
		foodLevel -= foodConsumptionRate;
		if(foodLevel <= 0) {
			foodLevel = 0;
		}
	}
	//Getters and Setters
	public int getMaximumSpeed() {
		return maximumSpeed * (maxDamage - damageLevel)/maxDamage;
	}
	
	public int getDirection() {
		return direction;
	}
	
	public int getDamageLevel() {
		return damageLevel;
	}
	
	public int getMaxDamage() {
		return maxDamage;
		
	}
	
	public int getLastFlagReached() {
		return lastFlagReached;
	}
	
	public void setLastFlagReached(int sequenceNumber) {
		lastFlagReached = sequenceNumber;
	}
	
	public int getFoodLevel() {
		return foodLevel;
	}
	
	public void setFoodLevel(int amount) {
		foodLevel = amount;
	}
}
