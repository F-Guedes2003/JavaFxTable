module org.example.tableview {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jdk.jconsole;


    opens org.example.tableview to javafx.fxml;
    exports org.example.tableview;
}