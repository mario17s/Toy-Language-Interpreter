package statements;

import exceptions.MyException;
import model.*;
import state.PrgState;

public class RepeatUntilStatement implements IStmt{
    private IStmt statement;
    private Exp expression;

    public RepeatUntilStatement(IStmt statement, Exp expression) {
        this.statement = statement;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IStmt newst = new CompStmt(statement, new WhileStmt(new NotExp(expression), statement));
        state.getExeStack().push(newst);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typee = expression.typecheck(typeEnv);
        if(typee.equals(new BoolType()))
            return statement.typecheck(typeEnv);
        else throw new MyException("exp not of type bool");
    }

    @Override
    public IStmt deepCopy() {
        return new RepeatUntilStatement(statement.deepCopy(), expression.deepCopy());
    }

    public String toString(){
        return "rep " + statement.toString() + "until " + expression.toString();
    }
}
