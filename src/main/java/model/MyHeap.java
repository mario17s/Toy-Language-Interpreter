package model;

import java.util.HashMap;
import java.util.Map;

public class MyHeap<U> implements MyIHeap<Integer,U>{
    Map<Integer,U> heap;
    Integer newFree;

    public MyHeap() {
        heap = new HashMap<>();
        newFree = 1;
    }

    public Map<Integer, U> getHeap() {
        return heap;
    }

    public void setHeap(Map<Integer, U> heap) {
        this.heap = heap;
    }

    @Override
    public Integer add(U value) {
        heap.put(newFree, value);
        newFree++;
        return newFree - 1;
    }

    @Override
    public String display() {
        return heap.toString();
    }

    @Override
    public U getElementByKey(Integer key) {
        return heap.get(key);
    }

    @Override
    public boolean isDefined(Integer key) {
        return heap.containsKey(key);
    }

    @Override
    public void update(Integer key, U newValue) {
        heap.replace(key, newValue);
    }

    @Override
    public String toString() {
        return "MyHeap{" +
                "heap=" + heap +
                ", newFree=" + newFree +
                '}';
    }
}
