package org.pastorga.java.jdbc;

import org.pastorga.java.jdbc.model.Product;
import org.pastorga.java.jdbc.repository.ProductRepositoryImplement;
import org.pastorga.java.jdbc.repository.Repository;
import org.pastorga.java.jdbc.util.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class DeleteExample {
    public static void main(String[] args) {

        try (
                //AutoClose, caracteristica relativamente nueva de JAVA
                Connection conn = DBConnection.getInstance();
                )
        {
            Repository<Product> repository = new ProductRepositoryImplement();
            System.out.println("================ ListAll ================");
            repository.listAll().forEach(System.out::println);

            System.out.println("================ getById ================");
            System.out.println(repository.getById(1L));

            System.out.println("================ Delete Nuevo ================");
            Product product = new Product();
            product.setId(3L); //Hardcoded for testing
            repository.delete(product.getId());
            System.out.println("Deleted the product");

            System.out.println("================ ListAll After Delete ================");
            repository.listAll().forEach(System.out::println);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
