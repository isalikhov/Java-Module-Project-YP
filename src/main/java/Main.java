import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //для корректной работы с точкой
        Locale.setDefault(Locale.US);

        Scanner scanner = new Scanner(System.in);

        // Ввод количества людей >1
        int n = 0;
        boolean errorPersonsCount = true;

        do {
            System.out.println("На скольких человек необходимо разделить счёт?");
            try {
                n = scanner.nextInt();
            } catch (Exception e) {
            }

 /*         альтернативная обработки ошибочного ввода
            if (scanner.hasNextInt()){
                n = scanner.nextInt();
            } else scanner.nextLine();
*/
            if (n > 1) {
                errorPersonsCount = false;
            } else
                System.out.println("Ошибка: некорректное значение для подсчёта.");
            scanner.nextLine();
        }

        while (errorPersonsCount);

        // Добавление товаров в калькулятор
        String tovarName = "";
        float tovarPrice = -1;
        boolean notEndTovars = true;
        Tovar tovar1 = new Tovar();
        Calc calc1 = new Calc();

        do {
            System.out.print("Введите название товара: ");
            tovarName = scanner.nextLine();
            tovar1.name = tovarName;

            boolean errorInPrice = true;
            do {
                tovarPrice = -1;

                //Товар ввели, вводим цену товара
                System.out.print("Введите стоимость товара: ");
                try {
                    tovarPrice = scanner.nextFloat();
                } catch (Exception e) {
                }
                scanner.nextLine();
                if (tovarPrice >= 0) {
                    errorInPrice = false;
                    tovar1.price = tovarPrice;

                    //Цену товара ввели, добавляем товар в калькулятор
                    calc1.addTovar(tovar1);
                    System.out.println("Товар " + tovar1.name + " успешно добавлен");
                } else System.out.println("Ошибка: некорректная стоимость.");

            } while (errorInPrice);

            //  Завершить ввод товаров?
            System.out.println("Хотите добавить ещё один товар? (введите Завершить если достаточно)");
            if (scanner.nextLine().equalsIgnoreCase("завершить")) {
                notEndTovars = false;
            }
        }
        while (notEndTovars);

// Вывод результата, Количество людей, Товары
        System.out.println("Добавленные товары:");
        System.out.println(calc1.allTovars);
        System.out.println("Каждый человек должен заплатить: " +
                String.format("%.2f", calc1.totalPrice / n) + " " + floatToRubles(calc1.totalPrice / n));
        scanner.close();
    } //конец main

    public static String floatToRubles(float r) {
        int i = (int) r;
        int i10 = i % 10, i100 = i % 100;
        if (i100 >= 10 && i100 <= 20) {
            return "рублей";
        } else if (i10 >= 5) {
            return "рублей";
        } else if (i10 == 0) {
            return "рублей";
        } else if (i10 == 1) {
            return "рубль";
        } else return "рубля";
    }
}

class Tovar {
    String name = "";
    float price = 0.00f;
}

class Calc {
    String allTovars = "";
    float totalPrice = 0.00f;

    void addTovar(Tovar tovar) {
        allTovars += tovar.name + "\n";
        totalPrice += tovar.price;
    }
}
