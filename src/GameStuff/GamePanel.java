package GameStuff;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
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
    static final int y[] = new int[GAME_UNITS];
    
    int bodyParts = 6; //Snake size.
    int applesEaten = 0; //Default is zero.
    int appleX; // Apple's coordinate
    int appleY;
    boolean isRunning = false;
    char direction = 'R'; // Directions mapping: R = right, L = left, U = Up, D = Down
    Timer  timer;
    Random  random;




    GamePanel() {

        random = new Random();
        this.setPreferredSize( new Dimension( SCREEN_WIDTH, SCREEN_HEIGHT ));
        this.setBackground( Color.black );
        this.setFocusable(true );
        this.addKeyListener( new MyKeyAdapter() ); 
        // Just need to call the game 

        startGame();
        
    }

    public void startGame() {
        newApple();
        isRunning = true;
        timer = new Timer( DELAY, this ); // not quite sure about the "this" seems to be used to refer to its main method, in this case, the one that makes the game panel.
        timer.start();
    }
    //This "Graphics" stuff is a data type of its own. Ngl got me at first.
    public void paintComponent( Graphics g )  {
        super.paintComponent( g );
        draw( g ); 
    }

    public void draw( Graphics g )  {
        if(isRunning)   {
                //Make grids collumns
                /*
            for( int i = 0; i < SCREEN_HEIGHT/UNIT_SIZE ; i++ )  {
                //Explain these lines to yourself later. It is a grid, but try to understand how graphics are made.
                
                g.drawLine( i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT ); 
                g.drawLine( 0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE );  
                g.setColor( Color.green );
            }
            */
            g.setColor(Color.orange);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
            //snake parts

            for( int i = 0; i < bodyParts; i++ )    {
                if(i == 0)  {
                    g.setColor( Color.green );
                    g.fillRect( x[i], y[i], UNIT_SIZE, UNIT_SIZE );
                }else   {
                    g.setColor( new Color(45,100,0) );
                    g.fillRect( x[i], y[i], UNIT_SIZE, UNIT_SIZE );
                }
            }
            g.setColor(Color.white);
            g.setFont(new Font("Ink Free", Font.ITALIC, 75));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Points" + " " + applesEaten*100, (SCREEN_WIDTH - metrics.stringWidth("Points" + applesEaten*10))/2, g.getFont().getSize());

        }
        else{
            gameOver(g);
        }
        

    }

    public void movements() {
        // Create the entire snakes
        for( int i = bodyParts; i > 0; i-- )  {
            
            x[i] = x[i - 1];
            y[i] = y[i - 1];

        }
        switch( direction ) {
            case 'U' :
                y[0] = y[0] - UNIT_SIZE; // snake's head is equal snake's head next position
                break;
            case 'D' :
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'R' :
                x[0] = x[0] + UNIT_SIZE;
                break;
            case 'L' :
                x[0] = x[0] - UNIT_SIZE;
                break;
        } 
    }

    public void checkPoints()   {
        if((x[0] == appleX) && (y[0] == appleY))    {
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }

    public void checkCollisions()   {
        // check if head collides with border
        for(int i = bodyParts; i > 0; i--)  {
            if( (x[0] == x[i]) && (y[0] == y[i]) )  {
                
                isRunning = false;
            }
        }
        //check left border
        if( x[0] < 0 )  {
            isRunning = false;
        }
        // right
        if( x[0] > SCREEN_WIDTH)    {
            isRunning = false;
        }
        // top
         if( y[0] < 0 )  {
            isRunning = false;
        }
        // bottom
        if( y[0] > SCREEN_HEIGHT)    {
            isRunning = false;
        }
        if(!isRunning)  {
            timer.stop();
        }
    }

    public void newApple()  {
        //What purpose does it serve?
            appleX = random.nextInt( ( int )( SCREEN_WIDTH / UNIT_SIZE )) * UNIT_SIZE;
            appleY = random.nextInt( ( int )( SCREEN_HEIGHT / UNIT_SIZE )) * UNIT_SIZE;
       
    }

    public void gameOver(Graphics g)  {
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.ITALIC, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Too Bad" + " " + applesEaten * 10, (SCREEN_WIDTH - metrics.stringWidth("Too Bad"))/3, SCREEN_HEIGHT/2);
    }

    @Override
    public void actionPerformed( ActionEvent e ) {
        
        if(isRunning) {
            movements(); 
            checkPoints();
            checkCollisions();
        }
        repaint();
    }

    //Inner class

    public class MyKeyAdapter extends KeyAdapter    {
        @Override
        public void keyPressed( KeyEvent e )    {
            //the snake will have to turn when going to the oposite direction, else the game will break.
            switch(e.getKeyCode())  {
                case KeyEvent.VK_LEFT:
                    if(direction != 'R')    {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L')    {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction != 'D')    {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U')    {
                        direction = 'D';
                    }
            }
        }
    }

    
}
