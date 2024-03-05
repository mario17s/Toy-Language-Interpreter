package statements;

import exceptions.MyException;
import model.MyIDictionary;
import model.Type;
import state.PrgState;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException;
    MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException;
    IStmt deepCopy();

}
