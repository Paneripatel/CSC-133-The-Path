package com.mycompany.a2;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
//another command 
public class SoundCommand extends Command{
	
	private GameWorld target;
	
	public SoundCommand(GameWorld gameWorld) {
		super("Sound");
		target = gameWorld;
	}
	
	public void actionPerformed(ActionEvent actionEvent) {
		if(((CheckBox)actionEvent.getComponent()).isSelected()) {
			target.setSound(true);
		}
		else {
			target.setSound(false);
		}
	}
	
}