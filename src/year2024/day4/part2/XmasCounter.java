package year2024.day4.part2;

import util.FileReader;

public class XmasCounter {
    public static void main(String[] args) {
        char[][] input = FileReader.readFileAsCharArray("src/year2024/day4/input.txt");
        int count = 0;

        for(int i = 1; i < input.length - 1; i++) {
            for(int j = 1; j < input[0].length - 1; j++) {
                if(input[i][j] == 'A') {
                    char lo = input[i-1][j-1], ro = input[i+1][j-1], ru = input[i+1][j+1], lu = input[i-1][j+1];
                    if((lo == 'M' && ru == 'S' || lo == 'S' && ru == 'M')
                            && (ro == 'M' && lu == 'S' || ro == 'S' && lu == 'M'))
                        count++;
                }
            }
        }

        System.out.println(count);
    }
}
