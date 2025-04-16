import java.util.*;

public class AutoBoxingSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter numbers separated by spaces: ");
        String input = sc.nextLine();

        String[] parts = input.split(" ");
        ArrayList<Integer> numbers = new ArrayList<>();

        for (String part : parts) {
            // Autoboxing: primitive to Integer object
            numbers.add(Integer.parseInt(part));
        }

        int sum = 0;
        for (Integer num : numbers) {
            // Unboxing: Integer to primitive
            sum += num;
        }

        System.out.println("Sum of numbers: " + sum);
    }
}
