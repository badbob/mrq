package vladimir.loshchin;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

public class ColumnBuilderTest {

    @Test  
    void constructor() {
        assertThrows(IllegalArgumentException.class, () -> new ColumnBuilder(Set.of(0, 1, 2)));
        assertThrows(IllegalArgumentException.class, () -> new ColumnBuilder(Set.of(3)));
    }

    @Test
    void testNoSpaces() {
        assertArrayEquals(new int[] {1, 3, 5}, 
                new ColumnBuilder(Set.of()).add(5).add(3).add(1).build());
    }

    @Test
    void testOneSpace() {
        assertArrayEquals(new int[] {0, 1, 5}, 
                new ColumnBuilder(Set.of(0)).add(5).add(1).build());
    }

    @Test
    void testTwoSpaces() {
        assertArrayEquals(new int[] {0, 1, 0}, 
                new ColumnBuilder(Set.of(0, 2)).add(1).build());
    }

    @Test
    void testEmptyColumn() {
        assertThrows(IllegalStateException.class, () -> new ColumnBuilder(Set.of()).build());
    }

    @Test
    void testFilled() {
        assertFalse(new ColumnBuilder(Set.of()).add(1).add(2).filled());
        assertTrue(new ColumnBuilder(Set.of()).add(1).add(2).add(3).filled());

        assertFalse(new ColumnBuilder(Set.of(0)).add(1).filled());
        assertTrue(new ColumnBuilder(Set.of(0)).add(1).add(2).filled());
    }
}
