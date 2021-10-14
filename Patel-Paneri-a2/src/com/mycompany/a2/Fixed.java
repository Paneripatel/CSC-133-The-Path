package com.mycompany.a2;

import com.codename1.charts.models.Point;

public abstract class Fixed extends GameObject{
	
	public Fixed(int worldSizeX, int worldSizeY, int size, Point location, int color) {
		super(worldSizeX, worldSizeY, size, location, color);
	}
	//Fixed objects cannot change location so nothing will happen if setLocation is called.
	public void setLocation(Point location) {
		
	}
	
}
