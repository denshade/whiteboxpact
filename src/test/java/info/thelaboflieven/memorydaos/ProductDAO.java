package info.thelaboflieven.memorydaos;

import info.thelaboflieven.Product;
import info.thelaboflieven.ProductRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements ProductRepository {

        private List<Product> products = new ArrayList<>();
        private int nextProductId = 1;

        @Override
        public void createProduct(Product product) throws SQLException {
            try {
                product.setId(nextProductId);
                products.add(product);
                nextProductId++;
            } catch (Exception e) {
                throw new SQLException("Failed to create Product");
            }
        }

        @Override
        public Product getProductById(int productId) throws SQLException {
            try {
                for (Product product : products) {
                    if (product.getId() == productId) {
                        return product;
                    }
                }
                throw new SQLException("Product not found");
            } catch (Exception e) {
                throw new SQLException("Failed to get Product by ID");
            }
        }

        @Override
        public List<Product> getAllProducts() throws SQLException {
            try {
                return new ArrayList<>(products);
            } catch (Exception e) {
                throw new SQLException("Failed to retrieve all Products");
            }
        }

        @Override
        public void updateProduct(Product product) throws SQLException {
            try {
                int index = -1;
                for (int i = 0; i < products.size(); i++) {
                    if (products.get(i).getId() == product.getId()) {
                        index = i;
                        break;
                    }
                }
                if (index != -1) {
                    products.set(index, product);
                } else {
                    throw new SQLException("Product not found for update");
                }
            } catch (Exception e) {
                throw new SQLException("Failed to update Product");
            }
        }

        @Override
        public void deleteProduct(int productId) throws SQLException {
            try {
                Product productToDelete = null;
                for (Product product : products) {
                    if (product.getId() == productId) {
                        productToDelete = product;
                        break;
                    }
                }
                if (productToDelete != null) {
                    products.remove(productToDelete);
                } else {
                    throw new SQLException("Product not found for deletion");
                }
            } catch (Exception e) {
                throw new SQLException("Failed to delete Product");
            }
        }

}
