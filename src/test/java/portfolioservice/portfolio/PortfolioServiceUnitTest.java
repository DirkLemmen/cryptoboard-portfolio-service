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
import org.springframework.security.core.parameters.P;
import portfolioservice.account.Account;
import portfolioservice.account.AccountRepository;
import portfolioservice.coin.CoinRepository;
import portfolioservice.coin.PortfolioCoin;
import portfolioservice.exception.ApiRequestException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PortfolioServiceUnitTest {

    @Mock
    CoinRepository coinRepository;

    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    private PortfolioService underTest;

    @BeforeEach
    void setUp() {
        underTest = new PortfolioService(coinRepository, accountRepository);
    }

    @Test
    void canGetCoinsOnGet() throws Exception {
        // given
        String uid = "ABC123";

        given(coinRepository.countByUid(uid)).willReturn(1L);
        // when
        underTest.getCoinsInPortfolio(uid);
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
        assertThatThrownBy(() -> underTest.getCoinsInPortfolio(uid))
                .isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("There are no coins found");
    }

    @Test
    void canDeleteCoinFromPortfolioAndAddCredit() {
        Long id = 1L;
        String uid = "ABC123";

        PortfolioCoin portfolioCoin = new PortfolioCoin(1L, 1,"bitcoin","btc",uid);
        Account account = new Account(1,uid, 1000.40);

        given(coinRepository.findById(id)).willReturn(java.util.Optional.of(portfolioCoin));
        given(accountRepository.findByUid(uid)).willReturn(java.util.Optional.of(account));

        underTest.deleteCoinFromPortfolio(uid,id);
        verify(accountRepository).save(account);
        verify(coinRepository).deleteById(id);
    }

    @Test
    void willThrowNoAccountExceptionOnDelete() {
        Long id = 1L;
        String uid = "ABC123";

        PortfolioCoin portfolioCoin = new PortfolioCoin(id, 1,"bitcoin","btc",uid);

        given(coinRepository.findById(id)).willReturn(java.util.Optional.of(portfolioCoin));
        given(accountRepository.findByUid(uid)).willReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.deleteCoinFromPortfolio(uid,id))
                .isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("Couldn't find account belonging to " + uid);
    }

    @Test
    void willThrowNoCoinExceptionOnDelete() {
        Long id = 1L;
        String uid = "ABC123";

        given(coinRepository.findById(id)).willReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.deleteCoinFromPortfolio(uid,id))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Coin "+id+" does not exist");
    }

}