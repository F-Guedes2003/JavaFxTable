package org.example.tableview;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class UpdateView{

    public void start(ObservableList<Product> productList, String sku) {
        FXMLLoader loader = new FXMLLoader();
        try{
            Pane pane = loader.load(Objects.requireNonNull(getClass().getResource("updateView.fxml")).openStream());
            UpdateController controller = loader.getController();
            controller.setList(productList);
            controller.setSku(sku);
            Scene scene =  new Scene(pane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            System.err.println(e + "Failed to open the update view!");
        }
    }
}
