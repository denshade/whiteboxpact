package info.thelaboflieven;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Date;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class OneProviderOneConsumerStrategyTest {

    @Test
    void confirmPactVerified() throws SQLException {
        var lib = mock(PactLib.class);
        var strategy = new OneProviderOneConsumerStrategy(lib);
        strategy.confirmPactVerified(new Product(1, "product"), new Pact(1, new Timestamp(new Date().getTime()), "{expect}"), "version");
        //verify(lib).
    }

    @Test
    void updateProductEnvironment() {
    }

    @Test
    void canIDeploy() {
    }
}