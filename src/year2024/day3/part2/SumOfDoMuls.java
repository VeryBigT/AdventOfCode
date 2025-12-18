package year2024.day3.part2;

import util.io.FileReader;

import java.util.List;

public class SumOfDoMuls {
    public static void main(String[] args) {
        List<String> input = FileReader.readFile("src/year2024/day3/input.txt");
        String text = String.join("", input);
        int result = 0;
        boolean isOnDo = true;
        for(int i = 0; i < text.length(); i++) {
            if(text.startsWith("do()", i)) {
                isOnDo = true;
                continue;
            }
            if(text.startsWith("don't()", i)) {
                isOnDo = false;
                continue;
            }
            if(!text.startsWith("mul(", i) || !isOnDo)
                continue;
            String s = text.substring(i, Math.min(i + 12, text.length()));
            System.out.println("mul() found");
            System.out.println(s);
            if(s.matches("mul\\([0-9]{1,3},[0-9]{1,3}\\).*")) {
                int f1 = Integer.parseInt(text.substring(text.indexOf("(", i)+1, text.indexOf(",", i))),
                        f2 = Integer.parseInt(text.substring(text.indexOf(",", i)+1, text.indexOf(")", i)));
                System.out.println(f1 + " * " + f2);
                result += f1 * f2;
            }
        }
        System.out.println(result);
    }
}
