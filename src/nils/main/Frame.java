package nils.main;

import java.util.ArrayList;

import javax.swing.JFrame;
import nils.main.*;

public class Frame extends JFrame {
	public static int width = 1000;
	public static int height = 1000;
	private Panel panel = new Panel();

	public static void main(String[] args) {
		Frame f = new Frame();
		while (true) {
			f.repaint();
		}
	}

	public Frame() {
		setSize(width + 6, height + 29);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setContentPane(panel);
		addKeyListener(panel);

		setVisible(true);
//		ArrayList<Double> angles = new ArrayList<>();
//		for (double a= -Math.PI; a <Math.PI; a+=.02) {
//			angles.add(a);
//		}
//		
//		for (Double angle : angles) {
//			System.out.println(angle + ":::" +Math.tan(angle));
//		}
//		
//		System.exit(0);
	}
}
