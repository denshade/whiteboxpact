import java.sql.SQLException;
import java.util.List;

public interface ProductEnvironmentRepository {
    void createProductEnvironment(ProductEnvironment productEnvironment) throws SQLException;

    ProductEnvironment getProductEnvironment(int productId, String environment) throws SQLException;

    List<ProductEnvironment> getAllProductEnvironments(int productId) throws SQLException;

    void updateProductEnvironment(ProductEnvironment productEnvironment) throws SQLException;

    void deleteProductEnvironment(int productId, String environment) throws SQLException;
}
