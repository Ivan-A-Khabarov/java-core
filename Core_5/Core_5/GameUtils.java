package Core_5;

import java.io.*;
import java.nio.file.*;
import java.util.Arrays;

public class GameUtils {

    /**
     * Создание резервной копии всех файлов из текущей директории
     */
    public static void backupCurrentDir() throws IOException {
        try {
            Files.createDirectories(Path.of("./backup")); // создаем директорию backup

            @SuppressWarnings("resource")
            DirectoryStream<Path> directory = Files.newDirectoryStream(Path.of("."), path -> !path.getFileName().toString().equals("backup"));

            for (Path file : directory) {
                if (!Files.isRegularFile(file)) continue; // пропускаем каталоги

                String filename = file.getFileName().toString(); // получаем имя файла
                Files.copy(file, Path.of("./backup/" + filename), StandardCopyOption.REPLACE_EXISTING); // копируем файлы
            }
        } catch (IOException e) {
            throw new IOException("Ошибка при создании резервной копии.", e);
        }
    }

    /**
     * Сохраняет состояние игрового поля (3x3) в файл 'save1.out'
     */
    public static void saveGameField(int[] field) throws IOException {
        if (field.length != 9 || Arrays.stream(field).anyMatch(x -> x > 3 || x < 0))
            throw new IllegalArgumentException("Неправильный размер массива или недопустимые значения.");

        try (FileOutputStream fos = new FileOutputStream("save1.out")) {
            for (int row = 0; row < 3; row++) {
                byte packedByte = 0;
                for (int col = 0; col < 3; col++) {
                    packedByte |= (byte)(field[row*3+col] << (col * 2));
                }
                fos.write(packedByte);
            }
        }
    }

    /**
     * Читает сохраненное игровое поле из файла 'save1.out' и восстанавливает его в массив
     */
    public static int[] loadGameField() throws IOException {
        int[] loadedField = new int[9];

        try (FileInputStream fis = new FileInputStream("save1.out")) {
            int index = 0;
            int readByte;
            while ((readByte = fis.read()) != -1 && index < 9) {
                for (int shift = 0; shift < 3; shift++, index++) {
                    loadedField[index] = (readByte >>> (shift * 2)) & 0x3;
                }
            }
        }

        return loadedField;
    }
}