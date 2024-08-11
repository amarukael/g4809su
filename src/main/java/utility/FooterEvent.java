package utility;

//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.Font;
//import com.itextpdf.text.pdf.BaseFont;
//import com.itextpdf.text.pdf.PdfContentByte;
//import com.itextpdf.text.pdf.PdfPageEventHelper;
//import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;

//public class FooterEvent  extends PdfPageEventHelper {
//    public void onEndPage(PdfWriter writer, Document document) {
//        PdfContentByte cb = writer.getDirectContent();
//        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.NORMAL); // Reduced font size to 6
//
//        try {
//            BaseFont bf = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
//
//            cb.beginText();
//            cb.setFontAndSize(bf, 6);
//            cb.setTextMatrix(document.leftMargin(), document.bottom() - 20);
//            cb.showTextAligned(Element.ALIGN_LEFT, "All rights reserved. No part of this document may be reproduced, stored in a retrieval system, or transmitted, in any form or by any means, electronic, mechanical, photocopying, recording, or otherwise, without the prior written permission of PT. INOVASI DAYA SOLUSI.", document.left(), document.bottom() - 20, 0);
//            cb.endText();
//        } catch (DocumentException | IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
