package org.example.tableview;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InsertController {
    private ObservableList<Product> list;
    @FXML TextField nameField;
    @FXML TextField skuField;
    @FXML TextField priceField;
    @FXML TextField quantityField;


    public void close() {
        try {
            String name = nameField.getText();
            String sku = skuField.getText();
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            persistOnDatabase(name, sku, price, quantity);
        } catch (IllegalArgumentException e) {
            System.err.println(e + ": You are trying to assign a non numerical value to price or quantity!");
        }
    }

    public void persistOnDatabase(String name, String sku, double price, int quantity) {
        var service = new ProductDbService();
        Product product = new Product(name, sku, price, quantity);
        service.save(product);
        list.add(product);
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

    public void setList(ObservableList<Product> list) {
        this.list = list;
    }
}
