package za.co.mtax.tool.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import za.co.mtax.tool.domain.enums.FormType;
import za.co.mtax.tool.domain.dto.FieldDescriptionResults;
import za.co.mtax.tool.service.BatchFileService;
import za.co.mtax.tool.service.MetadataService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LandingControllerTest {

    @Mock
    private MultipartFile multipartFile;

    @Mock
    private BatchFileService batchFileservice;

    @Mock
    private FieldDescriptionResults fieldDescriptionResults;

    @Mock
    private MetadataService metadataService;

    @InjectMocks
    private LandingController landingController = new LandingController();

    @Test
    public void testGetIndex() {
        ModelAndView modelAndView = landingController.showIndex();
        assertEquals(1, modelAndView.getModel().size());
        assertNotNull(modelAndView.getModel().get(LandingController.FORM_TYPES_KEY));
    }

    @Test
    public void testUpload() throws Exception {
        when(multipartFile.getBytes()).thenReturn(new byte[]{7,3,3,7});
        when(batchFileservice.verifyBatchFileSpec(any(FormType.class), anyString())).thenReturn(fieldDescriptionResults);
        Model model = new BindingAwareModelMap();

        landingController.handleFileUpload(multipartFile, FormType.FORM_AD.getFormName(), model);

        verify(multipartFile).getBytes();
        verify(batchFileservice).verifyBatchFileSpec(any(FormType.class), anyString());
        verify(metadataService).getColumnNames();

        assertEquals(4, model.asMap().size());
    }
}