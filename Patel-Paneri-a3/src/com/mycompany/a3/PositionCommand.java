package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class PositionCommand extends Command{

	private GameWorld gameWorld;
	
	public PositionCommand(GameWorld gameWorld) {
		super("Position");
		this.gameWorld = gameWorld;
	}
	
	public void actionPerformed(ActionEvent actionEvent) {
		IIterator iterator = gameWorld.getWorld().getIterator();
		while(iterator.hasNext()) {
			Object obj = iterator.getNext();
			if(obj instanceof ISelectable) {
				ISelectable sel = (ISelectable)obj;
				if(sel.isSelected()) {
					gameWorld.positionCommand(sel);
					break;
				}
			}
		}
	} 
}