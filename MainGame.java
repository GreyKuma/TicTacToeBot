package tictactoebot;

import java.util.Scanner;
public class MainGame {
	static Scanner scan = new Scanner(System.in);
	static Bot bot = new Bot();
	
	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		String curPlay = "X";
		while(true){
			if (curPlay.equals("X")){
				game.makeMove(bot.makeMove(game.getGs()),"X");
			}else{
				int input = scan.nextInt();
				if (input == 0){
					break;
				}
				game.makeMove(input-1, curPlay);
			}



			game.printboard();
			if (game.checkWinCondition()){
				System.out.println(curPlay + " has won!");
				break;
			}
			if(curPlay.equals("X")){
				curPlay = "O";
			}else{
				curPlay = "X";
			}
		}
	}
}
