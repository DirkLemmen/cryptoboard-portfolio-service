package portfolioservice.portfolio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import portfolioservice.account.Account;
import portfolioservice.account.AccountRepository;
import portfolioservice.coin.PortfolioCoin;
import portfolioservice.coin.CoinRepository;
import portfolioservice.exception.ApiRequestException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@Service
public class PortfolioService {

    private final CoinRepository coinRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public PortfolioService(CoinRepository coinRepository, AccountRepository accountRepository) {
        this.coinRepository = coinRepository;
        this.accountRepository = accountRepository;
    }

    public List<PortfolioCoin> getCoinsInPortfolio(String uid) throws Exception {
        if (coinRepository.countByUid(uid) == 0)
        {
            throw new ApiRequestException("There are no coins found");
        }
        else {
            return coinRepository.findByUid(uid);
        }
    }

    public void saveCoinInPortfolio(PortfolioCoin coin) {
        coinRepository.save(coin);
    }

    public void deleteCoinFromPortfolio(String uid, Long id) {
        // Check if coin exists in portfolio
        Optional<PortfolioCoin> coin = coinRepository.findById(id);
        if (coin.isPresent()) {
            // Calculate credit
            double amount = coin.get().getAmount();
            double value = getCoinValue(coin.get().getCoinId());
            double credit = amount * value;
            // Add to account credit
            Optional<Account> optionalAccount = accountRepository.findByUid(uid);
            if (optionalAccount.isPresent())
            {
                Account account = optionalAccount.get();
                account.setCredit(credit);
                accountRepository.save(account);

                // Delete coin from portfolio
                coinRepository.deleteById(id);
            }
            else
            {
                throw new ApiRequestException("Couldn't find account belonging to " + uid);
            }
        }
        else {
            throw new IllegalStateException("Coin "+id+" does not exist");
        }
    }

    public double getCoinValue(String coin) {
        RestTemplate restTemplate = new RestTemplate();
        String currency = "eur";
        String uri = "https://api.coingecko.com/api/v3/simple/price?ids="+coin+"&vs_currencies=" + currency;

        try {
            LinkedHashMap response = restTemplate.getForObject(uri, LinkedHashMap.class);
            LinkedHashMap t = (LinkedHashMap) response.get(coin);

            double value = Double.parseDouble(t.get(currency).toString());

            return value;
        }
        catch (Exception ex)
        {
            throw new ApiRequestException("Couldn't find "+ coin +" value");
        }
    }
}
