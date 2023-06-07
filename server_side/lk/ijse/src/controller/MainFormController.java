
package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import util.Server;

import java.io.IOException;
import java.net.ServerSocket;

public class MainFormController {



    public ImageView imgLogo;
    public Button btnStart;
    public Label lblNotification;
    Server server;


    public void initialize() {}


    public void btnStartOnAction(ActionEvent actionEvent) {
        if((btnStart.getText().equals("START SERVER"))){

            //notify gui server accept successed
            lblNotification.setText("Ready to use");
            btnStart.setText("STOP SERVER");

            //create serversocket object
            System.out.println("Server is start");
            Thread thread = new Thread(() -> {
                ServerSocket serverSocket  = null;
                try {
                    serverSocket = new ServerSocket(5000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Server is Running");
                server = new Server(serverSocket);
                server.runServer();
            });
            thread.start();


        }else if((btnStart.getText().equals("STOP SERVER"))){
            System.out.println("Server is Running");
            server.closeServer();

            //notify gui server socket closed
            lblNotification.setText("Stopped");
            btnStart.setText("START SERVER");
        }

    }
}
