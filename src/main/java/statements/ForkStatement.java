package statements;

import exceptions.MyException;
import model.MyIDictionary;
import model.MyIStack;
import model.MyStack;
import model.Type;
import state.PrgState;

public class ForkStatement implements  IStmt{
    IStmt statement;

    public ForkStatement(IStmt statement) {
        this.statement = statement;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stkk = new MyStack<>();
        stkk.push(statement);
        return new PrgState(stkk, state.getSymTable().clone(), state.getOut(), state.getFileTable(), state.getHeap(), statement);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        statement.typecheck(typeEnv.clone());
        return typeEnv;
    }

    @Override
    public String toString(){
        return "Fork " + statement.toString();
    }

    @Override
    public IStmt deepCopy() {
        return new ForkStatement(statement.deepCopy());
    }
}
