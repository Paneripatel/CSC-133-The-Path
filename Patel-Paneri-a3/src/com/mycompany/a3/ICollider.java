package com.mycompany.a3;

import java.util.ArrayList;

public interface ICollider {
	ArrayList<ICollider> getCollisions();
	boolean collidesWith(ICollider other);
	void handleCollision(ICollider other);
}