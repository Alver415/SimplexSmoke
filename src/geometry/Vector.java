package geometry;

import java.awt.color.ColorSpace;

public class Vector {
	
	public static Vector ZERO(){ 		return new Vector(0,0);	}
	public static Vector LEFT(){ 		return new Vector(-1,0);	}
	public static Vector RIGHT(){ 		return new Vector(1,0);	}
	public static Vector UP(){	  		return new Vector(0,1);	}
	public static Vector DOWN(){		return new Vector(0,-1);	}
	
	private double x = 0;
	private double y = 0;
	
	public Vector(double x, double y){
		set(x,y);
	}

	public void set(double x, double y){
		setX(x);
		setY(y);
	}
	public void set(Vector v){
		setX(v.getX());
		setY(v.getY());
	}

	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}

	public void setX(double x){
		this.x = x;
	}
	public void setY(double y){
		this.y = y;
	}

	
	public void add(Vector v){
		this.set(v.x + this.x, v.y + this.y);
	}
//	public Vector subtract(Vector vector){
//		Vector result = new Vector();
//		for (int i = 0; i < 3; i++){
//			result.setElement(i, getElement(i) - vector.getElement(i));
//		}
//		return result;
//	}
	public Vector scalar(double scale){
		x *= scale;
		y *= scale;
		return this;
	}
//	public Vector cross(Vector p){
//		return cross(this, p);
//	}
//	public static Vector cross(Vector a, Vector b){
//		return new Vector (	(a.getY() * b.getZ()) - (a.getZ() * b.getY()),
//							(a.getZ() * b.getX()) - (a.getX() * b.getZ()),
//							(a.getX() * b.getY()) - (a.getY() * b.getX()));
//	}
//	public double dot(Vector p){
//		return getX() * p.getX() + getY() * p.getY() + getZ() * p.getZ();
//	}
//	public static double dot(Vector a, Vector b){
//		return a.getX() * b.getX() + a.getY() * b.getY() + a.getZ() * b.getZ();
//	}
	public Vector normalize(){
		double mag = magnitude();
		set(getX() / mag, getY() / mag);
		return this;
	}
	public double magnitude(){
		return (double) Math.sqrt(getX() * getX() + getY() * getY());
	}
//	public double magnitudeSquare(){
//		return getX() * getX() + getY() * getY() + getZ() * getZ();
//	}
//	public double distance(Vector p){
//		return subtract(p).magnitude();
//	}
//	public double distanceSquare(Vector p){
//		return subtract(p).magnitudeSquare();
//	}
//	public Vector midpoint(Vector v){
//		return new Vector((getX() + v.getX()) / 2, (getY() + v.getY()) / 2, (getZ() + v.getZ()) / 2);
//	}
//	public static Vector midpoint(Vector v1, Vector v2){
//		return new Vector((v1.getX() + v2.getX()) / 2, (v1.getY() + v2.getY()) / 2, (v1.getZ() + v2.getZ()) / 2);
//	}
	
	public Vector clone(){
		return new Vector(getX(), getY());
	}
	@Override
	public String toString(){
		return "[" + getX() + ", " + getY() + "]";
	}
	
}
