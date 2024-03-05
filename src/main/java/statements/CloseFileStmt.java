package statements;

import exceptions.MyException;
import state.PrgState;
import model.*;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseFileStmt implements IStmt{
    Exp exp;

    public CloseFileStmt(Exp exp) {
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        //state.getExeStack().pop();
        Value e = exp.eval(state.getSymTable(), state.getHeap());
        if(!e.getType().equals(new StringType()))
            throw new MyException("expression must be a string");
        if(!state.getFileTable().isDefined((StringValue) e))
            throw new MyException("File not found in File Table");
        BufferedReader buff = state.getFileTable().getElementByKey((StringValue) e);
        try {
            buff.close();
        } catch (IOException ex) {
            throw new MyException(ex.getMessage());
        }
        state.getFileTable().remove((StringValue) e);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        exp.typecheck(typeEnv);
        return typeEnv;
    }

    @Override
    public IStmt deepCopy() {
        return new CloseFileStmt(exp.deepCopy());
    }
}
