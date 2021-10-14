package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

//Same as all the other commands
public class CollideWithFoodStationCommand extends Command{
	
	private GameWorld target;
	
	public CollideWithFoodStationCommand(GameWorld gameWorld) {
		super("Collide with Food Station");
		target = gameWorld;
	}
	
	public void actionPerformed(ActionEvent actionEvent) {
		
	}
	
}