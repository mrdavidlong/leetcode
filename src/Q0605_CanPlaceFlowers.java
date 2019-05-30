/**
 * Created by davidlong on 7/5/18.
 */
public class Q0605_CanPlaceFlowers {
    //https://github.com/CyC2018/Interview-Notebook/blob/master/notes/Leetcode%20题解.md#算法思想
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length;
        int cnt = 0;
        for (int i = 0; i < len && cnt < n; i++) {
            if (flowerbed[i] == 1) {
                continue;
            }

            int pre = i == 0 ? 0 : flowerbed[i - 1];
            int next = i == len - 1 ? 0 : flowerbed[i + 1];
            if (pre == 0 && next == 0) {
                cnt++;
                if (cnt >= n) return true; // shortcut

                flowerbed[i] = 1;
            }
        }
        return cnt >= n;
    }

    public static void main(String[] args) {
        Q0605_CanPlaceFlowers sol = new Q0605_CanPlaceFlowers();

        int[] flowerbed1 = {1,0,0,0,1};
        int n1 = 1;
        boolean canPlaceFlower1 = sol.canPlaceFlowers(flowerbed1, n1); // true;

        int[] flowerbed2 = {1,0,0,0,1};
        int n2 = 2;
        boolean canPlaceFlower2 = sol.canPlaceFlowers(flowerbed2, n2); // false
    }
}
