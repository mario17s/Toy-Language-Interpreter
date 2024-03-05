package model;

public class BoolValue implements Value{

    protected boolean val;

    public BoolValue(boolean val) {
        this.val = val;
    }

    public boolean getVal(){
        return val;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof BoolValue))
            return false;
        return val == ((BoolValue) obj).getVal();
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }

    @Override
    public Type getType() {
        return new BoolType();
    }

    @Override
    public Value deepCopy() {
        return new BoolValue(val);
    }
}
