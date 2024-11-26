package org.example.tableview;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try(var connection = DriverManager.getConnection("jdbc:sqlite:products.db")) {

        } catch (SQLException e) {
            System.err.println(e + ": Failed to intialize database");
        }

        Pane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("productTable.fxml")));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
