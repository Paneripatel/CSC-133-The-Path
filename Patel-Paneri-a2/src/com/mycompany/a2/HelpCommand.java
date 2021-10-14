package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
//another command
public class HelpCommand extends Command{
		
	public HelpCommand() {
		super("Help");
	}
	
	public void actionPerformed(ActionEvent actionEvent) {
		new CommandForm();
	}
	
	private class CommandForm extends Form{
		
		public CommandForm() {
			Command cOkay = new Command("Okay");
			Command c = Dialog.show("Help", "[a]ccelerate\n[b]rake\n[l]eft turn\n[r]ight turn\ncollide with [f]ood station\ncollide with Spider [g]\n[t]ick", cOkay);
		}
		
	}
	
}