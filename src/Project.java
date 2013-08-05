import java.util.Scanner;


public class Project {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String name1,name2;
		System.out.println("What is your name, player one?");
		Scanner scan = new Scanner(System.in);
		name1 = scan.nextLine();
		System.out.println("and yours, player 2?");
		name2 = scan.nextLine();
		Board x = new Board();
		System.out.println("Alright "+name1+" and "+name2+"\nYou may quit at any time by pressing q\nGood Luck!");
		while(!x.playMove(name1,name2)){
			
		}
		scan.close();
	}
}

/*
public boolean playMove(String name1, String name2){
		printBoard();
		System.out.println(name1+". your turn.");
		int move = scan.nextInt();
		if(move == -1) return true;
		while (!dropPiece(-1,move)) move = scan.nextInt();
		printBoard();
		
		if(scoreBoard()<-400){
			System.out.println("Blue wins!");//                                                             REMEMBER TO PUT THIS BACK
			return true;
		}
		System.out.println(name2+", your turn");
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

*/