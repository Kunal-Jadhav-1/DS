import java.util.*;

public class BerkeleyClock {

    static class Node {
        String name;
        int clockTime; // in minutes (e.g., 10:00 = 600)

        Node(String name, int clockTime) {
            this.name = name;
            this.clockTime = clockTime;
        }

        void adjustTime(int offset) {
            this.clockTime += offset;
        }

        @Override
        public String toString() {
            int hours = clockTime / 60;
            int minutes = clockTime % 60;
            return String.format("%s: %02d:%02d", name, hours, minutes);
        }
    }

    public static void main(String[] args) {
        // Example nodes
        Node master = new Node("Master", 600);     // 10:00
        Node slave1 = new Node("Slave1", 612);     // 10:12
        Node slave2 = new Node("Slave2", 589);     // 09:49
        Node slave3 = new Node("Slave3", 605);     // 10:05

        List<Node> nodes = Arrays.asList(master, slave1, slave2, slave3);

        // Step 1: Collect all times
        int total = 0;
        for (Node node : nodes) {
            total += node.clockTime;
        }

        // Step 2: Calculate average time
        int averageTime = total / nodes.size();

        // Step 3: Compute and apply offset
        System.out.println("Offsets:");
        for (Node node : nodes) {
            int offset = averageTime - node.clockTime;
            System.out.printf("%s offset: %+d minutes\n", node.name, offset);
            node.adjustTime(offset);
        }

        // Step 4: Print new times
        System.out.println("\nSynchronized Clocks:");
        for (Node node : nodes) {
            System.out.println(node);
        }
    }
}