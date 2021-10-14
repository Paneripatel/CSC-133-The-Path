package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;

//The purpose of this command is to collide with the flag that user inputs using a dialog box
public class CollideWithFlagCommand extends Command{

	private GameWorld target;
	
	public CollideWithFlagCommand(GameWorld gameWorld) {
		super("Collide with Flag");
		target = gameWorld;
	}
	
	//create a new form
	public void actionPerformed(ActionEvent actionEvent) {
		new CommandForm();
	}
	
	//the form
	private class CommandForm extends Form{
	
		//create a dialog box and wait for user input
		public CommandForm() {
			Command cOkay = new Command("Okay");
			TextField textField = new TextField();
			Command c = Dialog.show("Enter a number between 1-9", textField, cOkay);
			String text = textField.getText();
			if(isInteger(text)) {
				int sequenceNumber = Integer.parseInt(text); 
				target.collideWithFlag(sequenceNumber);
			}
		}
		
		//check to see if user input was a valid number
		private boolean isInteger(String string) {
			if(string == null) {
				return false;
			}
			try {
				int i = Integer.parseInt(string);
			} catch (NumberFormatException e) {
				return false;
			}
			return true;
		}
	}
	
}