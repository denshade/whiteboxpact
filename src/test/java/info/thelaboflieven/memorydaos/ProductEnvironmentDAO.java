package info.thelaboflieven.memorydaos;

import info.thelaboflieven.ProductEnvironment;
import info.thelaboflieven.ProductEnvironmentRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductEnvironmentDAO implements ProductEnvironmentRepository {

        private Map<Integer, List<ProductEnvironment>> productEnvironments = new HashMap<>();

        @Override
        public void createProductEnvironment(ProductEnvironment productEnvironment) throws SQLException {
            try {
                int productId = productEnvironment.getProduct();
                List<ProductEnvironment> environments = productEnvironments.getOrDefault(productId, new ArrayList<>());
                environments.add(productEnvironment);
                productEnvironments.put(productId, environments);
            } catch (Exception e) {
                throw new SQLException("Failed to create ProductEnvironment");
            }
        }

        @Override
        public ProductEnvironment getProductEnvironment(int productId, String environment) throws SQLException {
            try {
                List<ProductEnvironment> environments = productEnvironments.get(productId);
                if (environments != null) {
                    for (ProductEnvironment env : environments) {
                        if (env.getEnvironment().equals(environment)) {
                            return env;
                        }
                    }
                }
                throw new SQLException("ProductEnvironment not found");
            } catch (Exception e) {
                throw new SQLException("Failed to get ProductEnvironment");
            }
        }

        @Override
        public List<ProductEnvironment> getAllProductEnvironments(int productId) throws SQLException {
            try {
                List<ProductEnvironment> environments = productEnvironments.get(productId);
                return (environments != null) ? new ArrayList<>(environments) : new ArrayList<>();
            } catch (Exception e) {
                throw new SQLException("Failed to retrieve ProductEnvironments");
            }
        }

        @Override
        public void updateProductEnvironment(ProductEnvironment productEnvironment) throws SQLException {
            try {
                int productId = productEnvironment.getProduct();
                List<ProductEnvironment> environments = productEnvironments.get(productId);
                if (environments != null) {
                    for (int i = 0; i < environments.size(); i++) {
                        ProductEnvironment env = environments.get(i);
                        if (env.getEnvironment().equals(productEnvironment.getEnvironment())) {
                            environments.set(i, productEnvironment);
                            productEnvironments.put(productId, environments);
                            return;
                        }
                    }
                }
                throw new SQLException("ProductEnvironment not found for update");
            } catch (Exception e) {
                throw new SQLException("Failed to update ProductEnvironment");
            }
        }

        @Override
        public void deleteProductEnvironment(int productId, String environment) throws SQLException {
            try {
                List<ProductEnvironment> environments = productEnvironments.get(productId);
                if (environments != null) {
                    ProductEnvironment environmentToDelete = null;
                    for (ProductEnvironment env : environments) {
                        if (env.getEnvironment().equals(environment)) {
                            environmentToDelete = env;
                            break;
                        }
                    }
                    if (environmentToDelete != null) {
                        environments.remove(environmentToDelete);
                        productEnvironments.put(productId, environments);
                    } else {
                        throw new SQLException("ProductEnvironment not found for deletion");
                    }
                } else {
                    throw new SQLException("Product not found for deletion");
                }
            } catch (Exception e) {
                throw new SQLException("Failed to delete ProductEnvironment");
            }
        }

}
