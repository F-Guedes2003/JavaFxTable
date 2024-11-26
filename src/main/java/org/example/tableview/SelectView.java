package org.example.tableview;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.tableview.Enums.Context;

import java.io.IOException;
import java.util.Objects;

public class SelectView {
    public void start(ObservableList<Product> productList, Context context) {
        FXMLLoader loader = new FXMLLoader();

        try {
            Pane pane = loader.load(Objects.requireNonNull(getClass().getResource("selectProductBySkuView.fxml")).openStream());
            SelectController controller = loader.getController();
            controller.setList(productList);
            controller.setContext(context);
            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch(IOException e) {
            System.err.println(e + ": Failed to render the product selection view!");
        }

        Stage stage = new Stage();

    }
}
