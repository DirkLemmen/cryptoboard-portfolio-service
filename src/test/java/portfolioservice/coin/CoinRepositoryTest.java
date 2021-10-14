package portfolioservice.coin;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CoinRepositoryTest {

    @Autowired
    private CoinRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll(); // Clears database for every test
    }

    @Test
    void findByUid() {
        // given
        Coin coin = new Coin(
                5.5f,
                "bitcoin",
                "btc",
                "123123123"
        );
        underTest.save(coin);
        String uid = "123123123";
        // when
        var expected = underTest.findByUid(uid);
        // then
        assertThat(expected).isNotNull();
    }
}