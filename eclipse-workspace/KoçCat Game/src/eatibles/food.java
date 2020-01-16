package eatibles;

import javax.swing.JPanel;

import MovingObjects.Drawable;

public abstract class food extends Drawable {

	public food() {
		super();
		
	}
	//abstract methods
	public abstract void consumed();
	public abstract void grow();
	
}
