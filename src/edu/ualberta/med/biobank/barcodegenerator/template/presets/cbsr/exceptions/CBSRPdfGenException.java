package edu.ualberta.med.biobank.barcodegenerator.template.presets.cbsr.exceptions;

public class CBSRPdfGenException extends Exception {
	
	private static final long serialVersionUID = -6225763254642894265L;
	
	String mistake;

	public CBSRPdfGenException() {
		super();
		mistake = Messages.CBSRPdfGenException_mistake_notavailable_text;
	}

	public CBSRPdfGenException(String error) {
		super(error);
		mistake = error;
	}

	public String getError() {
		return mistake;
	}
}
