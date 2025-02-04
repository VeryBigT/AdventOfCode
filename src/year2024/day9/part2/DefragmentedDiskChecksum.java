package year2024.day9.part2;

import util.FileReader;
import util.Range;

import java.util.*;
import java.util.stream.IntStream;

public class DefragmentedDiskChecksum {
    public static void main(String[] args) {
        String input = FileReader.readFile("src/year2024/day9/input.txt").get(0);
        //String input = "2333133121414131402";
        int[] disk = diskFromMap(input);
        pack(disk);
        long checkSum = IntStream.range(0, disk.length)
                .filter(idx -> disk[idx] != -1)
                .mapToLong(idx -> (long) idx * disk[idx])
                .sum();
        System.out.println(checkSum);
    }

    private static int[] diskFromMap(String diskMap) {
        int[] disk = new int[diskMap.chars().map(i -> i-'0').sum()];
        Arrays.fill(disk, -1);
        boolean isFile = true;
        for(int i = 0, j = 0; i < diskMap.length(); i++, isFile = ! isFile) {
            int length = Integer.parseInt(String.valueOf(diskMap.charAt(i)));
            if(isFile)
                Arrays.fill(disk, j, j + length, i/2);
            j += length;
        }
        return disk;
    }

    private static void pack(int[] disk) {
        List<Range> files = new ArrayList<>();
        List<Range> gaps = new ArrayList<>();
        int start = 0;
        for(int end = 1; end < disk.length; end++) {
            int id = disk[start];
            if(disk[end] == id)
                continue;
            if(id != -1)
                files.add(new Range(start, end));
            else if(end - start >= 1)
                gaps.add(new Range(start, end));
            start = end;
        }
        files.add(new Range(start, disk.length));
        //print(disk);
        for(int id = files.size()-1; id >= 0; id--) {
            Range file = files.get(id);
            for(int j = 0; j < gaps.size(); j++) {
                Range gap = gaps.get(j);
                if(gap == null || gap.length() < file.length() || gap.start() > file.start())
                    continue;
                Arrays.fill(disk, (int) gap.start(), (int) (gap.start() + file.length()), id);
                Arrays.fill(disk, (int) file.start(), (int) file.end(), -1);
                //print(disk);
                gap.cutFront(file.length());
                break;
            }
        }
    }

    private static void print(int[] disk) {
        StringBuilder sb = new StringBuilder();
        for(int i : disk)
            sb.append(i == -1 ? '.' : "" + i);
        System.out.println(sb);
    }
}
