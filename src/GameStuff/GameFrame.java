package GameStuff;

//I should research these
import javax.swing.JFrame;


public class GameFrame extends JFrame   {

    GameFrame() {
        
        this.add( new GamePanel() );
        this.setTitle("Snake"); 
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); //Close on close button
        this.setResizable( false ); //Frame is not resizable
        this.pack( );  //Just a pack to manage window size
        this.setVisible( true ); // Shows frame in the screen
        this.setLocationRelativeTo( null ); //Will center the frame

    }
    
}
