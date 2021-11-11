package portfolioservice.coin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoinRepository extends JpaRepository<PortfolioCoin, Long> {
    PortfolioCoin findById(long id);
    List<PortfolioCoin> findByUid(String uid);
    @Query("SELECT COUNT(u) FROM PortfolioCoin u WHERE u.uid=:uid")
    long countByUid(@Param("uid") String uid);
}
