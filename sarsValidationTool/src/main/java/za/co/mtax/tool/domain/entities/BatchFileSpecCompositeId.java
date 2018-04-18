package za.co.mtax.tool.domain.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class BatchFileSpecCompositeId implements Serializable{


    @Column(name = "FILE_SPEC_TYPE_CDE")
    private Integer fileSpecSectionCode;

    @Column(name = "FIELD_NAME")
    private String fieldName;

    @Column(name = "SPEC_VERSION")
    private String specVersion;

    @Column(name = "BATCH_TYPE_CDE")
    private Integer formType;

    public BatchFileSpecCompositeId() {
    }

    public BatchFileSpecCompositeId(Integer fileSpecSectionCode, String fieldName, String specVersion, Integer formType) {
        this.fileSpecSectionCode = fileSpecSectionCode;
        this.fieldName = fieldName;
        this.specVersion = specVersion;
        this.formType = formType;
    }

    public String getSpecVersion() {
        return specVersion;
    }

    public Integer getFileSpecSectionCode() {
        return fileSpecSectionCode;
    }

    public String getFieldName() {
        return fieldName;
    }
}
