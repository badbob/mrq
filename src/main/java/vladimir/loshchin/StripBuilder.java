package vladimir.loshchin;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class StripBuilder {

    public StripBuilder() {

    }

    public List<Ticket> build() {
        
        Matrix counter = new Matrix(3, 6);
        List<Slice> slices = new ArrayList<>();
        
        var slice = new SliceBuilder(new Range(1, 9), counter).build();
        counter = counter.plus(slice.mask());
        slices.add(slice);

        slice = new SliceBuilder(new Range(10, 19), counter).build();
        counter = counter.plus(slice.mask());
        slices.add(slice);

        slice = new SliceBuilder(new Range(20, 29), counter).build();
        counter = counter.plus(slice.mask());
        slices.add(slice);

        slice = new SliceBuilder(new Range(30, 39), counter).build();
        counter = counter.plus(slice.mask());
        slices.add(slice);
        
        slice = new SliceBuilder(new Range(40, 49), counter).build();
        counter = counter.plus(slice.mask());
        slices.add(slice);

        //////////////////////////////////////////////////////////////////////////////
        // Here we need to take counter into account because it could be bigger then
        // 4 already.
        //////////////////////////////////////////////////////////////////////////////

        slice = new SliceBuilder(new Range(50, 59), counter).build();
        counter = counter.plus(slice.mask());
        slices.add(slice);

        slice = new SliceBuilder(new Range(60, 69), counter).build();
        counter = counter.plus(slice.mask());
        slices.add(slice);

        slice = new SliceBuilder(new Range(70, 79), counter).build();
        counter = counter.plus(slice.mask());
        slices.add(slice);

        slice = new SliceBuilder(new Range(80, 90), counter).build();
        counter = counter.plus(slice.mask());
        slices.add(slice);

        List<TicketBuilder> tbList = IntStream.range(0, 6)
                .mapToObj(i -> new TicketBuilder()).collect(toList());

        for (int i = 0; i < tbList.size(); ++i) {
            var tb = tbList.get(i);
            var idx = i;
            slices.forEach(s -> {
                tb.addColumn(Utils.sortWithHoles(s.column(idx)));
            });
        }
            
        return tbList.stream().map(TicketBuilder::build).collect(toList());
    }
}
