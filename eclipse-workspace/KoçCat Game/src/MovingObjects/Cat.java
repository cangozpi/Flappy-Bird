package MovingObjects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.sound.midi.MidiMessage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import Main.Main_JFrame;
import MovingObjects.Cat.Face;

public class Cat extends Drawable implements KeyListener {
	// instance variables
	ImageIcon rightfin; // last resized images of cat
	ImageIcon leftfin;
	ImageIcon upfin;
	ImageIcon downfin;
	public static JLabel imageCat; // initial cat
	// will display image

	public enum Face { // checks direction of koçCat
		North, South, East, West
	}

	public static Face direction = Face.East;

	public Cat() throws Exception {
		super();
		this.setFocusable(true);
		addKeyListener(this);
		// initializes direction icons to correct size 80x80
		ImageIcon downCat = new ImageIcon(getClass().getResource("Down.png"));
		Image down = downCat.getImage().getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH); // scales the image
		downfin = new ImageIcon(down);
		ImageIcon upCat = new ImageIcon(getClass().getResource("Up.png"));
		Image up = upCat.getImage().getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH); // scales the image
		upfin = new ImageIcon(up);
		ImageIcon leftCat = new ImageIcon(getClass().getResource("Left.png"));
		Image left = leftCat.getImage().getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH); // scales the image
		leftfin = new ImageIcon(left);
		ImageIcon rightCat = new ImageIcon(getClass().getResource("Right.png"));
		Image right = rightCat.getImage().getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH); // scales the image
		rightfin = new ImageIcon(right);
		imageCat = new JLabel(rightfin);
		
	}

	@Override
	public void draw() {
		imageCat.setVisible(true);
		imageCat.setOpaque(false);
		imageCat.addKeyListener(this); // adds keylistener to JLabel Cat Icon
		this.add(imageCat);
		
	}
	// adds action listeners

	@Override
	public void keyPressed(KeyEvent e) {
		this.removeAll(); //gets rid of old cat image 
		if (e.getKeyCode() == KeyEvent.VK_UP) {// up arrow
			MovingObjects.Cat.direction = Face.North;
			imageCat = new JLabel(upfin);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {// down arrow
			MovingObjects.Cat.direction = Face.South;
			imageCat = new JLabel(downfin);
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {// right arrow
			MovingObjects.Cat.direction = Face.East;
			imageCat = new JLabel(rightfin);
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {// left arrow
			MovingObjects.Cat.direction = Face.West;
			imageCat = new JLabel(leftfin);
		}
		imageCat.setBounds(0,0,80,80); //according to JPanel its in 0 0 coordinates
		this.draw();
		this.repaint(); //refreshes this JPanel to be displayed
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void doAction() {
		Timer time1 = new Timer(100, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//animates cat to move in directions !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            	if(direction==Face.East) {
            		if(Main_JFrame.myCat.getX()+97<=Main_JFrame.mainFrame.getWidth()) {//checks for borders
            			Main_JFrame.myCat.setBounds(Main_JFrame.myCat.getX()+8, Main_JFrame.myCat.getY(), 80, 80);}
            	}else if(direction==Face.North) {
            		if(Main_JFrame.myCat.getY()+13>=0) {
            			Main_JFrame.myCat.setBounds(Main_JFrame.myCat.getX(), Main_JFrame.myCat.getY()-8, 80, 80);}
                	} else if(direction==Face.South) {
                		
                		if(Main_JFrame.myCat.getY()+127<=Main_JFrame.mainFrame.getHeight()) {
                			Main_JFrame.myCat.setBounds(Main_JFrame.myCat.getX(), Main_JFrame.myCat.getY()+8, 80, 80);}
                	}else if(direction==Face.West) {
                		
                		if(Main_JFrame.myCat.getX()+13>=0) {
                			Main_JFrame.myCat.setBounds(Main_JFrame.myCat.getX()-8,Main_JFrame.myCat.getY(), 80, 80);}
                		// cat animating code ends here !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                		
                	}
            	Main_JFrame. mainFrame.repaint();
	            
			}
		});
		time1.start();
	}
	
	
}
