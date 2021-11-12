package portfolioservice.coin;

import javax.persistence.*;

@Entity
@Table
public class PortfolioCoin {
    @Id
    @SequenceGenerator(
            name = "coin_sequence",
            sequenceName = "coin_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "coin_sequence"
    )

    private Long id;
    private double amount;
    private String coinId;
    private String symbol;
    private String uid;

    public PortfolioCoin() {
    }

    public PortfolioCoin(double amount, String coinId, String symbol, String uid) {
        this.amount = amount;
        this.coinId = coinId;
        this.symbol = symbol;
        this.uid = uid;
    }

    public PortfolioCoin(Long id, double amount, String coinId, String symbol, String uid) {
        this.id = id;
        this.amount = amount;
        this.coinId = coinId;
        this.symbol = symbol;
        this.uid = uid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCoinId() {
        return coinId;
    }

    public void setCoinId(String coinId) {
        this.coinId = coinId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
