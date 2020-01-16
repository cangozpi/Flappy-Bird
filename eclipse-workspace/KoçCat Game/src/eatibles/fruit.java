package eatibles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random; //for random generator

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import Main.Main_JFrame;
import SetupInfo.setup;

public class fruit extends food {
	//instance variables
	Color color = Color.BLUE;
	private int age=1;
	private int x;
	private int y;
	public static ArrayList<Integer> coordinateList = new ArrayList<>();
	//getters setter
	public int getXCoordinate() {
		return this.x;
	}
	public int getYCoordinate() {
		return this.y;
	}
	public ArrayList<Integer> getCoordinateList(){
		return coordinateList;
	}
	public int getAge() {
		return this.age;
	}
	public void setAge(int i) {
		this.age=i;
	}
	//implemented methods
	@Override 
	public void consumed() {
		
		Main_JFrame.setKocCatScore(Main_JFrame.getKocCatScore()+(5*this.getAge()));//increases koccat score
		this.setAge(9);
		this.grow(); // new fruit is created
	}
	@Override 
	public void grow() {
		
		this.age++;
		if(this.getAge()==5) { //color will change from blue to green
			this.color= Color.green;
		}
		if(this.getAge()==10) {
			this.setAge(1); //initializes age as 0
			Random rgen = new Random();
			coordinateList.remove((Object)(x*100000+y)); //removes older coordinate from list
			do { //new random coordinates for new fruit replacement
				this.x = rgen.nextInt(11)*80+5;//sets random coordinates for fruit to be displayed at
				this.y = rgen.nextInt(11)*80+5;
			}while(coordinateList.contains(this.getXCoordinate()*100000+this.getYCoordinate()));
		coordinateList.add(this.getXCoordinate()*100000+this.getYCoordinate());
		this.color= Color.BLUE;
		}
		
	}
	
	// draw method
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;//draws blue circles
		g2d.setColor(color);
		g2d.fillOval(this.x, this.y,70, 70);
		
		
	}
	//constructor
	public fruit() {
		super();
		this.draw();
	}
	@Override
	public void draw() {
		this.setAge(0); //initializes age as 0
		Random rgen = new Random();
		do {
		x = rgen.nextInt(11)*80+5;//sets random coordinates for fruit to be displayed at
		y = rgen.nextInt(11)*80+5;
		}while(x==405&&y==405); //makes sure it don't ovelap cat
		this.setSize(1000,1000);
		this.setVisible(true);
		this.setOpaque(false);
	}
	@Override
	public void doAction() {
		Timer timer1 = new Timer(1000,new ActionListener() {  // loops in each 10 seconds
            public void actionPerformed(ActionEvent event) {//updates food objects 
            	for(int i=0;i<Main_JFrame.getFruitNum();i++) {
            		setup.fruitList[i].grow();
            	}
            	   Main_JFrame.mainFrame.repaint();
	             }
	         } );
			timer1.start();
		
		
	}
	
	
}
