package portfolioservice.portfolio;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;

import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Base64Utils;
import portfolioservice.coin.PortfolioCoin;
import portfolioservice.exception.ApiRequestException;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PortfolioController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PortfolioIntegrationTest {

    @MockBean
    PortfolioService portfolioService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnPortfolio() throws Exception {
        String uid = "ABC123";
        PortfolioCoin portfolioCoin = new PortfolioCoin(1L, 1, "bitcoin", "btc", uid);
        List<PortfolioCoin> portfolioCoinList = new ArrayList<>();
        portfolioCoinList.add(portfolioCoin);

        when(portfolioService.GetCoinsInPortfolio(uid)).thenReturn(portfolioCoinList);

        mockMvc.perform(get("/api/v1/private/portfolio/{uid}", uid))
                .andDo(print()).andExpect(status().isOk()).andExpect(content().json(convertObjectToJsonString(portfolioCoinList)));
    }

    @Test
    public void shouldReturnExceptionMessage() throws Exception {
        String uid = "ABC123";

        when(portfolioService.GetCoinsInPortfolio(uid)).thenThrow(new ApiRequestException("There are no coins found"));

        mockMvc.perform(get("/api/v1/private/portfolio/{uid}", uid))
                .andDo(print()).andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ApiRequestException))
                .andExpect(result -> assertEquals("There are no coins found", result.getResolvedException().getMessage()));
    }

    private String convertObjectToJsonString(List<PortfolioCoin> portfolioCoinList) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(portfolioCoinList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

}
