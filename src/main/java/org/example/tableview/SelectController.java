package org.example.tableview;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.tableview.Enums.Context;

import java.util.Optional;

public class SelectController {
    ObservableList<Product> list;
    Context context;
    @FXML TextField skuField;

    public void setList(ObservableList<Product> list) {
        this.list = list;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void close() {
        String sku = skuField.getText();
        Stage stage = (Stage) skuField.getScene().getWindow();

        if(context == Context.UPDATE) {
            UpdateView view = new UpdateView();
            view.start(list, sku);
            stage.close();
        }

        else {
            var service = new ProductDbService();
            Product product = service.findOne(sku)
                    .orElseThrow(() -> new NullPointerException("Failed to get the product by sku"));

            DetailsView view = new DetailsView();
            view.start(product);
            stage.close();
        }
    }
}
