package state;

import exceptions.MyException;
import model.*;
import statements.IStmt;

import java.io.BufferedReader;

public class PrgState {
    MyIStack<IStmt> exeStack;
    MyIDictionary<String, Value> symTable;
    MyIList<Value> out;
    IFileTable<StringValue, BufferedReader> fileTable;
    int id;

    private static int nextFree = 1;

    MyIHeap<Integer, Value> heap;
    IStmt originalProgram;

    public PrgState(MyIStack<IStmt> exeStack,
                    MyIDictionary<String, Value> symTable,
                    MyIList<Value> out, IFileTable<StringValue, BufferedReader> fileTable, MyIHeap<Integer, Value> heap, IStmt originalProgram) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.fileTable = fileTable;
        this.heap = heap;
        this.originalProgram = originalProgram.deepCopy();
        this.id = getNextFree();
    }

    public int getId(){
        return id;
    }

    public static synchronized int getNextFree(){
        nextFree++;
        return nextFree - 1;
    }

    public PrgState oneStep() throws MyException {
        if(exeStack.isEmpty()) throw new MyException("prgstate stack is empty");
        IStmt crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }

    public String toString(){
        return String.valueOf(this.id) + "\n" + exeStack.toString() + "\n" + symTable.toString() + "\n" + out.toString() + "\n" + fileTable.toString() + "\n" + heap.toString() + "\n";
    }

    public MyIHeap<Integer, Value> getHeap() {
        return heap;
    }

    public MyIStack<IStmt> getExeStack() {
        return exeStack;
    }

    public MyIDictionary<String, Value> getSymTable() {
        return symTable;
    }

    public IFileTable<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public MyIList<Value> getOut() {
        return out;
    }

    public IStmt getOriginalProgram() {
        return originalProgram;
    }

    public Boolean isNotCompleted(){
        if(!exeStack.isEmpty())
            return true;
        return false;
    }

}
