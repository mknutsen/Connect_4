package mknutsen.connectfour;

public class Piece{
	private int color;
	private int row;
	private int col;
	
	public Piece(int x, int r, int c){
		color = x;
		row = r;
		col = c;
	}
	public Piece(int r, int c){
		color = 0;
		row = r;
		col = c;
	}
	public Piece(){
		color = 0;
		row = -1;
		col = -1;
	}
	public int getColor(boolean x) {return color;}
	public int getRow() {return row;}
	public int getCol() {return col;}
	public String toString(){
		return "("+row+","+col+")";
	}
	public void setCol(int x){col = x;}
	public void setRow(int x){row = x;}
	public void setCoord(int x, int y){
		row = x;
		col = y;
	}
	public void setColor(int x) {color = x;}
	public char getColor(){
		if (color ==-1)
			return 'X';
		if (color == 1)
			return 'O';
		else if(color==2)
			return 'n';
		else
			return ' ';
	}
}
