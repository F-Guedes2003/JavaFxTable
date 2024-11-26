package org.example.tableview;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import org.example.tableview.Enums.RadioStates;

import java.util.List;


public class DeleteController {
    private ObservableList<Product> list;

    @FXML RadioButton skuRadioBtn;
    @FXML RadioButton nameRadioBtn;
    @FXML TextField txtField;
    @FXML Label label;

    @FXML
    public void initialize() {
        var group = new ToggleGroup();
        skuRadioBtn.setToggleGroup(group);
        nameRadioBtn.setToggleGroup(group);
    }

    public void close() {
        Stage stage = (Stage) label.getScene().getWindow();
        deleteFromDatabase();
        updateList();
        stage.close();
    }

    private void updateList() {
        var service = new ProductDbService();
        List<Product> products = service.getAll();
        list.clear();
        list.addAll(products);
    }

    private void deleteFromDatabase() {
        var service = new ProductDbService();
        String delectionParam = txtField.getText();

        if(verifyRadioBtn() == RadioStates.NAME) service.deleteByName(delectionParam);

        else service.deleteBySku(delectionParam);
    }

    public void setDelectionFieldType() {
        if(verifyRadioBtn() == RadioStates.NAME) label.setText("Name:");
        else label.setText("Sku:");
    }

    private RadioStates verifyRadioBtn() {
        if(skuRadioBtn.isSelected()) return RadioStates.SKU;

        return RadioStates.NAME;
    }

    public void setList(ObservableList<Product> list) {
        this.list = list;
    }


}
