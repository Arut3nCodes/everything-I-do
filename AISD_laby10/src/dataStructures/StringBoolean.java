package dataStructures;

public class StringBoolean {
    String string;
    boolean bool;

    public StringBoolean(String string, boolean bool){
        setString(string);
        setBool(bool);
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public boolean isBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }
}
