package portfolioservice.coin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Coin {
    public String id;
    public String symbol;
    public String image;
    public double current_price;
    public Object market_cap;
    public int market_cap_rank;
    public long fully_diluted_valuation;
    public Object total_volume;
    public double high_24h;
    public double low_24h;
    public double price_change_24h;
    public double price_change_percentage_24h;
    public double market_cap_change_24h;
    public double market_cap_change_percentage_24h;
    public double circulating_supply;
    public double total_supply;
    public double max_supply;
    public double ath;
    public double ath_change_percentage;
    public Date ath_date;
    public double atl;
    public double atl_change_percentage;
    public Date atl_date;
    public Date last_updated;

    public Coin() {
    }


}
