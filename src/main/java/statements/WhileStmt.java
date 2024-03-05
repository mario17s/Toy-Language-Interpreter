package statements;

import exceptions.MyException;
import model.*;
import state.PrgState;

public class WhileStmt implements IStmt{
    Exp expression;
    IStmt statement;

    public WhileStmt(Exp expression, IStmt statement) {
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public String toString() {
        return "WhileStmt{" +
                "expression=" + expression +
                ", statement=" + statement +
                '}';
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        Value v = expression.eval(state.getSymTable(), state.getHeap());
        if(v.getType().equals(new BoolType())){
            if(((BoolValue) v).getVal()) {
                state.getExeStack().push(this);
                state.getExeStack().push(statement);
            }
        }else throw new MyException("Exp not bool");
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typexp=expression.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            statement.typecheck(typeEnv.clone());
            return typeEnv;
        }
        else
            throw new MyException("The condition of WHILE has not the type bool");
    }

    @Override
    public IStmt deepCopy() {
        return new WhileStmt(expression.deepCopy(), statement.deepCopy());
    }
}
