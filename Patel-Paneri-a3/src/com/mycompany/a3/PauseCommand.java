package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class PauseCommand extends Command{
	
	private Game game;
	private GameWorld target;
	
	public PauseCommand(Game game, GameWorld gameWorld) {
		super("Pause");
		this.game = game;
		target = gameWorld;
	}
	
	public void actionPerformed(ActionEvent actionEvent) {
		game.pause();
		target.pause();
	}
}