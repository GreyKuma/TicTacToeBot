package tictactoebot;

import java.util.Scanner;

public class TicTacToe {
	// constructor
	// todo make playing field bigger.
	public TicTacToe() {

	}

	// global variables
	Scanner scan = new Scanner(System.in);
	private String[] gs = {" ", " ", " ", " ", " ", " ", " ", " ", " "};		//gamestate

	public String[] getGs() {
		return gs;
	}

	private boolean curPlayer = true;

	public void play(){
		while (true){
			System.out.println("Press 1 to 9 on the numpad for the corresponding space");
			int input = scan.nextInt();
			System.out.println();
			if (input == 0){
				break;
			}else{
				if(gs[input-1].equals("X") || gs[input-1].equals("O")){
					System.out.println("invalid input\n");
				}else{
					if(curPlayer){
						makeMove(input-1, "X");
					}else{
						makeMove(input-1, "O");
					}
					if (checkWinCondition()) {
						if(curPlayer){
							System.out.println("X has won!\n");

						}else{
							System.out.println("O has won!\n");
						}
						printboard();
						break;
					}
					curPlayer = !curPlayer;
				}
			}
			printboard();
		}
		scan.close();
	}

	public boolean checkWinCondition(){
		boolean win = false;
		for(int i = 0; i < 3; i++){
			win =	win ||
					((gs[i]+gs[i+3]+gs[i+6]).equals("XXX")) ||
					((gs[i]+gs[i+3]+gs[i+6]).equals("OOO")) ||
					((gs[3*i]+gs[3*i+1]+gs[3*i+2]).equals("XXX")) ||
					((gs[3*i]+gs[3*i+1]+gs[3*i+2]).equals("OOO"));

		}
		win = 	win ||
				((gs[0]+gs[4]+gs[8]).equals("XXX")) ||
				((gs[0]+gs[4]+gs[8]).equals("OOO")) ||
				((gs[2]+gs[4]+gs[6]).equals("XXX")) ||
				((gs[2]+gs[4]+gs[6]).equals("OOO"));
		return win;
	}

	public void printboard(){
		String spielfeld =
						  "_"+gs[6]+"_|_"+gs[7]+"_|_"+gs[8]+"_\n"
						+ "_"+gs[3]+"_|_"+gs[4]+"_|_"+gs[5]+"_\n"
						+ " "+gs[0]+" | "+gs[1]+" | "+gs[2]+" \n";
		System.out.println(spielfeld);
	}


	public boolean makeMove(int field, String player) {
		// field contains a number from 0-8, corresponding to the field, numpad numbers - 1
		// player is either X or O
		if(gs[field].equals(" ")){
			gs[field] = player;
			return true;
		}
		return false;
	}
}
