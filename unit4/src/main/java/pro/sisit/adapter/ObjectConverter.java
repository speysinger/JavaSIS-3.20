package pro.sisit.adapter;

import java.util.List;

public interface ObjectConverter {

    List<String> getListOfFields();
    void initializeObject(List<String> fields);
}
