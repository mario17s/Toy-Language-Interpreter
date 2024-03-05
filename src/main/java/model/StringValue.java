package model;

import java.util.Objects;

public class StringValue implements Value{
    String val;

    public StringValue(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public String toString(){return val;}

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof StringValue))
            return false;
        return val == ((StringValue) obj).getVal();
    }

    @Override
    public Type getType() {
        return new StringType();
    }

    @Override
    public Value deepCopy() {
        return new StringValue(val);
    }
}
