/*THIS CODE IS MY OWN WORK. I DID NOT CONSULT TO ANY  PROGRAM WRITTEN BY OTHER STUDENTS. 
I READ AND FOLLOWED THE GUIDELINE GIVEN IN THE PROGRAMMING ASSIGNMENT. NAME: Can Gözpýnar
*/
package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import SetupInfo.setup;
import SetupInfo.setup.*;
import eatibles.food;
import eatibles.fruit;
import eatibles.poison;
import Background.*;
import MovingObjects.Cat;
import MovingObjects.Cat.Face;
import MovingObjects.Ghosts;

public class Main_JFrame {

	// instance variables
	public static setup mySetup;
	private static int fruitNum;
	private static int poisonNum;
	private static int ghostNum;
	public static Ghosts[] ghostList;
	public static JFrame mainFrame = new JFrame("KoçCat Game !!!"); // initialized games main JFrame
	public static board myBoard = new board();
	public static JLayeredPane myLayeredPane;
	public static Cat myCat;
	private static int kocCatScore = 0;
	public static Timer checkCollusion;
	public static Timer checkCollision2;
	private static boolean lost = false;
	public static JFrame scorePanel = new JFrame("Score Board");

	// setters and getters
	public static void setKocCatScore(int i) {
		kocCatScore = i;
	}

	public static int getKocCatScore() {
		return kocCatScore;
	}

	public static void setFruitNum(int i) {
		fruitNum = i;
	}

	public static void setPoisonNum(int i) {
		poisonNum = i;
	}

	public static int getGhostNum() {
		return ghostNum;
	}

	public static void setGhostNum(int i) {
		ghostNum = i;
	}

	public static int getFruitNum() {
		return fruitNum;
	}

	public static int getPoisonNum() {
		return poisonNum;
	}

	public static void main(String[] args) throws Exception {

		// creates an instance of class to display info
		mySetup = new setup();
		mySetup.setVisible(true);
		mySetup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mySetup.setLocation(1425, 200);
		mySetup.setSize(310, 195);
		// adds CatIcon
		myCat = new Cat();
		myCat.setVisible(true);
		myCat.draw(); // adds to JFrame
		// adds scorepanel
		scorePanel.add(new JLabel("Current KoçCat Score --> " + Main_JFrame.getKocCatScore()));
		scorePanel.setVisible(false);
		scorePanel.getContentPane().setBackground(Color.cyan);
		scorePanel.setBounds(1425, 400, 100, 100);
		scorePanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// adds the layers to JlayeredPane and finally to JFrame to be displayed at
		myLayeredPane = new JLayeredPane();
		myLayeredPane.add(myBoard, 3);
		myBoard.setOpaque(false);
		myCat.setOpaque(false);
		myBoard.setVisible(true);
		myCat.setVisible(true);
		myBoard.setBounds(0, 0, 1000, 1000);
		myCat.setBounds(400, 400, 80, 80); // sets coordinates of Cat Icon // JPanels coordinates are set via this to
											// 400
		myLayeredPane.add(myCat, JLayeredPane.DRAG_LAYER);
		myBoard.background.setOpaque(false);
		myBoard.background.setBounds(0, 0, 1000, 1000);
		Main_JFrame.myLayeredPane.add(myBoard.background, 10);
		mainFrame.setContentPane(myLayeredPane);
		// sets up mainFrame JFrame
		mainFrame.setSize(905, 938);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);
		// Game loop
		fruit myfruit = new fruit(); // just to call doAction on fruit
		myfruit.doAction();
		poison mypoison = new poison(); // just to call doAction on poison
		mypoison.doAction();

		checkCollision2(); // checks for collisions by means of squares

