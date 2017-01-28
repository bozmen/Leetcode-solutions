package hackerrank;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/contests/w27/challenges/hackonacci-matrix-rotations
 *
 * Created by burak on 12/25/2016.
 */

public class HackonacciMatrixRotations {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int q = in.nextInt();
        int[] h = {1,2,1,2,2,1,1,1,2,1};
        boolean[][] hMatrix = new boolean[n][n];
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                int xy = (x + 1) * (y + 1);
                int hOutput = h[(((xy * xy) - 1) % 10)];
                hMatrix[x][y] = hOutput % 2 == 1;
                System.out.print(hMatrix[x][y] + " ");
            }
            System.out.println();
        }
    }
}
