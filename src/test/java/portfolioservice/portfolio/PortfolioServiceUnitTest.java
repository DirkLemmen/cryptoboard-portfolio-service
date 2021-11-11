package portfolioservice.portfolio;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import portfolioservice.coin.CoinRepository;
import portfolioservice.coin.PortfolioCoin;
import portfolioservice.exception.ApiRequestException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PortfolioServiceUnitTest {

    @Mock
    CoinRepository coinRepository;

    @InjectMocks
    private PortfolioService underTest;

    @BeforeEach
    void setUp() {
        underTest = new PortfolioService(coinRepository);
    }

    @Test
    void canGetCoinsOnGet() throws Exception {
        // given
        String uid = "ABC123";

        given(coinRepository.countByUid(uid)).willReturn(1L);
        // when
        underTest.GetCoinsInPortfolio(uid);
        // then
        verify(coinRepository).findByUid(uid);
    }

    @Test
    void throwsNoCoinsFoundOnGet() {
        // given
        String uid = "ABC123";

        given(coinRepository.countByUid(uid)).willReturn(0L);
        // when
        // then
        assertThatThrownBy(() -> underTest.GetCoinsInPortfolio(uid))
                .isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("There are no coins found");
    }

    @Test
    void GetCoinWorth() throws IOException {
        underTest.GetCoinWorth();
    }
}