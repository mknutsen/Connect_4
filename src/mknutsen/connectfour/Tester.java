package mknutsen.connectfour;

import java.util.Scanner;


public class Tester {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Press enter to play against a human\nor type anything and press enter to play against the AI");
		Board x = new Board();
		Scanner scan = new Scanner(System.in);
		String p = scan.nextLine();
		if(p.equals("")){
			//while(!x.playMove());
		}
		else{
			System.out.println("Pick a difficulty between 1 and 5\n(Note that difficulties above 3 take some time to pick a move)");
			p=scan.nextLine();
			int z=Integer.parseInt(p);
			while(z<0 || z>5) z=Integer.parseInt(scan.nextLine());
			while(!x.playMove(z));
		}
			
		scan.close();
	}
}