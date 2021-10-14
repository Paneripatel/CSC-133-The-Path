package com.mycompany.a1;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class Spider extends Movable{
	//Spider moves in random location.
	
	public Spider(Point location) {
		super(10+new Random().nextInt(40-10+1), location, ColorUtil.rgb(255, 0, 255));
		setHeading(new Random().nextInt(360));
		setSpeed(new Random().nextInt(6)+5);
		
	}

	public void move() {
		setHeading(getHeading()+(new Random().nextInt(11)-5));
		super.move();
		if(getLocation().getX()==1000f || getLocation().getX()==0f || getLocation().getY()==1000f||getLocation().getY()==0f) {
			setHeading(getHeading()+180);
		}
	}
	//Spider are not allowed to change color once created.
	public void setColor(int red, int green, int blue) {
		
	}
}
