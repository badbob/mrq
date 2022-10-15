package vladimir.loshchin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * @see https://en.wikipedia.org/wiki/Builder_pattern
 * @author Vladimir Loshchin <vladimir.loshchin@gmail.com>
 */
public class TicketBuilder {

    public static final int MAX_TICKET_SIZE = 15;
    private final Set<Integer> values = new HashSet<>();
    private List<List<Integer>> cols = new ArrayList<>();

    public TicketBuilder() {}

    public TicketBuilder addColumn(int[] col) {

        var column = IntStream.of(col).mapToObj(v -> v != 0 ? Integer.valueOf(v) : null).collect(toList());
        column.stream().filter(Objects::nonNull).forEach(values::add);
        cols.add(column);

        return this;
    }


    public Ticket build() {
        
        Integer[][] table = new Integer[][] {
            new Integer[] {null, null, null, null, null, null, null, null, null, },
            new Integer[] {null, null, null, null, null, null, null, null, null, },
            new Integer[] {null, null, null, null, null, null, null, null, null, },
        };
        
        for (int i=0; i<9; ++i) {
            for (int j=0; j<3; ++j) {
                List<Integer> col = cols.get(i);
                table[j][i] = j < col.size() ? col.get(j) : null;
            }
        }
        
        return new Ticket(table);
    }
    
    @Override
    public String toString() {
        return values.toString();
    }

    public boolean filled() {
        return values.size() == 15;
    }
    
    public int width() {
        return cols.size();
    }

    public int size() {
        return values.size();
    }
}
