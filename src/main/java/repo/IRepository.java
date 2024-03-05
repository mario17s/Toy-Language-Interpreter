package repo;

import exceptions.MyException;
import state.PrgState;

import java.util.List;

public interface IRepository {
    void addProgram(PrgState pg);
    void logPrgStateExec(PrgState state) throws MyException;

    List<PrgState> getPrgList();
    public PrgState getIdd(int id);

    void setPrgList(List<PrgState> other);
}
