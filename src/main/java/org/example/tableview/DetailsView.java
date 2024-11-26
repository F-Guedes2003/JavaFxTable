package org.example.tableview;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Objects;

public class DetailsView {
    public void start(Product product) {
        FXMLLoader loader = new FXMLLoader();


        try {
            Pane pane = loader.load(Objects.requireNonNull(getClass().getResource("productDetails.fxml")).openStream());
            DetailsController controller = loader.getController();
            controller.setProduct(product);

            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
