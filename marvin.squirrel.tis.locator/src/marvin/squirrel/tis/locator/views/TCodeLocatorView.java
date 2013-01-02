/**
 * 
 */
package marvin.squirrel.tis.locator.views;

import marvin.squirrel.tis.locator.actions.TOpenFunctionEditorAction;
import marvin.squirrel.tis.locator.data.handler.TDataSourceLoader;
import marvin.squirrel.tis.locator.data.handler.TModelProxyFactory;
import marvin.squirrel.tis.locator.exception.TDataSourceException;
import marvin.squirrel.tis.locator.i18n.Messages;
import marvin.squirrel.tis.locator.views.controller.TCodeLocatorController;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

/**
 * @author Marvin Wang
 * @date 2012-12-22
 */
public class TCodeLocatorView extends ViewPart {

	private Label keyWordLbl;
	
	private Text keyWordTxt;
	
	private Button locateBtn;
	
	private Composite searchComposite;
	
	private SashForm mainSashForm;
	
	private TCodeLocatorController controller;
	
	private List functionNameList;
	
	private Label pluginLbl;//Plugin Label
	
	private Label classNameLbl;//Class Name Label
	
	private Label methodLbl;//Method label
	
	private Label descLbl;//Function description label
	
	private Label repositoryLbl;//Repository in SVN
	
	private Text pluginTxt;//Plugin Text
	
	private Text classNameTxt;//Class Name Text
	
	private Text methodTxt;//Method Text
	
	private Text descText;//Description Text
	
	private Text repositoryTxt;//Repository Text
	
	private Action openFunctionEditorAction;
	
	
	/**
	 * 
	 */
	public TCodeLocatorView() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(2,false));
		
//		The above composite.
		searchComposite = new Composite(parent, SWT.None);
		GridDataFactory.fillDefaults().grab(true, false).span(2, 1).applyTo(searchComposite);
		searchComposite.setLayout(new GridLayout(3,false));
		createSearchContents(searchComposite);
		
		mainSashForm = new SashForm(parent, SWT.HORIZONTAL);
		GridDataFactory.fillDefaults().span(2, 1).grab(true, true).applyTo(mainSashForm);
		mainSashForm.setLayout(new FillLayout()); 
		
//		The composite at left of main composite.
		Composite mainLeftComp = new Composite(mainSashForm, SWT.NONE);
		mainLeftComp.setLayout(new GridLayout(1,false));
		
		createMainLeftContents(mainLeftComp);
		
