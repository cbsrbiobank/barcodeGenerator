package edu.ualberta.med.biobank.barcodegenerator.template.jasper.element.barcodes;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.datamatrix.DataMatrixBean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

import edu.ualberta.med.biobank.barcodegenerator.template.jasper.exceptions.BarcodeCreationException;

/**
 * Wrapper for the barcode4j library.
 * 
 * @author Thomas Polasek 2011
 * 
 */
public class BarcodeGenerator {

    /**
     * Creates a 2D datamatrix barcode buffered image.
     * 
     * @param barcodeMsg
     * @param dpi
     * @return
     * @throws IOException
     * @throws BarcodeCreationException
     */
    public static BufferedImage generate2DBarcode(String barcodeMsg, int dpi)
        throws IOException, BarcodeCreationException {

        if (barcodeMsg == null || barcodeMsg.length() == 0)
            throw new BarcodeCreationException(
                Messages.BarcodeGenerator_empty_msg_2D_error);

        if (dpi < 1 || dpi > 1000)
            throw new BarcodeCreationException(Messages.BarcodeGenerator_dpi_error);

        DataMatrixBean barcodeGenDataMatrix = new DataMatrixBean();
        barcodeGenDataMatrix.setMaxSize(new Dimension(18, 18));
        barcodeGenDataMatrix.setMinSize(new Dimension(18, 18));
        barcodeGenDataMatrix.setModuleWidth(1);
        barcodeGenDataMatrix.setQuietZone(0);
        barcodeGenDataMatrix.setVerticalQuietZone(0);

        BitmapCanvasProvider provider = new BitmapCanvasProvider(dpi,
            BufferedImage.TYPE_BYTE_GRAY, true, 0);
        barcodeGenDataMatrix.generateBarcode(provider, barcodeMsg);

        provider.finish();
        return provider.getBufferedImage();
    }

    /**
     * Creates a 128 code 1D barcode buffered image.
     * 
     * @param barcodeMsg
     * @param font
     * @param dpi
     * @return
     * @throws BarcodeCreationException
     * @throws IOException
     */
    public static BufferedImage generate1DBarcode(String barcodeMsg, Font font,
        int dpi) throws BarcodeCreationException, IOException {

        if (font == null)
            throw new BarcodeCreationException(
                Messages.BarcodeGenerator_font_error);

        if (barcodeMsg == null || barcodeMsg.length() == 0)
            throw new BarcodeCreationException(
                Messages.BarcodeGenerator_empty_msg_1D_error);

        if (dpi < 1 || dpi > 1000)
            throw new BarcodeCreationException(Messages.BarcodeGenerator_dpi_error);

        Code128Bean barcodeGenCode128 = new Code128Bean();
        barcodeGenCode128.setBarHeight(5);// 6
        barcodeGenCode128.setModuleWidth(0.2);// 0.2
        barcodeGenCode128.setFontName(font.getName());
        barcodeGenCode128.setQuietZone(0);
        barcodeGenCode128.setVerticalQuietZone(0);

        BitmapCanvasProvider provider = new BitmapCanvasProvider(dpi,
            BufferedImage.TYPE_BYTE_GRAY, true, 0);
        barcodeGenCode128.generateBarcode(provider, barcodeMsg);

        provider.finish();
        return provider.getBufferedImage();
    }

}
