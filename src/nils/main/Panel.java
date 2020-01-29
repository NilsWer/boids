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
	private int numberOfBoids = 100;

	public Panel() {
		Timer timer = new Timer();

//		boids.add(new Boid());

		obs.add(new Obstical(-BORDER, -BORDER + 1, Frame.width + 2 * BORDER, BORDER));
		obs.add(new Obstical(-BORDER + 1, -BORDER, BORDER, Frame.height + 2 * BORDER));
		obs.add(new Obstical(Frame.width - 11, -BORDER, BORDER, Frame.height + 2 * BORDER));
		obs.add(new Obstical(-BORDER, Frame.height - 11, Frame.width + 2 * BORDER, BORDER));
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				update();
//				repaint();
			}
		}, 100, 30);

	}

	@Override
	public void paint(Graphics g) {
		for (int i = 0; i < boids.size(); i++) {
			boids.get(i).paint(g);
		}
		for (Obstical ob : obs) {
			ob.paint(g);
		}
	}

	public void update() {
		for (int i = 0; i < boids.size(); i++) {
			boids.get(i).update();
			checkCollision();
		}
		while (boids.size() < numberOfBoids) {
			boids.add(new Boid());
		}

	}

	private void checkCollision() {
		for (int i = 0; i < boids.size(); i++) {
			Boid boid = boids.get(i); // current boid
			// setzt die Werte der Sensoren wieder auf 1
			for (int j = 0; j < boid.getSensorCount(); j++) {
				boid.sensorList[j] = 1;
			}
			for (Obstical ob : obs) {

				// Only if the Obstical is in the near of the Boid
				if (ob.width / 2 + 200 > Math.abs(ob.xMitte - boid.getPos().x)
						&& ob.height / 2 + 200 > Math.abs(ob.yMitte - boid.getPos().y)) {
					// check if death
					if (boid.getPos().x <= ob.x + ob.width && boid.getPos().x >= ob.x && boid.getPos().y >= ob.y
							&& boid.getPos().y <= ob.y + ob.height) {
						boids.remove(i);
					}

					for (int k = 0; k < boid.getSensorCount(); k++) {
						double currentX = boid.sensorPoints[k][0];
						double currentY = boid.sensorPoints[k][1];

						// TODO überprüft nur, ob die Enden der Sensoren in dem Hinderniss sind, das ist
						// bei kleinen Hindernissen nicht akkurat.
						if (currentX < ob.x + ob.width && currentX > ob.x && currentY < ob.y + ob.height
								&& currentY > ob.y) {
							// TODO Distanz zum nächsten Hindernis nicht einfach nur 0.
							boid.sensorList[k] = 0;
						}
					}

				}
			}
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
