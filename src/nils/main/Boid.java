package nils.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Boid {
	private Random random = new Random(System.nanoTime());
	private DoublePoint pos;
	private DoublePoint dir;
	private Color color = Color.BLACK;
	private double[][] shape = { { 15, 0 }, { -15, 10 }, { -10, 0}, { -15, -10 } };
	private double sightAngle = Math.PI;
	private int sensorCount = 20;
	private double[] sensorAngleList;
	double[][] sensorPoints;
	double[] sensorList;
	private double sensorDistance = 200D;

	public Boid() {
		pos = new DoublePoint(Frame.width / 2, Frame.height / 2);
		dir = new DoublePoint(random.nextDouble()-.5, random.nextDouble()-.5);
		dir.nomalize();
		dir.multiply(3D);

		sensorAngleList = new double[sensorCount];
		sensorList = new double[sensorCount];
		sensorPoints = new double[sensorCount][2];
		for (int i = 0; i < sensorCount; i++) {
			sensorAngleList[i] = (sightAngle * i / (sensorCount - 1)) - sightAngle / 2;
			sensorList[i] = 1;
		}
	}

	public void paint(Graphics g) {

		g.setColor(color);

		int[] xshape = new int[shape.length];
		int[] yshape = new int[shape.length];
		for (int i = 0; i < shape.length; i++) {
			double[] point = transform(shape[i][0], shape[i][1]);
			xshape[i] = (int) point[0];
			yshape[i] = (int) point[1];
		}

		g.fillPolygon(xshape, yshape, shape.length);
		for (int i = 0; i < sensorCount; i++) {
			if (sensorList[i] < 1) {
				g.setColor(Color.RED);
			} else {
				g.setColor(Color.BLACK);
			}
			g.drawLine((int) pos.x, (int) pos.y, (int) sensorPoints[i][0], (int) sensorPoints[i][1]);
		}
	}

	/**
	 * This Method transforms coordinates from the frame_coordinatesystem to the
	 * boid_coordinatesystem.
	 * 
	 * @param x
	 * @param y
	 * @return transformated Point as double[]
	 */
	private double[] transform(double x, double y) {
		return transform(x, y, 0D);
	}

	private double[] transform(double x, double y, double a) {
		double angle = Math.atan(dir.y / dir.x);

		if (dir.x < 0 && dir.y < 0) {
			angle -= Math.PI;
		} else if (dir.x < 0 && dir.y > 0) {
			angle += Math.PI;
		}

		double[] Point = rotate(x, y, angle + a);
		Point[0] += pos.x;
		Point[1] += pos.y;
		return Point;
	}

	private double[] rotate(double x, double y, double angle) {
		double[] point = new double[2];
		point[0] = (x * Math.cos(angle) - y * Math.sin(angle));
		point[1] = (x * Math.sin(angle) + y * Math.cos(angle));
		return point;
	}

	public void move() {
		pos.x += dir.x;
		pos.y += dir.y;
	}

	/**
	 * 
	 * @param input: value between 0 and 1. 0.5 is straight forward
	 */
	public void turn(double input) {
		double[] newdir = rotate(dir.x, dir.y, input - 0.5);
		dir.x = newdir[0];
		dir.y = newdir[1];
	}
	

	public void update() {
		for (int i = 0; i < sensorCount; i++) {
			sensorPoints[i] = transform(sensorDistance, 0D, sensorAngleList[i]);
		}
		move();
	}
	public double getSensorDistance() {
		return sensorDistance;
	}

	public void setSensorDistance(double sensorDistance) {
		this.sensorDistance = sensorDistance;
	}

	public DoublePoint getPos() {
		return pos;
	}

	public int getSensorCount() {
		return sensorCount;
	}
}
