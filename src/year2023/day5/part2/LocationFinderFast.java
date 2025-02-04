package year2023.day5.part2;

import util.Range;
import util.StringUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class LocationFinderFast {
    public static void main(String[] args) {
        List<String> input = util.FileReader.readFile("src/year2023/day5/input.txt");
        List<Long> seeds = StringUtil.stringToLongList(input.get(0).split(":")[1]);
        RangeMap2 seedToSoil;
        {
            String line;
            int i;
            ArrayList<Long> offsets = new ArrayList<>();
            ArrayList<Range> ranges = new ArrayList<>();
            for (i = 3; input.get(i).length() != 0; ++i) {
                line = input.get(i);
                long[] longs = StringUtil.stringToLongArray(line);
                long destStart = longs[0],
                        sourceStart = longs[1],
                        range = longs[2];
                ranges.add(new Range (sourceStart, sourceStart + range));
                offsets.add(destStart - sourceStart);
            }
            seedToSoil = new RangeMap2(ranges, offsets);
            i += 2;
            offsets = new ArrayList<>();
            ranges = new ArrayList<>();
            for (; input.get(i).length() != 0; ++i) {
                line = input.get(i);
                long[] longs = StringUtil.stringToLongArray(line);
                long destStart = longs[0],
                        sourceStart = longs[1],
                        range = longs[2];
                ranges.add(new Range (sourceStart, sourceStart + range));
                offsets.add(destStart - sourceStart);
            }
            RangeMap2 soilToFertilizer = new RangeMap2(ranges, offsets);
            seedToSoil.setNext(soilToFertilizer);
            i += 2;
            offsets = new ArrayList<>();
            ranges = new ArrayList<>();
            for (; input.get(i).length() != 0; ++i) {
                line = input.get(i);
                long[] longs = StringUtil.stringToLongArray(line);
                long destStart = longs[0],
                        sourceStart = longs[1],
                        range = longs[2];
                ranges.add(new Range (sourceStart, sourceStart + range));
                offsets.add(destStart - sourceStart);
            }
            RangeMap2 fertilizerToWater = new RangeMap2(ranges, offsets);
            soilToFertilizer.setNext(fertilizerToWater);
            i += 2;
            offsets = new ArrayList<>();
            ranges = new ArrayList<>();
            for (; input.get(i).length() != 0; ++i) {
                line = input.get(i);
                long[] longs = StringUtil.stringToLongArray(line);
                long destStart = longs[0],
                        sourceStart = longs[1],
                        range = longs[2];
                ranges.add(new Range (sourceStart, sourceStart + range));
                offsets.add(destStart - sourceStart);
            }
            RangeMap2 waterToLight = new RangeMap2(ranges, offsets);
            fertilizerToWater.setNext(waterToLight);
            i += 2;
            offsets = new ArrayList<>();
            ranges = new ArrayList<>();
            for (; input.get(i).length() != 0; ++i) {
                line = input.get(i);
                long[] longs = StringUtil.stringToLongArray(line);
                long destStart = longs[0],
                        sourceStart = longs[1],
                        range = longs[2];
                ranges.add(new Range (sourceStart, sourceStart + range));
                offsets.add(destStart - sourceStart);
            }
            RangeMap2 lightToTemperature = new RangeMap2(ranges, offsets);
            waterToLight.setNext(lightToTemperature);
            i += 2;
            offsets = new ArrayList<>();
            ranges = new ArrayList<>();
            for (; input.get(i).length() != 0; ++i) {
                line = input.get(i);
                long[] longs = StringUtil.stringToLongArray(line);
                long destStart = longs[0],
                        sourceStart = longs[1],
                        range = longs[2];
                ranges.add(new Range (sourceStart, sourceStart + range));
                offsets.add(destStart - sourceStart);
            }
            RangeMap2 temperatureToHumidity = new RangeMap2(ranges, offsets);
            lightToTemperature.setNext(temperatureToHumidity);
            i += 2;
            offsets = new ArrayList<>();
            ranges = new ArrayList<>();
            for (; i < input.size() && input.get(i).length() != 0; ++i) {
                line = input.get(i);
                long[] longs = StringUtil.stringToLongArray(line);
                long destStart = longs[0],
                        sourceStart = longs[1],
                        range = longs[2];
                ranges.add(new Range (sourceStart, sourceStart + range));
                offsets.add(destStart - sourceStart);
            }
            RangeMap2 humidityToLocation = new RangeMap2(ranges, offsets);
            temperatureToHumidity.setNext(humidityToLocation);
        }
        long start = System.currentTimeMillis();
        long result = IntStream.range(0, seeds.size())
                .filter(x -> x % 2 == 0)
                //.parallel()
                .mapToLong(x -> {
                    Range range = new Range(seeds.get(x), seeds.get(x) + seeds.get(x+1));
                    Collection<Range> ranges = seedToSoil.convert(range);
                    return Collections.min(ranges).min();
                })
                .min().orElse(-1);
        long end = System.currentTimeMillis();
        System.out.println(result);
        System.out.println("calculated in " + (end - start) + "ms");
    }
}
