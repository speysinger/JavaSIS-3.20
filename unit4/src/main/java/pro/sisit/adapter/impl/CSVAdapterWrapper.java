package pro.sisit.adapter.impl;

import pro.sisit.adapter.IOAdapter;

import java.io.IOException;

public class CSVAdapterWrapper<T> implements IOAdapter<T> {
    private CSVAdapter<T> csvAdapter;

    public CSVAdapterWrapper(CSVAdapter<T> csvAdapter) {
        this.csvAdapter = csvAdapter;
    }

    @Override
    public T read(int index) {
        System.out.println("Попытка чтения строки под номером -> " + (index + 1));
        return csvAdapter.read(index);
    }

    @Override
    public int append(T entity) throws IOException {
        System.out.println("Попытка добавления строки, содержащей -> " + entity);
        return csvAdapter.append(entity);
    }
}
