package portfolioservice.portfolio;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import portfolioservice.coin.PortfolioCoin;
import portfolioservice.coin.CoinRepository;
import portfolioservice.coin.SelectedCoin;
import portfolioservice.exception.ApiRequestException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Service
public class PortfolioService {

    private final CoinRepository coinRepository;

    @Autowired
    public PortfolioService(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }

    public List<PortfolioCoin> GetCoinsInPortfolio(String uid) throws Exception {
        if (coinRepository.countByUid(uid) == 0)
        {
            throw new ApiRequestException("There are no coins found");
        }
        else {
            return coinRepository.findByUid(uid);
        }
    }

    public void SaveCoinInPortfolio(PortfolioCoin coin) {
        coinRepository.save(coin);
    }

//    public void DeleteCoinFromPortfolio(String uid, Long id) {
//        // Check if coin exists
//        Optional<PortfolioCoin> coin = coinRepository.findById(id);
//        if (coin.isPresent()) {
//            // Add value to account credit
//                // Calculate worth
//                    // Get actual worth
//                    // Amount x Worth
//            float amount = coinRepository.getById(id).getAmount();
////            float worth =
////            double credit =  x 1;
//                    // Add to account credit
//            // Delete coin from portfolio
//            coinRepository.deleteById(id);
//        }
//        else {
//            throw new IllegalStateException("Coin does not exist");
//        }
//    }

    public void GetCoinWorth() throws IOException {
        RestTemplate restTemplate = new RestTemplate();

        ObjectMapper objectMapper = new ObjectMapper();

        String uri = "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin&vs_currencies=eur";
        Object response = restTemplate.getForObject(uri, Object.class);

        SelectedCoin selectedCoin = objectMapper.readValue(new URL(uri), SelectedCoin.class);
        System.out.println(response.toString());
    }
}
