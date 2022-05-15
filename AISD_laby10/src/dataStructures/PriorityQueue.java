package dataStructures;

import java.util.ArrayList;

public class PriorityQueue implements IQueue{
    ArrayList<Node> alist;

    public PriorityQueue(){
        alist = new ArrayList<>();
    }
    @Override
    public boolean isEmpty(){
        return alist.isEmpty();
    }

    @Override
    public boolean isFull(){
        return false;
    }

    @Override
    public Node dequeue(){
        return alist.remove(0);
    }

    @Override
    public void enqueue(Node elem){
        int size = alist.size();
        boolean check = false;
        for(int i = size - 1; i >= 0; i--){
            if(elem.getPriority() >= alist.get(i).getPriority()){
                if(i == size - 1) {
                    alist.add(elem);
                    check = true;
                    break;
                }
                else{
                    alist.add(i + 1, elem);
                    check = true;
                    break;
                }
            }
        }
        if(check == false){
            alist.add(0, elem);
        }
    }

    public int size(){
        return alist.size();
    }

    public Node first(){
        return alist.get(0);
    }



}
