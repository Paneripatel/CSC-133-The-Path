package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;
import com.codename1.ui.Container;


//View from the MVC
//extends container (is the center of the layout) and implements observer as it observes the GameWorld
public class MapView extends Container implements Observer{
	@Override
	public void update(Observable observable, Object data) {
		if(observable instanceof GameWorld) {
			GameWorld world = (GameWorld)observable;
			//calls the mCommand from GameWorld to display the map to the console
			world.mCommand();
		}
	}

}