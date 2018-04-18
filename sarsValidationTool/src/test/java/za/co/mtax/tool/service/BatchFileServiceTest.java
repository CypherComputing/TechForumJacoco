package za.co.mtax.tool.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.mtax.tool.domain.enums.FormType;
import za.co.mtax.tool.domain.dto.FieldDescriptionResults;
import za.co.mtax.tool.repository.BatchFileSpecRepositoryTest;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest()
@EntityScan("za.co.mtax.tool.domain.entities")
@TestPropertySource("classpath:test_inmemory.properties")
public class BatchFileServiceTest extends AbstractTransactionalJUnit4SpringContextTests{

    @Autowired
    private BatchFileService service;

    @Test
    public void testParseBatchFile() throws Exception {
        ClassPathResource classPathResource = new ClassPathResource("FORMAD.C030130-Made-to-work");
        List<String> formAdResponseFile = Files.readAllLines(Paths.get(classPathResource.getFile().getAbsolutePath()));
        FieldDescriptionResults data = service.verifyBatchFileSpec(FormType.FORM_AD, formAdResponseFile);

        assertNotNull(data);
        assertTrue(data.getMessage().toString(), data.isValid());
        Assert.assertEquals(BatchFileSpecRepositoryTest.EXPECTED_ROW_COUNT, data.getRecordCount());
    }

    @Test
    public void testParseBatchFileValidationError() throws Exception {
        ClassPathResource classPathResource = new ClassPathResource("FORMAD.C030130-fail");
        List<String> formAdResponseFile = Files.readAllLines(Paths.get(classPathResource.getFile().getAbsolutePath()));
        FieldDescriptionResults data = service.verifyBatchFileSpec(FormType.FORM_AD, formAdResponseFile);
//
        assertNotNull(data);
        assertFalse(data.isValid());
        assertNotNull(data.getMessage());
    }
}
