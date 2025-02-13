import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите выражение: ");                                                  //всплывающий текст
        System.out.println(calc(sc.nextLine()));              //возвращение введённого значения, если выполнено условие
    }

    public static String calc(String input) {
        boolean romanOrArab = false;                              // Для понимания какое число на выходе (рим или араб)
        String error = "throws Exception";                                                                // Исключение
        var romanExamination = new Main();                              // Вводим для проверки и перевода из рим в араб
        var arabToRoman = new Main();                                                  // Для перевода ответа в римские
        int result;                                                                                // Считает выражение
        String[] inputSplit = input.split(" ");
        if (inputSplit.length != 3){
            return error;                                                            // Анализируем, если не 3 элемента
        }
        int fNumb;
        int sNumb;
        try {
            fNumb = Integer.parseInt(inputSplit[0]);
            sNumb = Integer.parseInt(inputSplit[2]);
        } catch (NumberFormatException e) {                                            // Анализируем, если не арабское
            try {
                fNumb = romanExamination.romanToArab(inputSplit[0]);
                sNumb = romanExamination.romanToArab(inputSplit[2]);
                romanOrArab = true;
            } catch (NumberFormatException ex) {                                        // Анализируем, если не римское
                return error;
            }
        }
        // Указываем диапазон значений
        if ((fNumb < 1) || (fNumb > 10) || (sNumb < 1) || (sNumb > 10)){
            return error;
        }
        String sign = inputSplit[1];
        switch (sign) {
            case "+" -> result = fNumb + sNumb;
            case "-" -> result = fNumb - sNumb;
            case "*" -> result = fNumb * sNumb;
            case "/" -> result = fNumb / sNumb;
            default -> {
                return error;                                                              // Анализируем, если не знак
            }
        }
        String output;                                                                                     // Наш вывод
        if (romanOrArab){
            if(result < 1){
                return error;
            } else {
                output = arabToRoman.arabToRome(result);
            }
        } else output = Integer.toString(result);
        return "Результат выражения: \n" + "\u001B[34m" + output + "\u001B[0m";
    }
    int romanToArab(String romanInput){                                                        // Перевод рим. в араб.
        int resultInt = 0;
        int[] arab = {10, 9, 5, 4, 1};
        String[] roman = {"X", "IX", "V", "IV", "I"};
        for (int i = 0; i < arab.length; i++ ) {
            while (romanInput.indexOf(roman[i]) == 0) {
                resultInt += arab[i];
                romanInput = romanInput.substring(roman[i].length());                    // Исключаем посчитанные числа
            }
        }
        return resultInt;
    }
    String arabToRome(int arabInput){                                                           // Перевод араб. в рим.
        String result = "";
        int[] arab = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roman = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i < arab.length; i++){
            int value = arabInput / arab[i];
            for (int j = 0; j < value; j++){
                result = result.concat(roman[i]);
            }
            arabInput = arabInput % arab[i];
        }
        return result;
    }
}