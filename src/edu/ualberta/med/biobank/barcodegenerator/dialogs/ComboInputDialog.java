package edu.ualberta.med.biobank.barcodegenerator.dialogs;

import java.util.List;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import edu.ualberta.med.biobank.gui.common.dialogs.BgcBaseDialog;
import edu.ualberta.med.biobank.gui.common.widgets.utils.ComboSelectionUpdate;

public class ComboInputDialog extends BgcBaseDialog {

    public class InvalidOptionsException extends Exception {

        private static final long serialVersionUID = 9119312688155129785L;

        public InvalidOptionsException() {
            super();
        }
    }

    private class ValuePojo {
        public String name;

        public ValuePojo() {

        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    };

    private ValuePojo value;

    private String title;

    private String message;

    private List<String> options;

    private String defaultOption;

    public ComboInputDialog(String title, String message, List<String> options,
        String defaultOption, Shell parent) throws InvalidOptionsException {
        super(parent);

        if ((options == null) || (options.size() == 0)) {
            throw new InvalidOptionsException();
        }

        this.title = title;
        this.message = message;
        this.options = options;
        this.defaultOption = defaultOption;
        value = new ValuePojo();
    }

    @Override
    protected String getDialogShellTitle() {
        return title;
    }

    @Override
    protected String getTitleAreaMessage() {
        return message;
    }

    @Override
    protected String getTitleAreaTitle() {
        return title;
    }

    @Override
    protected void createDialogAreaInternal(Composite parent) throws Exception {

        if (options.isEmpty())
            throw new Exception(Messages.ComboInputDialog_nooptions_msg);

        if ((defaultOption == null) || !options.contains(defaultOption))
            defaultOption = options.get(0);

        Composite content = new Composite(parent, SWT.NONE);
        content.setLayout(new GridLayout(2, false));
        content.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        value.setName(defaultOption);

        createComboViewer(content, Messages.ComboInputDialog_config_label, options,
            defaultOption, Messages.ComboInputDialog_config_validator_msg,
            new ComboSelectionUpdate() {
                @Override
                public void doSelection(Object selectedObject) {
                    value.setName((String) selectedObject);
                }
            }, new LabelProvider());
        setOkButtonEnabled(true);
    }

    public String getValue() {
        return value.getName();
    }

    public void setValue(String value) {
        this.value.setName(value);
    }
}