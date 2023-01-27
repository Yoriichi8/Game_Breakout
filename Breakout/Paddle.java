package Breakout;
//*********************************
import java.awt.*;
import java.awt.event.*;

public class Paddle extends Rectangle{

	int id;
	int dx;
	int speed = 10;
	
	Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){
		super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
		this.id=id;
	}
	
	public void keyPressed(KeyEvent e) {
		switch(id) {
		case 1:
			if(e.getKeyCode()==KeyEvent.VK_LEFT) {
				setDX(-speed);
			}
			if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
				setDX(speed);
			}
			break;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		switch(id) {
		case 1:
			if(e.getKeyCode()==KeyEvent.VK_LEFT) {
				setDX(0);
			}
			if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
				setDX(0);
			}
			break;
		}
	}
	
	public void setDX(int vectorX) {
		dx = vectorX;
	}
	
	public void move() {
		x= x + dx;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect(x, y, width, height);
	}
}
