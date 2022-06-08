package BasicForms;

import java.util.Random;

public class Process {
    int load;
    int frequency;
    int time;

    public Process(int maxPossibleLoad){
        Random generator = new Random();
        setLoad(generator.nextInt(maxPossibleLoad) + 1);
        setTime(generator.nextInt(80) + 300);
        setFrequency();
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency){
        this.frequency = frequency;
    }

    public void setFrequency() {
        this.frequency = this.time;
    }

    public void setTime(int time){
        this.time = time;
    }

    public void getTime(){

    }

    public String toString()
    {
        return("Udział: " + load + "%" + "\t" + "Czas wykonania: " + frequency + " ms");
    }

    public void doTask()
    {
        this.frequency = this.frequency - 1;
    }

    public boolean isDone()
    {
        return this.frequency<=0;
    }

    public void increase(int a)
    {
        this.frequency = this.frequency * (this.load/a);
        this.load = a;
    }
}
