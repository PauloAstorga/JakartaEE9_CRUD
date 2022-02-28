package org.pastorga.java.jdbc;

import org.pastorga.java.jdbc.model.Product;
import org.pastorga.java.jdbc.repository.ProductRepositoryImplement;
import org.pastorga.java.jdbc.repository.Repository;
import org.pastorga.java.jdbc.util.DBConnection;

import java.sql.*;
import java.util.Date;

public class ReadExample {
    public static void main(String[] args) {

        try (
                //AutoClose the connection, it's the same as conn.close(); at the end
                Connection conn = DBConnection.getInstance();
                )
        {
            Repository<Product> repository = new ProductRepositoryImplement();
            System.out.println("================ ListAll ================");
            repository.listAll().forEach(System.out::println);

            System.out.println("================ getById ================");
            System.out.println(repository.getById(1L));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
