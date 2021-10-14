package com.mycompany.a2;

import java.util.Iterator;
import java.util.Observable;
import java.util.Random;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class GameWorld extends Observable{
	
	private PlayerAnt player;
	private int clock;
	private int lives;
	private int highestFlagNumber;
	private int highestFlagNumberReached;
	private GameObjectCollection world;
	private boolean sound;
	private int sizeX;
	private int sizeY;
	
	public GameWorld() {
		clock = 0;
		lives = 3;
		highestFlagNumberReached = 1;
		
	}
	public void init() {
		world = new GameObjectCollection();
		world.add(new Flag(sizeX, sizeY, new Point(200f, 200f), 1));
		world.add(new Flag(sizeX, sizeY, new Point(200f, 800f), 2));
		world.add(new Flag(sizeX, sizeY, new Point(700f, 800f), 3));
		world.add(new Flag(sizeX, sizeY, new Point(900f, 400f), 4));
		highestFlagNumber = 4;//max number of flag
		player = PlayerAnt.getInstance();
		player.setWorldSize(sizeX, sizeY);
		IIterator iterator = world.getIterator();
		while(iterator.hasNext()) {
			Object object = iterator.getNext();
			if(object instanceof Flag) {
				Flag flag = (Flag)object;
				if(flag.getSequenceNumber()==1) {
					player.setLocation(flag.getLocation());
					break;
				}
			}
		}
		player.setFoodLevel(100);
		player.setHeading(0);
		player.setSpeed(10);
		player.setHealthLevel(0);
		world.add(player);
		world.add(new Spider(sizeX, sizeY, new Point(new Random().nextFloat()*1000f, new Random().nextFloat()*1000f)));
		world.add(new FoodStation(sizeX, sizeY));
		world.add(new FoodStation(sizeX, sizeY));
		iterator = world.getIterator();
		while(iterator.hasNext()) {
			Object object = iterator.getNext();
			
		}
		setChanged();
		notifyObservers();
	}
	
	
	public void init(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		init();
	}
	
	public Point getNextFlagLocation(int lastFlagReachedSequenceNumber) {
		int nextFlagSequenceNumber = lastFlagReachedSequenceNumber +1;
		if(nextFlagSequenceNumber>highestFlagNumber) {
			nextFlagSequenceNumber = highestFlagNumber;
		}
		IIterator iterator = world.getIterator();
		while(iterator.hasNext()) {
			Object object = iterator.getNext();
			if(object instanceof Flag) {
				Flag flag = (Flag)object;
				if(flag.getSequenceNumber()==nextFlagSequenceNumber) {
					return flag.getLocation();
				}
			}
		}
		return new Point();
	}
	public int getClock() {
		return clock;
	}
	public int getLives() {
		return lives;
	}
	public boolean getSound() {
		return sound;
	}
	public int getSizeX() {
		return sizeX;
	}
	public int getSizeY() {
		return sizeY;
	}
	
	//Accelerate the ant command.
	public void accelerate() {
		System.out.println("You accelerated the Ant.\n");
		player.accelerate(5);
		setChanged();
		notifyObservers();
	}
	//Brake the ant command
	public void brake() {
		System.out.println("You applied brake on the Ant.\n");
		player.brake(5);
		setChanged();
		notifyObservers();
	}
	//Moving leftside command for ant.
	public void leftTurn() {
		System.out.println("You changed the direction of the Ant to left.\n");
		player.left();
		setChanged();
		notifyObservers();
		
	}
	//moving rightside command for ant.
	public void rightTurn() {
		System.out.println("You changed the direction of the Ant to right.\n");
		player.right();
		setChanged();
		notifyObservers();
	}
	
	
	//ant colliding with food station command
	public void collideWithFoodStation() {
		System.out.println("You collided with a food station.\n");
		int index;
		int capacity = 0;
		IIterator iterator = world.getIterator();
		while(iterator.hasNext()) {
			Object object = iterator.getNext();
			if(object instanceof FoodStation && ((FoodStation)object).getCapacity()>0) {
				FoodStation station = (FoodStation)object;
				capacity = station.getCapacity();
				station.setCapacity(0);
				player.setFoodLevel(player.getFoodLevel()+capacity);
				world.add(new FoodStation(sizeX, sizeY));
				break;
			}
			else {
				System.out.println("No food stations have more than zero capacity remaining.\n");
			}
		}
		setChanged();
		notifyObservers();
		
	}
	//ant colliding with spider
	public void collideWithSpider() {
		System.out.println("You collided with a spider.\n");
		player.imposingDamage(2);
		if(player.isDead()) {
			System.out.println("You have no health so you lost a life.\n");
			lives--;
			if(lives <= 0) {
				System.out.println("Game over, you failed.\n");
				System.exit(0);
			}
			init();
		}
		setChanged();
		notifyObservers();
	}
	//ticks the game clock command
	public void tick() {
		System.out.println("You ticked the game clock.\n");
		IIterator iterator = world.getIterator();
		while(iterator.hasNext()) {
			Object object = iterator.getNext();
			if(object instanceof Movable) {
				Movable movable = (Movable)object;
				if(movable instanceof Ant) {
					Ant ant = (Ant)movable;
					ant.setHeading(ant.getHeading()+ant.getDirection());
					ant.consumeFood();
					if(ant==player&&player.isDead()) {
						System.out.println("You have no food left so you lost a life.\n");
						lives--;
						if(lives<=0) {
							System.out.println("Game over, you failed.\n");
							System.exit(0);
						}
						init();
					}
				}
				movable.move();
			}
			clock++;
			setChanged();
			notifyObservers();
		}
	}
	

	
	
	public void setSound(boolean sound) {
		System.out.println("You pressed the sound check box.\n");
		this.sound = sound;
		setChanged();
		notifyObservers();
	}
	
	//display game state command
	public void dCommand() {
		System.out.println("Displaying the game state.\n");
		System.out.println("lives="+ lives + " ");
		System.out.println("clock="+ clock + " ");
		System.out.println("highestFlagNumberReached="+ highestFlagNumberReached + " ");
		System.out.println("foodLevel="+ player.getFoodLevel()+ " ");
		System.out.println("healthLevel="+ player.getHealthLevel()+ "\n");
		System.out.println();
	}
	//ant colliding with a flag command
	public void collideWithFlag(int sequenceNumber) {
		System.out.println("You attempted to collided with the flag number " + sequenceNumber + ".\n");
		IIterator iterator = world.getIterator();
		while(iterator.hasNext()) {
			Object object = iterator.getNext();
			if(object instanceof Flag) {
				Flag flag = (Flag)object;
				if(flag.getSequenceNumber()==sequenceNumber) {
					System.out.println("You collided with flag number "+sequenceNumber+".\n");
					if(sequenceNumber -1 == player.getLastFlagReached()) {
						player.setLastFlagReached(sequenceNumber);
					}
					if(player.getLastFlagReached()> highestFlagNumberReached) {
						highestFlagNumberReached = player.getLastFlagReached();
					}
					if(highestFlagNumberReached >= highestFlagNumber) {
						System.out.println("Game over, you win.");
						System.exit(0);
					}
				}
			}
		}
		setChanged();
		notifyObservers();
	}
		
	//display map command
	public void mCommand() {
		System.out.println("Displaying the map.\n");
		IIterator iterator = world.getIterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.getNext().toString());
		}
		System.out.println();
				
	}
	
	
}
