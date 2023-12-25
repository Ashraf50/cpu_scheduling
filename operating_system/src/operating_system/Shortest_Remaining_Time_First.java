package operating_system;

import java.util.Scanner;

public class Shortest_Remaining_Time_First {

    public static void main(String[] args) {
        int[] a = new int[10];
        int[] b = new int[10];
        int[] x = new int[10];
        int[] waiting = new int[10];
        int[] turnaround = new int[10];
        int[] completion = new int[10];
        int i, j, smallest, count = 0, time, n;
        double avgwt = 0, avgta = 0, end;

        Scanner input = new Scanner(System.in);

        System.out.print("Enter the number of Processes: ");
        n = input.nextInt();

        for (i = 0; i < n; i++) {
            System.out.print("Enter arrival time of process: ");
            a[i] = input.nextInt();
        }

        for (i = 0; i < n; i++) {
            System.out.print("Enter burst time of process: ");
            b[i] = input.nextInt();
        }

        for (i = 0; i < n; i++) {
            x[i] = b[i];
        }

        b[9] = 9999;

        for (time = 0; count != n; time++) {
            smallest = 9;

            for (i = 0; i < n; i++) {
                if (a[i] <= time && b[i] < b[smallest] && b[i] > 0) {
                    smallest = i;
                }
            }

            b[smallest]--;

            if (b[smallest] == 0) {
                count++;
                end = time + 1;
                completion[smallest] = (int) end;
                waiting[smallest] = (int) (end - a[smallest] - x[smallest]);
                turnaround[smallest] = (int) (end - a[smallest]);
            }
        }

        System.out.println("Pid\tBT\tAT\tWT\tTA\tCT");

        for (i = 0; i < n; i++) {
            System.out.println("p" + (i + 1) + "\t" + x[i] + "\t" + a[i] + "\t" + waiting[i] + "\t" + turnaround[i] + "\t" + completion[i]);
            avgwt += waiting[i];
            avgta += turnaround[i];
        }

        System.out.println("\nAverage waiting time = " + avgwt / n);
        System.out.println("Average Turnaround time = " + avgta / n);
    }
}
