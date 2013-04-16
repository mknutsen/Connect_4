import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class Board {
	private Scanner scan = new Scanner(System.in);
	private BoardScore redScorer;
	private BoardScore blueScorer;
	private NodeTree x;
	private int winner;
	public int total=0;
	private final int row = 6;
	private final int col = 7;
	public ArrayList<Piece> moves;
	Piece[][] board;
	public Board(){
		winner = 0;
		moves = new ArrayList<Piece>();
		redScorer=new BoardScore(1);
		blueScorer= new BoardScore(-1);
		//redScorer.printBoard(redScorer.diagonal);
		board=new Piece[row][col];
		for(int i=0; i<row; i++){
			for(int j=0;j<col;j++){
				board[i][j] = new Piece();
			}
		}
	}
	public int getWinner(){
		return winner;
	}
	public boolean playMove(int difficulty){ // returns true if the player wants to end the game
		printBoard();
		int move = scan.nextInt();
		//System.out.println(move);
		if(move == -1) return true;
		while (!dropPiece(-1,move)){
			move = scan.nextInt();
		}
		if(scoreBoard()<-400){
			System.out.println("Player wins!");
			printBoard();
			return true;
		}
		//System.out.println("this");
		x = new NodeTree(this, difficulty);
		dropPiece(1,x.bestMove());
		if(scoreBoard()>400){
			System.out.println("Francis wins!");
			printBoard();
			return true;
		}
		return false;
	}
	public boolean playMove(){
		printBoard();
		System.out.println("Currrent score: "+scoreBoard());
		int move = scan.nextInt();
		if(move == -1) return true;
		while (!dropPiece(-1,move)) move = scan.nextInt();
		printBoard();
		
		if(scoreBoard()<-400){
			System.out.println("Blue wins!");//                                                             REMEMBER TO PUT THIS BACK
			return true;
		}
		System.out.println("Currrent score: "+scoreBoard());
		move = scan.nextInt();
		if(move == -1) return true;
		while (!dropPiece(1,move)) move = scan.nextInt();
		if(scoreBoard()>400){
			System.out.println("Red wins!");//                                                             REMEMBER TO PUT THIS BACK
			printBoard();
			return true;
		}
		return false;
	}
	public boolean playMove(int move,int difficulty){
		dropPiece(-1,move);
		if(scoreBoard()<-400){
			winner = -1;
			return true;
		}
		total++;
		//System.out.println("this");
		x = new NodeTree(this, difficulty);
		dropPiece(1,x.bestMove());
		if(scoreBoard()>400){
			winner = 1;
			return true;
		}
		total++;
		return false;	
	}
	public boolean playMove(int move, boolean turn){
		if(turn){
			dropPiece(1,move);
			if(scoreBoard()>400){
				winner = 1;
				return true;
			}
		}
		else{
			dropPiece(-1,move);
			if(scoreBoard()<-400){
				winner = -1;
				return true;
			}
		}
		return false;
	}
	public boolean dropPiece(int colorr, int column){
		if(column>col || column < 0) return false;
		else{
			for(int i = 0;i<row;i++){
				if(board[i][column].getColor(true)==0){
					//recent = new Piece(colorr, i, column);
					board[i][column] = new Piece(colorr,i,column);
					moves.add(new Piece(colorr,i,column));
					return true;
				}
			}
		}
		return false;
	}
	/*public int scoreBoard(){
		int red = 0;
		int blue = 0;
		for(int i=0;i<row-4;i++){
			for(int j=0;j<col-4;j++){
				System.out.println("red");
				red=red+redScorer.scoreSection(cutPiece(i,j,i+4,j+4));
				System.out.println("blue");
				blue=blue+blueScorer.scoreSection(cutPiece(i,j,i+4,j+4));
				System.out.println("SWITCH");
			}
		}
		return red - blue;
	}*/
	public int scoreBoard(){
		int red = 0;
		int blue = 0;
		for(int i=0;i<col;i++){
			red=red+redScorer.scoreVertical(cutPiece(0,i,row-1,i));
			blue=blue+blueScorer.scoreVertical(cutPiece(0,i,row-1,i));
		}
		for(int i=0;i<row;i++){
			red=red+redScorer.scoreHorizontal(cutPiece(i,0,i,col-1));
			blue=blue+blueScorer.scoreHorizontal(cutPiece(i,0,i,col-1));
		}
		for(int i=0;i<row-3;i++){
			//System.out.println("This:"+i);
			for(int j=0;j<col-3;j++){
				red=red+redScorer.scoreDiagonal(cutPiece(i,j,i+3,j+3));
				blue=blue+blueScorer.scoreDiagonal(cutPiece(i,j,i+3,j+3));
			}
		}
		return red - blue;
	}
	public SmallBoard cutPiece(int r1, int c1, int r2, int c2){
		Piece[][] piece = new Piece[(r2-r1)+1][(c2-c1)+1];
		for(int x=0;x<=(r2-r1);x++){
			for(int y=0;y<=(c2-c1);y++){
				piece[x][y] = board[r1+x][c1+y];
			}
		}
		return new SmallBoard(piece);
	}
	public void setBoard(Board b){
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				board[i][j].setColor(b.board[i][j].getColor(true));
			}
		}
	}
	public void printBoard(){
		System.out.println();
		for(int y=row-1;y>=0;y--){
			for(int x=0;x<col;x++){
				/*if(board[y][x].equals(recent))
					System.out.print("{"+board[y][x].getColor()+"} ");*/
				//else
					System.out.print("["+board[y][x].getColor()+"] ");
			}
			System.out.println();
		}
		for(int i=0;i<col;i++){
			System.out.print(" "+i+"  ");
		}
		System.out.println();
	}
	public void printBoard(File file,int win) throws IOException{
		
		FileWriter writer = null;
		writer = new FileWriter(file,true);
		if(win==1){
			writer.write("AI won\n");
		}
		else if(win==-1){
			writer.write("Human won\n");
		}
		else{
			writer.write("Tie game, nobody won\n");
		}
		writer.write("board score was: "+scoreBoard()+"\n");
		writer.write("\n");
		for(int y=row-1;y>=0;y--){
			for(int x=0;x<col;x++){
				/*if(board[y][x].equals(recent))
					writer.print("{"+board[y][x].getColor()+"} ");*/
				//else
					writer.write("["+board[y][x].getColor()+"] ");
			}
			writer.write("\n");
		}
		for(int i=0;i<col;i++){
			writer.write(" "+i+"  ");
		}
		writer.write("\n");
		writer.close();
	}
	public void undo(Piece m){
		moves.remove(m);
		board[m.getRow()][m.getCol()].setColor(0);
	}
	public void undo(Piece move1, Piece move2) {
		// TODO Auto-generated method stub
		undo(move1);
		undo(move2);
	}
	public boolean catsCheck(){
		for(int i =0;i<7;i++){
			if(board[row-1][i].getColor(true)==0){
				return false;
			}
		}
		return true;
	}
	public void setWinner(int i) {
		winner = i;
		
	}
}