//		The composite at right of main composite.
		Composite mainRightComp = new Composite(mainSashForm, SWT.NONE);
		mainRightComp.setLayout(new GridLayout(2,false));
		
		createMainRightContents(mainRightComp);
		
		regListeners();
		loadData();
		initController();
		
		makeActions();
		contributeToActionBars();
	}
	
	private void initController(){
		controller = new TCodeLocatorController(this);
	}
	
	private void loadData(){
		try {
			TModelProxyFactory.getInstance().initModel();
		} catch (TDataSourceException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param parent
	 */
	protected void createSearchContents(Composite parent){
		keyWordLbl = new Label(parent,SWT.NONE);
		keyWordLbl.setText(Messages.getString("TCodeLocatorView.lbl.keywords"));
		GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.CENTER).applyTo(keyWordLbl);
		
		keyWordTxt = new Text(parent, SWT.BORDER);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).grab(true, false).applyTo(keyWordTxt);
		
		locateBtn = new Button(parent, SWT.NONE);
		locateBtn.setEnabled(false);
		locateBtn.setText(Messages.getString("TCodeLocatorView.btn.locate"));
		GridDataFactory.fillDefaults().applyTo(locateBtn);
	}
	
	/**
	 * 
	 * @param parent
	 */
	protected void createMainLeftContents(Composite parent){
		Label functionNameLbl = new Label(parent, SWT.NONE);
		functionNameLbl.setText(Messages.getString("TCodeLocatorView.lbl.functionName"));
		
		functionNameList = new List(parent, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(functionNameList);
	}
	
	/**
	 * 
	 * @param parent
	 */
	protected void createMainRightContents(Composite parent){
//		Plugin widgets
		pluginLbl = new Label(parent, SWT.NONE);
		pluginLbl.setText(Messages.getString("TCodeLocatorView.lbl.plugin"));
		
		pluginTxt = new Text(parent, SWT.BORDER);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(pluginTxt);
		
//		Class Name widgets
		classNameLbl = new Label(parent, SWT.NONE);
		classNameLbl.setText(Messages.getString("TCodeLocatorView.lbl.className"));
		
		classNameTxt = new Text(parent, SWT.BORDER);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(classNameTxt);
		
//		Method widgets
		methodLbl = new Label(parent, SWT.NONE);
		methodLbl.setText(Messages.getString("TCodeLocatorView.lbl.methodName"));
		
		methodTxt = new Text(parent, SWT.BORDER);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(methodTxt);
		
//		Description widgets
		descLbl = new Label(parent, SWT.NONE);
		descLbl.setText(Messages.getString("TCodeLocatorView.lbl.description"));
		GridDataFactory.fillDefaults().span(1, 4).grab(false, false).applyTo(descLbl);
		
		descText = new Text(parent, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
		GridDataFactory.fillDefaults().span(1, 4).grab(true, true).applyTo(descText);
		
//		Repository widgets
		repositoryLbl = new Label(parent, SWT.NONE);
		repositoryLbl.setText(Messages.getString("TCodeLocatorView.lbl.reporitory"));
		
		repositoryTxt = new Text(parent, SWT.BORDER);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(repositoryTxt);
	}

	/**
	 * Registers all listeners for widgets required.
	 */
	protected void regListeners(){
		regLocateBtnListener();
		regKeyWordTxtListener();
		regFunctionNameListListener();
	}
	
	/**
	 * Registers a listener for the text widget of "Key Word".
	 */
	private void regKeyWordTxtListener(){
		keyWordTxt.addModifyListener(new ModifyListener(){
			public void modifyText(ModifyEvent e){
				String keyWordStr = keyWordTxt.getText();
				if("".equals(keyWordStr)){
					locateBtn.setEnabled(false);
				}else{
					locateBtn.setEnabled(true);
				}
			}
		});
	}
	
	/**
	 * Registers a listener for the button widget of "Locate".
	 */
	private void regLocateBtnListener(){
		locateBtn.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				String keyWordStr = keyWordTxt.getText();
				controller.doLocate(keyWordStr);
			}
		});
	}
	
	private void regFunctionNameListListener(){
		functionNameList.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				String[] list = functionNameList.getSelection();
				controller.doFunctionNameSelect(list);
			}
		});
	}
	
	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalToolBar(bars.getToolBarManager());
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(openFunctionEditorAction);
//		manager.add(new Separator());
	}
	
	protected void makeActions(){
		makeAddFunctionAction();
	}
	
	/**
	 * 
	 */
	private void makeAddFunctionAction(){
		openFunctionEditorAction = new TOpenFunctionEditorAction(Messages.getString("TCodeLocatorView.action.openFunctionEditor.text")
				, PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_ADD));
		openFunctionEditorAction.setToolTipText(Messages.getString("TCodeLocatorView.action.openFunctionEditor.tooltip"));
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {

	}

	public List getFunctionNameList() {
		return functionNameList;
	}

	public Text getPluginTxt() {
		return pluginTxt;
	}

	public void setPluginTxt(Text pluginTxt) {
		this.pluginTxt = pluginTxt;
	}

	public Text getClassNameTxt() {
		return classNameTxt;
	}

	public void setClassNameTxt(Text classNameTxt) {
		this.classNameTxt = classNameTxt;
	}

	public Text getMethodTxt() {
		return methodTxt;
	}

	public void setMethodTxt(Text methodTxt) {
		this.methodTxt = methodTxt;
	}

	public Text getDescText() {
		return descText;
	}

	public void setDescText(Text descText) {
		this.descText = descText;
	}

	public Text getRepositoryTxt() {
		return repositoryTxt;
	}

	public void setRepositoryTxt(Text repositoryTxt) {
		this.repositoryTxt = repositoryTxt;
	}

}
