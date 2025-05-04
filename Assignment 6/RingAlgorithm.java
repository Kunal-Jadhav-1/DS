import java.util.*;

public class RingAlgorithm {
    public static void main(String[] args) {
        int thisProcessId, numProcesses, failedID;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        numProcesses = scanner.nextInt();

        // Validation for process ID that initiates election
        while (true) {
            System.out.print("Enter the ID of the process initiating the election (between 1 and " + numProcesses + "): ");
            thisProcessId = scanner.nextInt();
            if (thisProcessId < 1 || thisProcessId > numProcesses) {
                System.out.println("Please enter a valid process ID.");
            } else {
                break;
            }
        }

        // Validation for failed process ID
        while (true) {
            System.out.print("Enter the ID of the process which failed: ");
            failedID = scanner.nextInt();
            if (failedID < 1 || failedID > numProcesses) {
                System.out.println("Please enter a valid process ID for the failed process.");
            } else {
                break;
            }
        }

        // Create the ring of processes
        RingProcess[] ring = new RingProcess[numProcesses];
        for (int i = 0; i < numProcesses; i++) {
            ring[i] = new RingProcess(i + 1);
        }

        // Set the next process in the ring for each process
        for (int i = 0; i < numProcesses; i++) {
            ring[i].setNextProcess(ring[(i + 1) % numProcesses]);
        }

        // Start the election
        ring[thisProcessId - 1].startElection(failedID, numProcesses);
        scanner.close();
    }
}

class RingProcess {
    private int processId;
    private RingProcess nextProcess;

    public RingProcess(int processId) {
        this.processId = processId;
    }

    public void setNextProcess(RingProcess nextProcess) {
        this.nextProcess = nextProcess;
    }

    public void startElection(int failedID, int numProcesses) {
        System.out.println("Process " + processId + " starts the election.");

        // If the current process is the one that failed, do not participate
        if (processId == failedID) {
            System.out.println("Process " + processId + " has failed and will not participate in the election.");
            return;
        }

        // Create a list of active processes excluding the failed process
        List<Integer> activeProcesses = new ArrayList<>();
        RingProcess currentProcess = this;

        do {
            if (currentProcess.processId != failedID) {
                activeProcesses.add(currentProcess.processId);
            }
            currentProcess = currentProcess.nextProcess;
        } while (currentProcess != this);

        // Determine the process with the maximum ID as the leader
        int leader = Collections.max(activeProcesses);
        System.out.println("Process " + leader + " is elected as the new leader.");
    }
}
