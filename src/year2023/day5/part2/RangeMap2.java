package year2023.day5.part2;

import util.Range;

import java.util.*;

public class RangeMap2 {

    private final ArrayList<Range> ranges;
    private final ArrayList<Long> offsets;

    private RangeMap2 nextMap;

    public RangeMap2(ArrayList<Range> ranges, ArrayList<Long> offsets, RangeMap2 nextMap) {
        this.offsets = new ArrayList<>(offsets);
        this.ranges = new ArrayList<>(ranges);
        this.nextMap = nextMap;
    }

    public RangeMap2(ArrayList<Range> ranges, ArrayList<Long> offsets) {
        this(ranges, offsets, null);
    }

    public Collection<Range> convert(Collection<Range> keys) {
        Collection<Range> result = new ArrayList<>();
        for(Range key : keys) {
            Collection<Range> leftovers = Collections.singletonList(key);
            for(int i = 0; i < ranges.size(); ++i) {
                Range range = ranges.get(i);
                long offset = offsets.get(i);
                Range overlap = range.overlap(key);
                if (overlap != null) {
                    leftovers = Range.without(leftovers, overlap);
                    overlap.offset(offset);
                    result.add(overlap);
                }
            }
            result.addAll(leftovers);
        }
        //no improvement
        //result = Range.combine(result);
        //System.out.println(keys + "->" + result);
        if(nextMap == null)
            return result;
        else
            return nextMap.convert(result);
    }

    public Collection<Range> convert(Range key) {
        return convert(Collections.singletonList(key));
    }

    public void setNext(RangeMap2 nextMap) {
        this.nextMap = nextMap;
    }


    @Override
    public String toString() {
        return "RangeMap{" +
                "offsets=" + offsets +
                "\n ranges=" + ranges +
                "\n nextMap= \n" + nextMap +
                '}';
    }
}
