package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
//another command
public class TickCommand extends Command{
	
	private GameWorld target;
	
	public TickCommand(GameWorld gameWorld) {
		super("Tick");
		target = gameWorld;
	}
	
	public void actionPerformed(ActionEvent actionEvent) {
		target.tick();
	}
	
}