package org.example.tableview;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class DeleteView {
    public void start(ObservableList<Product> productList) {
        FXMLLoader loader = new FXMLLoader();

        try{
            Pane pane = loader.load(Objects.requireNonNull(getClass().getResource("deleteView.fxml")).openStream());
            DeleteController controller = loader.getController();
            controller.setList(productList);

            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            System.err.println(e + ": Failed to open the delete view");
        }
    }
}
