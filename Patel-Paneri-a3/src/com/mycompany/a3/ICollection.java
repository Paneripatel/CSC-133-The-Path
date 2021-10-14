package com.mycompany.a3;



//an interface to help with the iterator design pattern
public interface ICollection {
	
	public void add(Object object);
	
	public IIterator getIterator();
	
}