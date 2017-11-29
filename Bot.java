package tictactoebot;

public class Bot {
    public int makeMove(String[] gs){
        for (int i = 0; i < gs.length; i++) {
            if (gs[i].equals(" ")){
                return i;
            }
        }
        return 1;
    }
}
