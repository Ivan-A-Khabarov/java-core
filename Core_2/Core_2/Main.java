/*Написать метод, возвращающий количество чётных элементов массива. countEvens([2, 1, 2, 3, 4]) → 3 countEvens([2, 2, 0]) → 3 countEvens([1, 3, 5]) → 0
Написать функцию, возвращающую разницу между самым большим и самым маленьким элементами переданного не пустого массива.
Написать функцию, возвращающую истину, если в переданном массиве есть два соседних элемента, с нулевым значением.
*/
package Core_2;

public class Main {

    public static int countEvens(int[] arr) {
        int counter = 0;
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] % 2 == 0) {
                counter++;
            }
        }
        return counter;
    }

    public static int spread(int[] arr) {
        int min = arr[0];
        int max = arr[0];
        for (int i = 1; i < arr.length; ++i) {
            if (arr[i] < min) min = arr[i];
            if (arr[i] > max) max = arr[i];
        }
        return max - min;
    }

    public static boolean zero2(int[] arr) {
        for (int i = 0; i < arr.length - 1; ++i) {
            if (arr[i] == 0 && arr[i + 1] == 0)
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] testArray = {2, 1, 0, 0, 4, 7, 8};

        // Вызов countEvens и вывод результата
        int evenCount = countEvens(testArray);
        System.out.println("Количество чётных чисел: " + evenCount);

        // Вызов spread и вывод результата
        int arraySpread = spread(testArray);
        System.out.println("Разброс значений (max - min): " + arraySpread);

        // Вызов zero2 и вывод результата
        boolean hasConsecutiveZeros = zero2(testArray);
        System.out.println("Есть ли два нуля подряд: " + hasConsecutiveZeros);
    }
}