package year2023.day5.part1;

import java.util.ArrayList;

public class RangeMap {

    private final ArrayList<Long> offsets;
    private final ArrayList<Long> sourceStarts;
    private final ArrayList<Long> ranges;

    private RangeMap nextMap;

    public RangeMap(ArrayList<Long> offsets, ArrayList<Long> sourceStarts, ArrayList<Long> ranges, RangeMap nextMap) {
        this.offsets = new ArrayList<>(offsets);
        this.sourceStarts = new ArrayList<>(sourceStarts);
        this.ranges = new ArrayList<>(ranges);
        this.nextMap = nextMap;
    }

    public RangeMap(ArrayList<Long> offsets, ArrayList<Long> sourceStarts, ArrayList<Long> ranges) {
        this(offsets, sourceStarts, ranges, null);
    }

    public long convert(long key) {
        long sourceStart;
        long offset = 0;
        long range;
        for(int i = 0; i < ranges.size(); ++i) {
            sourceStart = sourceStarts.get(i);
            range = ranges.get(i);
            if(key >= sourceStart && key < sourceStart + range) {
                offset = offsets.get(i);
                break;
            }
        }
        if(nextMap == null)
            return key + offset;
        else
            return nextMap.convert(key + offset);
    }

    public void setNext(RangeMap nextMap) {
        this.nextMap = nextMap;
    }


    @Override
    public String toString() {
        return "RangeMap{" +
                "offsets=" + offsets +
                "\n sourceStarts=" + sourceStarts +
                "\n ranges=" + ranges +
                "\n nextMap= \n" + nextMap +
                '}';
    }
}
