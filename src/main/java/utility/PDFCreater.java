package utility;
//
//import com.itextpdf.text.*;
//import com.itextpdf.text.pdf.*;
//
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
public class PDFCreater {
//    static PDFCreater pdfCreater;
//    private final RandomNumberFormatter r = new RandomNumberFormatter();
//    private PdfWriter writer;
//
//    public void createPDFPage(Document document, String pathFolder, String status) throws Exception {
//            writer = PdfWriter.getInstance(document, Files.newOutputStream(Paths.get(pathFolder)));
//            initializeDocument(document, status);
//    }
//
//    private void initializeDocument(Document document, String status) throws DocumentException {
//        document.open();
//        for (int i = 0; i < 11; i++) {
//            document.add(new Paragraph("\n"));
//        }
//
//        FooterEvent event = new FooterEvent();
//        writer.setPageEvent(event);
//
//        Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD);
//        Font subtitleFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.ITALIC);
//        Font contentFont = new Font(Font.FontFamily.TIMES_ROMAN, 12);
//
//        Paragraph title = new Paragraph("INOVASI DAYA SOLUSI", titleFont);
//        title.setAlignment(Paragraph.ALIGN_CENTER);
//        document.add(title);
//
//        Paragraph subtitle = new Paragraph(status, subtitleFont);
//        subtitle.setAlignment(Paragraph.ALIGN_CENTER);
//        document.add(subtitle);
//
//        Paragraph content = new Paragraph("Execution Date: " + r.getCurrentDate(), contentFont);
//        content.setAlignment(Paragraph.ALIGN_CENTER);
//        document.add(content);
//    }
//
//
//    public static PDFCreater getPDFCreater() {
//        if (pdfCreater == null) {
//            pdfCreater = new PDFCreater();
//        }
//        return pdfCreater;
//    }
//    public static Document createDocument() {
//        return new Document(PageSize.A4.rotate());
//    }
//
//
}
