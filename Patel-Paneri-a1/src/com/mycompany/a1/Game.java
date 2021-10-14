package com.mycompany.a1;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

public class Game extends Form{
	private GameWorld gameWorld;
	private boolean requestExit = false;
	//Game method will call play method to play the game.
	public Game() {
		gameWorld = new GameWorld();
		gameWorld.init();
		play();
	}
	//play method interacts with user to play the game further with different commands
	private void play() {
		
		Label myLabel = new Label("Enter a command: ");
		this.addComponent(myLabel);
		final TextField myTextField = new TextField();
		this.addComponent(myTextField);
		this.show();
		
		myTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String sCommand=myTextField.getText().toString();
				myTextField.clear();
				if(sCommand.length()!=0) {
					//if player want to exit game ask for y/n to perform action accordingly.
					if(requestExit) {
						switch(sCommand.charAt(0)) {
						case 'y':
							System.out.println("You have exited the game!");
							System.exit(0);
							break;
						case 'n':
							System.out.println("Yoh have returned to the game!\n");
							requestExit = false;
							break;
						default:
							System.out.println("Select [y] to exit the game OR Select [n] to return to the game.\n");
							break;
							
						}
					}//or else choose any of the below command to play further.
					else {
						switch (sCommand.charAt(0)) {
						case'a':
							gameWorld.aCommand();
							break;
						case'b':
							gameWorld.bCommand();
							break;
						case'l':
							gameWorld.lCommand();
							break;
						case'r':
							gameWorld.rCommand();
							break;
						case'1':
						case'2':
						case'3':
						case'4':
						case'5':
						case'6':
						case'7':
						case'8':
						case'9':
							gameWorld.nCommand(Integer.parseInt(String.valueOf(sCommand.charAt(0))));
							break;
						case'f':
							gameWorld.fCommand();
							break;
						case'g':
							gameWorld.gCommand();
							break;
						case't':
							gameWorld.tCommand();
							break;
						case'd':
							gameWorld.dCommand();
							break;
						case'm':
							gameWorld.mCommand();
							break;
						case'x':
							System.out.println("Select [y] tp exit the game OR Select [n] to return to the game.\n");
							requestExit = true;
							break;
					    default:
					    	break;
							
							
						}
					}
				}
			}
		});
	}
}