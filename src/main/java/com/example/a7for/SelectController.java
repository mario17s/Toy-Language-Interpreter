package com.example.a7for;

import ctrl.Controller;
import exceptions.MyException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.StringConverter;
import model.*;
import repo.IRepository;
import repo.Repository;
import state.PrgState;
import statements.*;
import ui.ExitCommand;
import ui.RunExample;
import ui.TextMenu;

import java.io.BufferedReader;

public class SelectController {
        @FXML
        private ListView<RunExample> examplesListView;

        public ListView<RunExample> getExamplesListView(){
            return this.examplesListView;
        }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();

    }

        @FXML
        public void initialize() throws Exception {


            TextMenu menu = new TextMenu();
            menu.addCommand(new ExitCommand("0", "exit"));
            MyIStack<IStmt> stk = new MyStack<IStmt>();
            MyIDictionary<String, Value> symTbl = new MyDictionary<String, Value>();
            MyIList<Value> out = new MyList<Value>();
            IFileTable<StringValue, BufferedReader> fileTbl = new FileTable<StringValue, BufferedReader>();
            MyIHeap<Integer, Value> heap = new MyHeap<Value>();

            IStmt ex1=new CompStmt(new VarDeclStmt("a", new IntType()),
                    new CompStmt(new VarDeclStmt("b", new IntType()),
                            new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(2))),
                                    new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(3))),
                                            new IfStmt(new RelationalExp("<", new VarExp("a"), new VarExp("b")),
                                                    new PrintStmt(new VarExp("a")), new PrintStmt(new VarExp("b")))))));
            stk.push(ex1);

                PrgState prg1 = new PrgState(stk, symTbl, out, fileTbl, heap, ex1);
                IRepository repo1 = new Repository(prg1,"C:\\Users\\Asus\\IdeaProjects\\A5\\src\\ui\\log1.txt");
                Controller ctr1 = new Controller(repo1);
                menu.addCommand(new RunExample("1",ex1.toString(),ctr1));



            MyIStack<IStmt> stk2 = new MyStack<IStmt>();
            MyIDictionary<String, Value> symTbl2 = new MyDictionary<String, Value>();
            MyIList<Value> out2 = new MyList<Value>();
            IFileTable<StringValue, BufferedReader> fileTbl2 = new FileTable<StringValue, BufferedReader>();
            MyIHeap<Integer, Value> heap2 = new MyHeap<Value>();

            IStmt ex2=new CompStmt( new VarDeclStmt("a",new IntType()),
                    new CompStmt(new VarDeclStmt("b",new IntType()),
                            new CompStmt(new AssignStmt("a", new ArithExp('+',new ValueExp(new IntValue(2)),
                                    new ArithExp('*',new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                    new CompStmt(new AssignStmt("b",new ArithExp('+',new VarExp("a"),
                                            new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
            stk2.push(ex2);

                PrgState prg2 = new PrgState(stk2, symTbl2, out2, fileTbl2, heap2, ex2);
                IRepository repo2 = new Repository(prg2,"C:\\Users\\Asus\\IdeaProjects\\A5\\src\\ui\\log2.txt");
                Controller ctr2 = new Controller(repo2);
                menu.addCommand(new RunExample("2",ex2.toString(),ctr2));




            MyIStack<IStmt> stk3 = new MyStack<IStmt>();
            MyIDictionary<String, Value> symTbl3 = new MyDictionary<String, Value>();
            MyIList<Value> out3 = new MyList<Value>();
            IFileTable<StringValue, BufferedReader> fileTbl3 = new FileTable<StringValue, BufferedReader>();
            MyIHeap<Integer, Value> heap3 = new MyHeap<Value>();

            IStmt ex3= new CompStmt(new VarDeclStmt("a",new BoolType()),
                    new CompStmt(new VarDeclStmt("v", new IntType()),
                            new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                    new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",
                                            new ValueExp(new IntValue(2))), new AssignStmt("v",
                                            new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));
            stk3.push(ex3);

                PrgState prg3 = new PrgState(stk3, symTbl3, out3, fileTbl3, heap3, ex3);
                IRepository repo3 = new Repository(prg3,"C:\\Users\\Asus\\IdeaProjects\\A5\\src\\ui\\log3.txt");
                Controller ctr3 = new Controller(repo3);
                menu.addCommand(new RunExample("3",ex3.toString(),ctr3));



            MyIStack<IStmt> stk4 = new MyStack<IStmt>();
            MyIDictionary<String, Value> symTbl4 = new MyDictionary<String, Value>();
            MyIList<Value> out4 = new MyList<Value>();
            IFileTable<StringValue, BufferedReader> fileTbl4 = new FileTable<StringValue, BufferedReader>();
            MyIHeap<Integer, Value> heap4 = new MyHeap<Value>();

            IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()),
                    new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("C:\\Users\\Asus\\IdeaProjects\\A2\\src\\ui\\test.in"))),
                            new CompStmt(new OpenFileStmt(new VarExp("varf")),
                                    new CompStmt(new VarDeclStmt("varc", new IntType()),
                                            new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"),
                                                    new CompStmt(new PrintStmt(new VarExp("varc")),
                                                            new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"),
                                                                    new CompStmt(new PrintStmt(new VarExp("varc")), new CloseFileStmt(new VarExp("varf"))))))))));
            stk4.push(ex4);

                PrgState prg4 = new PrgState(stk4, symTbl4, out4, fileTbl4, heap4, ex4);
                IRepository repo4 = new Repository(prg4,"C:\\Users\\Asus\\IdeaProjects\\A5\\src\\ui\\log4.txt");
                Controller ctr4 = new Controller(repo4);
                menu.addCommand(new RunExample("4",ex4.toString(),ctr4));



            MyIStack<IStmt> stk5 = new MyStack<IStmt>();
            MyIDictionary<String, Value> symTbl5 = new MyDictionary<String, Value>();
            MyIList<Value> out5 = new MyList<Value>();
            IFileTable<StringValue, BufferedReader> fileTbl5 = new FileTable<StringValue, BufferedReader>();
            MyIHeap<Integer, Value> heap5 = new MyHeap<Value>();

            IStmt ex5 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                    new CompStmt(new HeapAllocStmt("v", new ValueExp(new IntValue(20))),
                            new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                    new CompStmt(new HeapAllocStmt("a", new VarExp("v")),
                                            new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new VarExp("a")))))));
            stk5.push(ex5);

                PrgState prg5 = new PrgState(stk5, symTbl5, out5, fileTbl5, heap5, ex5);
                IRepository repo5 = new Repository(prg5,"C:\\Users\\Asus\\IdeaProjects\\A5\\src\\ui\\log5.txt");
                Controller ctr5 = new Controller(repo5);
                menu.addCommand(new RunExample("5",ex5.toString(),ctr5));



            MyIStack<IStmt> stk6 = new MyStack<IStmt>();
            MyIDictionary<String, Value> symTbl6 = new MyDictionary<String, Value>();
            MyIList<Value> out6 = new MyList<Value>();
            IFileTable<StringValue, BufferedReader> fileTbl6 = new FileTable<StringValue, BufferedReader>();
            MyIHeap<Integer, Value> heap6 = new MyHeap<Value>();

            IStmt ex6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                    new CompStmt(new HeapAllocStmt("v", new ValueExp(new IntValue(20))),
                            new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                    new CompStmt(new HeapAllocStmt("a", new VarExp("v")),
                                            new CompStmt(new PrintStmt(new RHExp(new VarExp("v"))), new PrintStmt(new ArithExp('+',new RHExp(new RHExp(new VarExp("a"))), new ValueExp(new IntValue(5)))))))));
            stk6.push(ex6);

                PrgState prg6 = new PrgState(stk6, symTbl6, out6, fileTbl6, heap6, ex6);
                IRepository repo6 = new Repository(prg6,"C:\\Users\\Asus\\IdeaProjects\\A5\\src\\ui\\log6.txt");
                Controller ctr6 = new Controller(repo6);
                menu.addCommand(new RunExample("6",ex6.toString(),ctr6));



            MyIStack<IStmt> stk7 = new MyStack<IStmt>();
            MyIDictionary<String, Value> symTbl7 = new MyDictionary<String, Value>();
            MyIList<Value> out7 = new MyList<Value>();
            IFileTable<StringValue, BufferedReader> fileTbl7 = new FileTable<StringValue, BufferedReader>();
            MyIHeap<Integer, Value> heap7 = new MyHeap<Value>();

            IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                    new CompStmt(new HeapAllocStmt("v", new ValueExp(new IntValue(20))),
                            new CompStmt(new PrintStmt(new RHExp(new VarExp("v"))),
                                    new CompStmt(new HeapWriteStmt("v", new ValueExp(new IntValue(30))), new PrintStmt(new ArithExp('+', new RHExp(new VarExp("v")), new ValueExp(new IntValue(5))))))));
            stk7.push(ex7);

                PrgState prg7 = new PrgState(stk7, symTbl7, out7, fileTbl7, heap7, ex7);
                IRepository repo7 = new Repository(prg7,"C:\\Users\\Asus\\IdeaProjects\\A5\\src\\ui\\log7.txt");
                Controller ctr7 = new Controller(repo7);
                menu.addCommand(new RunExample("7",ex7.toString(),ctr7));



            MyIStack<IStmt> stk8 = new MyStack<IStmt>();
            MyIDictionary<String, Value> symTbl8 = new MyDictionary<String, Value>();
            MyIList<Value> out8 = new MyList<Value>();
            IFileTable<StringValue, BufferedReader> fileTbl8 = new FileTable<StringValue, BufferedReader>();
            MyIHeap<Integer, Value> heap8 = new MyHeap<Value>();

            IStmt ex8 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                    new CompStmt(new HeapAllocStmt("v", new ValueExp(new IntValue(20))),
                            new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                    new CompStmt(new HeapAllocStmt("a", new VarExp("v")),
                                            new CompStmt(new HeapAllocStmt("v", new ValueExp(new IntValue(30))),
                                                    new CompStmt(new VarDeclStmt("f", new RefType(new IntType())),
                                                            new CompStmt(new HeapAllocStmt("f", new ValueExp(new IntValue(123))),
                                                                    new CompStmt(new HeapAllocStmt("f", new ValueExp(new IntValue(100))),
                                                                            new PrintStmt(new RHExp(new RHExp(new VarExp("a"))))))))))));
            stk8.push(ex8);

                PrgState prg8 = new PrgState(stk8, symTbl8, out8, fileTbl8, heap8, ex8);
                IRepository repo8 = new Repository(prg8,"C:\\Users\\Asus\\IdeaProjects\\A5\\src\\ui\\log8.txt");
                Controller ctr8 = new Controller(repo8);
                menu.addCommand(new RunExample("8",ex8.toString(),ctr8));



            MyIStack<IStmt> stk9 = new MyStack<IStmt>();
            MyIDictionary<String, Value> symTbl9 = new MyDictionary<String, Value>();
            MyIList<Value> out9 = new MyList<Value>();
            IFileTable<StringValue, BufferedReader> fileTbl9 = new FileTable<StringValue, BufferedReader>();
            MyIHeap<Integer, Value> heap9 = new MyHeap<Value>();

            IStmt ex9 = new CompStmt(new VarDeclStmt("a", new IntType()),
                    new CompStmt(new WhileStmt(new RelationalExp("<", new VarExp("a"), new ValueExp(new IntValue(10))),
                            new CompStmt(new AssignStmt("a", new ArithExp('+', new VarExp("a"), new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("a")))),
                            new PrintStmt(new VarExp("a"))));
            stk9.push(ex9);

                PrgState prg9 = new PrgState(stk9, symTbl9, out9, fileTbl9, heap9, ex9);
                IRepository repo9 = new Repository(prg9,"C:\\Users\\Asus\\IdeaProjects\\A5\\src\\ui\\log9.txt");
                Controller ctr9 = new Controller(repo9);
                menu.addCommand(new RunExample("9",ex9.toString(),ctr9));


            MyIStack<IStmt> stk10 = new MyStack<IStmt>();
            MyIDictionary<String, Value> symTbl10 = new MyDictionary<String, Value>();
            MyIList<Value> out10 = new MyList<Value>();
            IFileTable<StringValue, BufferedReader> fileTbl10 = new FileTable<StringValue, BufferedReader>();
            MyIHeap<Integer, Value> heap10 = new MyHeap<Value>();

            IStmt ex10 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                    new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))), new CompStmt(new HeapAllocStmt("a", new ValueExp(new IntValue(22))),
                            new CompStmt(new ForkStatement(new CompStmt(new HeapWriteStmt("a", new ValueExp(new IntValue(30))),
                                    new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                                            new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new RHExp(new VarExp("a"))))))),
                                    new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new RHExp(new VarExp("a")))))
                    ))));
            stk10.push(ex10);

                PrgState prg10 = new PrgState(stk10, symTbl10, out10, fileTbl10, heap10, ex10);
                IRepository repo10 = new Repository(prg10,"C:\\Users\\Asus\\IdeaProjects\\A5\\src\\ui\\log10.txt");
                Controller ctr10 = new Controller(repo10);
                menu.addCommand(new RunExample("10",ex10.toString(),ctr10));


            MyIStack<IStmt> stk11 = new MyStack<IStmt>();
            MyIDictionary<String, Value> symTbl11 = new MyDictionary<String, Value>();
            MyIList<Value> out11 = new MyList<Value>();
            IFileTable<StringValue, BufferedReader> fileTbl11 = new FileTable<StringValue, BufferedReader>();
            MyIHeap<Integer, Value> heap11 = new MyHeap<Value>();


            IStmt ex11 = new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                    new CompStmt(new VarDeclStmt("counter", new IntType()),
                            new WhileStmt(new RelationalExp("<", new VarExp("counter"), new ValueExp(new IntValue(10))),
                                    new CompStmt(new ForkStatement(new ForkStatement(new CompStmt(new HeapAllocStmt("a", new VarExp("counter")),
                                            new PrintStmt(new RHExp(new VarExp("a")))))), new AssignStmt("counter", new ArithExp('+', new VarExp("counter"), new ValueExp(new IntValue(1))))))));
            stk11.push(ex11);

                PrgState prg11 = new PrgState(stk11, symTbl11, out11, fileTbl11, heap11, ex11);
                IRepository repo11 = new Repository(prg11,"C:\\Users\\Asus\\IdeaProjects\\A5\\src\\ui\\log11.txt");
                Controller ctr11 = new Controller(repo11);
                menu.addCommand(new RunExample("11",ex11.toString(),ctr11));


            MyIStack<IStmt> stk18 = new MyStack<IStmt>();
            MyIDictionary<String, Value> symTbl18 = new MyDictionary<String, Value>();
            MyIList<Value> out18 = new MyList<Value>();
            IFileTable<StringValue, BufferedReader> fileTbl18 = new FileTable<StringValue, BufferedReader>();
            MyIHeap<Integer, Value> heap18 = new MyHeap<Value>();

            IStmt ex18 = new CompStmt(new VarDeclStmt("v", new IntType()),
                         new CompStmt(new VarDeclStmt("x", new IntType()),
                        new CompStmt(new VarDeclStmt("y", new IntType()),
                    new CompStmt(
                            new RepeatUntilStatement(
                                   new CompStmt(
                                           new ForkStatement(new CompStmt(
                                                   new PrintStmt(new VarExp("v")),
                                                   new AssignStmt("v", new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1))))
                                           )), new AssignStmt("v", new ArithExp('+', new VarExp("v"), new ValueExp(new IntValue(1))))),
                                    new RelationalExp("==", new VarExp("v"), new ValueExp(new IntValue(3)))
                                   ),
                            new CompStmt(new AssignStmt("x", new ValueExp(new IntValue(1))),
                            new CompStmt(new NopStmt(),
                            new CompStmt(new AssignStmt("y", new ValueExp(new IntValue(3))),
                            new CompStmt(new NopStmt(), new PrintStmt(new ArithExp('*', new VarExp("v"), new ValueExp(new IntValue(10))))))))

                            )
                    )));
            try{
                ex18.typecheck(new MyDictionary<>());
                stk18.push(ex18);

                PrgState prg18 = new PrgState(stk18, symTbl18, out18, fileTbl18, heap18, ex18);
                IRepository repo18 = new Repository(prg18,"C:\\Users\\Asus\\IdeaProjects\\A7For\\src\\main\\java\\ui\\log13.txt");
                Controller ctr18 = new Controller(repo18);
                menu.addCommand(new RunExample("18",ex18.toString(),ctr18));
                this.examplesListView.getItems().add(new RunExample("18", ex18.toString(), ctr18));
            }
            catch(MyException e){
                System.out.println(e.getMessage());
            }

            this.examplesListView.setCellFactory(TextFieldListCell.forListView(new StringConverter<RunExample>() {
                @Override
                public String toString(RunExample runExampleCommand) {
                    return runExampleCommand.toString();
                }

                @Override
                public RunExample fromString(String s) {
                    return null;
                }
            }));

            this.examplesListView.getItems().add(new RunExample("1", ex1.toString(), ctr1));
            this.examplesListView.getItems().add(new RunExample("2", ex2.toString(), ctr2));
            this.examplesListView.getItems().add(new RunExample("3", ex3.toString(), ctr3));
            this.examplesListView.getItems().add(new RunExample("4", ex4.toString(), ctr4));
            this.examplesListView.getItems().add(new RunExample("5", ex5.toString(), ctr5));
            this.examplesListView.getItems().add(new RunExample("6", ex6.toString(), ctr6));
            this.examplesListView.getItems().add(new RunExample("7", ex7.toString(), ctr7));
            this.examplesListView.getItems().add(new RunExample("8", ex8.toString(), ctr8));
            this.examplesListView.getItems().add(new RunExample("9", ex9.toString(), ctr9));
            this.examplesListView.getItems().add(new RunExample("10", ex10.toString(), ctr10));
            this.examplesListView.getItems().add(new RunExample("11", ex11.toString(), ctr11));


            this.examplesListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        }

}
