/**
 * 
 */
package marvin.squirrel.tis.locator.editors;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import marvin.squirrel.tis.locator.constants.TPropertyForModel;
import marvin.squirrel.tis.locator.editors.controller.TCodeEditorController;
import marvin.squirrel.tis.locator.exception.TDataSourceException;
import marvin.squirrel.tis.locator.i18n.Messages;
import marvin.squirrel.tis.locator.utils.TFunctionTypeUtils;
import marvin.squirrel.tis.locator.utils.TProductUtils;
import marvin.squirrel.tis.locator.utils.TSVNUtils;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
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
public class TCodeLocatorEditor extends EditorPart implements PropertyChangeListener{

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
	
	private Label svnVersionLbl;
	
	private Combo svnVersionCombo;
	
	private TCodeEditorController controller;
	
	private boolean isDirty = false;
	
	/**
	 * 
	 */
	public TCodeLocatorEditor() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#doSave(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void doSave(IProgressMonitor monitor) {
		String functionName = functionNameTxt.getText();
		boolean isExist = controller.checkIfFunctionExist();
		if(isExist){
			MessageDialog.openWarning(this.getEditorSite().getShell(), "Warning", "The function named \'" + functionName + " \' is existing!");
		}else{
			try {
				controller.doSave(monitor);
				isDirty = false;
				this.firePropertyChange(PROP_DIRTY);
			} catch (TDataSourceException e) {
				e.printStackTrace();
			}
		}
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
		if(!(input instanceof TCodeLocatorEditorInput))
			throw new PartInitException("");
		this.setInput(input);
		this.setSite(site);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#isDirty()
	 */
	@Override
	public boolean isDirty() {
		return isDirty;
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
		
		regListeners();
		controller = new TCodeEditorController(this);
		controller.init();
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
		typeCombo.setItems(TFunctionTypeUtils.fetchAllDisplayNames());
		
		productLbl = new Label(basicGroup, SWT.NONE);
		productLbl.setText(Messages.getString("FunctionLocatorEditor.lbl.product"));
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).span(1, 1).grab(false, false).applyTo(productLbl);
		
		productCombo = new Combo(basicGroup, SWT.NONE);
		GridDataFactory.fillDefaults().span(1, 1).grab(true, false).applyTo(productCombo);
		productCombo.setItems(TProductUtils.fetchAllProducts());
		
//		Description widgets
		descriptionLbl = new Label(basicGroup, SWT.NONE);
		descriptionLbl.setText(Messages.getString("FunctionLocatorEditor.lbl.desc"));
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.BEGINNING).span(1, 3).grab(false, false).applyTo(descriptionLbl);
		
		descriptionTxt = new Text(basicGroup, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | SWT.WRAP);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).span(3, 3).grab(true, true).applyTo(descriptionTxt);
		
//		SVN Group
		svnGroup = new Group(parent, SWT.NONE);
		svnGroup.setText(Messages.getString("FunctionLocatorEditor.grp.svn"));
		GridDataFactory.fillDefaults().span(1, 1).grab(true, true).applyTo(svnGroup);
		
		svnGroup.setLayout(new GridLayout(4,false));
		
//		SVN Repository
		repositoryLbl = new Label(svnGroup, SWT.NONE);
		repositoryLbl.setText(Messages.getString("FunctionLocatorEditor.lbl.repository"));
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).span(1, 1).grab(false, false).applyTo(repositoryLbl);
		
		repositoryCombo = new Combo(svnGroup, SWT.NONE);
		GridDataFactory.fillDefaults().span(1, 1).grab(true, false).applyTo(repositoryCombo);
		repositoryCombo.setItems(TSVNUtils.fetchAllRepository());
		
