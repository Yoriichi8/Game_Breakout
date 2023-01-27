package Breakout;
//*********************************
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Score extends Rectangle{

	static int GAME_WIDTH;
	static int GAME_HEIGHT;
	int player1=0;
	int vite=3;
	int tieni;
	private ImageIcon v;
	private Image vi;
	
	Score(int GAME_WIDTH, int GAME_HEIGHT){
		Score.GAME_WIDTH = GAME_WIDTH;
		Score.GAME_HEIGHT = GAME_HEIGHT;
		//-----------------------------------------------
		
		v=new ImageIcon("src/Immagini/prova_3-removebg-preview.png");
		vi=v.getImage();
	}
	
	public void draw(Graphics g, int by, int BALL_DIAMETER) {
		g.setFont(new Font("Unispace",Font.PLAIN,50));
		g.setColor(Color.white);
		g.drawString(String.valueOf(player1/10)+String.valueOf(player1%10), (GAME_WIDTH-670), 50);
		
		if(vite>0) {
			g.drawImage(vi, 530, 0, 50, 50, null);
			if(vite>1) {
				g.drawImage(vi, 590, 0, 50, 50, null);
				if(vite>2) {
					g.drawImage(vi, 650, 0, 50, 50, null);
					
				}
			}
		}
		gameOver(g,player1,tieni);
	}
	
	
	public void gameOver(Graphics g, int player1, int tieni) {
		
		//chi vincerà testo
		g.setFont(new Font("Consolas",Font.PLAIN,50));
		if(player1==448) {
			g.setColor(Color.GREEN);
			g.drawString(String.valueOf("Hai vinto"), ((GAME_WIDTH/2)-150), (GAME_HEIGHT/2));
		}
		else if( vite==0 )
		{
			g.setColor(Color.RED);
			g.drawString(String.valueOf("Hai perso"), ((GAME_WIDTH/2)-150), (GAME_HEIGHT/2));
			tieni=player1;
		}
	}
}
