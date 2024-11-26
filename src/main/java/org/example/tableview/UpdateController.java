package org.example.tableview;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;

public class UpdateController {
    private ObservableList<Product> list;
    private String sku;
    @FXML TextField nameField;
    @FXML TextField skuField;
    @FXML TextField priceField;
    @FXML TextField quantityField;

    public void close() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        updateDatabase();
        stage.close();
    }

    private void getProductData() {
        String sql = """
                SELECT * FROM product
                WHERE sku = ?;
                """;

        try(var connection = DriverManager.getConnection("jdbc:sqlite:products.db")) {
            var statement = connection.prepareStatement(sql);
            statement.setString(1, sku);
            ResultSet qr = statement.executeQuery();
            if(qr.next()) {

                nameField.setText(qr.getString("name"));
                skuField.setText(qr.getString("sku"));
                double price = qr.getDouble("price");
                int quantity = qr.getInt("quantity");
                priceField.setText(Double.toString(price));
                quantityField.setText(Integer.toString(quantity));
            }
        } catch(SQLException e) {
            System.err.println(e + ": Failed to get products data on UpdateController");
        }
    }

    private void updateDatabase() {
        var service = new ProductDbService();
        var name = nameField.getText();
        var newSku = skuField.getText();
        var price = priceField.getText();
        var quantity = quantityField.getText();

        service.update(name, newSku, Double.parseDouble(price), Integer.parseInt(quantity), sku);
        updateList();
    }

    public void updateList() {
        var service = new ProductDbService();
        var products = service.getAll();
        list.clear();
        list.addAll(products);
    }

    public void setList(ObservableList<Product> list) {
        this.list = list;
    }

    public void setSku(String sku) {
        this.sku = sku;
        getProductData();
    }
}
