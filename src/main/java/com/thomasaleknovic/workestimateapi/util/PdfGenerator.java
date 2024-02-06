package com.thomasaleknovic.workestimateapi.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.processor.text.ITextProcessor;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;
import com.thomasaleknovic.workestimateapi.models.Estimate;

@Component
public class PdfGenerator {

    private static SpringTemplateEngine springTemplateEngine;

    @Autowired
    PdfGenerator(SpringTemplateEngine springTemplateEngine) {
        PdfGenerator.springTemplateEngine = springTemplateEngine;
    }

    public static String parseThymeleafTemplate(Estimate estimateData) {
    Context context = new Context();
    context.setVariable("estimate", estimateData);
    return springTemplateEngine.process("estimate", context);

}
    
    public static void generatePdfFromHtml(String html) throws DocumentException, IOException {
        String outputFolder = System.getProperty("user.home") + File.separator + "orçamento.pdf";
        OutputStream outputStream = new FileOutputStream(outputFolder);
    
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);
        

        
    
        outputStream.close();
    }
}
