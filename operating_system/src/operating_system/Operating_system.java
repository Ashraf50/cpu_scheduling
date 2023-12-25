package operating_system;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Operating_system {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Queue<Integer> queue1 = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();
        Queue<Integer> queue2 = new LinkedList<>();
        System.out.print("Enter total number:");
        int totalNumbers = input.nextInt();
        int queue1Size = (int) (totalNumbers * 0.3);
        int stackSize = (int) (totalNumbers * 0.4);
        int queue2Size = (int) (totalNumbers * 0.3);

        Random random = new Random();

        for (int i = 1; i <= totalNumbers; i++) {
            int choice = random.nextInt(3) + 1;

            switch (choice) {
                case 1:
                    if (queue1.size() < queue1Size) {
                        queue1.add(i);
                    } else {
                        if (stack.size() < stackSize) {
                            stack.push(i);
                        } else {
                            queue2.add(i);
                        }
                    }
                    break;
                case 2:
                    if (stack.size() < stackSize) {
                        stack.push(i);
                    } else {
                        if (queue1.size() < queue1Size) {
                            queue1.add(i);
                        } else {
                            queue2.add(i);
                        }
                    }
                    break;
                case 3:
                    if (queue2.size() < queue2Size) {
                        queue2.add(i);
                    } else {
                        if (queue1.size() < queue1Size) {
                            queue1.add(i);
                        } else {
                            stack.push(i);
                        }
                    }
                    break;
                default:
                    break;
            }
        }

        // Print the contents of each container
        System.out.println("Queue 1: " + queue1);
        System.out.println("Stack: " + stack);
        System.out.println("Queue 2: " + queue2);
    }
}
