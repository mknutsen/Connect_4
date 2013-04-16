
public class Node {
	private Board board;
	private Node[] boards = new Node[7];
	private int score;
	private int color;
	private int steps;
	private boolean deadEnd;
	private int column=-1;
	public Node(int s, int c, Board b){
		board = b;
		steps = s;
		color = c;
		setUp(steps);
	}
	public Node(int s, int c, Board b, int col){
		board = b;
		steps = s;
		column = col;
		color = c;
		setUp(steps);
	}
	private void setUp(int steps){
		score = board.scoreBoard();
		Board temp=new Board();
		boolean temp2;
		for(int i=0;i<7;i++){
			temp.setBoard(board);
			temp2=!temp.dropPiece(color, i);
			//System.out.println(i);
			//temp.printBoard();
			if(column == -1){
				boards[i]=new Node(steps-1,(color*-1),temp, i);
				boards[i].setDeadEnd(temp2);
			}
			else if(steps>0){
				boards[i]=new Node(steps-1,(color*-1),temp, column);
				boards[i].setDeadEnd(temp2);				
			}
		}
		if(steps>0 && ((score<1000 && color==1) ||(color==-1 && score>-1000))){
			score=getNodeScore(0);
			if(color==1){
				for(int i=0;i<boards.length;i++){
					if(!boards[i].isDeadEnd()){
						score=Math.max(score, getNodeScore(i));
					}
				}
			}
			else if(color==-1){
				for(int i=0;i<boards.length;i++){
					if(!boards[i].isDeadEnd()){
						score=Math.min(score, getNodeScore(i));	
					}
				}
			}
		}
		else score = board.scoreBoard();
	}
	public Node getNode(int num){
		return boards[num];
	}
	public int getNodeScore(int num){
		return boards[num].getScore();
	}
	public int getScore() {
		return score;
	}
	public boolean isDeadEnd(){
		return deadEnd;
	}
	public void setDeadEnd(boolean x){
		deadEnd=x;
	}
	public boolean checkDeadEnd(int x){
		return boards[x].isDeadEnd();
	}
	public Board getBoard(){
		return board;
	}
	/*public int evaluate(){
		int max;
		if(steps == 1){
			max=getNodeScore(0);
			for(int i=1;i<7;i++){
				Math.max(max, getNodeScore(i));
			}
		}
		else{
			max=getNode(0).evaluate();
			for(int i=1;i<7;i++){
				Math.max(max,getNode(i).evaluate());
			}
		}
		return max;
	}*/
}
