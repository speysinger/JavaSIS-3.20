package pro.sisit.adapter;

public interface CSVAdopted<T> {
    String getCSVLine();
    void setCSVObject(String data);
}
