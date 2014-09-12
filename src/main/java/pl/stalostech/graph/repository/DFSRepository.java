package pl.stalostech.graph.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import pl.stalostech.graph.domain.DFS;

public interface DFSRepository extends GraphRepository<DFS> {

    @Query("MATCH (n:DFS) WHERE n.name={0} RETURN n LIMIT 1")
    public DFS findByName(String name);

}
