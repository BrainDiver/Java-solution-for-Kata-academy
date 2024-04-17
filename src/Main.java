// Импорты классов
import java.util.*;

// Основной Класс
public class Main {

    public static String calc(String input) throws Exception {

        String[] operators = new String[] {"\\+", "-", "/", "\\*"};
        String operator = null;
        String first_number = null;
        String second_number = null;
        for (int i = 0; i < operators.length; i++) {
            var result = input.split(operators[i]);
            if (result.length > 1 & result.length < 3) {
                operator = operators[i];
                first_number = result[0];
                second_number = result[result.length - 1];
                //System.out.println(Arrays.toString(result));
                break;
            } else if (result.length > 1 & result.length >= 3 ) {
                throw new Exception("Не правильное выражение");
            } else if (result.length == 1 & i == operators.length-1) {
                throw new Exception("Не правильное выражение");
            }
        }

        String[] numbers = new String[] {"1","2","3","4","5", "6", "7", "8", "9", "10"};
        String[] rome_numbers = new String[] {"I","II","III","IV","V","VI","VII","VIII","IX","X"};
        Set<String> numbers_hs = new HashSet<>(Arrays.asList(numbers));
        Set<String> rome_numbers_hs = new HashSet<>(Arrays.asList(rome_numbers));
        String calc_result = "";

        if (numbers_hs.contains(first_number) & numbers_hs.contains(second_number)) {
            int number = Integer.parseInt(first_number);
            int number_2 = Integer.parseInt(second_number);
            switch(operator) {
                case "\\+":
                    calc_result = String.valueOf(number + number_2);
                    break;
                case "-":
                    calc_result = String.valueOf(number - number_2);
                    break;
                case "\\*":
                    calc_result = String.valueOf(number * number_2);
                    break;
                case "/":
                    calc_result = String.valueOf(number / number_2);
                    break;
            }
        } else if (rome_numbers_hs.contains(first_number) & rome_numbers_hs.contains(second_number)) {
            int number = Roman.valueOf(first_number).toInt();
            int number_2 = Roman.valueOf(second_number).toInt();
            switch(operator) {

                case "\\+":
                    calc_result = toRoman(number + number_2);
                    break;

                case "-":
                    if ( (number - number_2) <= 0 ) {
                        throw new Exception("В Римской системе счисления нет отрицательных и нуля");
                    } else {
                        calc_result = toRoman(number - number_2);
                    }
                    break;

                case "\\*":
                    calc_result = toRoman(number * number_2);
                    break;

                case "/":
                    if ( (number / number_2) <= 0 ) {
                        throw new Exception("В римской системе счисления нет отрицателных чисел и нуля");
                    } else {
                        calc_result = toRoman(number / number_2);
                    }
                    break;
            }
        } else {
            throw new Exception("Не допустимые числа или неправильное выражение");
        }
        return calc_result;
    }


    enum Roman {
        I(1),II(2), III(3), IV(4), V(5), VI(6), VII(7), VIII(8), IX(9), X(10);
        private final int value;
        Roman(int value) {
            this.value = value;
        }
        private int toInt() {
            return value;
        }
    }


    private static String toRoman(int num) {
        Integer[] arabic_numbers = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roman_numbers = {"C","XC","L", "XL", "X", "IX","V", "IV","I"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arabic_numbers.length && num > 0; i++) {
            while (num >= arabic_numbers[i]) {
                num -= arabic_numbers[i];
                sb.append(roman_numbers[i]);
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) throws Exception {

        boolean start = true;
        while (start == true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Введите математическое выражение или слово exit для завершения работы программы:");
            String myStr = scan.nextLine();
            if (myStr.equals("exit")) {
                start = false;
            } else {
                myStr = myStr.replaceAll(" ", "");
                System.out.println(calc(myStr));
            }
        }

    }

}