package za.co.mtax.tool.domain.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "MT_BATCH_FILE_SPEC")
public class BatchFileSpec implements Serializable {

    @EmbeddedId
    private BatchFileSpecCompositeId batchFileSpecCompositeId = new BatchFileSpecCompositeId();

    @Column
    private int orderId;
    @Column
    private String propertyName;
    @Column
    private String batchFieldName;
    @Column
    private String batchRecordFieldName;
    @Column
    private String aggregateFieldName;
    @Column
    private String value;
    @Column
    private int length;
    @Column
    private int leftJustified;
    @Column
    private String paddingChar;
    @Column
    private int aggregate;
    @Column
    private int count;
    @Column
    private String simpleDateFormat;

    public BatchFileSpec() {

    }

    public BatchFileSpec(int formType, int fileSpecTypeCde, String fieldName, String specVersion, int orderId,
                         String propertyName, String batchFieldName, String batchRecordFieldName, String aggregateFieldName,
                         String value, int length, int leftJustified, String paddingChar, int aggregate, int count, String simpleDateFormat) {

        this.batchFileSpecCompositeId = new BatchFileSpecCompositeId(fileSpecTypeCde, fieldName, specVersion, formType);

        this.orderId = orderId;
        this.propertyName = propertyName;
        this.batchFieldName = batchFieldName;
        this.batchRecordFieldName = batchRecordFieldName;
        this.aggregateFieldName = aggregateFieldName;
        this.value = value;
        this.length = length;
        this.leftJustified = leftJustified;
        this.paddingChar = paddingChar;
        this.aggregate = aggregate;
        this.count = count;
        this.simpleDateFormat = simpleDateFormat;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getLength() {
        return length;
    }

    public int getOrderId() {
        return orderId;
    }

    public Integer getFileSpecSectionCode() {
        return batchFileSpecCompositeId.getFileSpecSectionCode();
    }

    public String getSpecVersion() {
        return batchFileSpecCompositeId.getSpecVersion();
    }

    public String getFieldName() {
        return batchFileSpecCompositeId.getFieldName();
    }
}
