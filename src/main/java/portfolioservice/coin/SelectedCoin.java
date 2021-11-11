package portfolioservice.coin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SelectedCoin {
    private String key;
    @JsonProperty("eur")
    public double value;
}
