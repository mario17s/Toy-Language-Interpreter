package statements;

import exceptions.MyException;
import model.*;
import state.PrgState;

public class ForStatement implements  IStmt{
    String id;
    Exp exp1;
    Exp exp2;
    Exp exp3;

    IStmt statement;

    public ForStatement(String id, Exp exp1, Exp exp2, Exp exp3, IStmt statement) {
        this.id = id;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
        this.statement = statement;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IStmt newStmt = new CompStmt(new AssignStmt(id, exp1), new WhileStmt(new RelationalExp("<", new VarExp(id), exp2),
                new CompStmt(statement, new AssignStmt(id, exp3))));
        MyIStack<IStmt> stk = state.getExeStack();
        stk.push(newStmt);
        return null;
    }

    public String toString(){
        return "for" + id + "-" + exp1.toString() + "-" + exp2.toString() + "-" + exp3.toString() + "-" + statement.toString();
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public IStmt deepCopy() {
        return new ForStatement(id, exp1.deepCopy(), exp2.deepCopy(), exp3.deepCopy(), statement.deepCopy());
    }
}
