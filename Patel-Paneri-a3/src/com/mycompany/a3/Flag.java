package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Flag extends Fixed{
	private int sequenceNumber;
	private static final int SIZE = 150;
	
	public Flag(Point location, int sequenceNumber, GameWorld gameWorld) {
		super(SIZE, location, ColorUtil.rgb(150, 150, 255), gameWorld);
		this.sequenceNumber = sequenceNumber;
	}
	public void draw(Graphics g, Point pCmpRelPrnt) {
		Point center = new Point(pCmpRelPrnt.getX() + getLocation().getX(), pCmpRelPrnt.getY() + getLocation().getY());
		int[] xPoints = new int[] {(int)center.getX(), (int)(center.getX() - getSize() / 2f), (int)(center.getX() + getSize() / 2f)};
		int[] yPoints = new int[] {(int)(center.getY() + getSize() / 2f), (int)(center.getY() - getSize() / 2f), (int)(center.getY() - getSize() / 2f)};
		g.setColor(getColor());
		if(isSelected()) {
			g.drawPolygon(xPoints, yPoints, 3);
		}
		else{
			g.fillPolygon(xPoints, yPoints, 3);
		}
		g.setColor(ColorUtil.BLACK);
		g.drawString(Integer.toString(sequenceNumber), (int)(pCmpRelPrnt.getX() + getLocation().getX()), (int)(pCmpRelPrnt.getY() + getLocation().getY()));
	}
	public void handleCollision(ICollider other) {
		super.handleCollision(other);
		if(other instanceof Ant) {
			Ant ant = (Ant)other;
			if(ant.getLastFlagReached() == sequenceNumber - 1) {
				if(ant instanceof PlayerAnt) {
					getGameWorld().collideWithFlag(sequenceNumber);
				}
				else {
					ant.setLastFlagReached(ant.getLastFlagReached() + 1);
					if(ant.getLastFlagReached() >= getGameWorld().getHighestFlagNumber()) {
						System.out.println("A Non-Player Cybord reached the final base! Game over! You lose!");
						System.exit(0);
					}
					
				}
			}
		}
	}
	
	
	
	//Flags are not allowed to change color once they are created. 
	public void setColor(int red, int green, int blue) {
		
	}
	public int getSequenceNumber() {
		return sequenceNumber;
	}
	public String toString() {
		int color = getColor();
		return("Flag: loc=" + Math.round(this.getLocation().getX()*10.0)/10.0 + ","+Math.round(this.getLocation().getY()*10.0)/10.0+" color=["+ColorUtil.red(color)+","+ColorUtil.green(color)+","+ColorUtil.blue(color)+"] size="+this.getSize()+" seqNum=" + this.getSequenceNumber());	
	}
}