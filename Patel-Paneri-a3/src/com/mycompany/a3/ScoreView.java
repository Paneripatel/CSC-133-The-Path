package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;
import com.codename1.ui.Container;
import com.codename1.ui.Label;


//Viewfrom the MVC architecture
//sets up labels and updates them when the observable notifies this observer to do so
public class ScoreView extends Container implements Observer{

	private Label timeText;
	private Label timeValue;
	private Label livesText;
	private Label livesValue;
	private Label FlagText;
	private Label FlagValue;
	private Label FoodText;
	private Label FoodValue;
	private Label healthText;
	private Label healthValue;
	private Label soundText;
	private Label soundValue;
	
	public ScoreView() {
		//labels go here
		
		timeText = new Label("Time: ");
		timeValue = new Label();
		livesText = new Label("Lives Left: ");
		livesValue = new Label();
		FlagText = new Label("Last Flag Reached: ");
		FlagValue = new Label();
		FoodText = new Label("Food Level: ");
		FoodValue = new Label();
		healthText = new Label("Health Level: ");
		healthValue = new Label();
		soundText = new Label("Sound: ");
		soundValue = new Label();
		
		this.addComponent(timeText);
		this.addComponent(timeValue);
		this.addComponent(livesText);
		this.addComponent(livesValue);
		this.addComponent(FlagText);
		this.addComponent(FlagValue);
		this.addComponent(FoodText);
		this.addComponent(FoodValue);
		this.addComponent(healthText);
		this.addComponent(healthValue);
		this.addComponent(soundText);
		this.addComponent(soundValue);
		
	}
	
	public void update(Observable observable, Object data) {
		if(observable instanceof GameWorld) {
			GameWorld world = (GameWorld)observable;
			timeValue.setText(Integer.toString(world.getClock()));
			livesValue.setText(Integer.toString(world.getLives()));
			FlagValue.setText(Integer.toString(PlayerAnt.getInstance().getLastFlagReached()));
			FoodValue.setText(Integer.toString(PlayerAnt.getInstance().getFoodLevel()));
			healthValue.setText(Integer.toString(PlayerAnt.getInstance().getHealthLevel()));
			if(world.getSound()) {
				soundValue.setText("ON");
			} else {
				soundValue.setText("OFF");
			}
			revalidate();
		}
	}

}