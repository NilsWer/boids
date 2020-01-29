package nils.main;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class Panel extends JPanel implements KeyListener {
	ArrayList<Boid> boids = new ArrayList<>();
	ArrayList<Obstical> obs = new ArrayList<>();
	private static final int BORDER = 100;

	public Panel() {
		Timer timer = new Timer();
		boids.add(new Boid());

		obs.add(new Obstical(-BORDER, 			-BORDER + 1, 		Frame.width + 2 * BORDER, 	BORDER));
		obs.add(new Obstical(-BORDER + 1, 		-BORDER, 			BORDER, 					Frame.height + 2 * BORDER));
		obs.add(new Obstical(Frame.width - 11, 	-BORDER, 			BORDER, 					Frame.height + 2 * BORDER));
		obs.add(new Obstical(-BORDER, 			Frame.height - 11, 	Frame.width + 2 * BORDER, 	BORDER));
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				update();
			}
		}, 100, 30);

	}

	@Override
	public void paint(Graphics g) {
		for (Boid boid : boids) {
			boid.paint(g);
		}
		for (Obstical ob : obs) {
			ob.paint(g);
		}
	}

	public void update() {
		for (Boid boid : boids) {
			boid.update(obs);
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		switch (e.getKeyChar()) {
		case 'a':
			boids.get(0).turn(0);
			break;
		case 'd':
			boids.get(0).turn(1);
			break;

		default:
			break;
		}
		;

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
