package graphs;

public class Path {

    private Node from;
    private Node to;
    private Integer weight = Integer.MAX_VALUE;

    public Path(Node from, Node to, Integer weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public Path() {
    }

    public Node getFrom() {
        return from;
    }

    public void setFrom(Node from) {
        this.from = from;
    }

    public Node getTo() {
        return to;
    }

    public void setTo(Node to) {
        this.to = to;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
