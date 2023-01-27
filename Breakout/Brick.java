package Breakout;
//*********************************
import java.awt.*;
import java.awt.event.*;

public class Brick extends Rectangle{

	int x, y;
	int brickWidth, brickHeight;
	
	public Brick(int x, int y, int brickWidth, int brickHeight) {
		super(x,y,brickWidth,brickHeight);
		this.x=x;
		this.y=y;
		this.brickWidth=brickWidth;
		this.brickHeight=brickHeight;
	}
	
	public void draw(Graphics g, int id) {
		switch(id) {
		case 0:
			g.setColor(Color.red);
            g.fillRect(x, y, brickWidth, brickHeight);
            break;
		case 1:
			g.setColor(Color.red);
            g.fillRect(x, y, brickWidth, brickHeight);
            break;
        case 2: 
            g.setColor(Color.orange);
            g.fillRect(x, y, brickWidth, brickHeight);
            break;
        case 3: 
            g.setColor(Color.orange);
            g.fillRect(x, y, brickWidth, brickHeight);
            break;
        case 4: 
            g.setColor(Color.green);
            g.fillRect(x, y, brickWidth, brickHeight);
            break;
        case 5: 
            g.setColor(Color.green);
            g.fillRect(x, y, brickWidth, brickHeight);
            break;
        case 6: 
            g.setColor(Color.yellow);
            g.fillRect(x, y, brickWidth, brickHeight);
            break;
        case 7: 
            g.setColor(Color.yellow);
            g.fillRect(x, y, brickWidth, brickHeight);
            break;
        }
	}
}