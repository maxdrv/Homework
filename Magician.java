/**
 * Класс Magician предназначен для запуска математических фокусов в консольном режиме.
 * Доступные фокусы:
 * 1) Угадай день рождения guessingBirthday
 *
 * @author Деревнин Максим
 * @version 1.0
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class Magician {

    final private static String[] arrayMonthName = {"январь", "февраль" , "март" , "апрель" , "май" , "июнь",
                                                    "июль", "август", "сентябрь", "октябрь", "ноябрь", "декабрь"};
    final private static int[] arrayDayLimit = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    final private static int lowerLimit = 1;
    final private static int upperLimitForMonth = 12;

    /**
     * Метод обеспечивает взаимосвязь с пользователем для задачи guessing birthday.
     * Принимает ввод через консоль от пользователя и выводит ответ в случае коректного ввода,
     * иначе снова предлагает пользователю ввести число.
     */
    public void guessingBirthday() {
        System.out.println("+---------------------------------------------------------------------+");
        System.out.println("|Умножте на 2 число дня своего рождения...                            |");
        System.out.println("|Теперь сложите получившееся произведение и число 5...                |");
        System.out.println("|Умножте на 50 полученную сумму...                                    |");
        System.out.println("|Прибавьте к полученному результату номер месяца своего рождения...   |");
        System.out.println("+---------------------------------------------------------------------+");

        int number;
        int exit = -1;
        while (exit < 0) {
            try {
                number = getUserInput();
                int[] arrayAnswer = logic(number);

                if (arrayAnswer[0] == -1) {
                    System.out.println("Не правильно посчитали, попробуйте снова...");
                } else {
                    exit = arrayAnswer[0];
                    System.out.println("Ваш день рождения " + arrayAnswer[1] + ", а месяц " + arrayMonthName[arrayAnswer[2]] + ".");
                }

            } catch(InputMismatchException ex) {
                System.out.println("Ошибка ввода: нельзя вводить буквы, символы, числа с плавающей точкой, а так же выходить за пределы для числа типа int.");
            }
        }
    }

    /**
     * Метод обеспечивает логику решения задачи guessing birthday.
     * @param number число введенное пользователем с клавиатуры.
     * @return возвращает массив параметров, в случае корректного ввода пользователя, результат будет {1, день рождения, номер месяца},
     * в случае не корректного ввода {-1, -1, -1}
     *
     */
    public int[] logic(int number) {
        int[] arrayAnswer = {-1, -1, -1};
        number -= 250;
        if (isIndexMonthValid(number)) {
            int day = number / 100;
            int month = number % 100;
            // условие требует, что бы месяц входил в диапазон от 1 до 12, а количество дней было больше 0 и не превышало количство дней месяца
            if (day <= arrayDayLimit[month - 1] && day >= lowerLimit && month <= upperLimitForMonth && month >= lowerLimit) {

                arrayAnswer[0] = 1;             // флаг 1 будет присвоен переменной exit, для выхода из цикла
                arrayAnswer[1] = day;
                arrayAnswer[2] = month - 1;     // вычитается единица, что бы привести номер месяца к индексу месяца в массиве
            }
        }
        return arrayAnswer;
    }

    /**
     * Метод проверяет число введенное пользователем, а именно входит ли расчитанный месяц в рамки массива.
     * @param number в качестве параметра используется число, введенное пользователем с клавиатуры и -250.
     * @return метод возвращает значение true, если месяц входит в рамки массива, и false если не входит.
     */
    private boolean isIndexMonthValid(int number) {
        if ((number % 100 - 1) < arrayMonthName.length && (number % 100) > 0) {
            return true;
        }
        return false;
    }

    /**
     * Метод обеспечивает пользовательский ввод с клавиатуры.
     * @return возвращает целочисленное число введенное пользователем с клавиатуры.
     * @exception InputMismatchException если пользователь ввел не число int.
     */
    private int getUserInput(){
        Scanner input = new Scanner(System.in);
        System.out.print("Введите полученное число: ");
        int number = input.nextInt();
        return number;
    }
}
