package nils.main;

public class DoublePoint {
	public double x, y;

	public DoublePoint(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void nomalize() {
		double a = Math.sqrt(this.x * this.x + this.y * this.y);
		this.x /= a;
		this.y /= a;
	}
	
	public void multiply(double factor) {
		this.x *= factor;
		this.y *= factor;
	}
	
	public String toString() {
		return "(" + x + ";" + y + ")";
	}
}
