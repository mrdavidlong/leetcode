import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SparseVector1 {

    private int[] array;

    SparseVector1(int[] nums) {
        array = nums;
    }

    public int dotProduct(SparseVector1 vec) {
        int result = 0;

        for (int i = 0; i < array.length; i++) {
            result += array[i] * vec.array[i];
        }
        return result;
    }
}

class SparseVector2 {
    // Map the index to value for all non-zero values in the vector
    Map<Integer, Integer> mapping;

    SparseVector2(int[] nums) {
        mapping = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != 0) {
                mapping.put(i, nums[i]);
            }
        }
    }

    public int dotProduct(SparseVector2 vec) {
        int result = 0;

        // iterate through each non-zero element in this sparse vector
        // update the dot product if the corresponding index has a non-zero value in the other vector
        for (Integer i : this.mapping.keySet()) {
            if (vec.mapping.containsKey(i)) {
                result += this.mapping.get(i) * vec.mapping.get(i);
            }
        }
        return result;
    }
}

class SparseVector3 {

    List<int[]> pairs;

    SparseVector3(int[] nums) {
        pairs = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                pairs.add(new int[]{i, nums[i]});
            }
        }
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector3 vec) {
        int result = 0, p = 0, q = 0;
        while (p < pairs.size() && q < vec.pairs.size()) {
            if (pairs.get(p)[0] == vec.pairs.get(q)[0]) {
                result += pairs.get(p)[1] * vec.pairs.get(q)[1];
                p++;
                q++;
            } else if (pairs.get(p)[0] > vec.pairs.get(q)[0]) {
                q++;
            } else {
                p++;
            }
        }
        return result;
    }
}


class SparseVector4 {

    int[][] pairs;

    SparseVector4(int[][] pairs) {
        this.pairs = pairs;
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector4 vec) {
        int result = 0, p = 0, q = 0;
        while (p < pairs.length && q < vec.pairs.length) {
            if (pairs[p][0] == vec.pairs[q][0]) {
                result += pairs[p][1] * vec.pairs[q][1];
                p++;
                q++;
            } else if (pairs[p][0] > vec.pairs[q][0]) {
                q++;
            } else {
                p++;
            }
        }
        return result;
    }
}

public class Q1570_Dot_Product_Of_Two_Sparse_Vector {

}
