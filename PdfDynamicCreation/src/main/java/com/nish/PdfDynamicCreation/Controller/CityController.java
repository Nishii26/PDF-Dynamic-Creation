package com.nish.PdfDynamicCreation.Controller;

import java.io.ByteArrayInputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nish.PdfDynamicCreation.Entity.City;
import com.nish.PdfDynamicCreation.Service.ICityService;
import com.nish.PdfDynamicCreation.Utils.GeneratePdfReport;

@Controller
public class CityController {

    @Autowired
    private ICityService OcityService;
    
    @RequestMapping(value = "/PdfViewOptions")
    public String PdfViewSelection(Model model)
    {
    	return "PdfViewOptions";
    }

    @RequestMapping(value = "/pdfreport", method = RequestMethod.GET,produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> citiesReport() {

        List<City> cities = (List<City>) OcityService.findAll();

        ByteArrayInputStream bis = GeneratePdfReport.citiesReport(cities);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

        return ResponseEntity
        		.ok()
        		.headers(headers)
        		.contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
    
    @RequestMapping(value = "/downloadpdf",method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> downloadReport()
    {
    	List<City> cities = (List<City>) OcityService.findAll();

        ByteArrayInputStream bis = GeneratePdfReport.citiesReport(cities);
        InputStreamResource resource = new InputStreamResource(bis);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=citiesreport.pdf");

        return ResponseEntity
        		.ok()
        		.headers(headers)
        		.contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
    
}