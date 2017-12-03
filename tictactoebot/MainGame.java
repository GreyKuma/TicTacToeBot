package tictactoebot;

import java.util.Scanner;
import java.util.Random;

public class MainGame {
	static Scanner scan = new Scanner(System.in);
	static Bot bot1 = new Bot();	// Player X
	static Bot bot2 = new Bot();	// Player O
	static int x, o = 0;
	
	public static void main(String[] args) {

		Random rnd = new Random();
		System.out.println("How many games should the bot train?");
		int trainingssize = scan.nextInt();
		System.out.println("How many games do you want to play against the trained bot?");
		int fightsize = scan.nextInt();
		long startTime = System.nanoTime();
		for(int i = 0; i < trainingssize; i++){
			if(i%100000 == 0){
				System.out.println(i);
			}
//			System.out.println("---------------Round " + i + "-----------------");
			playRound();
//			System.out.println("Wins X:O " + x + ":" + o);
		}
		long endTime = System.nanoTime();
		double durationms = (endTime-startTime)/1000000.0;
		double durations = durationms /1000.0;
		double durationm = durations / 60.0;
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");

		System.out.println("ms: " + df.format(durationms) + ", s: " + df.format(durations) + ", min: " + df.format(durationm));
		System.out.println("ca. " + df.format(durationms/(double)trainingssize) + "ms/game");
		System.out.println("ca. " + df.format(trainingssize/durations) + "games/s");
		x = 0;
		o = 0;
		for(int i=0; i< fightsize; i++){
			System.out.println("---------------Round " + i + "-----------------");

			playAgainstBot(rnd.nextInt(2));
			System.out.println("Wins X:O " + x + ":" + o);
		}
		if(x > o){
			System.out.println("The Machines always win!");
		}else if(x == 0){
			System.out.println("Draw -.-");
		}else{
			System.out.println("Human wins... For now...");
		}

		System.out.println("DEBUG");
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
			}else{
				move = bot2.makeMove(game.getGs());
			}
//			System.out.println(move+1);
			game.makeMove(move,curPlay);

//			game.printboard();
			if (game.checkWinCondition()){
				if(curPlay.equals("X")){
					bot1.reward();
					bot2.punish();
					x++;
				}else{
					bot1.punish();
					bot2.reward();
					o++;
				}
//				System.out.println(curPlay + " has won!");
				break;
			}
			if(curPlay.equals("X")){
				curPlay = "O";
			}else{
				curPlay = "X";
			}
		}
		if(moveCount == 9){
//			System.out.println("Draw!");

		}
	}

	static void playAgainstBot(int botnr){
		System.out.println("Playing against Bot " + botnr);
		TicTacToe game = new TicTacToe();
		Bot bot;
		String curPlay;

		int moveCount = 0;
		if(botnr == 0){
			curPlay = "X";
			bot = bot1;
		}else{
			curPlay = "O";
			bot = bot2;
		}
		game.printboard();

		while(moveCount < 9){
			moveCount++;
			int move;
			if (curPlay.equals("X")){
				move = bot.makeMove(game.getGs());
				System.out.println(move+1);
				game.makeMove(move,curPlay);
			}else{
				move = scan.nextInt()-1;
				while(!game.makeMove(move,curPlay)){
					System.out.println("Please enter one of the following numbers:");
					String possibleFields = "";
					for(int i=0; i<game.getGs().length; i++){
						if(game.getGs()[i].equals(" ")){
							possibleFields += (i+1) + ", ";
						}
					}
					System.out.println(possibleFields);
					move = scan.nextInt()-1;
				}
			}
			game.printboard();

			if (game.checkWinCondition()){
				if(curPlay.equals("X")){
					bot.reward();
					if(botnr == 0){

						x++;
					}else{
						o++;
					}
				}else{
					bot.punish();
					if(botnr == 1){
						o++;
					}else {
						x++;
					}
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
