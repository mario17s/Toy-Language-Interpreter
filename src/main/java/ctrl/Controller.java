package ctrl;

import exceptions.MyException;
import model.*;
import repo.IRepository;
import repo.Repository;
import state.PrgState;
import statements.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static java.util.Locale.filter;

public class Controller {
    public IRepository repo;
    private ExecutorService executor;
    boolean displayFlag;

    public Controller(IRepository repo) {
        this.repo = repo;
        displayFlag = true;
    }

    public IRepository getRepo() {
        return repo;
    }

    public boolean isDisplayFlag() {
        return displayFlag;
    }

    public void setDisplayFlag(boolean displayFlag) {
        this.displayFlag = displayFlag;
    }

    public Map<Integer,Value> unsafeGarbageCollector(List<Integer> symTableAddr, List<Integer> heapAddr, Map<Integer,Value> heap){
        return heap.entrySet().stream()
                .filter(e->symTableAddr.contains(e.getKey()) || heapAddr.contains(e.getKey()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));}
    List<Integer> getAddrFromSymTable(){
        List<PrgState> prgs = repo.getPrgList();
        List<Value> valuess = new ArrayList<>();
        for(PrgState s: prgs)
        {
            for(Value v: s.getSymTable().getDictionary().values())
                valuess.add(v);
        }
        return valuess.stream()
                .filter(v-> v instanceof RefValue)
                .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddress();})
                .collect(Collectors.toList());
    }

    List<Integer> getAddrFromHeap(Collection<Value> heapValues){
        return heapValues.stream()
                .filter(v -> v instanceof  RefValue)
                .map(v -> {RefValue v1 = (RefValue) v; return v1.getAddress();})
                .collect(Collectors.toList());
    }

    List<PrgState> removeCompletedPrg(List<PrgState> inPrgList){
        return inPrgList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }

    public void oneStepForAllPrg(List<PrgState>prgList) throws InterruptedException {
        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>)(() -> {return p.oneStep();}))
                .collect(Collectors.toList());
        List<PrgState> newPrgList = executor.invokeAll(callList). stream()
                . map(future -> { try { return future.get();}
                catch(InterruptedException | ExecutionException e) {
                    System.out.println(e.getMessage());
                } return null;})
                .filter(p -> p!=null)
                            .collect(Collectors.toList());
        prgList.addAll(newPrgList);
        /*prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (MyException e) {
                throw new RuntimeException(e);
            }
        });*/
        repo.setPrgList(prgList);

    }

   public  void allStep() throws MyException, InterruptedException {
       executor = Executors.newFixedThreadPool(2);
       List<PrgState> prgList=removeCompletedPrg(repo.getPrgList());

       while(prgList.size() > 0){
           System.out.println("---------------------");
           oneStepForAllPrg(prgList);
           prgList.stream().forEach(prg -> prg.getHeap().setHeap(unsafeGarbageCollector(
                 getAddrFromSymTable(), getAddrFromHeap(prg.getHeap().getHeap().values()), prg.getHeap().getHeap())
           ));
           prgList.stream().forEach(prg -> {
               try {
                   repo.logPrgStateExec(prg);
               } catch (MyException e) {
                   throw new RuntimeException(e);
               }
           });
           prgList.stream().forEach(prg -> System.out.println(prg));
           prgList=removeCompletedPrg(repo.getPrgList());
       }
       executor.shutdownNow();
       repo.setPrgList(prgList);
    }

    public  void oneStepFX() throws MyException, InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        List<PrgState> prgList=removeCompletedPrg(repo.getPrgList());

            oneStepForAllPrg(prgList);
            prgList.stream().forEach(prg -> prg.getHeap().setHeap(unsafeGarbageCollector(
                    getAddrFromSymTable(), getAddrFromHeap(prg.getHeap().getHeap().values()), prg.getHeap().getHeap())
            ));
            prgList.stream().forEach(prg -> {
                try {
                    repo.logPrgStateExec(prg);
                } catch (MyException e) {
                    throw new RuntimeException(e);
                }
            });
            prgList.stream().forEach(prg -> System.out.println(prg));
            prgList=removeCompletedPrg(repo.getPrgList());

        executor.shutdownNow();
        repo.setPrgList(prgList);
    }

}
