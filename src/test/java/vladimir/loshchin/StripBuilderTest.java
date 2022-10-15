package vladimir.loshchin;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class StripBuilderTest {

    @Disabled
    @Test void test() {
        List<Ticket> tickets = new StripBuilder().build();
        
        tickets.forEach(t -> {
            System.out.println(t.toString());
        });
    }
}
