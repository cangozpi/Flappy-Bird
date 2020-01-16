package SetupInfo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;

import Background.board;
import Main.Main_JFrame;
import MovingObjects.Ghosts;
import eatibles.*;

public class setup extends JFrame {

	// instance variables
	private int fruit;
	private int poison;
	private boolean checker = false; // used to close setup frame
	public static fruit[] fruitList=null;
	public static poison[] poisonList;
	public static int ghost;
	public static Ghosts myGhost;

	public setup() throws Exception {
		super("Game Setup");
		setLayout(new FlowLayout());
		JLabel textsummary = new JLabel();
		textsummary.setText("Enter Amounts to Initialize Game Below -->");
		// initializes corresponding texts and JTextField's
		JLabel textFruit = new JLabel("Amount of fruit : ");
		JLabel textPoison = new JLabel("Amount of poison : ");
		JTextField fieldFruit = new JTextField("Number here");
		JTextField fieldPoison = new JTextField("Number here");
		JTextField fieldGhost = new JTextField("Number here");
		JLabel textGhost = new JLabel("Number of Ghosts : ");
		// button to continue to game
		JButton button = new JButton("Press to Initialize your Game");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// gets values entered by the user for fruit and poison
				fruit = (int) Double.parseDouble(fieldFruit.getText());
				poison = (int) Double.parseDouble(fieldPoison.getText());
				ghost = (int) Double.parseDouble(fieldGhost.getText());
				if (fruit > 0 && poison > 0 && fruit+poison+ghost<121&&ghost>0)  {
					// sets num values for mainJFrame class to be later used
					Main_JFrame.setFruitNum(fruit);
					Main_JFrame.setPoisonNum(poison);
					Main_JFrame.setGhostNum(ghost);
					// adds fruit objects to main JFrame to play screen
					fruitList = new fruit[Main_JFrame.getFruitNum()];
					for (int i = 0; i < Main_JFrame.getFruitNum(); i++) {
						fruitList[i] = new fruit();
						fruitList[i].setVisible(true);
						if (fruitList[i].coordinateList.contains(
								fruitList[i].getXCoordinate() * 100000 + fruitList[i].getYCoordinate()) ) {
							if(i >= 0) {
							i--; // makes sure that incidentally fruits on't overlap;
							}
						} else {
							fruitList[i].coordinateList.add(fruitList[i].getXCoordinate() * 100000 + fruitList[i].getYCoordinate());
							Main_JFrame.myLayeredPane.add(fruitList[i], JLayeredPane.POPUP_LAYER);
							Main_JFrame.mainFrame.repaint();
						}
					}
					//poison
					poisonList = new poison[Main_JFrame.getPoisonNum()];
					for(int i=0;i<Main_JFrame.getPoisonNum();i++) {
						poisonList[i] = new poison();
						poisonList[i].setVisible(true);
						if (fruitList[0].coordinateList.contains(
								poisonList[i].getXCoordinate() * 100000 + poisonList[i].getYCoordinate()) ) {
							if(i>=0) {
							i--; // makes sure that incidentally fruits on't overlap;
							}
						} else {
							fruitList[0].coordinateList.add(poisonList[i].getXCoordinate() * 100000 + poisonList[i].getYCoordinate());
							Main_JFrame.myLayeredPane.add(poisonList[i], JLayeredPane.POPUP_LAYER);
							Main_JFrame.mainFrame.repaint();
						}
						
					}
			
					myGhost = new Ghosts(); // to initialize
					myGhost.draw(); // adds ghosts to screen
					setup.myGhost.doAction(); //animates ghost objects
					Main_JFrame.myCat.doAction(); // animates the cat on displayed screen
					Main_JFrame.scorePanel.setVisible(true);
					//
					removeAll(); // should terminate this jFrame here
				} else {
					try {
						throw new Exception("Values should be greater than 0"); // throws an exception
					} catch (Exception e1) {
						System.out.println("Values should be greater than 0");
						e1.printStackTrace();
					}
				}
				Main_JFrame.mainFrame.setVisible(true);//starts the game
				Main_JFrame.mySetup.setVisible(false);//hides setup menu once the game starts

			}
		});
		// adding components
		add(textsummary);
		add(textFruit);
		add(fieldFruit);
		add(textPoison);
		add(fieldPoison);
		add(textGhost);
		add(fieldGhost);
		add(button);
		//
		getContentPane().setBackground(Color.ORANGE); // sets background color for JFrame setup
		fieldFruit.setForeground(Color.RED);// sets color of the inner writing to red
		fieldPoison.setForeground(Color.RED);
		button.setBackground(Color.GREEN); // sets color of button to green
		fieldGhost.setForeground(Color.RED);
	}

}
