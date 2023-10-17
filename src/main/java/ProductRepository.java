import java.sql.SQLException;
import java.util.List;

public interface ProductRepository {
    void createProduct(Product product) throws SQLException;

    Product getProductById(int productId) throws SQLException;

    List<Product> getAllProducts() throws SQLException;

    void updateProduct(Product product) throws SQLException;

    void deleteProduct(int productId) throws SQLException;
}
