package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

//Same as all the other commands
public class CollideWithSpiderCommand extends Command{
	
	private GameWorld target;
	
	public CollideWithSpiderCommand(GameWorld gameWorld) {
		super("Collide with Spider");
		target = gameWorld;
	}
	
	public void actionPerformed(ActionEvent actionEvent) {
		target.collideWithSpider();
	}
	
}