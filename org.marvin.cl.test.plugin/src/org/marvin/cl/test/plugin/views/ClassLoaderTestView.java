package org.marvin.cl.test.plugin.views;


import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;


/**
 * This sample class demonstrates how to plug-in a new
 * workbench view. The view shows data obtained from the
 * model. The sample creates a dummy model on the fly,
 * but a real implementation would connect to the model
 * available either in this or another plug-in (e.g. the workspace).
 * The view is connected to the model using a content provider.
 * <p>
 * The view uses a label provider to define how model
 * objects should be presented in the view. Each
 * view can present the same model objects using
 * different labels and icons, if needed. Alternatively,
 * a single label provider can be shared between views
 * in order to ensure that objects of the same type are
 * presented in the same way everywhere.
 * <p>
 */

public class ClassLoaderTestView extends ViewPart {

	
	private Button btn;
	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.marvin.cl.test.plugin.views.ClassLoaderTestView";

	/**
	 * The constructor.
	 */
	public ClassLoaderTestView() {
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(2, false));
		
		Label label = new Label(parent, SWT.NONE);
		label.setText("Version: ");
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).span(1, 1).grab(false, false).applyTo(label);
		
		Combo versionCombo = new Combo(parent, SWT.NONE);
		versionCombo.setItems(new String[]{"V1.0", "V2.3"});
		versionCombo.select(0);
		GridDataFactory.fillDefaults().span(1, 1).grab(true, false).applyTo(versionCombo);
		
		btn = new Button(parent, SWT.PUSH);
		btn.setText("Print");
		GridDataFactory.fillDefaults().span(2, 1).grab(true, false).applyTo(versionCombo);
		
		Text outputTxt = new Text(parent, SWT.BORDER);
		GridDataFactory.fillDefaults().span(2, 1).grab(true, true).applyTo(outputTxt);
	}


	protected void regListener(){
		btn.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				
			}
		});
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
	}
}