package year2024.day4.part1;

import util.FileReader;

public class XmasCounter {
    public static void main(String[] args) {
        char[][] input = FileReader.readFileAsCharArray("src/year2024/day4/input.txt");
        int[][] directions = new int[][]{{1,0},{1,1},{0,1},{-1,1},{-1,0},{-1,-1},{0,-1},{1,-1}};
        char[] word = new char[]{'X','M','A','S'};
        int count = 0;

        for(int i = 0; i < input.length; i++) {
            for(int j = 0; j < input[0].length; j++) {
                if(input[i][j] == 'X') {
                    for(int[] d : directions) {
                        int x = i, y = j;
                        boolean wordFound = true;
                        for(int n = 1; n <= 3; n++) {
                            x += d[0];
                            y += d[1];
                            if(x < 0 || x >= input.length || y < 0 || y >= input[0].length || input[x][y] != word[n]) {
                                wordFound = false;
                                break;
                            }
                        }
                        if(wordFound)
                            count++;
                    }
                }
            }
        }

        System.out.println(count);
    }
}
