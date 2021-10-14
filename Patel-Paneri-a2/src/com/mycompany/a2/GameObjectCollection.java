package com.mycompany.a2;

import java.util.Vector;

//The game Object Collection is part of the Iterator design pattern 
public class GameObjectCollection implements ICollection {

	//Used Vector for this
	private Vector<Object> collection;
	
	public GameObjectCollection() {
		collection = new Vector<Object>();
	}
	
	public void add(Object object) {	
		collection.addElement(object);
	}

	//allows the outside world to get some data safely
	public IIterator getIterator() {
		return new GameObjectIterator();
	}
	
	//this private class hides the internal structure of the data from the outside world
	private class GameObjectIterator implements IIterator{
		
		private int index;
		
		public GameObjectIterator() {
			index = -1;
		}
		
		public boolean hasNext() {
			if(collection.size() <= 0)
				return false;
			if(index >= collection.size() - 1)
				return false;
			return true;
		}
		
		public Object getNext() {
			index++;
			return (collection.elementAt(index));
		}
		
	}
	
}