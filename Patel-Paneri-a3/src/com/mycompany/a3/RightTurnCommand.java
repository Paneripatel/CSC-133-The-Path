package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

//another command
public class RightTurnCommand extends Command{

	private GameWorld target;
	
	public RightTurnCommand(GameWorld gameWorld) {
		super("Right");
		target = gameWorld;
	}
	
	public void actionPerformed(ActionEvent actionEvent) {
		target.rightTurn();
	}
	
}