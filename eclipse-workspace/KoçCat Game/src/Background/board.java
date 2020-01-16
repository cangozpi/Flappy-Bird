package Background;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Main.Main_JFrame;


public class board extends JPanel {
	
	public static JLabel background;
	
	public board() {
		super();
		this.setOpaque(false);
		ImageIcon icon = new ImageIcon(getClass().getResource("2.jpg")); //3 BACKGROUNDS ARE AVAÝLABLE change 2 to 1 or 3 optionally
		Image img = icon.getImage().getScaledInstance(1000, 1000,java.awt.Image.SCALE_SMOOTH);
		background = new JLabel(new ImageIcon(img));
		background.setVisible(true);
		background.setOpaque(false);
	}

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		int x1 = 0;
		int y1 = 0;
		int x2 = 0;
		int y2 = 880;

		//drawing grids
		for (int i = 0; i < 12; i++) {// vertical lines
			g.drawLine(x1, y1, x2, y2);
			x1 = x1 + 80;
			x2 = x2 + 80;
		}

		x1 = 0;
		y1 = 0;
		x2 = 880;
		y2 = 0;

		for (int i = 0; i < 12; i++) {// horizontal lines
			g.drawLine(x1, y1, x2, y2);
			y1 = y1 + 80;
			y2 = y2 + 80;
		}
	}
	
}
