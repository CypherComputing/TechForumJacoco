package za.co.mtax.tool.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.mtax.tool.domain.enums.FormType;
import za.co.mtax.tool.domain.entities.BatchFileSpec;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest(showSql = true)
@EntityScan("za.co.mtax.tool.domain.entities")
@TestPropertySource("classpath:test_inmemory.properties")
public class BatchFileSpecRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

    private static final String SPEC_VERSION = "12";
    public static final int EXPECTED_ROW_COUNT = 187;

    @Autowired
    private BatchFileSpecRepository batchFileSpecRepository;

    @Test
    public void testGetMaxSpecVersion() throws Exception {
        Integer maxSpecVersion = batchFileSpecRepository.getMaxSpecVersion(FormType.FORM_AD.getId());
        assertNotNull(maxSpecVersion);
        assertEquals(12, maxSpecVersion.intValue());
    }

    @Test
    public void testGetFileSpecByFormType() throws Exception {
        ArrayList<BatchFileSpec> fileSpecList = batchFileSpecRepository.getFileSpecByFormType(FormType.FORM_AD.getId(), SPEC_VERSION);
        assertNotNull(fileSpecList);
        assertEquals(EXPECTED_ROW_COUNT, fileSpecList.size());
        checkOrderIncrementing(fileSpecList);
    }

    private void checkOrderIncrementing(ArrayList<BatchFileSpec> fileSpecList) {
        int start = 0;
        int section = fileSpecList.get(0).getFileSpecSectionCode();
        for (BatchFileSpec batchFileSpec : fileSpecList) {
            if (section != batchFileSpec.getFileSpecSectionCode()) {
                start = 0;
            }

            int orderId = batchFileSpec.getOrderId();
            assertTrue(start < orderId);
            start = batchFileSpec.getOrderId();
        }
    }
}