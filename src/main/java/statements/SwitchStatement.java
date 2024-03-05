package statements;

import exceptions.MyException;
import model.*;
import state.PrgState;

public class SwitchStatement implements IStmt{
    Exp exp;
    Exp exp1;
    IStmt st1;
    Exp exp2;
    IStmt st2;
    IStmt st3;

    public SwitchStatement(Exp exp, Exp exp1, IStmt st1, Exp exp2, IStmt st2, IStmt st3) {
        this.exp = exp;
        this.exp1 = exp1;
        this.st1 = st1;
        this.exp2 = exp2;
        this.st2 = st2;
        this.st3 = st3;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IStmt newSt = new IfStmt(new RelationalExp("==", exp, exp1), st1,
                new IfStmt(new RelationalExp("==", exp, exp2), st2, st3));
        state.getExeStack().push(newSt);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public IStmt deepCopy() {
        return new SwitchStatement(exp.deepCopy(), exp1.deepCopy(), st1.deepCopy(), exp2.deepCopy(), st2.deepCopy(), st3.deepCopy());
    }
}
