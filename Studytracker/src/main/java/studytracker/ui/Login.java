
package studytracker.ui;

import studytracker.dao.UserDao;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import studytracker.databases.Database;
import studytracker.domain.User;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.logging.Logger;

/**
 * Luokka vastaa kirjautumistoiminnosta
 */
public class Login extends Application {
    /**
     * Metodissa start luodaan kaksi nappia, joista toinen kirjaa käyttäjän sisään (login) ja toinen
     * luo uuden käyttäjän (newUser). Luodaan tekstikentät käyttäjän nimen ja salasanan syöttämiselle.
     */
    @Override
    public void start(Stage primaryStage) throws ClassNotFoundException, SQLException {
        Database db = new Database("jdbc:sqlite:studies.db");
        UserDao ud = new UserDao(db);

        Button login = new Button();
        login.setText("Log in");
        
        Button newUser = new Button();
        newUser.setText("Create new User");

        TextField tfUser = new TextField();
        TextField tfPassword = new TextField();
        tfUser.setMaxWidth(150);
        tfPassword.setMaxWidth(150);
        tfUser.setPromptText("Username");
        tfPassword.setPromptText("Password");
        
        /**
         * Nappia Login painamalla metodi tarkastaa onko käyttäjää olemassa. Jos käyttäjä on olemassa,
         * eikä metodi findUser palauta null, niin tapahtuu siirtyminen UI näkymään, joka on käyttäjälle
         * henkilökohtainen. UI luokkaan ohjataan findUser-metodin avulla.
         * Jos käyttäjää ei löydy findUser-metodilla, tulee näkyviin Error-hälytys, joka kertoo kirjautujalle,
         * että joko käyttäjänimi tai salasana on väärin.
         */
        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (ud.findUser(tfUser.getText(), tfPassword.getText()) != null) {
                        Stage userInterface = new Stage();
                        UI ui = new UI();
                        User user = ud.findUser(tfUser.getText(), tfPassword.getText());
                        ui.setId(user.getId());
                        ui.start(userInterface);
                        primaryStage.close();
                    } else {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Error");
                        alert.setContentText("Username or password is wrong!");
                        alert.showAndWait();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        /**
         * Nappia newUser painamalla metodi tarkastaa onko luotava käyttäjä null findUser-metodin avulla.
         * Jos käyttäjä on null, luodaan uusi käyttäjä create-metodilla ja näkyviin tulee New user created -hälytys,
         * joka kertoo kirjautujalle, että uusi käyttäjä on luotu onnistuneesti.
         * Jos luotava käyttäjä ei ole null, tulee näkyviin Error-hälytys, joka kertoo kirjautujalle että luotava käyttäjä on 
         * jo olemassa.
         */
        newUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                User user = new User(1, tfUser.getText(), tfPassword.getText());
                try {
                    if (ud.findUser(tfUser.getText(), tfPassword.getText()) == null) {
                        ud.create(user);
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("New user created");
                        alert.setContentText("User " + tfUser.getText() + " created succesfully!");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Error");
                        alert.setContentText("This user already exists");
                        alert.showAndWait();
                    }     
                } catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }   
        });
        
        /**
         * Luodaan luokan näkymä
         */
        StackPane root = new StackPane();
        root.getChildren().add(login);
        root.getChildren().add(newUser);
        root.getChildren().addAll(tfUser, tfPassword);

        StackPane.setAlignment(tfUser, Pos.TOP_LEFT);
        StackPane.setAlignment(tfPassword, Pos.TOP_RIGHT);
        StackPane.setAlignment(login, Pos.CENTER);
        StackPane.setAlignment(newUser, Pos.CENTER_RIGHT);
        
        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Log in");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Käynnistää luokan toiminnan
     */
    public static void main(String[] args) {
        launch(args);
    }

}
