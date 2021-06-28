import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Q0384_Shuffle_An_Array {
    private int[] array;
    private int[] original;

    private Random rand = new Random();

    private List<Integer> getArrayCopy() {
        List<Integer> asList = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            asList.add(array[i]);
        }
        return asList;
    }

    public Q0384_Shuffle_An_Array(int[] nums) {
        array = nums;
        original = nums.clone();
    }

    public int[] reset() {
        array = original.clone();
        return array;
    }

    public int[] shuffle() {
        List<Integer> aux = getArrayCopy();

        for (int i = 0; i < array.length; i++) {
            int removeIdx = rand.nextInt(aux.size());
            array[i] = aux.get(removeIdx);
            aux.remove(removeIdx);
        }

        return array;
    }

    public static void main(String[] args) {
        Q0384_Shuffle_An_Array sol = new Q0384_Shuffle_An_Array(new int[] {1, 2, 3});
        int[] a = sol.shuffle();
        int[] a2 = sol.reset();
        int[] a3 = sol.reset();
    }
}
