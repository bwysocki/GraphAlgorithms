package pl.stalostech.graph.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import pl.stalostech.graph.domain.MinMaxNode;

public interface MinMaxRepository extends GraphRepository<MinMaxNode> {

    @Query("MATCH (n:MINMAX) WHERE n.name={0} RETURN n LIMIT 1")
    public MinMaxNode findByName(String name);

}
