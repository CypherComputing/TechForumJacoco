package za.co.mtax.tool.domain.dto;


import org.springframework.util.Assert;
import za.co.mtax.tool.domain.enums.BatchSectionType;
import za.co.mtax.tool.domain.entities.BatchFileSpec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DTO that contains the results of attempting to parse form data
 */
public class FieldDescriptionResults {

    public static final int HEADER_INDEX = 0;
    private List<String> message = new ArrayList<>();
    private boolean valid;

    private final List<FormResponseData> headerDataList = new ArrayList<>();
    private final List<FormResponseData> bodyDataList = new ArrayList<>();
    private final List<FormResponseData> trailerDataList = new ArrayList<>();

    private List<BatchFileSpec> headerSpec = new ArrayList<>();
    private List<BatchFileSpec> bodySpec = new ArrayList<>();
    private List<BatchFileSpec> trailerSpec = new ArrayList<>();

    private boolean debug = true;

    public FieldDescriptionResults(List<BatchFileSpec> spec, List<String> formResponseFile) {
        valid = true;
        Assert.isTrue(formResponseFile.size() >= 3, "file must contain at least 3 parts: header, record and trailer");
        initSpecSectionList(spec);
        processFormResponseFile(formResponseFile);
    }

    public Map<String, List<BatchFileSpec>> getFileSpec() {
        Map<String, List<BatchFileSpec>> data = new HashMap<>();
        data.put(BatchSectionType.HEADER.getName(), headerSpec);
        data.put(BatchSectionType.RECORD.getName(), bodySpec);
        data.put(BatchSectionType.TRAILER.getName(), trailerSpec);
        return data;
    }

    private void createSection(List<BatchFileSpec> spec, String section, List<FormResponseData> dataList) {
        int index = 0;
        String sectionType = "";
        for (BatchFileSpec batchFileSpec : spec) {
            int length = batchFileSpec.getLength();

            if (logIfError(index, length, section, batchFileSpec)) {
                return;
            }

            String subString = section.substring(index, index + length);
            if ("SEC-ID".equals(batchFileSpec.getFieldName())) {
                sectionType = subString;
            }
            if (debug) {
                System.out.println("sectionid[" + batchFileSpec.getFileSpecSectionCode() + "] value[" + subString + "] section --[" + batchFileSpec.getFieldName() + "]");
            }
            dataList.add(new FormResponseData(batchFileSpec.getFieldName(), subString));
            index += length;
        }

        logIfOverflow(spec, section, index, sectionType);
    }

    private void processFormResponseFile(List<String> formResponseFile) {
        for (int index = 0; index < formResponseFile.size(); index++) {
            if (index == HEADER_INDEX) {
                createSection(headerSpec, formResponseFile.get(index), getHeaderDataList());
            } else if (index > HEADER_INDEX && index < formResponseFile.size() - 1) {
                createSection(bodySpec, formResponseFile.get(index), getBodyDataList());
            } else {
                createSection(trailerSpec, formResponseFile.get(index), getTrailerDataList());
            }
        }
    }

    private void initSpecSectionList(List<BatchFileSpec> batchFileSpecList) {
        batchFileSpecList.forEach(batchFileSpec -> {
            if (BatchSectionType.HEADER.getValue() == batchFileSpec.getFileSpecSectionCode()) {
                headerSpec.add(batchFileSpec);
            } else if (BatchSectionType.RECORD.getValue() == batchFileSpec.getFileSpecSectionCode()) {
                bodySpec.add(batchFileSpec);
            } else {
                trailerSpec.add(batchFileSpec);
            }
        });
    }

    private boolean logIfError(int index, int length, String data, BatchFileSpec batchFileSpec) {
        if (index + length > data.length()) {
            valid = false;
            message.add(String.format("Value access out of range in section %s input ended at %d or element %s",
                    BatchSectionType.valueOf(batchFileSpec.getFileSpecSectionCode()).toString(),
                    data.length(), batchFileSpec.getFieldName()
            ));
            return true;
        }
        return false;
    }

    private void logIfOverflow(List<BatchFileSpec> spec, String section, int index, String sectionType) {
        if (section.length() - 1 > index) {
            valid = false;
            String substring = section.substring(index, section.length());
            message.add(sectionType + ": \n\n There is more data in the file for section" +
                    BatchSectionType.valueOf(spec.get(0).getFileSpecSectionCode())
                    + "  than what was expected from position " + index + " in file with values of: " + substring + "\n\n");
        }
    }

    public List<String> getMessage() {
        return message;
    }

    public boolean isValid() {
        return valid;
    }

    public List<FormResponseData> getHeaderDataList() {
        return headerDataList;
    }

    public List<FormResponseData> getBodyDataList() {
        return bodyDataList;
    }

    public List<FormResponseData> getTrailerDataList() {
        return trailerDataList;
    }

    public int getRecordCount() {
        return headerSpec.size() + bodySpec.size() + trailerSpec.size();
    }
}