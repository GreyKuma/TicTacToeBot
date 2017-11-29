package tictactoebot;

import java.util.Scanner;

public class TicTacToe {
	public TicTacToe() {
		
	}
	public void play(){
		Scanner scan = new Scanner(System.in);
		int input = -1;
		String[] gamestate = {" ", " ", " ","_", "_", "_", "_", "_", "_"};
		boolean x = true;
		while (true){
			System.out.println("Press 1 to 9 on the numpad for the corresponding space");
			input = scan.nextInt();
			System.out.println();
			if (input == 0){
				break;
			}else{
				if(gamestate[input-1] == "X" || gamestate[input-1] == "O"){
					System.out.println("invalid input\n");
				}else{
					if(x){
						gamestate[input-1] = "X";
					}else{
						gamestate[input-1] = "O";
					}
					if (checkWinCondition(gamestate)) {
						if(x){
							System.out.println("X has won!\n");
							
						}else{
							System.out.println("O has won!\n");
						}
						printgame(gamestate);
						break;
					}
					x = !x;
				}
			}
			printgame(gamestate);
		}
		scan.close();
	}
	
	public static boolean checkWinCondition(String[] gs){
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
	public static void printgame(String[] gs){
		String spielfeld = 
				  "_"+gs[6]+"_|_"+gs[7]+"_|_"+gs[8]+"_\n"
				+ "_"+gs[3]+"_|_"+gs[4]+"_|_"+gs[5]+"_\n"
				+ " "+gs[0]+" | "+gs[1]+" | "+gs[2]+" \n";
		System.out.println(spielfeld);
	}
	
	
}
