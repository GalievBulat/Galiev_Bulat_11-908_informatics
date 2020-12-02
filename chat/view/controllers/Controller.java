package view.controllers;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import view.models.Message;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public Pane pane;
    @FXML
    public Button sendButton;
    @FXML
    public ListView<Message> listView;
    @FXML
    public TextField textField;
    @FXML
    public ChoiceBox<Integer> roomChoosing;
    public boolean ready = false;
    public boolean selected = false;

    public ObservableList<Message> notes = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sendButton.setOnMouseClicked((event -> {
            ready = true;
        }));
        roomChoosing.setOnAction((f-> {
            if (roomChoosing.getSelectionModel().getSelectedIndex()>=0)
                selected = true;
        }));
    }

    public void putData(List<Message> noteList) {
        if (noteList!= null && noteList.size()>0) {
            notes.addAll(noteList);
            listView.setItems(notes);
        }
    }
    public String getText(){
        return textField.getText();
    }
    public void setRooms(int[] rooms){
        roomChoosing.setItems(new ObservableListWrapper<Integer>(arrayToList(rooms)));
    }
    private List<Integer> arrayToList(int[] arr){
        List<Integer> list = new ArrayList<>();
        for (int n: arr){
            list.add(n);
        }
        return list;
    }
    public int getRoom(){
        return roomChoosing.getValue();
    }
}
