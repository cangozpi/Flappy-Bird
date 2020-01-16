package MovingObjects;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.Timer;

import Main.Main_JFrame;

public class Ghosts extends Drawable {

	// Instance variables
	private static JLabel ghostIcon;
	private String GhostType; // indicates type of the ghost initialized
	private boolean direction;

	public Ghosts() {
		GhostType = null;

	}

	public Ghosts(String str) throws Exception { // overloaded constructor
		super();
		ImageIcon ash = new ImageIcon(getClass().getResource("Ash.png"));
		Image ash1 = ash.getImage().getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH); // resizes ghost Icon
		ImageIcon ashfin = new ImageIcon(ash1);
		ImageIcon dolly = new ImageIcon(getClass().getResource("Dolly.png"));
		Image dolly1 = dolly.getImage().getScaledInstance(76, 76, java.awt.Image.SCALE_SMOOTH);
		ImageIcon dollyfin = new ImageIcon(dolly1);
		ImageIcon casper = new ImageIcon(getClass().getResource("Casper.png"));
		Image casper1 = casper.getImage().getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		ImageIcon casperfin = new ImageIcon(casper1);
		if (str.equals("ash")) {
			ghostIcon = new JLabel(ashfin);
			GhostType = "ash";
			boolean direction = true;
		} else if (str.equals("dolly")) {
			ghostIcon = new JLabel(dollyfin);
			GhostType = "dolly";
			boolean direction = true;
		} else if (str.equals("casper")) {
			ghostIcon = new JLabel(casperfin);
			GhostType = "casper";
		}
		ghostIcon.setVisible(true);
		ghostIcon.setOpaque(false);
		this.add(ghostIcon);
	}

	@Override
	public void draw() {
		// adds ghosts to screen
		Random rgen = new Random();
		int i = 0;
		Main_JFrame.ghostList = new Ghosts[Main_JFrame.getGhostNum()];
		while (i < Main_JFrame.getGhostNum()) {
			try {
				Main_JFrame.ghostList[i] = new Ghosts("ash");
				Main_JFrame.ghostList[i].setOpaque(false);
				Main_JFrame.ghostList[i].setBounds(rgen.nextInt(11) * 80, rgen.nextInt(11) * 80, 80, 80);
				Main_JFrame.myLayeredPane.add(Main_JFrame.ghostList[i], JLayeredPane.DRAG_LAYER, 1000);
				i++;
				if (i < Main_JFrame.getGhostNum()) {
					Main_JFrame.ghostList[i] = new Ghosts("dolly");
					Main_JFrame.ghostList[i].setOpaque(false);
					Main_JFrame.ghostList[i].setBounds(rgen.nextInt(11) * 80, rgen.nextInt(11) * 80, 80, 80);
					Main_JFrame.myLayeredPane.add(Main_JFrame.ghostList[i], JLayeredPane.DRAG_LAYER, 1000);
					i++;
				}
				if (i < Main_JFrame.getGhostNum()) {
					Main_JFrame.ghostList[i] = new Ghosts("casper");
					Main_JFrame.ghostList[i].setOpaque(false);
					Main_JFrame.ghostList[i].setBounds(rgen.nextInt(11) * 80, rgen.nextInt(11) * 80, 80, 80);
					Main_JFrame.myLayeredPane.add(Main_JFrame.ghostList[i], JLayeredPane.DRAG_LAYER, 1000);
					i++;

				}
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("something went wrong with ImageIcons !");
				break;
			}

		}
		Main_JFrame.mainFrame.repaint();

	}

	@Override
	public void doAction() {
		Timer time1 = new Timer(50, new ActionListener() { // for movements other than casper

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < Main_JFrame.ghostList.length; i++) {
					Ghosts loopGhost = Main_JFrame.ghostList[i];
					if (Main_JFrame.ghostList[i].GhostType.equals("ash")) {
						if (loopGhost.getX() + 100 <= Main_JFrame.mainFrame.getWidth() && loopGhost.direction) {
							loopGhost.setBounds(loopGhost.getX() + 5, loopGhost.getY(), 80, 80);
						} else if (loopGhost.getX() >= 0) {
							loopGhost.direction = false;
							loopGhost.setBounds(loopGhost.getX() - 5, loopGhost.getY(), 80, 80);
						} else {
							loopGhost.direction = true;
						}
					} else if (Main_JFrame.ghostList[i].GhostType.equals("dolly")) {
						if (loopGhost.getY() >= 0 && loopGhost.direction) {
							loopGhost.setBounds(loopGhost.getX(), loopGhost.getY() - 5, 80, 80);
						} else if (loopGhost.getY() + 139 <= Main_JFrame.mainFrame.getHeight()) {
							loopGhost.direction = false;
							loopGhost.setBounds(loopGhost.getX(), loopGhost.getY() + 5, 80, 80);
						} else {
							loopGhost.direction = true;
						}
					}

				}
			}

		});

		Timer timer2 = new Timer(800, new ActionListener() { // for casper's movement only

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < Main_JFrame.ghostList.length; i++) {
					Ghosts loopGhost = Main_JFrame.ghostList[i];
					if (loopGhost.GhostType.equals("casper")) {
						Random rgen = new Random();
						int num = rgen.nextInt(4);
						switch (num) {
						case 0:
							if (loopGhost.getX() <= 720) {
								loopGhost.setBounds(loopGhost.getX() + 80, loopGhost.getY(), 80, 80);
							}
							break;
						case 1:
							if (loopGhost.getY() <= 720) {
								loopGhost.setBounds(loopGhost.getX(), loopGhost.getY() + 80, 80, 80);
							}
							break;
						case 2:
							if (loopGhost.getX() >= 80) {
								loopGhost.setBounds(loopGhost.getX() - 80, loopGhost.getY(), 80, 80);
							}
							break;
						case 3:

							if (loopGhost.getY() >= 80) {
								loopGhost.setBounds(loopGhost.getX(), loopGhost.getY() - 80, 80, 80);
							}
							break;
						}
					}
				}
			}
		});

		time1.start(); // activates below Timer's
		timer2.start();

	}

}
