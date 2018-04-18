package za.co.mtax.tool.domain.dto;


public class FormResponseData {

    private final String key;
    private final String value;

    public FormResponseData(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

}