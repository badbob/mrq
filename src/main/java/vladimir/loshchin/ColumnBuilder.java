package vladimir.loshchin;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class ColumnBuilder {

    public static final int SIZE = 3;
    private final Set<Integer> banedIdx;

    private final TreeSet<Integer> values = new TreeSet<>();

    public ColumnBuilder(Set<Integer> banedIdx) {
        if (banedIdx.size() > SIZE - 1) {
            throw new IllegalArgumentException(String.format(
                    "You can't ban more then %d cells in column", SIZE -1));
        }

        banedIdx.forEach(i -> {
            if (i > SIZE - 1) {
                throw new IllegalArgumentException(
                        String.format("Index of baned cell can not be bigger then: %d. You passed: %d", SIZE, i));
            }
        });

        this.banedIdx = banedIdx;
    }

    public int capacity() {
        return SIZE - banedIdx.size();
    }
    
    public boolean hasSpace() {
        return banedIdx.size() + values.size() < SIZE;
    }
    
    public boolean filled() {
        return !hasSpace();
    }

    public ColumnBuilder add(int v) {
        if (values.size() < capacity()) {
            values.add(v);
            return this;
        } else {
            throw new IllegalStateException("Not enough capacity");
        }
    }

    public int[] build() {
        if (values.isEmpty()) {
            throw new IllegalStateException("Column can not be empty");
        }
        
        Set<Integer> spaceIdx = new HashSet<>(banedIdx);
        int howMuchSpaceNeedMore = SIZE - banedIdx.size() - values.size();
        
        if (howMuchSpaceNeedMore > 0) {
            var range = new Range(0, SIZE-1);
            range.removeAll(spaceIdx);

            while (howMuchSpaceNeedMore > 0) {
                spaceIdx.add(range.pop());
                howMuchSpaceNeedMore--;
            }
        }

        int[] col = new int[SIZE];
        Iterator<Integer> it = values.iterator();

        for (int i = 0; i < SIZE; i++) {
            if (!spaceIdx.contains(i)) {
                col[i] = it.next();
            }
        }
        
        return col;
    }
}
