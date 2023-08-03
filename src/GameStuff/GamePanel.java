package GameStuff;

import java.awt.Color;
import java.awt.Dimension;
//I should research these
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.*;
import java.awt.event.*;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener {

    //First setup window size

    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = ( SCREEN_WIDTH*SCREEN_HEIGHT )/UNIT_SIZE; //Check the size for units object
    static final int DELAY = 75; // Dunno
    static final int x[] = new int[GAME_UNITS]; //Snake size, as it can't outgrow the game.
    
    int bodyParts = 2; //Snake size.
    int applesEaten = 0; //Default is zero.
    int appleX; // Apple's coordinate
    int appleY;
    boolean isRunning = false;
    char direction = 'R'; // Directions mapping: R = right, L = left, U = Up, D = Down
    Timer  timer;
    Random  random;




    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension( SCREEN_WIDTH, SCREEN_HEIGHT ));
        this.setBackground( Color.black );
        this.setFocusable(true);
        this.addKeyListener( new MyKeyAdapter() ); 
        // Just need to call the game 

        startGame();
        
    }

    public void startGame() {
        newApple();
        isRunning = true;
        timer = new Timer(DELAY, this); // not quite sure about the "this" seems to be used to refer to its main method, in this case, the one that makes the game panel.
        timer.start();
    }
    //This "Graphics" stuff is a data type of its own. Ngl got me at first.
    public void paintComponent( Graphics g )  {
        super.paintComponent(g);
        draw(g); 
    }

    public void draw( Graphics g )  {
        //Make grids collumns
        for(int i = 0; i < SCREEN_HEIGHT/UNIT_SIZE ; i++)  {
            g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);   
        }
        

    }

    public void movements() {
        
    }

    public void checkPoints()   {

    }

    public void checkCollisions()   {

    }

    public void newApple()  {

    }

    public void gameOver()  {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    //Inner class

    public class MyKeyAdapter extends KeyAdapter    {
        @Override
        public void keyPressed( KeyEvent e )    {
            
        }
    }

    
}
