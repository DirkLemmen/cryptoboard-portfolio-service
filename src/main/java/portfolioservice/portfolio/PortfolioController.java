package portfolioservice.portfolio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import portfolioservice.coin.PortfolioCoin;

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
    public List<PortfolioCoin>  GetPortfolio(@PathVariable("userId") String uid) throws Exception {
        return portfolioService.getCoinsInPortfolio(uid);
    }

    @DeleteMapping(path = "/{userId}/{coinId}")
    public void DeleteCoinFromPortfolio(@PathVariable("userId") String uid, @PathVariable("coinId") Long coinId) {
        portfolioService.deleteCoinFromPortfolio(uid, coinId);
    }
}
