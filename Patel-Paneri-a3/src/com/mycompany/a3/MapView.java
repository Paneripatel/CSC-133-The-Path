package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.models.Point;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;


//View from the MVC
//extends container (is the center of the layout) and implements observer as it observes the GameWorld
public class MapView extends Container implements Observer{
	private GameWorld gameWorld;
	@Override
	public void update(Observable observable, Object data) {
		if(observable instanceof GameWorld) {
			gameWorld = (GameWorld)observable;
			//calls the mCommand from GameWorld to display the map to the console
			gameWorld.mCommand();
			repaint();
			
		}
	}
	public void paint(Graphics g) {
		super.paint(g);
		GameObjectCollection world = gameWorld.getWorld();
		IIterator i = world.getIterator();
		while(i.hasNext()) {
			Object obj = i.getNext();
			if(obj instanceof IDrawable) {
				IDrawable drawable = (IDrawable)obj;
				drawable.draw(g, new Point(getX(), getY()));
			}
		}
	}
	
	public void pointerPressed(int x, int y) {
		if(gameWorld.getPause()) {
			System.out.println("pointerPressed");
			x = x - getParent().getAbsoluteX();
			y = y - getParent().getAbsoluteX();
			if(gameWorld.waitingForReposition()) {
				gameWorld.reposition(x - getAbsoluteX(), y - getAbsoluteY());
			}
			else {
				ISelectable selected = null;
				IIterator iterator = gameWorld.getWorld().getIterator();
				while(iterator.hasNext()) {
					Object obj = iterator.getNext();
					if(obj instanceof ISelectable) {
						ISelectable sel = (ISelectable)obj;
						if(sel.contains(new Point(x, y), new Point(getAbsoluteX(), getAbsoluteY()))) {
							sel.setSelected(true);
							selected = sel;
							System.out.println(sel.toString());
							break;
						}
					}
				}
				iterator = gameWorld.getWorld().getIterator();
				while(iterator.hasNext()) {
					Object obj = iterator.getNext();
					if(obj instanceof ISelectable) {
						ISelectable sel = (ISelectable)obj;
						if(sel != selected && sel.isSelected()) {
							sel.setSelected(false);
						}
					}
				}
			}
			repaint();
		}
	}
}
	

