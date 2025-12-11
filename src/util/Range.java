package util;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Range implements Comparable<Range> {
    private long start;
    private long end;

    public Range(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public static Collection<Range> combine(@NotNull Collection<Range> ranges) {
        Set<Range> result = new HashSet<>();
        boolean hasUnions = false;
        for(Range range1 : ranges) {
            boolean hasUnion = false;
            for(Range range2 : ranges) {
                if(range1 == range2) {
                    continue;
                }
                Range union = range1.union(range2);
                if(union != null) {
                    result.add(union);
                    hasUnion = true;
                    hasUnions = true;
                }
            }
            if(!hasUnion)
                result.add(range1);
        }
        if(hasUnions)
            return combine(result);
        return result;
    }

    public static Collection<Range> without(@NotNull Collection<Range> ranges, Range o) {
        Collection<Range> result = new ArrayList<>();
        for(Range range : ranges) {
            if(range.overlaps(o)) {
                result.addAll(range.without(o));
            }
            else {
                result.add(range);
            }
        }
        return result;
    }

    public Collection<Range> without(Range o) {
        if(!this.overlaps(o))
            return Collections.singletonList(this);
        List<Range> result = new ArrayList<>();
        if(this.start < o.start)
            result.add(new Range(this.start, o.start));
        if(o.end < this.end)
            result.add(new Range(o.end, this.end));
        return result;
    }

    public boolean overlaps(@NotNull Range o) {
        return Math.max(this.start, o.start) <= Math.min(this.end, o.end);
    }

    public Range overlap(Range o) {
        Range result = null;
        if(this.overlaps(o))
            result = new Range(Math.max(this.start, o.start), Math.min(this.end, o.end));
        return result;
    }

    public Range union(Range o) {
        Range result = null;
        if(this.overlaps(o))
            result = new Range(Math.min(this.start, o.start), Math.max(this.end, o.end));
        return result;
    }

    public Range offset(long offset) {
        start += offset;
        end += offset;
        return this;
    }

    public long min() {
        return start;
    }

    public long start() {
        return start;
    }

    public long end() {
        return end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Range other = (Range) o;
        return this.start == other.start && this.end == other.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public int compareTo(@NotNull Range o) {
        return this.start - o.start < 0 ? -1 : 1;
    }

    public long length() {
        return end - start;
    }

    public long elements() {
        return end - start + 1;
    }

    public void cutFront(long length) {
        start += length;
    }

    public boolean contains(long value) {
        return start <= value && end >= value;
    }

    public static boolean contains(@NotNull Collection<Range> ranges, long value) {
        return ranges.stream().anyMatch(r -> r.contains(value));
    }

    @Override
    public String toString() {
        return "[" + start + ":" + end + "]";
    }

    public static void main(String[] args) {
        Set<Range> ranges = new HashSet<>();
        ranges.add(new Range(2, 6));
        ranges.add(new Range(4, 8));
        ranges.add(new Range(10, 12));

        Collection<Range> reduced = Range.combine(ranges);
        System.out.println(reduced);

        System.out.println(Range.without(reduced, new Range(3, 5)));
        System.out.println(Range.without(reduced, new Range(1, 4)));
        System.out.println(Range.without(reduced, new Range(7, 9)));

        System.out.println(new Range(1,2).union(new Range(2,3)));
        ranges = new HashSet<>();
        ranges.add(new Range(1, 2));
        ranges.add(new Range(2, 3));
        ranges.add(new Range(7, 8));
        ranges.add(new Range(3, 4));
        reduced = Range.combine(ranges);
        System.out.println(reduced);
    }
}
