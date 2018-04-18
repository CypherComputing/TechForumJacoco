package za.co.mtax.tool.domain.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public enum FormType {

    FORM_AD("Form AD", 5),
    FORM_B("Form B", 6),
    FORM_C("Form C", 7),
    FORM_E("Form E", 7),
    FORM_ROT01("ROT01", 8),
    FORM_ROT02("ROT02", 9);

    private String formName;
    private Integer id;

    FormType(String formName, Integer id) {
        this.formName = formName;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getFormName() {
        return formName;
    }

    public static FormType fromName(String name) {
        FormType[] values = values();
        for (FormType value : values) {
            if (value.getFormName().equals(name)) {
                return value;
            }
        }
        return null;
    }

    public static List<String> getFormNames() {
        List<String> formNames = new ArrayList<>();
        Stream.of(values()).forEach(formType -> {
            formNames.add(formType.getFormName());
        });
        return formNames;
    }
}
