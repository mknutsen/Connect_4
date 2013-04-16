
public class SmallBoard implements Comparable<SmallBoard>{
	private int score;
	private Piece[][] board;
	public SmallBoard(Piece[][] x){
		score = -1;
		board=x;
	}
	public SmallBoard(Piece[][] x, int s){
		score = s;
		board = x;
	}
	public void setScore(int x){
		score = x;
	}
	public void setBoard(Piece[][] x){
		board = x;
	}
	public void setPieceColor(int x, int y, int color){
		board[x][y].setColor(color);
	}
	public void setStuff(Piece[][] x, int y){
		board = x;
		score = y;
	}
	public int getScore(){
		return score;
	}
	public Piece[][] getBoard(){
		return board;
	}
	public int compareTo(SmallBoard x){
		Piece[][] temp = x.getBoard();
		if(temp.length != board.length || temp[0].length != board[0].length){
			return 0;
		}
		else{
			for(int i=0;i<board.length;i++){
				for(int j=0;j<board[0].length;j++){
					if(temp[i][j].getColor(true)!=board[i][j].getColor(true) && temp[i][j].getColor(true)!=2 && board[i][j].getColor(true)!=2){
							return 0;
						}
					}
				}
			}
		return 1;
	}
	
	public void printBoard(){
		System.out.println();
		for(int y=board.length-1;y>=0;y--){
			for(int x=0;x<board[0].length;x++){
				System.out.print("["+board[y][x].getColor()+"] ");
			}
			System.out.println();
		}
	}
	
}
