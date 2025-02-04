package year2023.day5.part1;

import util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class LocationFinder {
    public static void main(String[] args) {
        List<String> input = util.FileReader.readFile("src/year2023/day5/input.txt");
        List<Long> seeds = StringUtil.stringToLongList(input.get(0).split(":")[1]);
        RangeMap seedToSoil;
        {
            String line;
            int i;
            ArrayList<Long> sourceStarts = new ArrayList<>(),
                offsets = new ArrayList<>(),
                ranges = new ArrayList<>();
            for (i = 3; input.get(i).length() != 0; ++i) {
                line = input.get(i);
                long[] longs = StringUtil.stringToLongArray(line);
                long destStart = longs[0],
                        sourceStart = longs[1],
                        range = longs[2];
                sourceStarts.add(sourceStart);
                ranges.add(range);
                offsets.add(destStart - sourceStart);
            }
            seedToSoil = new RangeMap(offsets, sourceStarts, ranges);
            i += 2;
            sourceStarts = new ArrayList<>();
            offsets = new ArrayList<>();
            ranges = new ArrayList<>();
            for (; input.get(i).length() != 0; ++i) {
                line = input.get(i);
                long[] longs = StringUtil.stringToLongArray(line);
                long destStart = longs[0],
                        sourceStart = longs[1],
                        range = longs[2];
                sourceStarts.add(sourceStart);
                ranges.add(range);
                offsets.add(destStart - sourceStart);
            }
            RangeMap soilToFertilizer = new RangeMap(offsets, sourceStarts, ranges);
            seedToSoil.setNext(soilToFertilizer);
            i += 2;
            sourceStarts = new ArrayList<>();
            offsets = new ArrayList<>();
            ranges = new ArrayList<>();
            for (; input.get(i).length() != 0; ++i) {
                line = input.get(i);
                long[] longs = StringUtil.stringToLongArray(line);
                long destStart = longs[0],
                        sourceStart = longs[1],
                        range = longs[2];
                sourceStarts.add(sourceStart);
                ranges.add(range);
                offsets.add(destStart - sourceStart);
            }
            RangeMap fertilizerToWater = new RangeMap(offsets, sourceStarts, ranges);
            soilToFertilizer.setNext(fertilizerToWater);
            i += 2;
            sourceStarts = new ArrayList<>();
            offsets = new ArrayList<>();
            ranges = new ArrayList<>();
            for (; input.get(i).length() != 0; ++i) {
                line = input.get(i);
                long[] longs = StringUtil.stringToLongArray(line);
                long destStart = longs[0],
                        sourceStart = longs[1],
                        range = longs[2];
                sourceStarts.add(sourceStart);
                ranges.add(range);
                offsets.add(destStart - sourceStart);
            }
            RangeMap waterToLight = new RangeMap(offsets, sourceStarts, ranges);
            fertilizerToWater.setNext(waterToLight);
            i += 2;
            sourceStarts = new ArrayList<>();
            offsets = new ArrayList<>();
            ranges = new ArrayList<>();
            for (; input.get(i).length() != 0; ++i) {
                line = input.get(i);
                long[] longs = StringUtil.stringToLongArray(line);
                long destStart = longs[0],
                        sourceStart = longs[1],
                        range = longs[2];
                sourceStarts.add(sourceStart);
                ranges.add(range);
                offsets.add(destStart - sourceStart);
            }
            RangeMap lightToTemperature = new RangeMap(offsets, sourceStarts, ranges);
            waterToLight.setNext(lightToTemperature);
            i += 2;
            sourceStarts = new ArrayList<>();
            offsets = new ArrayList<>();
            ranges = new ArrayList<>();
            for (; input.get(i).length() != 0; ++i) {
                line = input.get(i);
                long[] longs = StringUtil.stringToLongArray(line);
                long destStart = longs[0],
                        sourceStart = longs[1],
                        range = longs[2];
                sourceStarts.add(sourceStart);
                ranges.add(range);
                offsets.add(destStart - sourceStart);
            }
            RangeMap temperatureToHumidity = new RangeMap(offsets, sourceStarts, ranges);
            lightToTemperature.setNext(temperatureToHumidity);
            i += 2;
            sourceStarts = new ArrayList<>();
            offsets = new ArrayList<>();
            ranges = new ArrayList<>();
            for (; i < input.size() && input.get(i).length() != 0; ++i) {
                line = input.get(i);
                long[] longs = StringUtil.stringToLongArray(line);
                long destStart = longs[0],
                        sourceStart = longs[1],
                        range = longs[2];
                sourceStarts.add(sourceStart);
                ranges.add(range);
                offsets.add(destStart - sourceStart);
            }
            RangeMap humidityToLocation = new RangeMap(offsets, sourceStarts, ranges);
            temperatureToHumidity.setNext(humidityToLocation);
        }
        System.out.println(seedToSoil);
        long result = seeds.stream().mapToLong(l -> l).map(seedToSoil::convert).min().orElse(-1);
        System.out.println(result);
    }
}
