package com.mycompany.a1;

import java.util.ArrayList;
import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class GameWorld {
	
	private ArrayList<GameObject> world;
	private Ant player;
	private int clock;
	private int lives;
	private int highestFlagNumber;
	private int highestFlagNumberReached;
	
	public GameWorld() {
		clock = 0;
		lives = 3;
		highestFlagNumberReached = 1;
		
	}
	public void init() {
		world = new ArrayList<GameObject>();//for adding 4 flags at different locations
		world.add(new Flag(new Point(200f, 200f), 1));
		world.add(new Flag(new Point(200f, 800f), 2));
		world.add(new Flag(new Point(700f, 800f), 3));
		world.add(new Flag(new Point(900f, 400f), 4));
		highestFlagNumber = 4;//max number of flag
		player = new Ant(world.get(0).getLocation(), 50);
		world.add(player);
		world.add(new Spider(new Point(new Random().nextFloat()*1000f, new Random().nextFloat()*1000f)));
		world.add(new Spider(new Point(new Random().nextFloat()*1000f, new Random().nextFloat()*1000f)));
		world.add(new FoodStation());
		world.add(new FoodStation());
	}
	//Accelerate the ant command.
	public void aCommand() {
		System.out.println("You accelerated the Ant.\n");
		player.accelerate(5);
	}
	//Brake the ant command
	public void bCommand() {
		System.out.println("You applied brake on the Ant.\n");
		player.brake(5);
	}
	//Moving leftside command for ant.
	public void lCommand() {
		System.out.println("You changed the direction of the Ant to left.\n");
		player.left();
		
	}
	//moving rightside command for ant.
	public void rCommand() {
		System.out.println("You changed the direction of the Ant to right.\n");
		player.right();
	}
	//ant colliding with food station command
	public void fCommand() {
		System.out.println("You collided with a food station.\n");
		int index;
		int capacity = 0;
		while(capacity == 0) {
			index = new Random().nextInt(world.size());
			if(world.get(index) instanceof FoodStation) {
				FoodStation station = (FoodStation)world.get(index);
				capacity = station.getCapacity();
				if(capacity != 0) {
					station.setCapacity(0);
				}
			}
		}
		player.setFoodLevel(player.getFoodLevel()+capacity);
		world.add(new FoodStation());
	}
	//ant colliding with spider
	public void gCommand() {
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
	}
	//ticks the game clock command
	public void tCommand() {
		System.out.println("You ticked the game clock.\n");
		for(int i = 0; i < world.size(); i++) {
			if(world.get(i) instanceof Movable) {
				Movable movable = (Movable)world.get(i);
				if(movable instanceof Ant) {
					Ant ant = (Ant)movable;
					ant.setHeading(ant.getHeading()+ ant.getDirection());
					ant.consumeFood();
					if(ant == player && player.isDead()) {
						System.out.println("You have no food, you lost a life.\n");
						lives--;
						if(lives <= 0) {
							System.out.println("Game over, you failed.\n");
							System.exit(0);
						}
						init();
					}
				}
				movable.move();
			}
		}
		clock++;
	}
	//display game state command
	public void dCommand() {
		System.out.println("Displaying the game state.\n");
		System.out.println("lives="+ lives + " ");
		System.out.println("clock="+ clock + " ");
		System.out.println("highestFlagNumberReached="+ highestFlagNumberReached + " ");
		System.out.println("foodLevel="+ player.getFoodLevel()+ " ");
		System.out.println("damageLevel="+ player.getDamageLevel()+ "\n");
		System.out.println();
	}
	//ant colliding with a flag command
	public void nCommand(int sequenceNumber) {
		System.out.println("You collided with the flag number " + sequenceNumber + ".\n");
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
	//display map command
	public void mCommand() {
		System.out.println("Displaying the map.\n");
		for(int i = 0; i < world.size(); i++) {
			if(world.get(i) instanceof Flag) {
				Flag flag = (Flag)world.get(i);
				System.out.print("Flag: ");
				System.out.print("loc=" + Math.round(flag.getLocation().getX()*10.0)/10.0 + "," + Math.round(flag.getLocation().getY()*10.0)/10.0+" ");
				int color = flag.getColor();
				System.out.print("color=[" + ColorUtil.red(color)+","+ ColorUtil.green(color)+ "," + ColorUtil.blue(color)+ "] ");
				System.out.print("size=" + flag.getSize() + " ");
				System.out.print("seqNum=" + flag.getSequenceNumber());
			
			}
			else if(world.get(i) instanceof Ant) {
				Ant ant = (Ant)world.get(i);
				System.out.print("Ant: ");
				System.out.print("loc=" + Math.round(ant.getLocation().getX()*10.0)/10.0 + "," + Math.round(ant.getLocation().getY()*10.0)/10.0+" ");
				int color = ant.getColor();
				System.out.print("color=[" + ColorUtil.red(color)+","+ ColorUtil.green(color)+ "," + ColorUtil.blue(color)+ "] ");
				System.out.print("heading="+ ant.getHeading()+ " ");
				System.out.print("speed="+ ant.getSpeed()+ " ");
				System.out.print("size=" + ant.getSize() + " ");
				System.out.print("maxSpeed="+ ant.getMaximumSpeed()+ " ");
				System.out.print("direction=" + ant.getDirection()+ " ");
				System.out.print("foodLevel=" + ant.getFoodLevel()+ " ");
				System.out.print("damageLevel="+ ant.getDamageLevel());
				
				
			}
			else if(world.get(i)instanceof Spider) {
				Spider spider = (Spider)world.get(i);
				System.out.print("Spider: ");
				System.out.print("loc=" + Math.round(spider.getLocation().getX()*10.0)/10.0 + "," + Math.round(spider.getLocation().getY()*10.0)/10.0+" ");
				int color = spider.getColor();
				System.out.print("color=[" + ColorUtil.red(color)+","+ ColorUtil.green(color)+ "," + ColorUtil.blue(color)+ "] ");
				System.out.print("heading="+ spider.getHeading()+ " ");
				System.out.print("speed="+ spider.getSpeed()+ " ");
				System.out.print("size=" + spider.getSize());
			}
			else if(world.get(i) instanceof FoodStation) {
				FoodStation station = (FoodStation)world.get(i);
				System.out.print("FoodStation: ");
				System.out.print("loc=" + Math.round(station.getLocation().getX()*10.0)/10.0 + "," + Math.round(station.getLocation().getY()*10.0)/10.0+" ");
				int color = station.getColor();
				System.out.print("color=[" + ColorUtil.red(color)+","+ ColorUtil.green(color)+ "," + ColorUtil.blue(color)+ "] ");
				System.out.print("size=" + station.getSize() + " ");
				System.out.print("capacity=" + station.getCapacity());
			}
			System.out.print("\n");
		}
		System.out.println();
	}
	
	
}
