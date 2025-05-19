package Core_5;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            // Тест первой функции — создание резервной копии
            GameUtils.backupCurrentDir();
            System.out.println("Резервная копия успешно создана!");

            // Тест второй функции — сохранение игрового поля
            int[] gameField = {0, 1, 2, 3, 0, 1, 2, 3, 0}; // Исходное игровое поле
            GameUtils.saveGameField(gameField);
            System.out.println("Игровое поле успешно записано в файл.");

            // Тест третьей функции — чтение игрового поля
            int[] restoredField = GameUtils.loadGameField();
            System.out.println("Восстанавливаемое игровое поле: " + Arrays.toString(restoredField));

        } catch (IOException | IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}