import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class PactLib {

        private Connection connection;
        private ProductDAO productDAO;
        private ProductEnvironmentDAO productEnvironmentDAO;
        private PactDAO pactDAO;
        private SupportedPactDAO supportedPactDAO;

        public PactLib(Connection connection) {
            this.connection = connection;
            productDAO = new ProductDAO(connection);
            productEnvironmentDAO = new ProductEnvironmentDAO(connection);
            pactDAO = new PactDAO(connection);
            supportedPactDAO = new SupportedPactDAO(connection);
        }

        // Product methods
        public void createProduct(Product product) throws SQLException {
            productDAO.createProduct(product);
        }

        public Product getProductById(int productId) throws SQLException {
            return productDAO.getProductById(productId);
        }

        public List<Product> getAllProducts() throws SQLException {
            return productDAO.getAllProducts();
        }

        public void updateProduct(Product product) throws SQLException {
            productDAO.updateProduct(product);
        }

        public void deleteProduct(int productId) throws SQLException {
            productDAO.deleteProduct(productId);
        }

        // ProductEnvironment methods
        public void createProductEnvironment(ProductEnvironment productEnvironment) throws SQLException {
            productEnvironmentDAO.createProductEnvironment(productEnvironment);
        }

        public ProductEnvironment getProductEnvironment(int productId, String environment) throws SQLException {
            return productEnvironmentDAO.getProductEnvironment(productId, environment);
        }

        public List<ProductEnvironment> getAllProductEnvironments(int productId) throws SQLException {
            return productEnvironmentDAO.getAllProductEnvironments(productId);
        }

        public void updateProductEnvironment(ProductEnvironment productEnvironment) throws SQLException {
            productEnvironmentDAO.updateProductEnvironment(productEnvironment);
        }

        public void deleteProductEnvironment(int productId, String environment) throws SQLException {
            productEnvironmentDAO.deleteProductEnvironment(productId, environment);
        }

        // Pact methods
        public void createPact(Pact pact) throws SQLException {
            pactDAO.createPact(pact);
        }

        public Pact getPactById(int pactId) throws SQLException {
            return pactDAO.getPactById(pactId);
        }

        public List<Pact> getAllPacts() throws SQLException {
            return pactDAO.getAllPacts();
        }

        public void updatePact(Pact pact) throws SQLException {
            pactDAO.updatePact(pact);
        }

        public void deletePact(int pactId) throws SQLException {
            pactDAO.deletePact(pactId);
        }

        // SupportedPact methods
        public void createSupportedPact(SupportedPact supportedPact) throws SQLException {
            supportedPactDAO.createSupportedPact(supportedPact);
        }

        public SupportedPact getSupportedPactById(int pactId, int product) throws SQLException {
            return supportedPactDAO.getSupportedPactById(pactId, product);
        }

        public List<SupportedPact> getAllSupportedPacts() throws SQLException {
            return supportedPactDAO.getAllSupportedPacts();
        }

        public void updateSupportedPact(SupportedPact supportedPact) throws SQLException {
            supportedPactDAO.updateSupportedPact(supportedPact);
        }

        public void deleteSupportedPact(int pactId, int product) throws SQLException {
            supportedPactDAO.deleteSupportedPact(pactId, product);
        }

        /*
         Here is the happy path:
         1. the consumer confirms they support this pact. confirmPactVerified
         2. this version is deployed to an environment like production updateProductEnvironment
         3. the provider wants to deploy to production, but has not verified it can. It has not verified the production pact //canIDeploy
         4. the provider confirms it supports the pact on production. (Or all pacts currently deployed!) //confirmPactVerified
         5. the provider deploys on production. //updateProductEnvironment
         */
        public static class OneProviderOneConsumerStrategy {
            private PactLib lib;

            public OneProviderOneConsumerStrategy(PactLib lib) {
                this.lib = lib;
            }


            //We confirm this pact was verified.
            public void confirmPactVerified(Product product, Pact pact, String version) throws SQLException {
                lib.createSupportedPact(new SupportedPact(pact.getId(), product.getId(), version, new Timestamp(new Date().getTime())));

            }
            public void updateProductEnvironment(ProductEnvironment productEnv) throws SQLException {
                lib.updateProductEnvironment(productEnv);
            }

            public boolean canIDeploy() {
                //
                return false;
            }
        }

}
