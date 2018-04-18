package za.co.mtax.tool.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest()
@ComponentScan("za.co.mtax.tool.service")
@TestPropertySource("classpath:test_inmemory.properties")
public class MetaDataServiceTest {

    @Autowired
    private MetadataService metadataService;

    @Test
    public void testColumnNames() throws Exception {

        List<String> columnNames = metadataService.getColumnNames();
        assertNotNull(columnNames);
        assertEquals(12, columnNames.size());
        assertTrue(columnNames.get(0).length() > 0);
    }
}
