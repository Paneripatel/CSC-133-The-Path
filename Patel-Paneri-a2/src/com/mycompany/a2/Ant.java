package com.mycompany.a2;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;


public class Ant extends Movable implements ISteerable{
	//As food consumption rate is same for all ant so I created static variable for it.
	private static int foodConsumptionRate = 1;
	private static final int SIZE = 40;
	
	private int maximumSpeed;
	private int foodLevel;
	private int lastFlagReached;
	private int healthLevel;
	private int maxDamage;
	private int direction;
	
	
	
	
	public Ant(int worldSizeX, int worldSizeY, Point location, int maximumSpeed) {
		super(worldSizeX, worldSizeY, SIZE, location, ColorUtil.rgb(255, 0, 0));
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
		float adjustedMaxSpeed = maximumSpeed * (maxDamage - healthLevel)/maxDamage;
		if(speed <= adjustedMaxSpeed) {
			super.setSpeed(speed);
			
		}else {
			super.setSpeed((int)adjustedMaxSpeed);
		}
	}
	//Method left for changing ant's direction towards left.
	public void left() {
		direction -= 5;
		if(direction > 40) {
			direction = 40;
		}else if(direction < -40) {
			direction = -40;
			
		}
	}
	//Method right for changing ant's direction towards right.
	public void right() {
		direction +=5;
		if(direction > 40) {
			direction = 40;
		}else if(direction < -40) {
			direction = -40;
		}
	}
	
	public void setHeading(int heading) {
		super.setHeading(heading);
	}
	//Method imposingDamage adds damage to method damageLevel and the color goes lighter as ant takes the damage.
	public void imposingDamage(int amount) {
		healthLevel += amount;
		if(healthLevel > maxDamage) {
			healthLevel = maxDamage;
		}
		setSpeed(getSpeed());
		int color = getColor();
		int red = ColorUtil.red(color)*(maxDamage - healthLevel)/maxDamage;
		int green = ColorUtil.green(color)*(maxDamage - healthLevel)/maxDamage;
		int blue = ColorUtil.blue(color)*(maxDamage - healthLevel)/maxDamage;
		setColor(red, green, blue);
		
	}
	//If ant is out of life or foodlevel then it will die.
	public boolean isDead() {
		if(healthLevel >= maxDamage || foodLevel <= 0) {
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
		return maximumSpeed * (maxDamage - healthLevel)/maxDamage;
	}
	
	public int getDirection() {
		return direction;
	}
	
	public int getHealthLevel() {
		return healthLevel;
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
	public void setHealthLevel(int level) {
		this.healthLevel = level;
	}
	public void setMaxHealthLevel(int level) {
		this.maxDamage = level;
	}
}
