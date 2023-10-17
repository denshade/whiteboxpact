package info.thelaboflieven.sqldao;

import info.thelaboflieven.PactRepository;
import info.thelaboflieven.ProductEnvironmentRepository;
import info.thelaboflieven.ProductRepository;
import info.thelaboflieven.SupportedPactRepository;

import java.sql.Connection;
public class SQLDAOFactory implements DAOFactory {
    SQLDAOFactory(Connection connection) {
        productDAO = new ProductDAO(connection);
        productEnvironmentDAO = new ProductEnvironmentDAO(connection);
        pactDAO = new PactDAO(connection);
        supportedPactDAO = new SupportedPactDAO(connection);
    }
    private ProductRepository productDAO;
    private ProductEnvironmentRepository productEnvironmentDAO;
    private PactRepository pactDAO;
    private SupportedPactRepository supportedPactDAO;

    @Override
    public ProductRepository getProductDAO() {
        return productDAO;
    }

    @Override
    public ProductEnvironmentRepository getProductEnvironmentDAO() {
        return productEnvironmentDAO;
    }

    @Override
    public PactRepository getPactDAO() {
        return pactDAO;
    }

    @Override
    public SupportedPactRepository getSupportedPactDAO() {
        return supportedPactDAO;
    }
}
