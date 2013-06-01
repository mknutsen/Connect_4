import java.util.ArrayList;

public class BoardScore {
	ArrayList<SmallBoard> vertical;
	ArrayList<SmallBoard> horizontal;
	ArrayList<SmallBoard> diagonal;
	private final int four =100000;
	private final int three = 50;
	private final int two = 1;
	public BoardScore(int color){
		vertical = new ArrayList<SmallBoard>();
		horizontal = new ArrayList<SmallBoard>();
		diagonal = new ArrayList<SmallBoard>();
		setUp(color);
	}
	private void setUp(int color){
		setHorizontal(color);
		setVertical(color);
		setDiag(color);		
	}
	private void setVertical(int color){
		Piece[][] temp = generateArray(4,1,color);
		vertical.add(new SmallBoard(temp,four));
		for(int i=0;i<4;i++){
			vertical.add(new SmallBoard(generateVertical(color, i),three));
		}
		vertical.add(new SmallBoard(generateVertical(color, 0,1),two));
		vertical.add(new SmallBoard(generateVertical(color, 0,2),two));
		vertical.add(new SmallBoard(generateVertical(color, 0,3),two));
		vertical.add(new SmallBoard(generateVertical(color, 1,2),two));
		vertical.add(new SmallBoard(generateVertical(color, 1,3),two));
		vertical.add(new SmallBoard(generateVertical(color, 3,2),two));
	}
	private void setHorizontal(int color){
		Piece[][] temp = generateArray(1,4,color);
		//printBoard(temp);
		horizontal.add(new SmallBoard(temp,four));
		for(int i=0;i<4;i++){
			horizontal.add(new SmallBoard(generateHorizontal(color, i),three));
		}
		horizontal.add(new SmallBoard(generateHorizontal(color, 0,1),two));
		horizontal.add(new SmallBoard(generateHorizontal(color, 0,2),two));
		horizontal.add(new SmallBoard(generateHorizontal(color, 0,3),two));
		horizontal.add(new SmallBoard(generateHorizontal(color, 1,2),two));
		horizontal.add(new SmallBoard(generateHorizontal(color, 1,3),two));
		horizontal.add(new SmallBoard(generateHorizontal(color, 3,2),two));
	}
	private void setDiag(int color){
		Piece[][] temp = generateDiag(color, 1);
		diagonal.add(new SmallBoard(temp,four));
		for(int i=0;i<4;i++){
			diagonal.add(new SmallBoard(generateDiag(color, 1, i),three));
		}
		diagonal.add(new SmallBoard(generateDiag(color,1,0,1),two));
		diagonal.add(new SmallBoard(generateDiag(color,1,0,2),two));
		diagonal.add(new SmallBoard(generateDiag(color,1,0,3),two));
		diagonal.add(new SmallBoard(generateDiag(color,1,1,2),two));
		diagonal.add(new SmallBoard(generateDiag(color,1,1,3),two));
		diagonal.add(new SmallBoard(generateDiag(color,1,3,2),two));
		
		Piece[][] tem2 = generateDiag(color,3);
		//printBoard(tem2);
		diagonal.add(new SmallBoard(tem2,four));
		for(int i=0;i<4;i++){
			diagonal.add(new SmallBoard(generateDiag(color, 3, i),three));
		}
		diagonal.add(new SmallBoard(generateDiag(color,3,0,1),two));
		diagonal.add(new SmallBoard(generateDiag(color,3,0,2),two));
		diagonal.add(new SmallBoard(generateDiag(color,3,0,3),two));
		diagonal.add(new SmallBoard(generateDiag(color,3,1,2),two));
		diagonal.add(new SmallBoard(generateDiag(color,3,1,3),two));
		diagonal.add(new SmallBoard(generateDiag(color,3,3,2),two));
	}
	public void printBoard(){
		System.out.println();
		for(int i=0;i<vertical.size();i++){
			for(int y=vertical.get(i).getBoard().length-1;y>=0;y--){
				for(int x=0;x<vertical.get(i).getBoard()[0].length;x++){
					System.out.print("["+vertical.get(i).getBoard()[y][x].getColor()+"] ");
				}
				System.out.println();
			}
			System.out.println();
		}
	}
	public void printBoard(Piece[][] z){
		System.out.println();
			for(int y=0;y<z.length;y++){
				for(int x=0;x<z[0].length;x++){
					System.out.print("["+z[y][x].getColor()+"] ");
				}
				System.out.println();
			}
			System.out.println();
	}
	public void printBoard(SmallBoard purple){
		Piece[][] z = purple.getBoard();
		System.out.println();
			for(int y=0;y<z.length;y++){
				for(int x=0;x<z[0].length;x++){
					System.out.print("["+z[y][x].getColor()+"] ");
				}
				System.out.println();
			}
			System.out.println();
	}
	public void printBoard(ArrayList<SmallBoard> x){
		for(int z=0;z<x.size();z++){
			printBoard(x.get(z).getBoard());
			System.out.println();
		}
	}
	public Piece[][] generateArray(int x, int y, int col){
		Piece[][] array = new Piece[x][y];
		for(int i=0;i<x;i++){
			for(int j=0;j<y;j++){
				array[i][j]= new Piece();
				array[i][j].setColor(col);
			}
		}
		return array;
	}
	public Piece[][] generateVertical(int col,int num){//num is the location of the pieces that are to be left blank or 0
		int x=4;
		int y=1;
		Piece[][] array = new Piece[x][y];
		for(int i=0;i<x;i++){
			for(int j=0;j<y;j++){
				array[i][j]= new Piece();
				if(num!=i)
					array[i][j].setColor(col);
			}
		}
		return array;
	}
	public Piece[][] generateVertical(int col,int num,int num2){//num is the location of the pieces that are to be left blank or 0
		int x=4;
		int y=1;
		Piece[][] array = new Piece[x][y];
		for(int i=0;i<x;i++){
			for(int j=0;j<y;j++){
				array[i][j]= new Piece();
				if(num!=i && num2!=i)
					array[i][j].setColor(col);
			}
		}
		return array;
	}
	public Piece[][] generateHorizontal(int col,int num){//num is the location of the pieces that are to be left blank or 0
		int x=1;
		int y=4;
		Piece[][] array = new Piece[x][y];
		for(int i=0;i<x;i++){
			for(int j=0;j<y;j++){
				array[i][j]= new Piece();
				if(num!=j)
					array[i][j].setColor(col);
			}
		}
		return array;
	}
	public Piece[][] generateHorizontal(int col,int num,int num2){ //num is the location of the pieces that are to be left blank or 0
		int x=1;
		int y=4;
		Piece[][] array = new Piece[x][y];
		for(int i=0;i<x;i++){
			for(int j=0;j<y;j++){
				array[i][j]= new Piece();
				if(num!=j && num2!=j)
					array[i][j].setColor(col);
			}
		}
		return array;
	}
	public Piece[][] generateDiag(int col,int dir){
		Piece[][] array = new Piece[4][4];
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				array[i][j]=new Piece();
				array[i][j].setColor(2);
			}
		}
		if(dir==1 || dir==9){
			array[3][0].setColor(col);
			array[2][1].setColor(col);
			array[1][2].setColor(col);
			array[0][3].setColor(col);
		}
		else{
			array[0][0].setColor(col);
			array[1][1].setColor(col);
			array[2][2].setColor(col);
			array[3][3].setColor(col);
		}
		return array;
	}
	public Piece[][] generateDiag(int col,int dir,int num){
		Piece[][] array = new Piece[4][4];
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				array[i][j]=new Piece();
				array[i][j].setColor(2);
			}
		}
		if(dir==1 || dir==9){
				array[3][0].setColor(col);
				array[2][1].setColor(col);
				array[1][2].setColor(col);
				array[0][3].setColor(col);
				array[num][3-num].setColor(0);
		}
		else{
				array[3][3].setColor(col);
				array[2][2].setColor(col);
				array[1][1].setColor(col);
				array[0][0].setColor(col);
				array[num][num].setColor(0);
		}
		
		return array;
	}
	public Piece[][] generateDiag(int col,int dir,int num,int num1){
		Piece[][] array = new Piece[4][4];
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				array[i][j]=new Piece();
				array[i][j].setColor(2);
			}
		}
		if(dir==1 || dir==9){
				array[3][0].setColor(col);
				array[2][1].setColor(col);
				array[1][2].setColor(col);
				array[0][3].setColor(col);
				array[num][3-num].setColor(0);
				array[num1][3-num1].setColor(0);
		}
		else{
				array[3][3].setColor(col);
				array[2][2].setColor(col);
				array[1][1].setColor(col);
				array[0][0].setColor(col);
				array[num][num].setColor(0);
				array[num1][num1].setColor(0);
		}
		
		return array;
	}
	public SmallBoard cutPiece(int r1, int c1, int r2, int c2, Piece[][] board){
		Piece[][] piece = new Piece[(r2-r1)+1][(c2-c1)+1];
		for(int x=0;x<=(r2-r1);x++){
			for(int y=0;y<=(c2-c1);y++){
				piece[x][y] = board[r1+x][c1+y];
			}
		}
		return new SmallBoard(piece);
	}
	public int scoreSection(SmallBoard b){
		int total = 0;
		Piece[][] board = b.getBoard();
		SmallBoard temp;
		for(int i=0;i<4;i++){
			temp=cutPiece(0,i,3,i,board);
			for(int j=0;j<vertical.size();j++){
				if(temp.compareTo(vertical.get(j))==1){
					total=total+vertical.get(j).getScore();
				}
			}
			temp=cutPiece(i,0,i,3,board);
			for(int j=0;j<horizontal.size();j++){
				if(temp.compareTo(horizontal.get(j))==1){
					total=total+horizontal.get(j).getScore();
				}
			}
		}
		for(int i=0;i<diagonal.size();i++){
			if(diagonal.get(i).compareTo(b)==1){
				total=total+diagonal.get(i).getScore();
			}
		}
		return total;
	}
	public int scoreVertical(SmallBoard b){
		int total=0;
		Piece[][] board = b.getBoard();
		SmallBoard temp;
		for(int i=0;i<board.length-3;i++){
			//temp=cutPiece(0,i,3,i,board);
			temp=cutPiece(i,0,i+3,0,board);
			//printBoard(temp);
			for(int j=0;j<vertical.size();j++){
				if(temp.compareTo(vertical.get(j))==1){
					total=total+vertical.get(j).getScore();
				}
			}
		}
		return total;
	}
	public int scoreHorizontal(SmallBoard b){
		int total=0;
		Piece[][] board = b.getBoard();
		SmallBoard temp;
		for(int i=0;i<board[0].length-3;i++){
			temp=cutPiece(0,i,0,i+3,board);
			for(int j=0;j<horizontal.size();j++){
				if(temp.compareTo(horizontal.get(j))==1){
					total=total+horizontal.get(j).getScore();
				}
			}
		}
		return total;
	}
	public int scoreDiagonal(SmallBoard b){
		int total=0;
		//System.out.println(b.getBoard().length);
		for(int i=0;i<diagonal.size();i++){
			if(diagonal.get(i).compareTo(b)==1){
				total=total+diagonal.get(i).getScore();
				//printBoard(b);
			}
		}
		return total;
	}
}
