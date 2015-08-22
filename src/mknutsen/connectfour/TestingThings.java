package mknutsen.connectfour;

public class TestingThings {
	public static void main(String[] args){
		Board x = new Board();
		for(int j=0;j<4;j++){
			x.board[5-j][j+3].setColor(-1);
		}
		x.printBoard();
		System.out.println(x.scoreBoard());
		
		x = new Board();
		for(int j=0;j<4;j++){
			x.board[5-j][j].setColor(1);
		}
		x.printBoard();
		System.out.println(x.scoreBoard());
		
		x = new Board();
		for(int j=1;j<5;j++){
			x.board[j+1][j].setColor(-1);
		}
		x.printBoard();
		System.out.println(x.scoreBoard());
		x = new Board();
		for(int j=1;j<5;j++){
			x.board[j+1][j].setColor(1);
		}
		x.printBoard();
		System.out.println(x.scoreBoard());
		/*BoardScore purple = new BoardScore(1);
		purple.printBoard(purple.diagonal);*/
	}
}
