package statements;

import exceptions.MyException;
import model.*;
import state.PrgState;

public class HeapWriteStmt implements IStmt{
    String id;
    Exp expression;

    public HeapWriteStmt(String id, Exp expression) {
        this.id = id;
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "HeapWriteStmt{" +
                "id='" + id + '\'' +
                ", expression=" + expression +
                '}';
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        if(!state.getSymTable().isDefined(id) || !(state.getSymTable().getElementByKey(id).getType() instanceof RefType) ||
        !state.getHeap().isDefined(((RefValue)state.getSymTable().getElementByKey(id)).getAddress()))
            throw new MyException("Heap write exception");
        Value v = expression.eval(state.getSymTable(), state.getHeap());
        if(!v.getType().equals(((RefType) state.getSymTable().getElementByKey(id).getType()).getInner()))
            throw new MyException("Heap write type exception");
        state.getHeap().update(((RefValue) state.getSymTable().getElementByKey(id)).getAddress(), v );
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.getElementByKey(id);
        Type typexp = expression.typecheck(typeEnv);
        if (typevar.equals(new RefType(typexp)))
            return typeEnv;
        else
            throw new MyException("HEAP WRITE stmt: right hand side and left hand side have different types ");
    }

    @Override
    public IStmt deepCopy() {
        return new HeapWriteStmt(id, expression.deepCopy());
    }
}
