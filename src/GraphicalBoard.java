import java.awt.*;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class GraphicalBoard extends JFrame {

	public GraphicalBoard(){
		B charles  = new B();
		charles.setPreferredSize(new Dimension(720,730));
		charles.setFocusable(true);
		charles.requestFocusInWindow();
		add(charles);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700,714);
		setLocationRelativeTo(null);
		setTitle("Connect4");
		setVisible(true);
		//Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage(""), new Point(this.getX(), this.getY()), "");
        //this.setCursor(c);//          THESE LINES SET THE CURSOR TO INVISIBLE
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GraphicalBoard b =new GraphicalBoard();
		b.setVisible(true);
	}

}
