package operating_system;

import java.util.Scanner;

public class round_robin {

    public static void main(String args[]) {
        System.out.print("Enter no of process = ");
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int i, qt, count = 0, temp, sq = 0;
        float avgwt = 0, avgtat = 0;
        int bt[] = new int[n];
        int wt[] = new int[n];
        int ta[] = new int[n];
        int rem_bt[] = new int[n];

        System.out.print("Enter the burst time of the process\n");
        for (i = 0; i < n; i++) {
            System.out.print("P" + (i + 1) + " = ");
            bt[i] = s.nextInt();
            rem_bt[i] = bt[i];
        }
        System.out.print("Enter the quantum time: ");
        qt = s.nextInt();
        while (true) {
            for (i = 0, count = 0; i < n; i++) {
                temp = qt;
                if (rem_bt[i] == 0) {
                    count++;
                    continue;
                }
                if (rem_bt[i] > qt) {
                    rem_bt[i] = rem_bt[i] - qt;
                } else if (rem_bt[i] >= 0) {
                    temp = rem_bt[i];
                    rem_bt[i] = 0;
                }
                sq = sq + temp;
                ta[i] = sq;
            }
            if (n == count) {
                break;
            }
        }

        System.out.println("\npid   brust   turn   waiting");
        for (i = 0; i < n; i++) {
            wt[i] = ta[i] - bt[i];
            avgwt += wt[i];
            avgtat += ta[i];
            System.out.print("\n" + (i + 1) + "\t" + bt[i] + "\t" + ta[i] + "\t" + wt[i] + "\n");
        }
        System.out.println("\nAverage waiting Time = " + (avgwt / n));
        System.out.println("Average turnaround time = " + (avgtat / n));
    }
}
