package edu.ualberta.med.biobank.barcodegenerator.template.jasper.element.text;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import edu.ualberta.med.biobank.barcodegenerator.template.configuration.Rectangle;
import edu.ualberta.med.biobank.barcodegenerator.template.jasper.element.Element;
import edu.ualberta.med.biobank.barcodegenerator.template.jasper.exceptions.ElementCreationException;

/**
 * Used for generating and rendering a text fields.
 * 
 * @author Thomas Polasek 2011
 * 
 */
public class Text extends Element {

    Font font;

    public Text(Rectangle rect, String message, Font font)
        throws ElementCreationException {

        if ((message == null) || (message.length() == 0))
            throw new ElementCreationException(
                Messages.Text_empty_msg_error);

        if (rect == null)
            throw new ElementCreationException(
                Messages.Text_null_dim_error);

        if (font == null)
            throw new ElementCreationException(
                Messages.Text_null_font_error);

        this.rect = rect;
        this.type = Element.TYPE.Text;
        this.message = message;
        this.font = font;
    }

    @Override
    public void render(Graphics2D g, int scale) {
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setFont(font);

        Color previousColor = g.getColor();

        g.setColor(Color.BLACK);
        g.drawString(message, mmToPixel(rect.getX(), scale),
            mmToPixel(rect.getY(), scale));

        g.setColor(previousColor);

    }

}