package mknutsen.connectfour;

import java.util.Scanner;


public class Project {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		String name1,name2;
		int row, column;
		System.out.println("rows?");
		row = scan.nextInt();
		System.out.println("columns?");
		column = scan.nextInt();
		System.out.println("What is your name, player one?");
		name1 = scan.next();
		System.out.println("and yours, player 2?");
		name2 = scan.next();
		Board x = new Board();
		System.out.println("Alright "+name1+" and "+name2+"\nYou may quit at any time by pressing q\nGood Luck!");
		while(!x.playMove(name1,name2, row, column)){
			
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