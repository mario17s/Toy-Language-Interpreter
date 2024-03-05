package model;

public class StringType implements Type{
    @Override
    public boolean equals(Object obj) {
        return obj instanceof StringType;
    }

    public String toString(){return "string";}

    @Override
    public Value defaultValue() {
        return new StringValue("");
    }

    @Override
    public Type deepCopy() {
        return new StringType();
    }
}
