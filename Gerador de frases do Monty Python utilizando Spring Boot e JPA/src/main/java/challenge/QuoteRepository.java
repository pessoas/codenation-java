package challenge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Integer> {

    @Query(value = "SELECT * " +
            " FROM scripts " +
            " ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Quote getQuote();

    @Query(value = "SELECT * " +
            " FROM scripts " +
            " WHERE actor LIKE :actor " +
            " ORDER BY RAND() LIMIT 1 ", nativeQuery = true)
    Quote getQuoteByActor(@Param("actor") String actor);
}
