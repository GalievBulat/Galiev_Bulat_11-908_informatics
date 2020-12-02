package view;

import javafx.application.Application;
import javafx.application.Platform;
import serverclient.Client;
import serverclient.ClientConnection;
import view.controllers.Controller;
import view.models.Message;

import java.util.List;

public class ExecutionPoint {

    public  void main(Controller controller) {
        ClientConnection connection = new ClientConnection();
        Client client = null;
        boolean entered = false;
        while (true){
            if (controller!= null) {
                if (client == null ){
                    if (controller.ready) {
                        client = connection.connectClient(controller.getText(), new int[]{0});
                        controller.setRooms(client.getUser().getRooms());
                        controller.ready = false;
                    }
                } else {
                    List<Message> list = client.getMessages();
                    if (list.size()>0)
                        Platform.runLater(()->controller.putData(list));
                    if (controller.selected && !entered) {
                        int room = controller.getRoom();
                        client.sendMessage("/enter " + room);
                        entered = true;
                    } else if(entered && controller.ready){
                        if (controller.getText().length() > 0)
                            client.sendMessage(controller.getText());
                        controller.ready = false;
                    }
                }
            }
        }
    }
}
