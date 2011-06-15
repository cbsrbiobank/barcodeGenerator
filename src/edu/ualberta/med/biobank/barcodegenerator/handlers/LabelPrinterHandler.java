package edu.ualberta.med.biobank.barcodegenerator.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;

import edu.ualberta.med.biobank.barcodegenerator.BarcodeGenPlugin;
import edu.ualberta.med.biobank.barcodegenerator.forms.SpecimanLabelEntryForm;
import edu.ualberta.med.biobank.barcodegenerator.perspective.LabelPrinterPerspective;
import edu.ualberta.med.biobank.gui.common.forms.BgcFormInput;

public class LabelPrinterHandler extends AbstractHandler implements IHandler {

    public static final String ID = "edu.ualberta.med.biobank.barcodegenerator.handlers.LabelPrinterHandler";

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        IWorkbench workbench = BarcodeGenPlugin.getDefault().getWorkbench();
        try {
            if (workbench.getActiveWorkbenchWindow().getActivePage()
                .closeAllEditors(true)) {
                workbench.showPerspective(LabelPrinterPerspective.ID,
                    workbench.getActiveWorkbenchWindow());

                PlatformUI
                    .getWorkbench()
                    .getActiveWorkbenchWindow()
                    .getActivePage()
                    .openEditor(
                        new BgcFormInput(SpecimanLabelEntryForm.ID,
                            SpecimanLabelEntryForm.ID), SpecimanLabelEntryForm.ID, true);

            }
        } catch (WorkbenchException e) {
            throw new ExecutionException(
                "Could not open label printer view : ", e);
        }
        return null;
    }

}
