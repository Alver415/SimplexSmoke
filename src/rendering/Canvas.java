package rendering;

import geometry.*;

public class Canvas {

	private final int[] pixels;
	private final int width;
	private final int height;	

	private Color background = Color.BLACK;
	
	public Canvas(){
		this(100);
	}
	public Canvas(int size){
		this(size, size);
	}
	public Canvas(int width, int height){
		this.width = width;
		this.height = height;
		this.pixels = new int[width * height];
		clear();
	}

	public void setBackground(Color c){
		this.background = c;
	}
	
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	public int getSize(){
		return pixels.length;
	}

	public int[] getIntArray(){
		return pixels;
	}
	public int getPixel(int x, int y){
		return pixels[getSize() - (width - x) - y * width];
	}
	public void setPixel(int x, int y, int color){
		if (x >= width || x < 0 || y >= height || y < 0){	
			return;
		}
		pixels[getSize() - (width - x) - y * width] = color;
	}

	public void clear(){
		for (int i = 0; i < getSize(); i++){
			pixels[i] = background.argb();
		}
	}
	public void fade(){
		for (int i = 0; i < getSize(); i++){
			Color c = new Color(pixels[i]);
			pixels[i] = Color.hsv(c.getHue(), c.getSaturation(), c.getValue() - 0.1).argb();
		}
	}

	public void drawLine(Vector m, Vector n, Color c){
		Vector a = m.clone();
		Vector b = n.clone();
		
		int Ax = (int) a.getX();
		int Bx = (int) b.getX();
		int Ay = (int) a.getY();
		int By = (int) b.getY();
		
		int dX = Math.abs(Bx - Ax);	// store the change in X and Y of the line endpoints
		int dY = Math.abs(By - Ay);

		int Xincr, Yincr;
		if (Ax > Bx) { Xincr=-1; } else { Xincr=1; }	// which direction in X?
		if (Ay > By) { Yincr=-1; } else { Yincr=1; }	// which direction in Y?

		if (dX >= dY){							// if X is the independent variable
			int dPr 	= dY << 1;            	// amount to increment decision if right is chosen (alwAys)
			int dPru 	= dPr - (dX<<1);   	 	// amount to increment decision if up is chosen
			int P 		= dPr - dX;  			// decision variable start value

			for (int counter = 0; counter <= dX; counter++){
				setPixel((int)Ax, (int)Ay, c.argb());
				if (P > 0){            // is the pixel going right AND up?
					Ax+=Xincr;	       // increment independent variable
					Ay+=Yincr;         // increment dependent variable
					P+=dPru;           // increment decision (for up)
				}
				else {                 // is the pixel just going right?
					Ax+=Xincr;         // increment independent variable
					P+=dPr;            // increment decision (for right)
				}
			}
		}
		else  {           				   // if Y is the independent variable
			int dPr 	= dX<<1;           // amount to increment decision if right is chosen (alwAys)
			int dPru 	= dPr - (dY<<1);   // amount to increment decision if up is chosen
			int P 		= dPr - dY;  	   // decision variable start value

			for (int counter = 0; counter <= dY; counter++) {      
				setPixel((int)Ax, (int)Ay, c.argb());
				if (P > 0){              // is the pixel going up AND right?
					Ax+=Xincr;           // increment dependent variable
					Ay+=Yincr;           // increment independent variable
					P+=dPru;             // increment decision (for up)
				}
				else {                    // is the pixel just going up?
					Ay+=Yincr;            // increment independent variable
					P+=dPr;            	  // increment decision (for right)
				}
			}
		}
	}
}