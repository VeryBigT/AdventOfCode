package year2024.day9.part1;

import util.io.FileReader;

import java.util.Arrays;
import java.util.stream.IntStream;

public class FragmentedDiskChecksum {
    public static void main(String[] args) {
        String input = FileReader.readFile("src/year2024/day9/input.txt").get(0);
        //String input = "2333133121414131402";
        int[] disk = diskFromMap(input);
        fragment(disk);
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

    private static void print(int[] disk) {
        StringBuilder sb = new StringBuilder();
        for(int i : disk)
            sb.append(i == -1 ? '.' : "" + i);
        System.out.println(sb);
    }

    private static void fragment(int[] disk) {
        int i = 0, j = disk.length - 1;
        while(i < j) {
            while(disk[i] != -1)
                i++;
            while(disk[j] == -1)
                j--;
            if(i < j) {
                int tmp = disk[i];
                disk[i] = disk[j];
                disk[j] = tmp;
                //print(disk);
            }
        }
    }
}
