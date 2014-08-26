package pl.stalostech.graph.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import pl.stalostech.graph.domain.BFS;

public interface BFSRepository extends GraphRepository<BFS> {

    @Query("MATCH (n:BFS) WHERE n.name={0} RETURN n LIMIT 1")
    public BFS findByName(String name);

}
