package year2023.day2.part1;

import java.util.Arrays;
import java.util.List;

public class DiceGame {
                                   //r,  g,  b
    public static int[] question = {12, 13, 14};

    public static void main(String[] args) {
        List<String> input = util.FileReader.readFile("src/year2023/day2/input.txt");
        int result = 0;
        for(int i = 0; i < input.size(); ++i) {
            int[] maxDices = getMaxDices(input.get(i));
            //System.out.println("Game " + i + ": " + Arrays.toString(maxDices));
            if(maxDices[0] <= question[0] && maxDices[1] <= question[1] && maxDices[2] <= question[2]) {
                result += i + 1;
            }
        }
        System.out.println(result);
    }

    private static int[] getMaxDices(String s) {
        int[] result = new int[3];
        String[] pulls = s.split(":")[1].split(";");
        for(String pull : pulls) {
            String[] dices = pull.split(",");
            for(String dice : dices) {
                int number = Integer.parseInt(dice.split(" ")[1]);
                if(dice.endsWith("red"))
                    result[0] = Math.max(result[0], number);
                if(dice.endsWith("green"))
                    result[1] = Math.max(result[1], number);
                if(dice.endsWith("blue"))
                    result[2] = Math.max(result[2], number);
            }
        }
        return result;
    }

}
