package info.thelaboflieven.sqldao;

import info.thelaboflieven.PactRepository;
import info.thelaboflieven.ProductEnvironmentRepository;
import info.thelaboflieven.ProductRepository;
import info.thelaboflieven.SupportedPactRepository;

public interface DAOFactory {
    ProductRepository getProductDAO();

    ProductEnvironmentRepository getProductEnvironmentDAO();

    PactRepository getPactDAO();

    SupportedPactRepository getSupportedPactDAO();
}
