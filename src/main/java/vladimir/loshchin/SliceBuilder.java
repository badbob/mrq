package vladimir.loshchin;

import java.util.Collections;
import java.util.List;

import static java.util.Objects.nonNull;

import java.util.ArrayList;

/**
 * This is 6x3 matrix represents the slice of the 
 * whole stripe cube.
 * 
 * @author Vladimir Loshchin <vladimir.loshchin@gmail.com>
 */
public class SliceBuilder {

    private final Range range;
    
    private final Matrix counter;

    public SliceBuilder(Range range, Matrix counter) {
        this.range = range;
        this.counter = counter;
    }

    public SliceBuilder(Range range, Matrix counter, Matrix mask) {
        this.range = range;
        this.counter = counter;
    }

    public Slice build() {
        var slice = new Slice();

        if (nonNull(counter)) {
            int max = counter.max();
            
            // Build layout matrix to alignment
            Matrix layout = counter.test(i -> i < max).invert();

            // Fill the layout
            while (layout.hasEmptyCells() && !range.empty()) {
                // Enumerate each column
                layout.forEachCol((i, col) -> {
                    List<Integer> indexesOfEmpty = new ArrayList<>(Utils.indexesOfHoles(col));

                    if (!indexesOfEmpty.isEmpty() && !range.empty()) {
                        Collections.shuffle(indexesOfEmpty);
                        int j = indexesOfEmpty.get(0);
                        slice.set(i, j, range.pop());
                        layout.set(i, j, 1);
                    }
                });
            }

            // Every column in the slice must have at least
            // one free cell, so we can fill the each column
            // at least with one value
            if (!slice.emptyCols().isEmpty() && range.empty()) {
                throw new IllegalStateException(
                        "The stack of slices has column with counter = [5, 5, 5]. "
                        + "To avoid this we probably need to count the rest of space "
                        + " or holes."
                    );
            }
        }

        slice.emptyCols().forEach(col -> {
            Utils.setRandom(col, range.pop());
        });
  
        // Fill the rest of the range into the random column
        // which has free spaces
        while (!range.empty()) {
            var notFilled = slice.notFilledCols();
            Collections.shuffle(notFilled);
            Utils.setRandom(notFilled.get(0), range.pop());
        }

        return slice;
    }
}