//		SVN Version
		svnVersionLbl = new Label(svnGroup, SWT.NONE); 
		svnVersionLbl.setText(Messages.getString("FunctionLocatorEditor.lbl.version"));
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).span(1, 1).grab(false, false).applyTo(svnVersionLbl);
		
		svnVersionCombo = new Combo(svnGroup, SWT.NONE);
		GridDataFactory.fillDefaults().span(1, 1).grab(true, false).applyTo(svnVersionCombo);
		svnVersionCombo.setItems(TSVNUtils.fetchAllSVNVersions());
	}
	
	protected void regListeners(){
		regFunctionNameTxtListener();
		regClassNameTxtListener();
		regMethodNameTxtListener();
		regPluginNameTxtListener();
		regTypeComboListener();
		regProductComboListener();
		regRepositoryComboListener();
		regSvnVersionComboListener();
	}
	
	protected void regFunctionNameTxtListener(){
		functionNameTxt.addModifyListener(new ModifyListener(){

			@Override
			public void modifyText(ModifyEvent e) {
				controller.doFunctionNameChange();
			}});
	}
	
	protected void regClassNameTxtListener(){
		classNameTxt.addModifyListener(new ModifyListener(){

			@Override
			public void modifyText(ModifyEvent e) {
				controller.doClassNameChange();
			}});
	}
	
	
	protected void regMethodNameTxtListener(){
		methodTxt.addModifyListener(new ModifyListener(){

			@Override
			public void modifyText(ModifyEvent e) {
				controller.doMethodNameChange();
			}});
	}
	
	protected void regPluginNameTxtListener(){
		pluginNameTxt.addModifyListener(new ModifyListener(){

			@Override
			public void modifyText(ModifyEvent e) {
				controller.doPluginNameChange();
			}});
	}
	
	protected void regFunctionDescTxtListener(){
		descriptionTxt.addModifyListener(new ModifyListener(){

			@Override
			public void modifyText(ModifyEvent e) {
				controller.doDescChange();
			}});
	}
	
	protected void regTypeComboListener(){
		typeCombo.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				controller.doTypeChange();
			}
		});
	}
	
	protected void regProductComboListener(){
		productCombo.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				controller.doProductChange();
			}
		});
	}
	
	protected void regRepositoryComboListener(){
		repositoryCombo.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				controller.doRepositoryChange();
			}
		});
	}
	
	protected void regSvnVersionComboListener(){
		svnVersionCombo.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				controller.doSvnVersionChange();
			}
		});
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {

	}

	/**
	 * @return the functionNameTxt
	 */
	public Text getFunctionNameTxt() {
		return functionNameTxt;
	}

	/**
	 * @param functionNameTxt the functionNameTxt to set
	 */
	public void setFunctionNameTxt(Text functionNameTxt) {
		this.functionNameTxt = functionNameTxt;
	}

	/**
	 * @return the classNameTxt
	 */
	public Text getClassNameTxt() {
		return classNameTxt;
	}

	/**
	 * @param classNameTxt the classNameTxt to set
	 */
	public void setClassNameTxt(Text classNameTxt) {
		this.classNameTxt = classNameTxt;
	}

	/**
	 * @return the methodTxt
	 */
	public Text getMethodTxt() {
		return methodTxt;
	}

	/**
	 * @param methodTxt the methodTxt to set
	 */
	public void setMethodTxt(Text methodTxt) {
		this.methodTxt = methodTxt;
	}

	/**
	 * @return the pluginNameTxt
	 */
	public Text getPluginNameTxt() {
		return pluginNameTxt;
	}

	/**
	 * @param pluginNameTxt the pluginNameTxt to set
	 */
	public void setPluginNameTxt(Text pluginNameTxt) {
		this.pluginNameTxt = pluginNameTxt;
	}

	/**
	 * @return the typeCombo
	 */
	public Combo getTypeCombo() {
		return typeCombo;
	}

	/**
	 * @param typeCombo the typeCombo to set
	 */
	public void setTypeCombo(Combo typeCombo) {
		this.typeCombo = typeCombo;
	}

	/**
	 * @return the productCombo
	 */
	public Combo getProductCombo() {
		return productCombo;
	}

	/**
	 * @param productCombo the productCombo to set
	 */
	public void setProductCombo(Combo productCombo) {
		this.productCombo = productCombo;
	}

	/**
	 * @return the descriptionTxt
	 */
	public Text getDescriptionTxt() {
		return descriptionTxt;
	}

	/**
	 * @param descriptionTxt the descriptionTxt to set
	 */
	public void setDescriptionTxt(Text descriptionTxt) {
		this.descriptionTxt = descriptionTxt;
	}

	/**
	 * @return the repositoryCombo
	 */
	public Combo getRepositoryCombo() {
		return repositoryCombo;
	}

	/**
	 * @param repositoryCombo the repositoryCombo to set
	 */
	public void setRepositoryCombo(Combo repositoryCombo) {
		this.repositoryCombo = repositoryCombo;
	}

	/**
	 * @return the svnVersionCombo
	 */
	public Combo getSvnVersionCombo() {
		return svnVersionCombo;
	}

	/**
	 * @param svnVersionCombo the svnVersionCombo to set
	 */
	public void setSvnVersionCombo(Combo svnVersionCombo) {
		this.svnVersionCombo = svnVersionCombo;
	}

	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		isDirty = true;
		this.firePropertyChange(PROP_DIRTY);
		if(TPropertyForModel.FUNCTION_NAME.equals(evt.getPropertyName())){
			String functionName = (String)evt.getNewValue();
			this.setTitleToolTip(functionName);
		}else if(TPropertyForModel.CLASS_NAME.equals(evt.getPropertyName())){
			String className = (String)evt.getNewValue();
		}else if(TPropertyForModel.CLASS_METHOD.equals(evt.getPropertyName())){
			
		}else if(TPropertyForModel.CLASS_PACKAGE.equals(evt.getPropertyName())){
			
		}else if(TPropertyForModel.FUNCTION_TYPE.equals(evt.getPropertyName())){
			
		}else if(TPropertyForModel.FUNCTION_PRODUCT.equals(evt.getPropertyName())){
			
		}
	}

}
