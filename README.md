# JakartaEE9 CRUD
JakartaEE9 simple CRUD utilizing Java interfaces for abstraction and implementation of MySQL database query management.

----------

## Remarkable information
Design patterns utilized in the implementation of the database access through code as shown below:

```java
public interface Repository<T> {
    List<T> listAll();

    T getById(Long id);

    void save(T t);

    void delete(Long id);
}
```
and then accesed via implemented interface

```java
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
```
which leads to easy manipulation of the data.

```java
Repository<Product> repository = new ProductRepositoryImplement();
System.out.println("================ ListAll ================");
repository.listAll().forEach(System.out::println);
```
