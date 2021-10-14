package portfolioservice.coin;

import javax.persistence.*;

@Entity
@Table
public class Coin {
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
    private Float amount;
    private String name;
    private String symbol;
    private String uid;

    public Coin() {
    }

    public Coin(Float amount, String name, String symbol, String uid) {
        this.amount = amount;
        this.name = name;
        this.symbol = symbol;
        this.uid = uid;
    }

    public Coin(Long id, Float amount, String name, String symbol, String uid) {
        this.id = id;
        this.amount = amount;
        this.name = name;
        this.symbol = symbol;
        this.uid = uid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}
