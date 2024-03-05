package repo;

import exceptions.MyException;
import model.MyIStack;
import model.StringValue;
import model.Value;
import state.PrgState;
import statements.IStmt;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Repository implements IRepository{
    List<PrgState> states;
    String filename;

    public Repository(PrgState program, String filename) {
        states = new ArrayList<PrgState>();
        states.add(program);
        this.filename = filename;
    }

    @Override
    public void addProgram(PrgState pg){
        states.add(pg);
    }

    @Override
    public void logPrgStateExec(PrgState state) throws MyException {
        PrintWriter logFile;
        try {
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)));
        } catch (IOException e) {
            throw new MyException(e.getMessage());
        }
        logFile.println(state.getId() + "\n");
        logFile.println("ExeStack: " + state.getExeStack().toString());
        logFile.println("SymTable: " + state.getSymTable().toString());
        logFile.println("Out: " + state.getOut().toString());
        logFile.println("FileTable: " + state.getFileTable().toString());
        logFile.println("Heap: " + state.getHeap().toString());
        logFile.println("-------------------------");
        logFile.close();
    }

    @Override
    public List<PrgState> getPrgList() {
        return states;
    }

    @Override
    public void setPrgList(List<PrgState> other) {
        this.states = other;
    }

    public PrgState getIdd(int id){
        return states.get(id);
    }
}
