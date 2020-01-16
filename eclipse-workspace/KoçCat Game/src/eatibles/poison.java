package eatibles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

import Main.Main_JFrame;
import SetupInfo.setup;

public class poison extends food {

	// instance variables
	Color color = Color.yellow;
	private int age = 1;
	private int x;
	private int y;

	// getters setter
	public int getXCoordinate() {
		return this.x;
	}

	public int getYCoordinate() {
		return this.y;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int i) {
		this.age = i;
	}

	// implemented methods
	@Override
	public void consumed() {

		Main_JFrame.setKocCatScore(Main_JFrame.getKocCatScore() - (10 * this.getAge())); // decrements koccat score
		this.setAge(1);
		/*   enable below if you want poison to change position
		Random rgen = new Random();
		fruit.coordinateList.remove((Object) (x * 100000 + y)); // removes older coordinate from list
		do { // new random coordinates for new fruit replacement
			this.x = rgen.nextInt(11) * 80 + 5;// sets random coordinates for fruit to be displayed at
			this.y = rgen.nextInt(11) * 80 + 5;
		} while (fruit.coordinateList.contains(this.getXCoordinate() * 100000 + this.getYCoordinate()));
		fruit.coordinateList.add(this.getXCoordinate() * 100000 + this.getYCoordinate());
		*/
		this.color = Color.yellow;

	}

	@Override
	public void grow() {

		this.age++;
		if (this.getAge() == 10) {// changes color to red
			color = Color.RED;
		}

	}

	// draw method
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;// draws blue circles
		g2d.setColor(color);
		g2d.fillRect(this.x, this.y, 70, 70);

	}

	// constructor
	public poison() {
		super();
		this.draw();
	}

	@Override
	public void draw() {
		this.setAge(0); // initializes age as 0
		Random rgen = new Random();
		do {
			x = rgen.nextInt(11) * 80 + 5;// sets random coordinates for fruit to be displayed at
			y = rgen.nextInt(11) * 80 + 5;
		} while (x == 405 && y == 405); // makes sure it doesn't overlap wit Cat
		this.setSize(1000, 1000);
		this.setVisible(true);
		this.setOpaque(false);

	}

	@Override
	public void doAction() {
		Timer timer1 = new Timer(1000, new ActionListener() { // loops in each 10 seconds
			public void actionPerformed(ActionEvent event) {// updates food objects
				for (int i = 0; i < Main_JFrame.getPoisonNum(); i++) {
					setup.poisonList[i].grow();
				}
				Main_JFrame.mainFrame.repaint();
			}
		});
		timer1.start();

	}

}
