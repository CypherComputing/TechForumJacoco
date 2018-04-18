package za.co.mtax.tool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import za.co.mtax.tool.domain.enums.FormType;
import za.co.mtax.tool.domain.dto.FieldDescriptionResults;
import za.co.mtax.tool.service.MetadataService;
import za.co.mtax.tool.service.BatchFileService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;


@Controller
public class LandingController {

    public static final String FORM_TYPES_KEY = "formTypes";
    @Autowired
    private MetadataService metadataService;
    @Autowired
    private BatchFileService batchFileservice;

    @RequestMapping("/index")
    public ModelAndView showIndex() {
        ModelAndView mv = new ModelAndView("Index");
        mv.addObject(FORM_TYPES_KEY, FormType.getFormNames());
        return mv;
    }

    @GetMapping
    public String getFile() {
        return "redirect:index";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("formTypeName") String formTypeName, Model model) throws IOException {

        FormType formType = FormType.fromName(formTypeName);
        FieldDescriptionResults fieldDescriptionResults = batchFileservice.verifyBatchFileSpec(formType, convertToString(file.getBytes()));
        ArrayList<String> getColumnNames = new ArrayList<>(metadataService.getColumnNames());
        HashMap getDBFileSpec = new HashMap<>(fieldDescriptionResults.getFileSpec());

        model.addAttribute("formType", formType);
        model.addAttribute("DBBatchFileSpec", getDBFileSpec);
        model.addAttribute("ColumnNames", getColumnNames);
        model.addAttribute("FileData", fieldDescriptionResults);

        return "Transposer";
    }

    public String convertToString(byte[] bytes) throws UnsupportedEncodingException {
        String data = new String(bytes, "UTF-8");
        return data;
    }
}
