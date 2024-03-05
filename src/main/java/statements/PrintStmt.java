package statements;

import exceptions.MyException;
import model.Exp;
import model.MyIDictionary;
import model.Type;
import model.Value;
import state.PrgState;

public class PrintStmt implements IStmt{
    Exp exp;

    public PrintStmt(Exp exp) {
        this.exp = exp;
    }

    public Exp getExp() {
        return exp;
    }

    public String toString(){
        return "print(" + exp.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        Value val = exp.eval(state.getSymTable(), state.getHeap());
        state.getOut().add(val);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        exp.typecheck(typeEnv);
        return typeEnv;
    }

    @Override
    public IStmt deepCopy() {
        return new PrintStmt(exp.deepCopy());
    }
}
