package org.example.tableview;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.tableview.Enums.Context;

import java.util.List;

public class ProductController {
    ObservableList<Product> list = FXCollections.observableArrayList();

    @FXML TableView<Product> table;
    @FXML TableColumn<Product, String> sku;
    @FXML TableColumn<Product, String> name;
    @FXML TableColumn<Product, Double> price;
    @FXML TableColumn<Product, Double> total;
    @FXML TableColumn<Product, Integer> quantity;

    @FXML
    public void initialize() {
        sku.setCellValueFactory(new PropertyValueFactory<Product, String>("sku"));
        name.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        price.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        quantity.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
        total.setCellValueFactory(new PropertyValueFactory<Product, Double>("total"));

        load();
        table.setItems(list);
    }

    public void load() {
        var service = new ProductDbService();
        List<Product> products = service.getAll();
        list.addAll(products);
    }

    public void handleInsertClick() {
        InsertView view = new InsertView();
        view.start(list);
    }

    public void handleDeleteClick() {
        DeleteView view = new DeleteView();
        view.start(list);
    }

    public void handleUpdateClick() {
        SelectView view = new SelectView();
        view.start(list, Context.UPDATE);
    }

    public void handleDetailsClick() {
        var view = new SelectView();
        view.start(list, Context.DETAIL);
    }
}
