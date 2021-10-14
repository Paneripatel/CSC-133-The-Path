package com.mycompany.a2;
//another command
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class LeftTurnCommand extends Command{

	private GameWorld target;
	
	public LeftTurnCommand(GameWorld gameWorld) {
		super("Left");
		target = gameWorld;
	}
	
	public void actionPerformed(ActionEvent actionEvent) {
		target.leftTurn();
	}
	
}