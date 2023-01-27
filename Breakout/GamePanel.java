package Breakout;
//*********************************

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{

	static final int GAME_WIDTH = 702;
	static final int GAME_HEIGHT = 690;
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
	//----------------------------------
	static final int BALL_DIAMETER = 15;
	//----------------------------------
	static int PADDLE_WIDTH = 90;
	static final int PADDLE_HEIGHT = 17;
	//----------------------------------
	static final int B_ROWS = 8; // righe di mattoncini
	static final int B_COLS = 14; // mattoncini per ogni riga
	static final int BRICK_WIDTH = 49;
	static final int BRICK_HEIGHT = 15;
	//----------------------------------
	private int rimb_2=60;
	//----------------------------------
	
	
	//_________________________________________________________________________
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	//-------------------------------------------------------------------------
	Paddle paddle1;
	Ball ball;
	Score score;
	Brick[][] brick = new Brick[B_ROWS][B_COLS]; //8x14 oggetti brick
	//_________________________________________________________________________
		
		
	GamePanel(){
		newPaddles();
		newBall();
		newBricks();
		score = new Score(GAME_WIDTH,GAME_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);
		
		gameThread = new Thread(this);
		gameThread.start();
	}
		
		
	public void newBall() {
		ball = new Ball(((GAME_WIDTH/2)-(BALL_DIAMETER/2)),((GAME_HEIGHT-70)-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);
	}
		
		
	public void newPaddles() {
		paddle1 = new Paddle((GAME_WIDTH/2)-(PADDLE_HEIGHT/2),(GAME_HEIGHT)-(PADDLE_WIDTH/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
	}
	
	
	public void newBricks() {
		int bY=rimb_2;
		for (int j=0; j<B_ROWS; j++) {
			int bX=0;
			for (int k=0; k< B_COLS; k++) {
					brick[j][k] = new Brick(bX, bY, BRICK_WIDTH, BRICK_HEIGHT);
					bX += BRICK_WIDTH+1;
			}
			bY += BRICK_HEIGHT+1;
		}
	}
		
		
	public void paint(Graphics g) {
		image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,0,0,this);
		
		//----------------------------------
		
		g.setColor(Color.gray);
		g.fillRect(0, rimb_2, 3, (GAME_HEIGHT-130));
		g.fillRect(0, rimb_2, 700, 3);
		g.fillRect(699, rimb_2, 3, (GAME_HEIGHT-130));
	}
		
		
	public void draw(Graphics g) {
		paddle1.draw(g);
		ball.draw(g);
		score.draw(g, ball.y, BALL_DIAMETER);
		
		for (int j=0; j<B_ROWS; j++) {
			for (int k=0; k< B_COLS; k++) {
				if(brick[j][k]!=null)	
					brick[j][k].draw(g,j);
			}
		}
		
		
	//-----------------------------------------
		Toolkit.getDefaultToolkit().sync();
	//-----------------------------------------
	}
		
		
	public void move() {
		paddle1.move();
		ball.move();
	}
		
		
	public void checkCollision() {
		
		//-------rimbalzo della pallina contro il bordo superiore e perde vita all'inferiore---------
		if(ball.y <=rimb_2) {
			ball.dy = -ball.dy;
		}
		if(ball.y >= GAME_HEIGHT-BALL_DIAMETER) {
			newBall();
			score.vite--;
			ball.hits=0;
		}
		for(int j=0; j<B_ROWS;j++) {
			for(int k=0; k<B_COLS; k++) {
				if(brick[j][k]!=null) {
					if(ball.intersects(brick[j][k])) {
						ball.dy = -ball.dy;
						ball.hits++;
						System.out.println("Player 1: "+score.player1);
						brick[j][k]=null;
						//-----------------punti------------------------
						if(j<2) {
							score.player1=score.player1+7;
						}
						if(j>=2 && j<4) {
							score.player1=score.player1+5;
						}
						if(j>=4 && j<6) {
							score.player1=score.player1+3;
						}
						if(j>=6 && j<8) {
							score.player1=score.player1+1;
						}
					}
				}
			}
		}
		//_________________________________________________________________________
		
		
		
		//-------rimbalzo della pallina contro i paddle--------
		if(ball.intersects(paddle1)) {
			ball.dy = Math.abs(ball.dy)*-1;
			ball.dy++;
		}
		//_________________________________________________________________________
			
		
		
		//----------paddle contro i lati della finestra-----------
		if(paddle1.x<=0)
			paddle1.x=0;
		if(paddle1.x >= (GAME_WIDTH-PADDLE_WIDTH))
			paddle1.x = (GAME_WIDTH-PADDLE_WIDTH);
		//rimb_1-PADDLE_WIDTH
		//_________________________________________________________________________
		
		
		
		//------palla rimbalza sui lati della finestra----------------
		if(ball.x <=0) {
			ball.dx = -ball.dx;
		}
		if(ball.x >= GAME_WIDTH-BALL_DIAMETER) {
			ball.dx = -ball.dx;
		}
		//rimb_1-BALL_DIAMETER
		//_________________________________________________________________________
		
		
		
		
			
		//-----------fine------------------------------------
		if(score.player1==448 || score.vite==0) {
			if(ball.y >= GAME_HEIGHT-BALL_DIAMETER) {
				ball.dy = -ball.dy;
			}
			paddle1.width= GAME_WIDTH;
			paddle1.dx=0;
			score.gameOver(graphics, BALL_DIAMETER, rimb_2);
			score.player1=score.tieni;
			
		}
		//_________________________________________________________________________
	}
		
		
	public void run() {
		//game loop
		long lastTime = System.nanoTime();
		double FPS =60.0;
		double duration = 1000000000 / FPS;
		double delta = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now -lastTime)/duration;
			lastTime = now;
			if(delta >=1) {
				move();
				checkCollision();
				repaint();
				delta--;
			}
		}
	}		
		
		
	public class AL extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			paddle1.keyPressed(e);
		}
		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
		}
	}
}
