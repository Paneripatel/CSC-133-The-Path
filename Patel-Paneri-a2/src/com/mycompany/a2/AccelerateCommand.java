package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

//The purpose of the this Command is to execute the code within the actionPerformed method when an action is performed
public class AccelerateCommand extends Command{
	
	private GameWorld target;
	
	public AccelerateCommand(GameWorld gameWorld) {
		super("Accelerate");
		target = gameWorld;
	}
	
	public void actionPerformed(ActionEvent actionEvent) {
		target.accelerate();
	}
	
}