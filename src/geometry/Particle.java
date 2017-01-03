package geometry;

public class Particle {
	
	
	public Vector pos = Vector.ZERO();
	public Vector vel = Vector.ZERO();
	public Vector acc = Vector.ZERO();
	
	
	public Particle(double x, double y){
		pos.setX(x);
		pos.setY(y);
	}
	
	public void update(double amp){
		vel.add(acc.scalar(amp));
		pos.add(vel.scalar(amp));
		acc.set(0,0);
	}
	
}
