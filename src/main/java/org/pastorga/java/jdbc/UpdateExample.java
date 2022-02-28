package org.pastorga.java.jdbc;

import org.pastorga.java.jdbc.model.Product;
import org.pastorga.java.jdbc.repository.ProductRepositoryImplement;
import org.pastorga.java.jdbc.repository.Repository;
import org.pastorga.java.jdbc.util.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class UpdateExample {
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

            System.out.println("================ Update ================");
            Product product = new Product();
            //As ID's provided here, it updates with the attributes below,
            //if ID is not provided, it inserts it, so it's an update statement.
            product.setId(4L);
            product.setName("Generic Product Name");
            product.setPrice(450);
            product.setRegisterDate(new Date());
            repository.save(product);
            System.out.println("Updated product");

            System.out.println("================ ListAll ================");
            repository.listAll().forEach(System.out::println);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
