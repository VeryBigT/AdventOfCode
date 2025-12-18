package year2023.day4.part1;

import util.io.FileReader;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WinningNumbers {

    public static void main(String[] args) {
        List<String> input = FileReader.readFile("src/year2023/day4/input.txt");
        int result = 0;
        for(String line : input) {
            String[] leftAndRight = line.split(":")[1].split("\\|");
            List<Integer> winningNumbers = Arrays.stream(leftAndRight[0].split(" "))
                    .filter(s -> s.length() != 0)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            List<Integer> yourNumbers = Arrays.stream(leftAndRight[1].split(" "))
                    .filter(s -> s.length() != 0)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            int schnitt = schnitt(winningNumbers, yourNumbers);
            System.out.println(winningNumbers);
            System.out.println(yourNumbers);
            int points = schnitt == 0 ? 0 : 1 << schnitt-1;
            System.out.println(points);
            result += points;
        }
        System.out.println(result);

        System.out.println(gewinn(new int[]{1,2,3}, new int[]{1,5,3}));
        System.out.println(gewinn(new int[]{1,2,3}, new int[]{4,5,6}));
        System.out.println(gewinn(new int[]{1,2,3}, new int[]{1,2,3}));
    }

     public static int schnitt(List<Integer> a, List<Integer> b) {
        int result = 0;
        Collections.sort(a);
        Collections.sort(b);
        for(int i = 0, j = 0; i < a.size() && j < b.size();) {
            int ai = a.get(i), bj = b.get(j);
            if(ai == bj) {
                result++;
                i++;
                j++;
                continue;
            }
            else if(ai < bj) {
                i++;
                continue;
            }
            j++;
        }
        return result;
    }

    static int gewinn(int[] auswahl, int[] gezogen)
    {
        int result = 0;
        for(int a : auswahl)
        {
            for(int g : gezogen)
            {
                if(a == g)
                {
                    if(result == 0)
                        result = 1;
                    else
                        result *= 2;
                }
            }
        }
        return result;
    }
}


