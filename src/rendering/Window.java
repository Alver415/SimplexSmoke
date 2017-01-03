package rendering;

import geometry.Vector;
import geometry.Particle;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

@SuppressWarnings({"serial"})
public class Window extends JFrame implements KeyListener{

	BufferedImage image;
	Canvas canvas;
	
	private int width;
	private int height;

	private double delta = 1;
	private double time = Math.random() * 1000;
	private double amplitude = 0.9;
	
	private Particle[] particles = new Particle[100000];
	
	
	
	public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Window();
            }
        });
	}
	
	public Window(){

		
    	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    	width  = (int) (screen.getWidth()  / 1f);
    	height = (int) (screen.getHeight() / 1f);

        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("PerlinTerrain");
        setPreferredSize(new Dimension(width,height));
        setLocation((int)((screen.getWidth() - width) / 2), (int)((screen.getHeight() - height) / 2));
        pack();
        setVisible(true);
        addKeyListener(this);
        
        canvas = new Canvas(width, height);
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        setContentPane(new JPanel(){
        	@Override 
        	public void paintComponent(Graphics g) {
        		g.drawImage(image, 0, 0, null);
        	}
        });

        
		image.setRGB(0, 0, width, height, canvas.getIntArray(), 0, width);
        repaint();
        
        for (int p = 0; p < particles.length;  p++){
            particles[p] = new Particle(Math.random() * width, Math.random() * height);
        }
        new Timer(1, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				time += delta;
				for (int i = 0; i < particles.length; i++){
					Particle p = particles[i];

					double n = SimplexNoise.noise(p.pos.getX() * 0.002, p.pos.getY() * 0.002, time * 0.01);
					p.acc = (new Vector(Math.cos((n + 1) * Math.PI), Math.sin((n + 1) * Math.PI)));
					p.update(amplitude);
					
					if (p.pos.getX() >= width || p.pos.getX() < 0 || p.pos.getY() >= height || p.pos.getY() < 0){
						p.pos.set(Math.random() * width, Math.random() * height);
					}
		
					Color c = new Color(canvas.getPixel((int)p.pos.getX(), (int)p.pos.getY()));
					c = Color.hsv(time, 1, c.getValue() + 0.05);
					canvas.setPixel((int)p.pos.getX(), (int)p.pos.getY(), c.argb());
				}
				
				image.setRGB(0, 0, width, height, canvas.getIntArray(), 0, width);
		        repaint();
				
			}
		}).start();
 
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case (KeyEvent.VK_UP):
			delta += 0.1;
			break;
		
		case (KeyEvent.VK_DOWN):
			delta -= 0.1;
			if (delta < 0) delta = 0;
			break;
			
		case (KeyEvent.VK_LEFT):
			amplitude -= 0.1;
			if (amplitude < 0) amplitude = 0;
			break;
		
		case (KeyEvent.VK_RIGHT):
			amplitude += 0.1;
		if (amplitude > 0.9) amplitude = 0.9;
			break;
		case (KeyEvent.VK_SPACE):
			canvas.clear();
			break;
			
		case (KeyEvent.VK_BACK_SPACE) :
			for (int p = 0; p < particles.length;  p++){
	            particles[p] = new Particle(Math.random() * width, Math.random() * height);
	        }
			break;
		case (KeyEvent.VK_ESCAPE):
			System.exit(0);
			break;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {	}
	@Override
	public void keyTyped(KeyEvent e) {	}


}
