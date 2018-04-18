package za.co.mtax.tool.service;

import org.springframework.stereotype.Service;
import za.co.mtax.tool.domain.entities.BatchFileSpec;

import javax.annotation.PostConstruct;
import javax.persistence.Column;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MetadataService {

    private List<String> columnNames = new ArrayList<>();

    @PostConstruct
    public void init() {
        initializeColumnNames();
    }

    private void initializeColumnNames() {
        for (Field field : BatchFileSpec.class.getDeclaredFields()) {
            String columnName;
            if (field.isAnnotationPresent(Column.class)) {
                columnName = field.getName();
                columnNames.add(columnName);
            }
        }
    }

    public List<String> getColumnNames() {
        return Collections.unmodifiableList(columnNames);
    }
}