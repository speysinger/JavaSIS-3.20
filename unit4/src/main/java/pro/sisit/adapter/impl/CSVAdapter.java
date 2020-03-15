package pro.sisit.adapter.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import pro.sisit.adapter.IOAdapter;

public class CSVAdapter<T> implements IOAdapter<T> {

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
        String lineByIndex = getLineAtIndex(index);

        if (lineByIndex.length() == 0) {
            throw new RuntimeException("������������ ������");
        }

        T entity = null;
        try {
            entity = entityType.getDeclaredConstructor(String.class).newInstance(lineByIndex);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                NoSuchMethodException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public int append(T entity) {
        if (entity.getClass() != entityType) {
            throw new RuntimeException("������������ ��������");
        }
        try {
            String entityString = entity.toString();
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
