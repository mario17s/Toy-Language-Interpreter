package statements;

import exceptions.MyException;
import model.Exp;
import model.MyIDictionary;
import model.Type;
import model.Value;
import state.PrgState;

public class AssignStmt implements IStmt{
    String id;
    Exp exp;

    public AssignStmt(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }

    public String toString(){
        return id + "=" + exp.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();

        if(symTbl.isDefined(id)){
            Value val = exp.eval(symTbl, state.getHeap());
            Type typId = symTbl.getElementByKey(id).getType();
            if(val.getType().equals(typId))
                symTbl.update(id, val);
            else throw new MyException("declared type of variable " + id + " and type of the assigned expression does not match");
        }
        else throw new MyException("the used variable " + id + " was not declared before");
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.getElementByKey(id);
        Type typexp = exp.typecheck(typeEnv);
        if (typevar.equals(typexp))
            return typeEnv;
        else
            throw new MyException("Assignment: right hand side and left hand side have different types ");
    }

    @Override
    public IStmt deepCopy() {
        return new AssignStmt(id, exp.deepCopy());
    }
}
