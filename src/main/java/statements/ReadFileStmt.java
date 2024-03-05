package statements;

import exceptions.MyException;
import state.PrgState;
import model.*;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStmt implements IStmt{
    Exp exp;
    String variable;

    public ReadFileStmt(Exp exp, String variable) {
        this.exp = exp;
        this.variable = variable;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        //IStmt popped = state.getExeStack().pop();
        MyIDictionary<String, Value> st = state.getSymTable();
        if(!st.isDefined(variable) || !st.getElementByKey(variable).getType().equals(new IntType()))
            throw new MyException("The variable is not ok");
        Value cc = exp.eval(st, state.getHeap());
        if(!cc.getType().equals(new StringType()))
            throw new MyException("The expression must be String!");
        StringValue actual = (StringValue) cc;
        if(!state.getFileTable().isDefined(actual))
            throw new MyException("File not found in File Table!");
        BufferedReader buff = state.getFileTable().getElementByKey(actual);
        try {
            String line = buff.readLine();
            int yy;
            if(line.equals(""))
                yy = 0;
            else
                yy = Integer.parseInt(line);
            st.add(variable, new IntValue(yy));
        } catch (IOException e) {
            throw new MyException(e.getMessage());
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.getElementByKey(variable);
        Type typexp = exp.typecheck(typeEnv);
        return typeEnv;
    }

    @Override
    public IStmt deepCopy() {
        return new ReadFileStmt(exp.deepCopy(), variable);
    }
}
