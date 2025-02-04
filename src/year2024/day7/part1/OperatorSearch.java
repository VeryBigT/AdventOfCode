package year2024.day7.part1;

import util.FileReader;
import util.StringUtil;

import java.util.Arrays;
import java.util.List;

public class OperatorSearch {
    public static void main(String[] args) {
        List<String> input = FileReader.readFile("src/year2024/day7/input.txt");
        long sum = input.parallelStream()
                .map(OperatorSearch::getEquation)
                .filter(Equation::solvable)
                .peek(Equation::print)
                .mapToLong(Equation::result)
                .sum();
        System.out.println(sum);
    }

    private static Equation getEquation(String line) {
        String[] tmp = line.split(":");
        long result = Long.parseLong(tmp[0]);
        int[] numbers = StringUtil.stringToIntArray(tmp[1]);
        return new Equation(result, numbers);
    }

    private record Equation (long result, int[] numbers, char[] operators) {
        Equation(long result, int[] numbers) {
            this(result, numbers, new char[numbers.length-1]);
            Arrays.fill(this.operators, '+');
        }

        boolean solvable() {
            for(int i = 0; i < 1 << operators.length; i++, iterateOP())
                if(this.result == calc())
                    return true;
            return false;
        }

        private void iterateOP() {
            for(int i = 0; i < operators.length; i++) {
                if(operators[i] == '+') {
                    operators[i] = '*';
                    return;
                }
                operators[i] = '+';
            }
        }

        private long calc() {
            long result = numbers[0];
            for(int i = 1; i < numbers.length; i++) {
                char op = operators[i-1];
                if(op == '+')
                    result += numbers[i];
                else
                    result *= numbers[i];
            }
            return result;
        }

        void print() {
            StringBuilder sb = new StringBuilder();
            sb.append(result).append(" = ").append("(".repeat(operators.length)).append(numbers[0]);
            for(int i = 0; i < operators.length; i++) {
                sb.append(' ').append(operators[i]).append(' ').append(numbers[i + 1]).append(')');
            }
            System.out.println(sb);
        }
    }
}