	//	checkCollision();// checks for collisions among objects //uncomment to check for collisions
							// without square approach

	}

	// used to check if things are in the same square by means of checkedboard
	public static void checkCollision2() {
		checkCollision2 = new Timer(10, new ActionListener() { // checks for cat icons collusion
			
			@Override
			public void actionPerformed(ActionEvent e) { // fruit consumption
				if (setup.fruitList != null) {
					for (int i = 0; i < setup.fruitList.length; i++) {
						int x = (int) (setup.fruitList[i].getXCoordinate() / 80);
						int y = (int) (setup.fruitList[i].getYCoordinate() / 80);
						int xc = (int) ((myCat.getX() + 40) / 80);
						int yc = (int) ((myCat.getY() + 40) / 80);
						
						if (x == xc && y == yc) {
							setup.fruitList[i].consumed();
						}
					}

				}
				if (setup.poisonList != null) { // poison consumption is implemented here
					for (int i = 0; i < setup.poisonList.length; i++) {
						int x = (int) (setup.poisonList[i].getXCoordinate() / 80);
						int y = (int) (setup.poisonList[i].getYCoordinate() / 80);
						int xc = (int) ((myCat.getX() + 40) / 80);
						int yc = (int) ((myCat.getY() + 40) / 80);
						if (x == xc && y == yc) {
							setup.poisonList[i].consumed();
						}
					}
				}
				if (ghostList != null) { // ghostCollusion check
					for (int i = 0; i < ghostList.length; i++) {
						int x = (int) (ghostList[i].getX() / 80);
						int y = (int) (ghostList[i].getY() / 80);
						int xc = ((int) (myCat.getX() + 40) / 80);
						int yc = (int) ((myCat.getY() + 40) / 80);
						if (x == xc && y == yc) {
							kocCatScore = -1;
						}

					}
				}
				if (kocCatScore < 0 && !lost) {
					mainFrame.setVisible(false);
					JFrame endFrame = new JFrame("Game Over!");
					JLabel lost1 = new JLabel("Your Score is : " + String.valueOf(kocCatScore));
					JLabel lost2 = new JLabel("Game Over, Try Again ...                                     ");
					lost2.setBounds(400, 400, 400, 400);
					lost2.setVisible(true);
					endFrame.setLayout(new FlowLayout());
					endFrame.add(lost2);
					lost1.setBounds(400, 400, 400, 400);
					endFrame.add(lost1);
					endFrame.setVisible(true);
					endFrame.setSize(450, 100);
					endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					endFrame.setLocationRelativeTo(null);
					endFrame.getContentPane().setBackground(Color.CYAN);
					endFrame.setResizable(false);
					lost = true;
					mySetup.setVisible(false);
					scorePanel.setVisible(false); // hides score frame
				}

				scorePanel.getContentPane().removeAll();
				scorePanel.add(new JLabel("Current KoçCat Score --> " + Main_JFrame.getKocCatScore()));
				scorePanel.revalidate();
				scorePanel.repaint();
			}
		});
		checkCollision2.start();
	}

	// used to check if it things touch
	public static void checkCollision() {
		checkCollusion = new Timer(10, new ActionListener() { // checks for cat icons collusion

			@Override
			public void actionPerformed(ActionEvent e) { // fruit consumption
				if (setup.fruitList != null) {
					for (int i = 0; i < setup.fruitList.length; i++) {
						int x = setup.fruitList[i].getXCoordinate() - 5;
						int y = setup.fruitList[i].getYCoordinate() - 5;
						int xc = myCat.getX() + 30;
						int yc = myCat.getY() + 30;
						if (x < xc && x + 80 > xc && y < yc && y + 80 > yc) {
							setup.fruitList[i].consumed();
						}
					}

				}
				if (setup.poisonList != null) { // poison consumption is implemented here
					for (int i = 0; i < setup.poisonList.length; i++) {
						int x = setup.poisonList[i].getXCoordinate() - 5;
						int y = setup.poisonList[i].getYCoordinate() - 5;
						int xc = myCat.getX() + 30;
						int yc = myCat.getY() + 30;
						if (x < xc && x + 80 > xc && y < yc && y + 80 > yc) {
							setup.poisonList[i].consumed();
						}
					}
				}
				if (ghostList != null) { // ghostCollusion check
					for (int i = 0; i < ghostList.length; i++) {
						int x = ghostList[i].getX();
						int y = ghostList[i].getY();
						int xc = myCat.getX() + 30;
						int yc = myCat.getY() + 30;
						if (x < xc && x + 80 > xc && y < yc && y + 80 > yc) {
							kocCatScore = -1;
						}

					}
				}
				if (kocCatScore < 0 && !lost) {
					mainFrame.setVisible(false);
					JFrame endFrame = new JFrame("Game Over!");
					JLabel lost1 = new JLabel("Your Score is : " + String.valueOf(kocCatScore));
					JLabel lost2 = new JLabel("Game Over, Try Again ...                                     ");
					lost2.setBounds(400, 400, 400, 400);
					lost2.setVisible(true);
					endFrame.setLayout(new FlowLayout());
					endFrame.add(lost2);
					lost1.setBounds(400, 400, 400, 400);
					endFrame.add(lost1);
					endFrame.setVisible(true);
					endFrame.setSize(450, 100);
					endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					endFrame.setLocationRelativeTo(null);
					endFrame.getContentPane().setBackground(Color.CYAN);
					endFrame.setResizable(false);
					lost = true;
					mySetup.setVisible(false);
					scorePanel.setVisible(false); // hides score frame
				}

				scorePanel.getContentPane().removeAll();
				scorePanel.add(new JLabel("Current KoçCat Score --> " + Main_JFrame.getKocCatScore()));
				scorePanel.revalidate();
				scorePanel.repaint();
			}
		});
		checkCollusion.start();
	}

}
