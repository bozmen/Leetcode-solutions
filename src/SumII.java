import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/4sum-ii/
 *
 * Created by burak on 11/26/2016.
 */
public class SumII {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> AB = new HashMap<>();
        Map<Integer, Integer> CD = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                int ABsum = A[i] + B[j];
                int CDsum = C[i] + D[j];
                if (AB.get(ABsum) != null) {
                    AB.put(ABsum, AB.get(ABsum) + 1);
                } else {
                    AB.put(ABsum, 1);
                }
                if (CD.get(CDsum) != null) {
                    CD.put(CDsum, CD.get(CDsum) + 1);
                } else {
                    CD.put(CDsum, 1);
                }
            }
        }
        int count = 0;
        for (Integer i : AB.keySet()) {
            if (CD.get(-i) != null && CD.get(-i) > 0) {
                count += CD.get(-i) * AB.get(i);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] A = {1, 2};
        int[] B = {-2, -1};
        int[] C = {-1, 2};
        int[] D = {0, 2};
        SumII sumII = new SumII();
        int count = sumII.fourSumCount(A,B,C,D);
        assert (count == 2);
    }
}
