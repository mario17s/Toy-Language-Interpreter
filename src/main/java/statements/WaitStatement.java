package statements;

import exceptions.MyException;
import model.*;
import state.PrgState;

public class WaitStatement implements IStmt{
    Value ee;

    public WaitStatement(Value ee) {
        this.ee = ee;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getExeStack();
        IntValue xx = (IntValue) ee;
        if(xx.getVal() != 0)
            stk.push(new CompStmt(new PrintStmt(new ValueExp(new IntValue(xx.getVal()))), new WaitStatement(new IntValue(xx.getVal() - 1)) ));
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public IStmt deepCopy() {
        return new WaitStatement(ee.deepCopy());
    }
}
