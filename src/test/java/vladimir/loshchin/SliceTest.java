package vladimir.loshchin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SliceTest {

    @Test
    public void testEmptyColumns() {
        var slice = new Slice();

        var emptyCols = slice.emptyCols();
        assertEquals(6, emptyCols.size());

        slice.set(0, 0, 1);
        slice.set(1, 0, 1);
        slice.set(2, 0, 1);
        slice.set(3, 0, 1);
        slice.set(4, 0, 1);
        slice.set(5, 0, 1);

        emptyCols = slice.emptyCols();
        assertEquals(0, emptyCols.size());
    }
}
