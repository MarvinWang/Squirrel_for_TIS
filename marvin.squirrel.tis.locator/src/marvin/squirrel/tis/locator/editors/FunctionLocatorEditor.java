/**
 * 
 */
package marvin.squirrel.tis.locator.editors;

import marvin.squirrel.tis.locator.i18n.Messages;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

/**
 * @author Marvin Wang
 * @email mwang@talend.com
 * @date Dec 27, 2012
 */
public class FunctionLocatorEditor extends EditorPart {

	public static final String ID = "marvin.squirrel.tis.locator.editor";
	
	private Group basicGroup;
	
	private Group svnGroup;
	
	private Label functionNameLbl;
	
	private Text functionNameTxt;
	
	private Label classNameLbl;
	
	private Text classNameTxt;
	
	private Label methodLbl;
	
	private Text methodTxt;
	
	private Label pluginNameLbl;
	
	private Text pluginNameTxt;
	
	private Label typeLbl;
	
	private Combo typeCombo;
	
	private Label productLbl;
	
	private Combo productCombo;
	
	private Label descriptionLbl;
	
	private Text descriptionTxt;
	
	private Label repositoryLbl;
	
	private Combo repositoryCombo;
	
	/**
	 * 
	 */
	public FunctionLocatorEditor() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#doSave(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void doSave(IProgressMonitor monitor) {

	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#doSaveAs()
	 */
	@Override
	public void doSaveAs() {

	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		if(!(input instanceof FunctionLocatorEditorInput))
			throw new PartInitException("");
		this.setInput(input);
		this.setSite(site);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#isDirty()
	 */
	@Override
	public boolean isDirty() {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#isSaveAsAllowed()
	 */
	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(parent, SWT.BORDER | 
				SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		Composite basicComposite = new Composite(scrolledComposite, SWT.NONE);
		scrolledComposite.setContent(basicComposite);
		basicComposite.setLayout(new GridLayout());
		
//		Create basic info
		createBasicContents(basicComposite);
		
		scrolledComposite.setMinSize(basicComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}
	
	protected void createBasicContents(Composite parent){
		basicGroup = new Group(parent, SWT.NONE);
		basicGroup.setText(Messages.getString("FunctionLocatorEditor.grp.basic"));
		GridDataFactory.fillDefaults().span(1, 1).grab(true, true).applyTo(basicGroup);
		
		basicGroup.setLayout(new GridLayout(4,false));
		
//		Funciton Name widgets
		functionNameLbl = new Label(basicGroup, SWT.NONE);
		functionNameLbl.setText(Messages.getString("FunctionLocatorEditor.lbl.functionName"));
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).span(1, 1).grab(false, false).applyTo(functionNameLbl);
		
		functionNameTxt = new Text(basicGroup, SWT.BORDER);
		GridDataFactory.fillDefaults().span(3, 1).grab(true, false).applyTo(functionNameTxt);
		
//		Class Name widgets
		classNameLbl = new Label(basicGroup, SWT.NONE);
		classNameLbl.setText(Messages.getString("FunctionLocatorEditor.lbl.className"));
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).span(1, 1).grab(false, false).applyTo(classNameLbl);
		
		classNameTxt = new Text(basicGroup, SWT.BORDER);
		GridDataFactory.fillDefaults().span(3, 1).grab(true, false).applyTo(classNameTxt);
		
//		Method Name widgets
		methodLbl = new Label(basicGroup, SWT.NONE);
		methodLbl.setText(Messages.getString("FunctionLocatorEditor.lbl.methodName"));
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).span(1, 1).grab(false, false).applyTo(methodLbl);
		
		methodTxt = new Text(basicGroup, SWT.BORDER);
		GridDataFactory.fillDefaults().span(3, 1).grab(true, false).applyTo(methodTxt);
		
//		Plugin Name widgets
		pluginNameLbl = new Label(basicGroup, SWT.NONE);
		pluginNameLbl.setText(Messages.getString("FunctionLocatorEditor.lbl.plugin"));
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).span(1, 1).grab(false, false).applyTo(pluginNameLbl);
		
		pluginNameTxt = new Text(basicGroup, SWT.BORDER);
		GridDataFactory.fillDefaults().span(3, 1).grab(true, false).applyTo(pluginNameTxt);
		
//		Type and Product widgets
		typeLbl = new Label(basicGroup, SWT.NONE);
		typeLbl.setText(Messages.getString("FunctionLocatorEditor.lbl.type"));
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).span(1, 1).grab(false, false).applyTo(typeLbl);
		
		typeCombo = new Combo(basicGroup, SWT.NONE);
		GridDataFactory.fillDefaults().span(1, 1).grab(true, false).applyTo(typeCombo);
		
		productLbl = new Label(basicGroup, SWT.NONE);
		productLbl.setText(Messages.getString("FunctionLocatorEditor.lbl.product"));
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).span(1, 1).grab(false, false).applyTo(productLbl);
		
		productCombo = new Combo(basicGroup, SWT.NONE);
		GridDataFactory.fillDefaults().span(1, 1).grab(true, false).applyTo(productCombo);
		
//		Description widgets
		descriptionLbl = new Label(basicGroup, SWT.NONE);
		descriptionLbl.setText(Messages.getString("FunctionLocatorEditor.lbl.desc"));
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.BEGINNING).span(1, 3).grab(false, false).applyTo(descriptionLbl);
		
		descriptionTxt = new Text(basicGroup, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | SWT.WRAP);
		GridData gd = new GridData();
		gd.verticalAlignment = SWT.FILL;
		gd.verticalSpan = 3;
		gd.grabExcessHorizontalSpace = true;
		gd.grabExcessVerticalSpace = true;
		GridDataFactory.createFrom(gd).applyTo(descriptionTxt);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {

	}

}
