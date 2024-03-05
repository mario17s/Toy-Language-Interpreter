package statements;

import exceptions.MyException;
import model.MyIDictionary;
import model.MyIStack;
import model.Type;
import state.PrgState;

public class NopStmt implements IStmt{
    @Override
    public PrgState execute(PrgState state) throws MyException {
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public IStmt deepCopy() {
        return new NopStmt();
    }

    @Override
    public String toString() {
        return "no operation";
    }
}
