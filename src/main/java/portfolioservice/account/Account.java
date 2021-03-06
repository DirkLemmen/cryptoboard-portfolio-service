package portfolioservice.account;

import javax.persistence.*;

@Entity
@Table
public class Account {
    @Id
    @SequenceGenerator(
            name = "account_sequence",
            sequenceName = "account_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "account_sequence"
    )
    private int id;
    private String uid;
    private double credit;

    public Account() {
    }

    public Account(int id, String uid, double credit) {
        this.id = id;
        this.uid = uid;
        this.credit = credit;
    }

    public Account(String uid, double credit ) {
        this.credit = credit;
        this.uid = uid;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
