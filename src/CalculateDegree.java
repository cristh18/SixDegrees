import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class CalculateDegree {

    private int peopleNumber;
    private int relationsNumber;
    private ArrayList<PeopleRelation> relations;
    private HashMap<String, Node> nodes;
    private HashSet<String> nodeValues;

    private HashSet<Integer> degrees;

    private String origin;

    private ArrayList<String> unCheckedValues;

    private int degree = 0;

    CalculateDegree() {
//        try {
//            manualInitialize();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        automaticInitialize();

        if (isGraphConnected()) {
            System.out.println("The maximum degreee is: " + getMaximumDegree());
        }
    }

    private void manualInitialize() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        System.out.println("Enter people number: ");

        peopleNumber = Integer.parseInt(br.readLine());
        System.out.println("Persons: " + peopleNumber);

        System.out.println("Enter relations number: ");

        relationsNumber = Integer.parseInt(br.readLine());
        System.out.println("Relations number: " + relationsNumber);

        getManualRelations(br);
        System.out.println("Node values: " + nodeValues);
        System.out.println("Relations: " + relations);

    }

    private void automaticInitialize() {
        peopleNumber = 5;
//        System.out.println("Persons: " + peopleNumber);
        relationsNumber = 5;
//        System.out.println("Relations number: " + relationsNumber);

        getAutomaticRelations();
        System.out.println("Node values: " + nodeValues);
//        System.out.println("Relations: " + relations);

        System.out.println("Graph: " + nodes);

    }

    private void getManualRelations(BufferedReader br) throws IOException {
        if (relationsNumber > 0) {
            int i = 0;
            nodeValues = new HashSet<>(peopleNumber);
            relations = new ArrayList<>(relationsNumber);
            while (i < relationsNumber) {
                System.out.println("Enter the ".concat(String.valueOf(i)).concat(" relation:"));
                System.out.println("Origin: ");
                String origin = br.readLine();
                System.out.println("Destiny: ");
                String destiny = br.readLine();

                nodeValues.add(origin);
                nodeValues.add(destiny);

                PeopleRelation peopleRelation = new PeopleRelation(origin, destiny);
                relations.add(peopleRelation);
                i++;
            }
        }
    }

    private void getAutomaticRelations() {
        nodeValues = new HashSet<>(peopleNumber);
        relations = new ArrayList<>(relationsNumber);
        nodes = new HashMap<>(peopleNumber);
        degrees = new HashSet<>();

        nodeValues.add("Andres");
        nodeValues.add("Diego");
        nodeValues.add("Felipe");
        nodeValues.add("Mario");
        nodeValues.add("Pacho");
        nodeValues.add("Cristhian");

        PeopleRelation peopleRelation1 = new PeopleRelation("Andres", "Diego");
        PeopleRelation peopleRelation2 = new PeopleRelation("Diego", "Mario");
        PeopleRelation peopleRelation3 = new PeopleRelation("Diego", "Felipe");
        PeopleRelation peopleRelation4 = new PeopleRelation("Mario", "Felipe");
        PeopleRelation peopleRelation5 = new PeopleRelation("Mario", "Pacho");
        PeopleRelation peopleRelation6 = new PeopleRelation("Pacho", "Cristhian");

        relations.add(peopleRelation1);
        relations.add(peopleRelation2);
        relations.add(peopleRelation3);
        relations.add(peopleRelation4);
        relations.add(peopleRelation5);
        relations.add(peopleRelation6);


//        nodeValues.add("Ashok");
//        nodeValues.add("Chun");
//        nodeValues.add("Ursala");
//        nodeValues.add("Kiyoshi");
//
//        PeopleRelation peopleRelation1 = new PeopleRelation("Ashok", "Chun");
//        PeopleRelation peopleRelation2 = new PeopleRelation("Ursala", "Kiyoshi");
//
//        relations.add(peopleRelation1);
//        relations.add(peopleRelation2);


//        nodeValues.add("Ashok");
//        nodeValues.add("Chun");
//        nodeValues.add("Ursala");
//        nodeValues.add("Kiyoshi");
//
//        PeopleRelation peopleRelation1 = new PeopleRelation("Ashok", "Kiyoshi");
//        PeopleRelation peopleRelation2 = new PeopleRelation("Ursala", "Chun");
//        PeopleRelation peopleRelation3 = new PeopleRelation("Ursala", "Kiyoshi");
//        PeopleRelation peopleRelation4 = new PeopleRelation("Kiyoshi", "Chun");
//
//        relations.add(peopleRelation1);
//        relations.add(peopleRelation2);
//        relations.add(peopleRelation3);
//        relations.add(peopleRelation4);

        validateRelations();
    }

    private void validateRelations() {
        for (PeopleRelation peopleRelation : relations) {
            buildNode(peopleRelation, true);
            buildNode(peopleRelation, false);
        }
    }

    private void buildNode(PeopleRelation peopleRelation, boolean isOrigin) {
        String nodeValue;
        if (isOrigin) {
            nodeValue = peopleRelation.getOrigin();
        } else {
            nodeValue = peopleRelation.getDestiny();
            peopleRelation = new PeopleRelation(peopleRelation.getDestiny(), peopleRelation.getOrigin());
        }

        if (nodes.containsKey(nodeValue)) {
            Node node = nodes.get(nodeValue);
            node.addRelation(peopleRelation);
            node.addChild(peopleRelation.getDestiny());
        } else {
            Node node = new Node(nodeValue);
            node.addRelation(peopleRelation);
            node.addChild(peopleRelation.getDestiny());
            nodes.put(nodeValue, node);
        }
    }

    private boolean isGraphConnected() {
        int nodesConnected = 0;
        ArrayList<String> nodes = new ArrayList<>(nodeValues);
        for (int i = 0; i < nodes.size(); i++) {
            boolean nodeConnected;
            int connections = 0;

            for (int j = 0; j < nodes.size(); j++) {
                unCheckedValues = new ArrayList<>(nodeValues);
                origin = nodes.get(i);
                String destiny = nodes.get(j);
                nodeConnected = isNodeConnectedToDestiny(nodes.get(i), nodes.get(j));
                if (nodeConnected) {
                    connections++;
                } else {
                    System.out.println("GRAPH DISCONNECTED: Origin: " + origin + " - Destiny: " + destiny);
                }
            }

            if (connections == nodeValues.size()) {
                nodesConnected++;
            }
        }

        System.out.println("nodesConnected: " + nodesConnected + ", elements: " + nodeValues.size());
        boolean graphConnected = (nodesConnected == nodeValues.size());
        System.out.println("IS GRAPH CONNECTED: " + graphConnected);
        return graphConnected;
    }

    private boolean isNodeConnectedToDestiny(String referenceNode, String destiny) {
        boolean isConnected;
        degree = 0;
        Node reference = nodes.get(referenceNode);
        if (reference.getChildren().contains(destiny)) {
            degree++;
            degrees.add(degree);
            isConnected = true;
            System.out.println("Connection: " + isConnected + ", " + origin + " - " + destiny);
        } else {
            degree = 1;
            updateUncheckedValues(reference.getValue());
            isConnected = iterateNetwork(new ArrayList<>(reference.getChildren()), destiny);
        }

//        System.out.println(referenceNode + "IS NODE CONNECTED: " + isConnected);
        return isConnected;
    }

    private void updateUncheckedValues(String value) {
        if (!unCheckedValues.isEmpty()) {
            unCheckedValues.remove(value);
        }
    }

    private int getMaximumDegree() {
        int maxValue = 0;
        for (Integer degree : degrees) {
            if (degree > maxValue) {
                maxValue = degree;
            }
        }
        return maxValue;
    }

    /**
     *
     */
    private boolean iterateNetwork(List<String> children, String destiny) {
        boolean found = false;
        degree++;
        HashSet<String> minorChildren = new HashSet<>();
        for (String child : children) {
            Node newReference = nodes.get(child);
            if (newReference.getChildren().contains(destiny)) {
                degrees.add(degree);
                found = true;
                break;
            } else {
                updateUncheckedValues(child);
                for (String n : newReference.getChildren()) {
                    if (unCheckedValues.contains(n)) {
                        minorChildren.add(n);
                    }
                }
//                minorChildren.addAll(newReference.getChildren());
            }
        }

        if (!found && !minorChildren.isEmpty()) {
//            System.out.println("RECURSIVE CALLED");
            return iterateNetwork(new ArrayList<>(minorChildren), destiny);
        }

        System.out.println("Connection: " + found + ", " + origin + " - " + destiny);
        return found;
    }
}
