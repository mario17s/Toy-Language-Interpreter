package statements;

import exceptions.MyException;
import model.Exp;
import model.MyIDictionary;
import model.Type;
import state.PrgState;

public class CondAssignStmt implements  IStmt{
    String var;
    Exp exp1;
    Exp exp2;
    Exp exp3;

    public CondAssignStmt(String var, Exp exp1, Exp exp2, Exp exp3) {
        this.var = var;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
       IStmt newst = new IfStmt(exp1, new AssignStmt(var, exp2), new AssignStmt(var, exp3));
       state.getExeStack().push(newst);
       return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public IStmt deepCopy() {
        return new CondAssignStmt(var, exp1.deepCopy(), exp2.deepCopy(), exp3.deepCopy());
    }
}
