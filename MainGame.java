package tictactoebot;

import java.util.Scanner;
public class MainGame {
	static Scanner scan = new Scanner(System.in);
	static Bot bot1 = new Bot();
	static Bot bot2 = new Bot();
	static int x, o = 0;
	
	public static void main(String[] args) {
		for(int i = 0; i < 10; i++){
			System.out.println("---------------Round " + i + "-----------------");
			playRound();
		}
		System.out.println("Wins X:O " + x + ":" + o);
	}

	static void playRound(){
		TicTacToe game = new TicTacToe();
		String curPlay = "X";
		int moveCount = 0;
		while(moveCount < 9){
			moveCount++;
			int move;
			if (curPlay.equals("X")){
				move = bot1.makeMove(game.getGs());
				System.out.println(move+1);
			}else{
				move = bot2.makeMove(game.getGs());
				System.out.println(move+1);
			}
			game.makeMove(move,curPlay);

			game.printboard();
			if (game.checkWinCondition()){
				if(curPlay.equals("X")){
					x++;
				}else{
					o++;
				}
				System.out.println(curPlay + " has won!");
				break;
			}
			if(curPlay.equals("X")){
				curPlay = "O";
			}else{
				curPlay = "X";
			}
		}
		if(moveCount == 9){
			System.out.println("Draw!");

		}
	}
}
