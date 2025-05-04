import java.util.*;

public class TokenRing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Get number of nodes
        System.out.print("Enter the number of nodes in the ring: ");
        int n = sc.nextInt();

        // Step 2: Display the ring
        System.out.println("Ring Formed is: ");
        for (int i = 0; i < n; i++) {
            System.out.print(i + " ");
        }
        System.out.println("0 (loop back to Node 0)");
        System.out.println("Note: Initially, Token is at Node 0");

        int token = 0;
        int choice = 1;

        // Step 3: Begin simulation
        while (choice == 1) {
            System.out.println("\nToken is at Node " + token);
            System.out.print("Does Node " + token + " want to send data? (1 = Yes / 0 = No): ");
            int wantsToSend = sc.nextInt();

            if (wantsToSend == 1) {
                // Ask for receiver
                int receiver;
                while (true) {
                    System.out.print("Enter Receiver Node (0 to " + (n - 1) + "): ");
                    receiver = sc.nextInt();
                    if (receiver < 0 || receiver >= n || receiver == token) {
                        System.out.println("Invalid receiver. Try again.");
                    } else {
                        break;
                    }
                }

                // Ask for data
                System.out.print("Enter Data to Send: ");
                int data = sc.nextInt();

                
                System.out.println("Node " + token + " holds the token.");

                // Simulate data transfer
                System.out.println("\nData Transfer: ");
                int i = token;
                while (i != receiver) {
                    System.out.print(i + " -> ");
                    i = (i + 1) % n;
                }
                System.out.println(receiver);
                System.out.println("Receiver " + receiver + " received the data: " + data);
                System.out.print("\nDo you want to continue simulation? (1 = Yes / 0 = No): ");
                choice = sc.nextInt();
            } else {
                System.out.println("Node " + token + " does not want to send data. Transfer the token to the next node.");
            }

            // Pass token to next node
            token = (token + 1) % n;

            // Ask if simulation should continue

        }

        sc.close();
        System.out.println("\nSimulation ended.");
    }
}
