package com.thomasaleknovic.workestimateapi.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.processor.text.ITextProcessor;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;
import com.thomasaleknovic.workestimateapi.models.Estimate;

public class PdfGenerator {

    public static String parseThymeleafTemplate(Estimate estimateData) {
    ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
    templateResolver.setSuffix(".html");
    templateResolver.setTemplateMode("HTML5");
    templateResolver.setCharacterEncoding("UTF-8");

    TemplateEngine templateEngine = new SpringTemplateEngine();
    templateEngine.setTemplateResolver(templateResolver);

    Context context = new Context();
    context.setVariable("estimate", estimateData);

    return templateEngine.process("workEstimatePdf.html", context);
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
