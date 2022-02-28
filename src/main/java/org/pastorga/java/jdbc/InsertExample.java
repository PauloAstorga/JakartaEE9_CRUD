package org.pastorga.java.jdbc;

import org.pastorga.java.jdbc.model.Product;
import org.pastorga.java.jdbc.repository.ProductRepositoryImplement;
import org.pastorga.java.jdbc.repository.Repository;
import org.pastorga.java.jdbc.util.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class InsertExample {
    public static void main(String[] args) {

        try (
                Connection conn = DBConnection.getInstance();
                )
        {
            Repository<Product> repository = new ProductRepositoryImplement();
            System.out.println("================ ListAll ================");
            repository.listAll().forEach(System.out::println);

            System.out.println("================ getById ================");
            System.out.println(repository.getById(1L));

            System.out.println("================ Insert ================");
            Product product = new Product();
            //As coded, if the ID's not specified, it's an insert, otherwise it's an update
            //so, as no ID is provided here, it's an insert statement.
            product.setName("Generic Product Name");
            product.setPrice(350);
            product.setRegisterDate(new Date());
            repository.save(product);
            System.out.println("Product added");

            System.out.println("================ ListAll ================");
            repository.listAll().forEach(System.out::println);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
