package statements;

import exceptions.MyException;
import model.*;
import state.PrgState;

public class VarDeclStmt implements IStmt{
    String name;
    Type typ;

    public VarDeclStmt(String name, Type typ) {
        this.name = name;
        this.typ = typ;
    }

    public String toString(){
        return typ.toString() + " " + name + ";";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getExeStack();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        if(symTbl.isDefined(name))
            throw new MyException("Variable is already declared");
        else{
            if(typ.equals(new IntType()))
                symTbl.add(name, new IntType().defaultValue());
            if(typ.equals(new BoolType()))
                symTbl.add(name, new BoolType().defaultValue());
            if(typ.equals(new StringType()))
                symTbl.add(name, new StringType().defaultValue());
            if(typ instanceof RefType)
                symTbl.add(name, typ.defaultValue());
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        typeEnv.add(name, typ);
        return typeEnv;
    }

    @Override
    public IStmt deepCopy() {
        return new VarDeclStmt(name, typ.deepCopy());
    }
}
