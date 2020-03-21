package pro.sisit.adapter.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import pro.sisit.adapter.IOAdapter;
import pro.sisit.adapter.ObjectConverter;

public class CSVAdapter<T extends ObjectConverter> implements IOAdapter<T> {

    final String delimiter = ";";

    private Class<T> entityType;
    private BufferedReader reader;
    private BufferedWriter writer;

    final int charactersLimit = 50000;

    public CSVAdapter(Class<T> entityType, BufferedReader reader,
                      BufferedWriter writer) {

        this.entityType = entityType;
        this.reader = reader;
        this.writer = writer;

    }

    private List<String> getLineAtIndex(int index) {
        String lineAtIndex;
        List<String> objectFields;
        int locIndex = 0;

        try {
            reader.mark(charactersLimit);
            do {
                lineAtIndex = reader.readLine();
                if (locIndex == index) {
                    break;
                }
                locIndex++;

            } while (lineAtIndex != null);

            if (lineAtIndex == null) {
                throw new RuntimeException("Строка с индексом -> " + index + " отсутствует");
            }

            objectFields = Arrays.asList(lineAtIndex.split(delimiter));
            reader.reset();

        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения строки с индексом -> " + locIndex);
        }
        return objectFields;
    }

    private int lineCount() {
        int count = 0;
        try {
            reader.mark(charactersLimit);
            while (reader.readLine() != null) {
                count++;
            }
            reader.reset();
        } catch (IOException e) {
            throw new RuntimeException("Ошибка подсчёта строк. Индекс последней прочитанной строки -> " + count);
        }
        return count;
    }

    @Override
    public T read(int index) {
        T entity;
        try {
            List<String> objectFieldsAtIndex = getLineAtIndex(index);
            entity = entityType.getDeclaredConstructor().newInstance();
            Field[] fields = entityType.getDeclaredFields();

            if (fields.length != objectFieldsAtIndex.size()) {
                throw new InstantiationException("Невозможно инициализировать объект");
            }

            entity.initializeObject(objectFieldsAtIndex);
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException("Ошибка создания объекта через конструктор по умолчанию class->" + entityType +
                    " на индексе -> " + index);
        }
        return entity;
    }

    @Override
    public int append(T entity) {
        if (entity.getClass() != entityType) {
            throw new RuntimeException("Отличный, от ожидаемого " + entityType + ", класс параметра");
        }

        try {
            List<String> entityFieldsList = entity.getListOfFields();
            String entityString = getCsvLine(entityFieldsList);

            if (lineCount() != 0)
                writer.newLine();

            writer.write(entityString);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи объекта ->" + entity);
        }

        return (lineCount() - 1);
    }

    private String getCsvLine(List<String> objectFields) {
        StringBuilder stringBuilder = new StringBuilder();
        objectFields.
                forEach(objectField -> stringBuilder.
                        append(createCsvColumn(objectField)));

        return stringBuilder.toString();
    }

    private String createCsvColumn(String fieldText) {
        return fieldText + delimiter;
    }
}
