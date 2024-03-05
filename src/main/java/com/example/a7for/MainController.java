package com.example.a7for;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.StringConverter;
import model.*;
import state.PrgState;
import statements.IStmt;
import ui.RunExample;

import java.io.BufferedReader;
import java.util.List;

public class MainController {
    private SelectController selectController;
    private RunExample selectedExample;
    private PrgState selectedProgram;

    int index;

    @FXML
    private TableView<HeapEntry> heapTableView;
    @FXML
    private TableColumn<HeapEntry, String> addressColumn;
    @FXML
    private TableColumn<HeapEntry, String> valueHeapColumn;


    @FXML
    private TableView<SymbolTableEntry> symbolTableView;
    @FXML
    private TableColumn<SymbolTableEntry, String> variableNameColumn;
    @FXML
    private TableColumn<SymbolTableEntry,String> valueSymColumn;

    @FXML
    private ListView<Value> outListView;

    @FXML
    private ListView<StringValue> fileTableListView;
    @FXML
    private ListView<PrgState> programStatesListView;

    @FXML
    private ListView<IStmt> executionStackListView;

    @FXML
    private TextField nrOfProgramStatesTextField;

    @FXML
    private Button runOneStepButton;

    public void setSelectController(SelectController newSelectController){
        this.selectController = newSelectController;
        this.selectController.getExamplesListView().getSelectionModel().selectedItemProperty().addListener((a,b,ex)->this.showDataForSelectedExample(ex));

    }

    @FXML
    private void initialize(){
        this.nrOfProgramStatesTextField.setEditable(false);

        this.addressColumn.setCellValueFactory(new PropertyValueFactory<HeapEntry, String>("heapAddress"));
        this.valueHeapColumn.setCellValueFactory(new PropertyValueFactory<HeapEntry, String >("heapValue"));

        this.variableNameColumn.setCellValueFactory(new PropertyValueFactory<SymbolTableEntry, String>("variableName"));
        this.valueSymColumn.setCellValueFactory(new PropertyValueFactory<SymbolTableEntry, String>("value"));

        this.outListView.setCellFactory(TextFieldListCell.forListView(new StringConverter<Value>() {
            @Override
            public String toString(Value valueInterface) {
                return valueInterface.toString();
            }

            @Override
            public Value fromString(String s) {
                return null;
            }
        }));

        this.fileTableListView.setCellFactory(TextFieldListCell.forListView(new StringConverter<StringValue>() {
            @Override
            public String toString(StringValue stringValue) {
                return stringValue.toString();
            }

            @Override
            public StringValue fromString(String s) {
                return null;
            }
        }));

        this.programStatesListView.setCellFactory(TextFieldListCell.forListView(new StringConverter<PrgState>() {
            @Override
            public String toString(PrgState programState) {
                return Integer.toString(programState.getId());
            }

            @Override
            public PrgState fromString(String s) {
                return null;
            }
        }));

        this.executionStackListView.setCellFactory(TextFieldListCell.forListView(new StringConverter<IStmt>() {
            @Override
            public String toString(IStmt statementInterface) {
                return statementInterface.toString();
            }

            @Override
            public IStmt fromString(String s) {
                return null;
            }
        }));

        this.programStatesListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        this.programStatesListView.getSelectionModel().selectedItemProperty().addListener((a,b,state)-> {
            if(state != null)
            {
                showDataForSelectedProgramState(state);
                index = this.programStatesListView.getSelectionModel().getSelectedIndex();
            }


        });

        this.runOneStepButton.setOnAction(actionEvent -> runOneStep(this.selectController.getExamplesListView().getSelectionModel().getSelectedItems().get(0)));

    }

    private void showDataForSelectedExample(RunExample example){
        this.heapTableView.getItems().clear();
        this.outListView.getItems().clear();
        this.fileTableListView.getItems().clear();
        this.programStatesListView.getItems().clear();
        this.symbolTableView.getItems().clear();
        this.executionStackListView.getItems().clear();

        List<PrgState> programStates = example.getCtr().getRepo().getPrgList();

        if(programStates.size() != 0)
            this.selectedProgram = programStates.get(0);

        MyIHeap<Integer, Value> sharedHeap = this.selectedProgram.getHeap();
        IFileTable<StringValue, BufferedReader> fileTable = this.selectedProgram.getFileTable();
        MyIList<Value> output = this.selectedProgram.getOut();

        sharedHeap.getHeap().forEach((address, value)->this.heapTableView.getItems().add(new HeapEntry(address, value)));
        fileTable.getTable().forEach((fileName, filePath)->this.fileTableListView.getItems().add(fileName));
        output.getList().forEach((value)->this.outListView.getItems().add(value));

        programStates.forEach((programState)->this.programStatesListView.getItems().add(programState));
        this.symbolTableView.getItems().clear();
        this.executionStackListView.getItems().clear();

        MyIStack<IStmt> executionStack = selectedProgram.getExeStack();
        MyIDictionary<String, Value> symbolTable = selectedProgram.getSymTable();
        executionStack.getStack().forEach((statement)->this.executionStackListView.getItems().add(statement));
        symbolTable.getDictionary().forEach((name, value)->this.symbolTableView.getItems().add(new SymbolTableEntry(name, value)));

        this.nrOfProgramStatesTextField.setText(Integer.toString(programStates.size()));

    }

    private void showDataForSelectedProgramState(PrgState program){
        this.symbolTableView.getItems().clear();
        this.executionStackListView.getItems().clear();

        MyIStack<IStmt> executionStack = program.getExeStack();
        MyIDictionary<String, Value> symbolTable = program.getSymTable();

        executionStack.getStack().forEach((statement)->this.executionStackListView.getItems().add(statement));
        symbolTable.getDictionary().forEach((name, value)->this.symbolTableView.getItems().add(new SymbolTableEntry(name, value)));
    }

    private void runOneStep(RunExample ex){
        try{
            ex.getCtr().oneStepFX();
        }
        catch (Exception e){

        }
        showDataForSelectedExample(ex);
        if (selectedProgram.getExeStack().isEmpty()) {
            showAlert("Stack is Empty", "Exestack is empty");
        }
    }
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();

    }
}
