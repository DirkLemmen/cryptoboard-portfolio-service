package portfolioservice.portfolio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import portfolioservice.coin.Coin;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/private/portfolio", produces = MediaType.APPLICATION_JSON_VALUE)
public class PortfolioController {

    private final PortfolioService portfolioService;

    @Autowired
    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping(path = "{userId}")
    public List<Coin>  GetPortfolio(@PathVariable("userId") String userId)
    {
        return portfolioService.GetCoinsInPortfolio(userId);
    }
}
