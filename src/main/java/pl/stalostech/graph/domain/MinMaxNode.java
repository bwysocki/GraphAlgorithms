package pl.stalostech.graph.domain;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Transient;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

/**
 * DB entity - representing MIN_MAX node.
 * @author Bartosz Wysocki
 */
@NodeEntity
public class MinMaxNode extends AbstractNode<MinMaxNode> implements Comparable<MinMaxNode> {

    @GraphId
    protected Long id;

    @Indexed
    protected String name;

    protected String tl, t, tr, ml, m, mr, bl, b, br = "";

    @Fetch
    @RelatedTo(type = "PATH", direction = Direction.OUTGOING)
    private Set<MinMaxNode> connections = new HashSet<MinMaxNode>();

    @Transient
    protected int score;

    @Override
    public Set<MinMaxNode> getConnections() {
        return connections;
    }

    public void setConnections(Set<MinMaxNode> connections) {
        this.connections = connections;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTl() {
        return tl == null ? "" : tl;
    }

    public void setTl(String tl) {
        this.tl = tl;
    }

    public String getT() {
        return t == null ? "" : t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getTr() {
        return tr == null ? "" : tr;
    }

    public void setTr(String tr) {
        this.tr = tr;
    }

    public String getMl() {
        return ml == null ? "" : ml;
    }

    public void setMl(String ml) {
        this.ml = ml;
    }

    public String getM() {
        return m == null ? "" : m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getMr() {
        return mr == null ? "" : mr;
    }

    public void setMr(String mr) {
        this.mr = mr;
    }

    public String getBl() {
        return bl == null ? "" : bl;
    }

    public void setBl(String bl) {
        this.bl = bl;
    }

    public String getB() {
        return b == null ? "" : b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getBr() {
        return br == null ? "" : br;
    }

    public void setBr(String br) {
        this.br = br;
    }

    @Override
    public String toString() {
        return "MinMaxNode [name=" + name + "]";
    }

    @Override
    public int compareTo(MinMaxNode o) {
        return o.getName().compareTo(getName());
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
