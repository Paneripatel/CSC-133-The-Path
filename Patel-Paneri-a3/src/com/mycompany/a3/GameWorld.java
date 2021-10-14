package com.mycompany.a3;

import java.util.*;
import com.codename1.charts.models.*;
import com.codename1.charts.util.ColorUtil;
import java.io.InputStream;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Button;
import com.codename1.ui.Label;


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
	private Sound crash;
	private Sound food;
	private Sound explosion;
	private BGSound rain;
	
	
	public void init(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		init();
	}
	
	public void init() {
		world = new GameObjectCollection();
		world.add(new Flag(new Point(200f, 200f), 1, this));
		world.add(new Flag(new Point(200f, 800f), 2, this));
		world.add(new Flag(new Point(700f, 800f), 3, this));
		world.add(new Flag(new Point(900f, 400f), 4, this));
		highestFlagNumber = 4;//max number of flag
		player = PlayerAnt.getInstance();
		player.setGameWorld(this);
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
		player.setFoodLevel(1000);
		player.setHeading(0);
		player.setSpeed(50);
		player.setHealthLevel(0);
		player.setDirection(0);
		world.add(player);
		world.add(new Spider(new Point(new Random().nextFloat()*1000f, new Random().nextFloat()*1000f), this));
		world.add(new FoodStation(this));
		world.add(new FoodStation(this));
		iterator = world.getIterator();
		while(iterator.hasNext()) {
			Object object = iterator.getNext();
			
		}
		pause = false;
		setChanged();
		notifyObservers();
	}
	private boolean reposition;
		
	public boolean waitingForReposition() {
		return reposition;
	}
		
	public void reposition(int x, int y) {
		System.out.println("reposition");
		IIterator iterator = world.getIterator();
		while(iterator.hasNext()) {
			Object obj = iterator.getNext();
			if(obj instanceof ISelectable) {
				ISelectable sel = (ISelectable)obj;
				if(sel.isSelected() && sel instanceof Fixed) {
					((Fixed)sel).setLocation(new Point(x, y));
					break;
				}
			}
		}
		reposition = false;
	}
		
	public void positionCommand(ISelectable sel) {
		if(sel != null)
			reposition = true;
	}
	public void setupSound() {
		crash = new Sound("CRASH.wav");
		food = new Sound("ENERGY.wav");
		explosion = new Sound("EXPLOSION.wav");
		rain = new BGSound("rain01.wav");
		rain.run();
		if(!sound) {
			rain.pause();
		}
	}
	private boolean pause;
		
	public void pause() {
		if(pause) {
			if(sound) {
				rain.play();
			}
			IIterator iterator = world.getIterator();
			while(iterator.hasNext()) {
				Object obj = iterator.getNext();
				if(obj instanceof ISelectable) {
					ISelectable selectable = (ISelectable)obj;
					selectable.setSelected(false);
				}
			}
			reposition = false;
		}
		else {
			rain.pause();
		}
		pause = !pause;
	}
	
	public boolean getPause() {
		return pause;
	}
	
	public GameObjectCollection getWorld() {
		return world;
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
	public int getHighestFlagNumber() {
		return highestFlagNumber;
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
	public void collideWithFoodStation(Ant ant, FoodStation foodStation) {
		System.out.println("You collided with a food station.\n");
		if(sound) {
			food.play();
		}
		ant.setFoodLevel(ant.getFoodLevel()+ foodStation.getCapacity());
		foodStation.setCapacity(0);
		world.add(new FoodStation(this));
		setChanged();
		notifyObservers();
	}
	
	//ant colliding with spider
	public void collideWithSpider(Ant ant) {
		System.out.println("You collided with a spider.\n");
		if(ant instanceof PlayerAnt) {
			PlayerAnt player = (PlayerAnt)ant;
			if(player == this.player) {
				player.imposingDamage(1);
				if(player.isDead()) {
					System.out.println("You have no health! You lost a life!\n");
					loseALife();
				}
			}
		}
		else {
			ant.imposingDamage(1);
		}
		
		setChanged();
		notifyObservers();
	}
	
	//ticks the game clock command
	public void tick(int ms) {
		System.out.println("You ticked the game clock.\n");
		IIterator iterator = world.getIterator();
		while(iterator.hasNext()) {
			Object object = iterator.getNext();
			if(object instanceof Movable) {
				Movable movable = (Movable)object;
				if(movable instanceof Ant) {
					Ant ant = (Ant)movable;
					ant.setHeading(ant.getHeading() + ant.getDirection());
					ant.consumeFood();
					if(ant == player && player.isDead()) {
						System.out.println("You have no energy! You lost a life!\n");
						loseALife();
					}
				}
				movable.move(ms);
			}
		}
		iterator = world.getIterator();
		while(iterator.hasNext()) {
			Object obj = iterator.getNext();
			if(obj instanceof ICollider) {
				ICollider col = (ICollider)obj;
				IIterator iterator2 = world.getIterator();
				while(iterator2.hasNext()) {
					Object obj2 = iterator2.getNext();
					if(obj instanceof ICollider) {
						ICollider other = (ICollider)obj2;
						if(col != other) {
							if(col.collidesWith(other)) {
								col.handleCollision(other);
							}
						}
					}
				}
			}
		}
		
		clock++;
		setChanged();
		notifyObservers();
	}
	
	
	public void setSound(boolean sound) {
		System.out.println("You pressed the sound check box.\n");
		this.sound = sound;
		setChanged();
		notifyObservers();
		if(sound && !pause) {
			rain.play();
		}
		else if(!sound || pause) {
			rain.pause();
		}
	}
	
	public void loseALife() {
		lives--;
		if(lives <= 0) {
			System.out.println("Game over, you failed!\n");
			System.exit(0);
		}
		init();
		if(sound) {
			explosion.play();
		}
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
