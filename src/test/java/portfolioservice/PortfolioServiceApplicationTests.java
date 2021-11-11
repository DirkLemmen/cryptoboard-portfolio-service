package portfolioservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import portfolioservice.portfolio.PortfolioController;

@SpringBootTest
class PortfolioServiceApplicationTests {

    @Autowired
    private PortfolioController portfolioController;

    @Test
    public void contextLoads() {
    }
}
