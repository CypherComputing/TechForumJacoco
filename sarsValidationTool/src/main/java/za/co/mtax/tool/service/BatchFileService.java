package za.co.mtax.tool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.mtax.tool.domain.enums.FormType;
import za.co.mtax.tool.domain.dto.FieldDescriptionResults;
import za.co.mtax.tool.domain.entities.BatchFileSpec;
import za.co.mtax.tool.repository.BatchFileSpecRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BatchFileService {

    @Autowired
    private BatchFileSpecRepository batchFileSpecRepository;

    public FieldDescriptionResults verifyBatchFileSpec(FormType formType, String formResponseFile) throws IOException {
        return verifyBatchFileSpec(formType, Arrays.asList(formResponseFile.split("\n")));
    }

    public FieldDescriptionResults verifyBatchFileSpec(FormType formType, List<String> formResponseFile) throws IOException {

        Integer maxSpecVersion = batchFileSpecRepository.getMaxSpecVersion(formType.getId());

        ArrayList<BatchFileSpec> batchFile = batchFileSpecRepository.getFileSpecByFormType(formType.getId(),
                String.valueOf(maxSpecVersion));

        return new FieldDescriptionResults(batchFile, formResponseFile);
    }
}
