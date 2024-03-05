package model;

public class IntValue implements Value{
    private int val;

    public IntValue(int val) {
        this.val = val;
    }

    public int getVal(){
        return val;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof IntValue))
            return false;
        return val == ((IntValue) obj).getVal();
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }

    @Override
    public Type getType() {
        return new IntType();
    }

    @Override
    public Value deepCopy() {
        return new IntValue(val);
    }
}
