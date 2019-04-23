package ui;

import domain.Course;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class UI extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        GridPane grid = new GridPane();
        HBox hbox = new HBox();
        Label lb = new Label("Passed courses");
        ObservableList<Course> data = FXCollections.observableArrayList();
        
        TextField addName = new TextField(); 
        addName.setPromptText("Course name");
        TextField addPoints = new TextField(); 
        addPoints.setPromptText("Study points");
        TextField addGrade = new TextField(); 
        addGrade.setPromptText("Grade");
        
        
        addPoints.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, 
                String newValue) {
                if (!newValue.matches("\\d*")) {
                    addPoints.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        addGrade.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, 
                String newValue) {
                if (!newValue.matches("\\d*")) {
                    addGrade.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
       
        Button button = new Button("Add");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println(addName.getText());
                data.add(new Course(9999,
                    addName.getText(),
                    Integer.valueOf(addPoints.getText()),
                    Integer.valueOf(addGrade.getText())
                ));
                addName.clear();
                addPoints.clear();
                addGrade.clear();
            }
        });
        
        
        ObservableList list = hbox.getChildren();  
        list.addAll(addName, addPoints, addGrade, button);    
        
      
        TableView passedCourses = new TableView();

        TableColumn courseNameColumn = new TableColumn("Course name");
        courseNameColumn.setMinWidth(200);
        
        courseNameColumn.setCellValueFactory(
            new PropertyValueFactory<Course,String>("Name")
        );
        TableColumn studypointsColumn = new TableColumn("Study points");
        studypointsColumn.setMinWidth(200);
        
        studypointsColumn.setCellValueFactory(
            new PropertyValueFactory<Course,String>("studyPoints")
        );
        TableColumn gradeColumn = new TableColumn("Grade");
        gradeColumn.setMinWidth(200);
        
        gradeColumn.setCellValueFactory(
            new PropertyValueFactory<Course,String>("Grade")
        );
        passedCourses.setItems(data);
        
        passedCourses.getColumns().addAll(courseNameColumn, studypointsColumn, gradeColumn);
        
        
        // TÄSTÄ ALKAA TOINEN LISTA ILMOTUT KURSSIT
        
        HBox hbox2 = new HBox();
        Label lb2 = new Label("Applied courses");
        ObservableList<Course> data2 = FXCollections.observableArrayList();
        
        TextField addName2 = new TextField(); 
        addName2.setPromptText("Course name");
        TextField addPoints2 = new TextField(); 
        addPoints2.setPromptText("Study points");
        
        addPoints2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, 
                String newValue) {
                if (!newValue.matches("\\d*")) {
                    addPoints.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        
        Button button2 = new Button("Add");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println(addName2.getText());
                data2.add(new Course(9999,
                    addName2.getText(),
                    Integer.valueOf(addPoints2.getText())
                ));
                addName2.clear();
                addPoints2.clear();
                
            }
        });
        
        ObservableList list2 = hbox2.getChildren();  
        list2.addAll(addName2, addPoints2, button2);
        
        TableView appliedCourses = new TableView();

        TableColumn courseNameColumn2 = new TableColumn("Course name");
        courseNameColumn2.setMinWidth(200);
        
        courseNameColumn2.setCellValueFactory(
            new PropertyValueFactory<Course,String>("Name")
        );
        TableColumn studypointsColumn2 = new TableColumn("Study points");
        studypointsColumn2.setMinWidth(200);
        
        studypointsColumn2.setCellValueFactory(
            new PropertyValueFactory<Course,String>("studyPoints")
        );
        
        appliedCourses.setItems(data2);
        
        appliedCourses.getColumns().addAll(courseNameColumn2, studypointsColumn2);
        
        
        // TÄHÄN LOPPUU
        
        grid.add(lb, 0, 0);
        grid.add(passedCourses, 0, 1);
        grid.add(hbox, 0, 3);
        
        grid.add(lb2, 1, 0);
        grid.add(appliedCourses, 1, 1);
        grid.add(hbox2, 1, 3);
        
        StackPane root = new StackPane();
        root.getChildren().add(grid);
        
        Scene scene = new Scene(root, 1000, 1000);
        
        primaryStage.setTitle("Studytracker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
