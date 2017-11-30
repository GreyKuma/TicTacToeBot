package tictactoebot;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Bag<T> {
    Map<T, Integer> bag = new HashMap<T,Integer>();
    public Bag(){

    }

    public void add(T t){
        if(bag.containsKey(t))
            bag.replace(t, bag.get(t)+1);
        else
            bag.put(t, 1);
    }

    public boolean remove(T t){
        if(bag.containsKey(t) && bag.get(t) > 1){
            return bag.replace(t, bag.get(t), bag.get(t)-1);
        }else{
            return bag.remove(t,1);
        }
    }

    public T pullRandom(){
        Random rnd = new Random();
        int n = rnd.nextInt(getTotal());
        for(Map.Entry<T,Integer> e: bag.entrySet()){
            n -= e.getValue();
            if(n<0){
                return e.getKey();
            }
        }
        return null;
    }

    public int getTotal(){
        int total = 0;
        for(Integer n : bag.values()){
            total += n;
        }
        return total;
    }

    public Map<T, Integer> getMap(){
        return bag;
    }

    public Integer getItem(T t){
        return bag.get(t);
    }
}
