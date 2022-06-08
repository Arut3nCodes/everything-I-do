package BasicForms;

import java.util.ArrayList;

public class Processor {
    public ArrayList<Process> ongoingProcesses;

    public Processor(){
        ongoingProcesses = new ArrayList<>();
    }

    public int load(){
        int obciazenie = 0;
        for(Process p: ongoingProcesses){
            obciazenie += p.getLoad();
        }
        return obciazenie;
    }

    public void addProcess(Process p){
        ongoingProcesses.add(p);
    }

    public Process getProcess(int i){
        return ongoingProcesses.get(i);
    }

    public void removeProcess(int i){
        ongoingProcesses.remove(i);
    }

    public void removeProcess(Process p){
        ongoingProcesses.remove(p);
    }



}
