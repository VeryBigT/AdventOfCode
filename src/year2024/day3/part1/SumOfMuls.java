package year2024.day3.part1;

import util.io.FileReader;

import java.util.List;

public class SumOfMuls {
    public static void main(String[] args) {
        List<String> input = FileReader.readFile("src/year2024/day3/input.txt");
        String text = String.join("", input);
        int i = 0;
        int result = 0;
        while((i = text.indexOf("mul(", i)) > 0) {
            String s = text.substring(i, Math.min(i + 12, text.length()));
            System.out.println(s);
            if(s.matches("mul\\([0-9]{1,3},[0-9]{1,3}\\).*")) {
                int f1 = Integer.parseInt(text.substring(text.indexOf("(", i)+1, text.indexOf(",", i))),
                        f2 = Integer.parseInt(text.substring(text.indexOf(",", i)+1, text.indexOf(")", i)));
                System.out.println(f1 + " * " + f2);
                result += f1 * f2;
            }
            i++;
        }
        System.out.println(result);
    }
}
