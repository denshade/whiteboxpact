package info.thelaboflieven;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

/*
 Here is the happy path:
 1. the consumer confirms they support this pact. confirmPactVerified
 2. this version is deployed to an environment like production updateProductEnvironment
 3. the provider wants to deploy to production, but has not verified it can. It has not verified the production pact //canIDeploy
 4. the provider confirms it supports the pact on production. (Or all pacts currently deployed!) //confirmPactVerified
 5. the provider deploys on production. //updateProductEnvironment
 */
public class OneProviderOneConsumerStrategy {
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
