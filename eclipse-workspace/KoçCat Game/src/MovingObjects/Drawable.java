package MovingObjects;

import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class Drawable extends JPanel {
	
	public Drawable() { //constructor
		super();
	}
	
	public abstract void draw();
	
	public abstract void doAction();
	
	
	
}
