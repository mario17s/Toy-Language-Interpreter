package model;

import java.util.*;

public class MyDictionary<T,U> implements MyIDictionary<T,U>{

    Map<T,U> dictionary;

    public MyDictionary() {
        this.dictionary = new HashMap<T,U>();
    }

    public Map<T, U> getDictionary() {
        return dictionary;
    }

    public void setDictionary(Map<T, U> dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public MyIDictionary<T, U> clone() {
        Map<T, U> newDict = new HashMap<>();
        Set<T> keyss = dictionary.keySet();
        for(T key: keyss){
            newDict.put(key, dictionary.get(key));
        }
        MyDictionary<T, U>  neww = new MyDictionary<T, U>();
        neww.setDictionary(newDict);
        return neww;
    }

    @Override
    public String toString() {
        return "dictionary=" + dictionary ;
    }

    @Override
    public void add(T key, U value) {
        dictionary.put(key, value);
    }

    @Override
    public String display() {
        return dictionary.toString();
    }

    @Override
    public U getElementByKey(T key) {
        return dictionary.get(key);
    }

    @Override
    public boolean isDefined(T key) {
        return dictionary.containsKey(key);
    }

    @Override
    public void update(T key, U newValue) {
        dictionary.replace(key, newValue);
    }
}
