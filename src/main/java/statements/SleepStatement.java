package statements;

import exceptions.MyException;
import model.IntValue;
import model.MyIDictionary;
import model.Type;
import model.Value;
import state.PrgState;

public class SleepStatement implements IStmt {
    Value num;

    public SleepStatement(Value num) {
        this.num = num;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IntValue xx = (IntValue) num;
        if(xx.getVal() != 0)
            state.getExeStack().push(new SleepStatement(new IntValue(xx.getVal() - 1)));
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public IStmt deepCopy() {
        return new SleepStatement(num);
    }

    public String toString(){
        return "sleep" + num;
    }
}
