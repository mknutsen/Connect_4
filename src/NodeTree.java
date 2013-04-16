
public class NodeTree {
	private Node root;
	public NodeTree(Board board, int steps){
		root = new Node(steps, 1, board);
	}
	public int bestMove(){
		//System.out.println(s);
		//int x = root.evaluate();
		int max = 0;
		Board temp = new Board();
		
		///*manually check if the ai can win next turn and goes there if it can
		for(int i=0;i<7;i++){ 
			temp.setBoard(root.getBoard());
			temp.dropPiece(1,i);
			if(temp.scoreBoard()>1000){
				System.out.println("I'm gonne win: "+i);
				return i;
			}
		}
		///*manually check if the opponent can win nmext turn and go there to block it
		for(int i=0;i<7;i++){
			temp.setBoard(root.getBoard());
			temp.dropPiece(-1,i);
			if(temp.scoreBoard()<-1000){
				//System.out.println("THEY'RE GONNE WIN: "+i);
				return i;
			}
		}
		//*/
		while(root.checkDeadEnd(max)){
			max++;
		}
		for(int i=1;i<7;i++){
			if(root.getNodeScore(max) < root.getNodeScore(i)&&root.checkDeadEnd(i)==false) max=i;
		}/*
		if(root.getNodeScore(max)<400){
			for(int i=0;i<7;i++){
				temp.setBoard(root.getBoard());
				temp.dropPiece(-1,i);
				if(temp.scoreBoard()<-400){
					return i;
				}
			}
		}*/
		return max;
	}
}
