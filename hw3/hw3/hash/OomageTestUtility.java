package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int[] buckets = new int[M];
        int N = oomages.size();
        for (Oomage o: oomages) {
            int bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            buckets[bucketNum]++;
        }
        for (int i = 0; i < M; i++) {
            if (buckets[i] <= N / 50 || buckets[i] >= N / 2.5) {
                return false;
            }
        }
        return true;
    }
}
