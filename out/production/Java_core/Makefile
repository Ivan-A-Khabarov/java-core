# Директории
SRC_DIR := src
OUT_DIR := out
DOC_DIR := docs

# Команды и флаги
JC := javac
JDOC := javadoc
JFLAGS := -sourcepath $(SRC_DIR) -d $(OUT_DIR) -cp $(OUT_DIR)
MAIN_CLASS := ru.gb.jcore.sample.Core_5.Main
MAIN_SOURCE := $(subst .,/,$(MAIN_CLASS))

.PHONY: all clean run docs

# Основная цель - сборка проекта
all:
	$(JC) $(JFLAGS) $(SRC_DIR)/$(MAIN_SOURCE).java

# Очистка скомпилированных файлов
clean:
	rm -rf $(OUT_DIR)/* $(DOC_DIR)/*

# Запуск приложения
run:
	java -cp $(OUT_DIR) $(MAIN_CLASS)

# Генерация документации
docs:
	$(JDOC) -d $(DOC_DIR) -sourcepath $(SRC_DIR) -subpackages ru

# Сборка и запуск
build-run: all run