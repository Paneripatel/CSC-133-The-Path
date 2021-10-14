package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;

//The About Command extends Command
//The purpose of the this Command is to execute the code within the actionPerformed method when an action is performed
public class AboutCommand extends Command{

	public AboutCommand() {
		super("About");
	}
	
	public void actionPerformed(ActionEvent actionEvent) {
		new CommandForm();
	}
	
	//The about command creates a form when action performed is executed to display information
	private class CommandForm extends Form{
		
		public CommandForm() {
			Command cOkay = new Command("Okay");
			Dialog.show("About", "Name: Paneri Patel \nClass: CSC 133 \nAssignment 2", cOkay);
		}
	}
	
}