/*package ui;

import ctrl.Controller;
import exceptions.MyException;
import model.*;
import repo.IRepository;
import repo.Repository;
import state.PrgState;
import statements.*;

import java.io.BufferedReader;
import java.util.Scanner;

public class View {
    Controller ctrl;
    static Scanner scanner = new Scanner(System.in);

    public View(Controller ctrl) {
        this.ctrl = ctrl;
    }

    public Controller getCtrl() {
        return ctrl;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public static void printOptions()
    {
        System.out.println("Here are the programs");
        System.out.println("1. int v; v=2;Print(v)");
        System.out.println("2. int a;int b; a=2+3*5;b=a+1;Print(b)");
        System.out.println("3. bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)");
        System.out.println("4. Exit");
    }


    public static void main(String[] args) {


        while(true)
        {
            printOptions();
            System.out.println("enter a number ");
            int choice = Integer.parseInt(scanner.next());
            IStmt program = null;
            if(choice == 1){
                program = new CompStmt(new CompStmt(new VarDeclStmt("v",new BoolType()), new VarDeclStmt("x", new BoolType())),
                        new CompStmt(new NopStmt(),
                                new PrintStmt(new LogicExp("||", new VarExp("v"), new VarExp("x")))));
            }
            if(choice == 2){
               program = new CompStmt( new VarDeclStmt("a",new IntType()),
                       new CompStmt(new VarDeclStmt("b",new IntType()),
                               new CompStmt(new AssignStmt("a", new ArithExp('+',new ValueExp(new IntValue(2)),
                                       new ArithExp('*',new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                       new CompStmt(new AssignStmt("b",new ArithExp('+',new VarExp("a"),
                                               new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
            }
            if(choice == 3){
                program = new CompStmt(new VarDeclStmt("a",new BoolType()),
                        new CompStmt(new VarDeclStmt("v", new IntType()),
                                new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                        new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",
                                                new ValueExp(new IntValue(2))), new AssignStmt("v",
                                                new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));
            }
            if(choice == 4) {
                program = new CompStmt(new VarDeclStmt("varf", new StringType()),
                        new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("C:\\Users\\Asus\\IdeaProjects\\A2\\src\\ui\\test.in"))),
                                new CompStmt(new OpenFileStmt(new VarExp("varf")),
                                        new CompStmt(new VarDeclStmt("varc", new IntType()),
                                                new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"),
                                                        new CompStmt(new PrintStmt(new VarExp("varc")),
                                                                new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"),
                                                                        new CompStmt(new PrintStmt(new VarExp("varc")), new CloseFileStmt(new VarExp("varf"))))))))));
            }
            if(choice == 5)
                break;
            MyIStack<IStmt> stk = new MyStack<IStmt>();
            MyIDictionary<String, Value> symTbl = new MyDictionary<String, Value>();
            MyIList<Value> out = new MyList<Value>();
            IFileTable<StringValue, BufferedReader> fileTbl = new FileTable<StringValue, BufferedReader>();
            PrgState state = new PrgState(stk, symTbl, out, fileTbl, program);
            IRepository repo;

        }

    }
}*/
