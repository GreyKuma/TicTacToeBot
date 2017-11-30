package tictactoebot;
import java.util.*;

public class Bot {

    private Map<String, Bag> map = new HashMap<String, Bag>();
    private List playPath = new LinkedList();

    public void resetPath(){
        playPath.clear();
        //todo, rewards
    }

    private String getStateString(String[] gs){
        String gstring = "";
        for (String state :gs) {
            gstring += state;
        }
        return gstring;
    }

    public int makeMove(String[] gs){
        String gstring = getStateString(gs);

        //smart algorithm
        if (!map.containsKey(gstring)) {
            map.put(gstring, new Bag<Integer>());
        }
        for (int i=0; i < gs.length; i++) {
            if(gs[i].equals(" ")){
                map.get(gstring).add(i);
            }
        }
        return findMove(gs);
    }

    private Integer findMove(String[] gs){
        return (Integer)map.get(getStateString(gs)).pullRandom();
    }

}
