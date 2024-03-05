package ui;

import ctrl.Controller;
import exceptions.MyException;

public class RunExample extends Command {
    private Controller ctr;
    private String descr;
    public RunExample(String key, String desc,Controller ctr){
        super(key, desc);
        this.descr = desc;
        this.ctr=ctr;
    }

    public Controller getCtr() {
        return ctr;
    }

    @Override
    public String toString(){
        return descr;
    }

    @Override
    public void execute() throws MyException {

        try {
            ctr.allStep();
        } catch (MyException e) {
            throw new MyException(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}