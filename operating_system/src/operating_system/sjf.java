package operating_system;
import java.util.Scanner;

class ProcessSJF {
    int pid;
    int bt;
    int art;
}

public class sjf {
    // Non-preemptive SJF Scheduling
    static void SJF() {
        Scanner sc = new Scanner(System.in);
        int[][] arr = new int[100][4];
        int i, j, n, total = 0, index;
        float avg_wt, avg_tat;

        System.out.print("Enter the number of processes: ");
        n = sc.nextInt();

        System.out.println("Enter Burst Time:");

        // User Input Burst Time and alloting Process ID.
        for (i = 0; i < n; i++) {
            System.out.print("P" + (i + 1) + ": ");
            arr[i][1] = sc.nextInt();
            arr[i][0] = i + 1;
        }

        // Sorting process according to their Burst Time.
        for (i = 0; i < n; i++) {
            index = i;
            for (j = i + 1; j < n; j++) {
                if (arr[j][1] < arr[index][1])
                    index = j;
            }

            // Swap two burst times and process IDs
            swap(arr, index, i);
        }

        // First process did not wait any time
        arr[0][2] = 0;

        // Calculation of Waiting Times
        for (i = 1; i < n; i++) {
            arr[i][2] = 0;
            // For loop to calculate the burst time of previous processes
            for (j = 0; j < i; j++)
                arr[i][2] += arr[j][1];
            total += arr[i][2];
        }

        avg_wt = (float) total / n;
        total = 0;
        System.out.println("P_ID     BT     WT      TAT");

        // Calculation of Turn Around Time and printing the data
        for (i = 0; i < n; i++) {
            arr[i][3] = arr[i][1] + arr[i][2];
            total += arr[i][3];
            System.out.println("P" + arr[i][0] + "         " + arr[i][1] + "     "
                    + arr[i][2] + "       " + arr[i][3]);
        }

        avg_tat = (float) total / n;
        System.out.println("Average Waiting Time= " + avg_wt);
        System.out.println("Average Turnaround Time= " + avg_tat);
    }

    // Preemptive SJF Scheduling
    static void SJFPreemptive() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();
        ProcessSJF[] proc = new ProcessSJF[n];

        System.out.println("Enter Arrival Time:");
        for (int i = 0; i < n; i++) {
            proc[i] = new ProcessSJF();
            proc[i].pid = i + 1;
            proc[i].art = sc.nextInt();
        }

        System.out.println("Enter Burst Time:");
        for (int i = 0; i < n; i++) {
            proc[i].bt = sc.nextInt();
        }

        int[] wt = new int[n];
        int[] tat = new int[n];
        int total_wt = 0;
        int total_tat = 0;
        findWaitingTime(proc, n, wt);
        findTurnAroundTime(proc, n, wt, tat);
        System.out.println("WT\t\tTAT");

        for (int i = 0; i < n; i++) {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
            System.out.println(proc[i].pid + "\t\t" + proc[i].bt + "\t\t" + wt[i] + "\t\t" + tat[i]);
        }

        System.out.println("\nAverage waiting time = " + (float) total_wt / n);
        System.out.println("Average turnaround time = " + (float) total_tat / n);
    }

    static void findWaitingTime(ProcessSJF[] proc, int n, int[] wt) {
        int[] rt = new int[n];
        for (int i = 0; i < n; i++)
            rt[i] = proc[i].bt;
        int complete = 0, t = 0, minm = Integer.MAX_VALUE;
        int shortest = 0, finish_time;
        boolean check = false;

        while (complete != n) {
            for (int j = 0; j < n; j++) {
                if ((proc[j].art <= t) && (rt[j] < minm) && rt[j] > 0) {
                    minm = rt[j];
                    shortest = j;
                    check = true;
                }
            }
            if (check == false) {
                t++;
                continue;
            }
            rt[shortest]--;
            minm = rt[shortest];
            if (minm == 0)
                minm = Integer.MAX_VALUE;

            if (rt[shortest] == 0) {
                complete++;
                check = false;
                finish_time = t + 1;
                wt[shortest] = finish_time - proc[shortest].bt - proc[shortest].art;

                if (wt[shortest] < 0)
                    wt[shortest] = 0;
            }
            t++;
        }
    }

    static void findTurnAroundTime(ProcessSJF[] proc, int n, int[] wt, int[] tat) {
        for (int i = 0; i < n; i++)
            tat[i] = proc[i].bt + wt[i];
    }

    static void swap(int[][] arr, int i, int j) {
        int temp0 = arr[i][0];
        int temp1 = arr[i][1];
        int temp2 = arr[i][2];
        int temp3 = arr[i][3];

        arr[i][0] = arr[j][0];
        arr[i][1] = arr[j][1];
        arr[i][2] = arr[j][2];
        arr[i][3] = arr[j][3];

        arr[j][0] = temp0;
        arr[j][1] = temp1;
        arr[j][2] = temp2;
        arr[j][3] = temp3;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Non-Preemptive SJF Scheduling");
        System.out.println("2. Preemptive SJF Scheduling");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                SJF();
                break;
            case 2:
                SJFPreemptive();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}