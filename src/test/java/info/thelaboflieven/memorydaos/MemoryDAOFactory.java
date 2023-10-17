package info.thelaboflieven.memorydaos;

import info.thelaboflieven.PactRepository;
import info.thelaboflieven.ProductEnvironmentRepository;
import info.thelaboflieven.ProductRepository;
import info.thelaboflieven.SupportedPactRepository;
import info.thelaboflieven.sqldao.DAOFactory;

import java.sql.Connection;
public class MemoryDAOFactory implements DAOFactory {
    MemoryDAOFactory() {
        productDAO = new ProductDAO();
        productEnvironmentDAO = new ProductEnvironmentDAO();
        pactDAO = new PactDAO();
        supportedPactDAO = new SupportedPactDAO();
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
