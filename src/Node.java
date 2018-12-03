import java.util.HashSet;

public class Node {

    private String value;

    private HashSet<PeopleRelation> relations;

    private HashSet<String> children;

    public Node(String value) {
        this.value = value;
        relations = new HashSet<>();
        children = new HashSet<>();
    }

    public String getValue() {
        return value;
    }

    public HashSet<PeopleRelation> getRelations() {
        return relations;
    }

    public HashSet<String> getChildren() {
        return children;
    }

    public void addRelation(PeopleRelation peopleRelation) {
        if (relations != null && peopleRelation != null) {
            relations.add(peopleRelation);
        }
    }

    public void addChild(String child) {
        if (children != null && child != null && !child.equalsIgnoreCase("")) {
            children.add(child);
        }
    }

    @Override
    public String toString() {
        return "Node{" +
//                "relations=" + relations +
                "RelationsNumber=" + relations.size() +
                ", children=" + children +
                '}';
    }
}
