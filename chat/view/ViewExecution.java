package view;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import serverclient.Client;
import serverclient.ClientConnection;
import view.ExecutionPoint;
import view.controllers.Controller;
import view.models.Message;

import java.io.IOException;
import java.util.List;

public class ViewExecution extends Application {

    public  Controller mainSceneController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop(){
        System.out.println("Stage is closing");
    }


    @Override
    public void start(Stage primaryStage){
        Parent root;
        Stage stage = new Stage();
        FXMLLoader loader;
        try {
            loader = new FXMLLoader(getClass().getResource("../chat.fxml"));
            root = loader.load();
            mainSceneController = loader.getController();
            mainSceneController.putData(loadMessages());
            stage.setTitle("Чат");
            stage.setScene(new Scene(root, 900, 700));
            stage.show();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        fun();
    }
    public void fun(){
        ExecutionPoint executionPoint = new ExecutionPoint();
        new Thread(()-> executionPoint.main(mainSceneController)).start();
    }
    public int getRoom(){
        return mainSceneController.getRoom();
    }
    public void setRooms(int[] rooms){
         mainSceneController.setRooms(rooms);
    }
    public String getMessage(){
        return mainSceneController.getText();
    }

    public List<Message> loadMessages() {
        /*List<Message> notes = new ArrayList<>();
        File file = new File("data.txt");
        Scanner scanner;
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(e);
        }
        String s;
        String[] strings;
        Message note;
        while(scanner.hasNextLine()){
            s = scanner.nextLine();
            lengths.add(s.length());
            strings = s.split(" ");
            String text = getCurrentText(strings[2]);
            Long id = Long.parseLong(strings[0]);
            checkLastId(id);
            categories.add(strings[3]);
            boolean deleted = Boolean.valueOf(strings[4]);
            note = new User(strings[1],text,strings[3], id, deleted);
            notes.add(note);
        }
        return notes;*/
        //TODO
        return null;
    }
    public void InsertUpdates(List<Message> messages){
        mainSceneController.putData(messages);
    }
/*
    private static void checkLastId(Long id) {
        if(id > IdFactory.currentId){
            IdFactory.setCurrentId(id);
        }
    }*/
/*
    private static String getCurrentText(String text) {
        StringBuilder a = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '\t') {
                a.append("\n");
            } else if (text.charAt(i) == '\f') {
                a.append(" ");
            } else {
                a.append(text.charAt(i));
            }
        }
        return a.toString();
    }*/


}
