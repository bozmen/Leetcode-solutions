import java.util.Arrays;
import java.util.Scanner;

public class CountingBits {
    public static void main(String[] args) {
        int num = -1;
        do {
            Scanner scanner = new Scanner(System.in);
            num = scanner.nextInt();
            System.out.println(Arrays.toString(countBits(num)));
        } while (num != -1);
    }

    public static int[] countBits(int num) {
        int[] arr = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            if (i == 0) {
                arr[i] = 0;
            } else if (isPowerOfTwo(i)) {
                arr[i] = 1;
            } else {
                arr[i] = arr[i ^ (i - 1)] - arr[i - 1] + (2 * arr[i & (i-1)]);
            }
        }
        return arr;
    }

    public static boolean isPowerOfTwo(int x) {
        return (x > 0) && (((x-1) & x) == 0);
    }
}