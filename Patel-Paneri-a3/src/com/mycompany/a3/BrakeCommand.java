package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

//The purpose of this command is to tell the gameWorld to brake the player ant
public class BrakeCommand extends Command{
	private GameWorld target;
	
	public BrakeCommand(GameWorld gameWorld) {
		super("Brake");
		target = gameWorld;
	}
	
	//call the gameworld brake function
	public void actionPerformed(ActionEvent actionEvent) {
		target.brake();
	}
}