package studytracker.ui;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import studytracker.domain.Course;
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
import studytracker.dao.CourseDao;
import studytracker.databases.Database;

public class UI extends Application {

    private int id;
    private TableView passedCourses = new TableView();
    private TableView appliedCourses = new TableView();

    @Override
    public void start(Stage primaryStage) throws SQLException, ClassNotFoundException {
        Database db = new Database("jdbc:sqlite:studies.db");
        CourseDao cd = new CourseDao(db);
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
        data.addAll(cd.passedCourses(id));
     

        Button button = new Button("Add");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                Course course = new Course(15,
                        id,
                        addName.getText(),
                        Integer.valueOf(addPoints.getText()),
                        Integer.valueOf(addGrade.getText()),
                        true);
                try {
                    course.setId(cd.getMaxId() + 1);
                } catch (SQLException ex) {
                    Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                }
                data.add(course);
                addName.clear();
                addPoints.clear();
                addGrade.clear();

                try {
                    cd.create(course);
                } catch (SQLException ex) {
                    Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        

        ObservableList list = hbox.getChildren();
        list.addAll(addName, addPoints, addGrade, button);

//        TableView passedCourses = new TableView();

        TableColumn courseNameColumn = new TableColumn("Course name");
        courseNameColumn.setMinWidth(200);

        courseNameColumn.setCellValueFactory(
                new PropertyValueFactory<Course, String>("Name")
        );
        TableColumn studypointsColumn = new TableColumn("Study points");
        studypointsColumn.setMinWidth(200);

        studypointsColumn.setCellValueFactory(
                new PropertyValueFactory<Course, String>("studyPoints")
        );
        TableColumn gradeColumn = new TableColumn("Grade");
        gradeColumn.setMinWidth(200);

        gradeColumn.setCellValueFactory(
                new PropertyValueFactory<Course, String>("Grade")
        );
        passedCourses.setItems(data);

        passedCourses.getColumns().addAll(courseNameColumn, studypointsColumn, gradeColumn);

        Label allStudyPoints = new Label("Total studypoints");

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
                    addPoints2.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        Button button2 = new Button("Add");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    Course course = new Course(cd.getMaxId() + 1,
                            id,
                            addName2.getText(),
                            Integer.valueOf(addPoints2.getText()),
                            -1,
                            false);
                    cd.create(course);
                    data2.add(course);
                    addName2.clear();
                    addPoints2.clear();

                } catch (SQLException ex) {
                    Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
        
        data2.addAll(cd.chosenCourses(id));

        ObservableList list2 = hbox2.getChildren();
        list2.addAll(addName2, addPoints2, button2);

//        TableView appliedCourses = new TableView();
        
        TableColumn courseIdColumn2 = new TableColumn("Course Id");
        courseIdColumn2.setMinWidth(100);
        
        courseIdColumn2.setCellValueFactory(
                new PropertyValueFactory<Course, String>("Id")
        );

        TableColumn courseNameColumn2 = new TableColumn("Course name");
        courseNameColumn2.setMinWidth(200);

        courseNameColumn2.setCellValueFactory(
                new PropertyValueFactory<Course, String>("Name")
        );
        
        TableColumn studypointsColumn2 = new TableColumn("Study points");
        studypointsColumn2.setMinWidth(200);

        studypointsColumn2.setCellValueFactory(
                new PropertyValueFactory<Course, String>("studyPoints")
        );

        appliedCourses.setItems(data2);

        appliedCourses.getColumns().addAll(courseIdColumn2, courseNameColumn2, studypointsColumn2);

        // TÄHÄN LOPPUU
        Label labelDelete = new Label("Delete by id: ");
        TextField tfDelete = new TextField();

        tfDelete.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    tfDelete.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        tfDelete.setPrefWidth(30);
        
        Button deleteButton = new Button();
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int number = Integer.valueOf(tfDelete.getText());
                for (int i = 0; i < data2.size(); i++) {
                    if (data2.get(i).getId() == number) {
                        Course course = data2.get(i);
                        try {
                            cd.delete(course.getId());
                        } catch (SQLException ex) {
                            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        data2.remove(i);
                        i = data2.size();
                    }
                }
                tfDelete.clear();
            }
        });

        Label labelPasscourse = new Label("Pass course with id and insert grade");

        TextField tfPass = new TextField();

        tfPass.setPrefWidth(30);
        Button passButton = new Button();
        passButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String passedCourse = tfPass.getText();
                String[] split = passedCourse.split(" ");
                int number = Integer.valueOf(split[0]);
                for (int i = 0; i < data2.size(); i++) {
                    if (data2.get(i).getId() == number) {
                        Course course = data2.get(i);
                        course.setPassed(true);
                        course.setGrade(Integer.valueOf(split[1]));
                        data.add(course);
                        data2.remove(i);
                        i = data2.size();
                    }
                }
                try {
                    cd.passCourse(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
                } catch (SQLException ex) {
                    Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                }
                tfPass.clear();
            }
        });

        deleteButton.setText("Delete");

        grid.add(labelDelete, 1, 4);
        grid.add(tfDelete, 1, 5);
        grid.add(deleteButton, 1, 6);

        passButton.setText("Pass course");

        grid.add(labelPasscourse, 1, 7);
        grid.add(tfPass, 1, 8);
        grid.add(passButton, 1, 9);

        grid.add(lb, 0, 0);
        grid.add(passedCourses, 0, 1);
        grid.add(hbox, 0, 3);

        grid.add(lb2, 1, 0);
        grid.add(appliedCourses, 1, 1);
        grid.add(hbox2, 1, 3);

        grid.add(allStudyPoints, 0, 4);

        StackPane root = new StackPane();
        root.getChildren().add(grid);

        Scene scene = new Scene(root, 1200, 1200);

        primaryStage.setTitle("Studytracker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void setId(int id) {
        this.id = id;
    }

//    public static void textLimiter(final TextField tf, final int maxLength) {
//            tf.textProperty().addListener(new ChangeListener<String>() {
//                @Override
//                public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
//                    if (tf.getText().length() > maxLength) {
//                        String s = tf.getText().substring(0, maxLength);
//                        tf.setText(s);
//                    }
//                }
//            });
//        }
}
