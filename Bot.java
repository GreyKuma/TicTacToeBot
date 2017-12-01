package tictactoebot;
import java.util.*;

public class Bot {
    /*
    Primitive Bot that uses a simple reward and punishment System
    to find an optimal strategy for a given problem,
    in this case TicTacToe.
     */
    // todo: Somehow save the "Brain" so Bot doesn't need to be trained every time.

    private Map<String, Bag> map = new HashMap<String, Bag>();      //Saving all the possible moves and their "next move bags".
    private List<Map.Entry<String,Integer>> playPath = new LinkedList<Map.Entry<String,Integer>>();       //To save the taken path for later adjustment of occurence percentages.

    public void reward(){
        for(Map.Entry<String,Integer> node: playPath){
            map.get(node.getKey()).add(node.getValue());
        }
        clearPath();
    }

    public void punish(){
        for (Map.Entry<String,Integer> node: playPath){
            map.get(node.getKey()).remove(node.getValue());
        }
        clearPath();
    }

    public void clearPath(){
        playPath.clear();
    }

    private String getStateString(String[] gs){
        /*
        Helper Function to turn the String Array gs
        into a hashable String.
         */
        String gstring = "";
        for (String state :gs) {
            gstring += state;
        }
        return gstring;
    }

    public int makeMove(String[] gs){
        /*
        Returns the Field number of the move the bot wants to do.
         */
        String gstring = getStateString(gs);

        //smart algorithm
        if (!map.containsKey(gstring)) {
            map.put(gstring, new Bag<Integer>());
            for (int i = 0; i < gs.length; i++) {
                if (gs[i].equals(" ")) {
                    map.get(gstring).add(i);
                }
            }
        }
        Integer move = findMove(gs);
        playPath.add(new AbstractMap.SimpleEntry(gstring,move));
        return move;
    }

    private Integer findMove(String[] gs){
        /*
        Helper Function to find the next move for a given gamestate gs.
         */
        return (Integer)map.get(getStateString(gs)).pullRandom();
    }

}
