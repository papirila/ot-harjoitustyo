/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dao.UserDao;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import databases.Database;
import domain.User;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author Pauliina
 */
public class Login extends Application {

    @Override
    public void start(Stage primaryStage) throws ClassNotFoundException, SQLException {
        Database db = new Database("jdbc:sqlite:test.db");
        UserDao ud = new UserDao(db);

        Button login = new Button();
        login.setText("Log in");

        TextField tfUser = new TextField();
        TextField tfPassword = new TextField();
        tfUser.setMaxWidth(150);
        tfPassword.setMaxWidth(150);
        tfUser.setPromptText("Username");
        tfPassword.setPromptText("Password");

        login.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                User user = new User(1, tfUser.getText(), tfPassword.getText());

                try {
                    if (ud.userExists(user)) {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Login");
                        alert.setContentText("Welcome!");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Error");
                        alert.setContentText("Username or password is wrong!");
                        alert.showAndWait();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(login);
        root.getChildren().addAll(tfUser, tfPassword);

        StackPane.setAlignment(tfUser, Pos.TOP_LEFT);
        StackPane.setAlignment(tfPassword, Pos.TOP_RIGHT);
        StackPane.setAlignment(login, Pos.CENTER);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Log in");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
