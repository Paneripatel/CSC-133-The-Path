package com.mycompany.a1;

import com.codename1.charts.models.Point;

public abstract class Fixed extends GameObject{
	
	public Fixed(int size, Point location, int color) {
		super(size, location, color);
	}
	//Fixed objects cannot change location so nothing will happen if setLocation is called.
	public void setLocation(Point location) {
		
	}
	
}
