package vladimir.loshchin;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;

public class UtilsTest {

    @Test void testSortWithHoles() {
        assertArrayEquals(new int[] {1, 2, 0, 7}, 
                Utils.sortWithHoles(new int[] {2, 7, 0, 1}));
    }

    @Test void testIndexesOfHoles() {
        assertEquals(Set.of(0, 2), Utils.indexesOfHoles(new int[] {0, 1, 0}));
    }
}
