package portfolioservice.portfolio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolioservice.coin.Coin;
import portfolioservice.coin.CoinRepository;

import java.util.List;

@Service
public class PortfolioService {

    private final CoinRepository coinRepository;

    @Autowired
    public PortfolioService(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }

    public List<Coin> GetCoinsInPortfolio(String userId){
        return coinRepository.findByUid(userId);
    }
}
