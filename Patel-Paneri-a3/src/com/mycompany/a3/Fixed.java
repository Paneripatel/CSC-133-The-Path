package com.mycompany.a3;

import com.codename1.charts.models.Point;

public abstract class Fixed extends GameObject implements ISelectable{
	
	public Fixed(int size, Point location, int color, GameWorld gameWorld) {
		super(size, location, color, gameWorld);
	}
	private boolean selected;
		
	public boolean isSelected() {
		return selected;
	}
		
	public void setSelected(boolean selected) {
			this.selected = selected;
	}
		
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		Point loc = getLocation();
		int size = getSize();
		float R1 = loc.getX() + pCmpRelPrnt.getX() + size / 2f;
		float L1 = loc.getX() + pCmpRelPrnt.getX() - size / 2f;
		float T1 = loc.getY() + pCmpRelPrnt.getY() + size / 2f;
		float B1 = loc.getY() + pCmpRelPrnt.getY() - size / 2f;
			
		if(pPtrRelPrnt.getX() > L1 && pPtrRelPrnt.getX() < R1 && pPtrRelPrnt.getY() < T1 && pPtrRelPrnt.getY() > B1) {
				return true;
		}
		return false;
	}
	
}
