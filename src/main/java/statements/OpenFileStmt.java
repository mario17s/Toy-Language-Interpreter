package statements;

import exceptions.MyException;
import model.*;
import state.PrgState;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OpenFileStmt implements IStmt {
    Exp exp;

    public OpenFileStmt(Exp exp) {
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getExeStack();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        IFileTable<StringValue, BufferedReader> ft = state.getFileTable();
        //IStmt popped = stk.pop();
        Value v = exp.eval(symTbl, state.getHeap());
        if (!v.getType().equals(new StringType()))
            throw new MyException("The value must be a string");
        else {
            StringValue key = (StringValue) v;
            if (ft.isDefined(key))
                throw new MyException("The key is already in the file table");
            BufferedReader buff;
            try {
                buff = new BufferedReader(new FileReader(key.getVal()));
            } catch (IOException io) {
                throw new MyException(io.getMessage());
            }
            ft.add(key, buff);
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        exp.typecheck(typeEnv);
        return  typeEnv;
    }

    @Override
    public IStmt deepCopy() {
        return new OpenFileStmt(exp.deepCopy());
    }
}
