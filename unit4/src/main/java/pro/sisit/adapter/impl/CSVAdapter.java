package pro.sisit.adapter.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import pro.sisit.adapter.CSVAdopted;
import pro.sisit.adapter.IOAdapter;

public class CSVAdapter<T extends CSVAdopted> implements IOAdapter<T> {

    private Class<T> entityType;
    private BufferedReader reader;
    private BufferedWriter writer;

    public CSVAdapter(Class<T> entityType, BufferedReader reader,
                      BufferedWriter writer) {

        this.entityType = entityType;
        this.reader = reader;
        this.writer = writer;

    }

    private String getLineAtIndex(int index) {
        String lineByIndex = "";
        int locIndex = 0;

        try {
            reader.mark(50000);
            do {
                lineByIndex = reader.readLine();
                if (locIndex == index) {
                    break;
                }
                locIndex++;

            } while (lineByIndex != null);
            reader.reset();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineByIndex;
    }

    private int lineCount() {
        int count = 0;
        try {
            reader.mark(50000);
            while (reader.readLine() != null) {
                count++;
            }
            reader.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public T read(int index) {
        String lineAtIndex = getLineAtIndex(index);

        if (lineAtIndex.length() == 0) {
            throw new RuntimeException("Недопустимый индекс");
        }

        T entity = null;
        try {
            entity = entityType.getDeclaredConstructor().newInstance();
            entity.setCSVObject(lineAtIndex);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                NoSuchMethodException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public int append(T entity) {
        if (entity.getClass() != entityType) {
            throw new RuntimeException("Недопустимый параметр");
        }
        try {
            String entityString = entity.getCSVLine();
            if (lineCount() != 0)
                writer.newLine();

            writer.write(entityString);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return (lineCount() - 1);
    }
}
