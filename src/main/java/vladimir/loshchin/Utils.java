package vladimir.loshchin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Utils {

    public static Set<Integer> indexesOfHoles(int[] arr) {
        Set<Integer> result = new HashSet<>();
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] == 0) {
                result.add(i);
            }
        }
        return result;
    }

    public static int[] sortWithHoles(int[] arr) {
        Set<Integer> indexesOfHoles = indexesOfHoles(arr);
        List<Integer> sorted = IntStream.of(arr).boxed().filter(i -> i > 0).collect(toList());
        Collections.sort(sorted);
        
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = indexesOfHoles.contains(i) 
                    ? 0 : sorted.remove(0);
        }
        return result;
    }

    public static void setRandom(int[] arr, int value) {
        var list = new ArrayList<>(indexesOfHoles(arr));
        Collections.shuffle(list);
        arr[list.get(0)] = value;
    }
}
