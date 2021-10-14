package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
//same as all the other commands
public class ExitCommand extends Command{
		
	public ExitCommand() {
		super("Exit");
	}
	
	public void actionPerformed(ActionEvent actionEvent) {
		new CommandForm();
	}
	
	private class CommandForm extends Form{
		
		public CommandForm() {
			Command cOkay = new Command("Yes");
			Command cCancel = new Command("No");
			Command[] cmds = new Command[] {cOkay, cCancel};
			Command c = Dialog.show("Would you like to exit?", "Please select Yes or No", cmds);
			if(c == cOkay) {
				System.out.println("You exit the game!");
				System.exit(0);
			}
		}
		
	}
	
}