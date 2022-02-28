package org.pastorga.java.jdbc.repository;

import org.pastorga.java.jdbc.model.Product;
import org.pastorga.java.jdbc.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

    public class ProductRepositoryImplement implements Repository<Product> {

        private Connection getConnection() throws SQLException {
            return DBConnection.getInstance();
        }

        @Override
        public List<Product> listAll() {
            List<Product> products = new ArrayList<>();

            try (Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM productos")) { //AutoClose stmt.close(); rs.close();
                while (rs.next()) {
                    products.add( crearProducto(rs) );
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return products;
        }

        @Override
        public Product getById(Long id) {
            Product product = null;

            try(PreparedStatement stmt = getConnection().
                    prepareStatement("SELECT * FROM productos WHERE id = ?")) { //AutoClose stmt.close();
                stmt.setLong(1, id);

                try (ResultSet rs = stmt.executeQuery()) { //AutoClose -> rs.close();
                    while (rs.next()) {
                        product = crearProducto(rs);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return product;
        }

    @Override
    public void save(Product product) {
        String sql;
        if (product.getId() !=null && product.getId() > 0) {
            sql = "UPDATE productos SET nombre = ?, precio = ? WHERE id = ?";
        } else {
            sql = "INSERT INTO productos (nombre, precio, fecha_registro) VALUES (?, ?, ?)";
        }
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) { //AutoClose stmt.close();
            stmt.setString(1, product.getName());
            stmt.setInt(2, product.getPrice());

            if (product.getId() !=null && product.getId() > 0) {
                stmt.setLong(3, product.getId());
            } else {
                stmt.setDate(3, new Date(product.getRegisterDate().getTime()));
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM productos WHERE id = ?")) { //AutoClose stmt.close();
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Product crearProducto(ResultSet rs) throws SQLException {
        return new Product(
                rs.getLong("id"),
                rs.getString("nombre"),
                rs.getInt("precio"),
                rs.getDate("fecha_registro")
        );
    }
}
