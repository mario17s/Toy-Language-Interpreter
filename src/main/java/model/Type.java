package model;

public interface Type {
    Value defaultValue();
    Type deepCopy();
}
