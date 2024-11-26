package org.example.tableview;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDbService {
    public void save(Product product) {
        String sql = """
                INSERT INTO product (sku, name, price, quantity)
                VALUES (?, ?, ?, ?);
                """;

        try(var connection = DriverManager.getConnection("jdbc:sqlite:products.db")) {
            var statement = connection.prepareStatement(sql);
            statement.setString(1, product.getSku());
            statement.setString(2, product.getName());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getQuantity());
            statement.executeUpdate();
        } catch(SQLException e) {
            System.err.println(e.getMessage() + ": Failed to insert product on database (ProductDbService)");
        }
    }

    public Optional<Product> findOne(String sku) {
        String sql = """
                SELECT * FROM product
                WHERE sku = ?
                """;

        try(var connection = DriverManager.getConnection("jdbc:sqlite:products.db")){
            System.out.println(sku);
            var statement = connection.prepareStatement(sql);
            statement.setString(1, sku);
            ResultSet qr = statement.executeQuery();

            if(qr.next()) {
                var name = qr.getString("name");
                var prodSku = qr.getString("sku");
                var price = qr.getDouble("price");
                var quantity = qr.getInt("quantity");
                return Optional.of(new Product(name, prodSku, price, quantity));
            }
        } catch(SQLException e) {
            System.err.println(e + "failed to load the products from database");
        }

        return Optional.empty();
    }

    public List<Product> getAll() {
        String sql = """
                SELECT *
                FROM product;
                """;

        try(var connection = DriverManager.getConnection("jdbc:sqlite:products.db")){
            var statement = connection.createStatement();
            var qr = statement.executeQuery(sql);
            List<Product> list = new ArrayList<>();
            while (qr.next()) {
                list.add(new Product(
                        qr.getString("name"),
                        qr.getString("sku"),
                        qr.getDouble("price"),
                        qr.getInt("quantity"))
                );
            }

            return list;
        } catch(SQLException e) {
            System.err.println(e + "failed to load the products from database");
        }


        return new ArrayList<>();
    }

    public void update(String name, String newSku, double price, int quantity, String oldSku) {
        String sql = """
                    UPDATE product
                    SET name = ?, sku = ?, price = ?, quantity = ?
                    WHERE sku = ?;
                """;
        try(var connection = DriverManager.getConnection("jdbc:sqlite:products.db")) {
            var statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, newSku);
            statement.setDouble(3, price);
            statement.setInt(4, quantity);
            statement.setString(5, oldSku);
            statement.executeUpdate();
        } catch(SQLException e) {
            System.err.println(e + ": Failed to update product on database!");
        }
    }

    public void deleteBySku(String sku) {
        String sql = """
                    DELETE FROM product
                    WHERE sku = ?;
                """;

        try(var connection = DriverManager.getConnection("jdbc:sqlite:products.db")) {
            var statement = connection.prepareStatement(sql);
            statement.setString(1, sku);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e + ": Failed to delete product from db");
        }
    }

    public void deleteByName(String name) {
        String sql = """
                    DELETE FROM product
                    WHERE name = ?;
                """;
        try(var connection = DriverManager.getConnection("jdbc:sqlite:products.db")) {
            var statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e + ": Failed to delete product from db");
        }
    }
}
