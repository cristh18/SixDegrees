public class PeopleRelation {

    private String origin;

    private String destiny;

    public PeopleRelation(String origin, String destiny) {
        this.origin = origin;
        this.destiny = destiny;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestiny() {
        return destiny;
    }

    public void setDestiny(String destiny) {
        this.destiny = destiny;
    }

    public String getRelationName(){
        return getOrigin().concat(getDestiny());
    }

    public String getInverseRelationName(){
        return getDestiny().concat(getOrigin());
    }

    @Override
    public String toString() {
        return "PeopleRelation{" +
                "origin='" + origin + '\'' +
                ", destiny='" + destiny + '\'' +
                '}';
    }
}
