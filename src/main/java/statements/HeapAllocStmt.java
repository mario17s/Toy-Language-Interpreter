package statements;

import exceptions.MyException;
import model.*;
import state.PrgState;


public class HeapAllocStmt implements IStmt{
    String id;
    Exp expression;

    public HeapAllocStmt(String id, Exp expression) {
        this.id = id;
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "HeapAllocStmt{" +
                "id='" + id + '\'' +
                ", expression=" + expression +
                '}';
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> sym = state.getSymTable();
        if(!sym.isDefined(id) || !(sym.getElementByKey(id).getType() instanceof RefType))
            throw new MyException("Cannot add variable to heap table");
        Value v = expression.eval(sym, state.getHeap());
        if(!v.getType().equals(((RefType) sym.getElementByKey(id).getType()).getInner()))
            throw new MyException("the types of the references don't correspond");
        MyIHeap<Integer, Value> heap = state.getHeap();
        Integer newFree = heap.add(v);
        sym.update(id, new RefValue(newFree, ((RefType) sym.getElementByKey(id).getType()).getInner()));
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.getElementByKey(id);
        Type typexp = expression.typecheck(typeEnv);
        if (typevar.equals(new RefType(typexp)))
            return typeEnv;
        else
            throw new MyException("NEW stmt: right hand side and left hand side have different types ");
    }

    @Override
    public IStmt deepCopy() {
        return new HeapAllocStmt(id, expression.deepCopy());
    }
}
