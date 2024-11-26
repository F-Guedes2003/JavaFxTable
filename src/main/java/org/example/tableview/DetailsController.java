package org.example.tableview;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class DetailsController {
    private Product product;


    @FXML Text name;
    @FXML Label price;
    @FXML Label quantity;
    @FXML Label SKU;

    public void setProduct(Product product) {
        this.product = product;
        setProductLabel();
    }

    public void setProductLabel() {
        name.setText(product.getName());
        price.setText(Double.toString(product.getPrice()));
        quantity.setText(Integer.toString(product.getQuantity()));
        SKU.setText(product.getSku());
    }
}
