class Edge {
    Vertex head;
    Vertex tail;
    long weight;

    Edge(Vertex head, Vertex tail, long weight) {
        this.head = head.key < tail.key ? head : tail;
        this.tail = head.key < tail.key ? tail : head;
        this.weight = weight;
    }

    Vertex other(Vertex v) {
        return v.key == head.key ? tail : head;
    }

    @Override
    public String toString() {
        return String.format("Edge {%d - %d, w: %d}", head.key, tail.key, weight);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof  Edge)) return false;
        Edge other = (Edge) obj;
        return this.weight == other.weight && this.head.key == other.head.key && this.tail.key == other.tail.key;
    }
}
