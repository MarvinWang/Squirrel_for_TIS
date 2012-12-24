/**
 * 
 */
package marvin.squirrel.tis.locator.views;

import marvin.squirrel.tis.locator.constants.TCodeLocatorConstants;
import marvin.squirrel.tis.locator.data.handler.CodeDataLoader;
import marvin.squirrel.tis.locator.views.controller.TCodeLocatorController;

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
	
	private List classNameList;
	
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
		mainLeftComp.setLayout(new GridLayout(1,false));
		
		createMainLeftContents(mainRightComp);
		
		regListeners();
		initController();
		
		CodeDataLoader.getInstance();
	}
	
	private void initController(){
		
	}
	
	/**
	 * 
	 * @param parent
	 */
	protected void createSearchContents(Composite parent){
		keyWordLbl = new Label(parent,SWT.NONE);
		keyWordLbl.setText(TCodeLocatorConstants.VIEW_LOCATOR_LBL_KEYWORK);
		GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.CENTER).applyTo(keyWordLbl);
		
		keyWordTxt = new Text(parent, SWT.BORDER);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).grab(true, false).applyTo(keyWordTxt);
		
		locateBtn = new Button(parent, SWT.NONE);
		locateBtn.setEnabled(false);
		locateBtn.setText(TCodeLocatorConstants.VIEW_LOCATOR_BTN_LOCATE);
		GridDataFactory.fillDefaults().applyTo(locateBtn);
	}
	
	/**
	 * 
	 * @param parent
	 */
	protected void createMainLeftContents(Composite parent){
		Label classNameLbl = new Label(parent, SWT.NONE);
		classNameLbl.setText(TCodeLocatorConstants.VIEW_LOCATOR_LBL_CLASSNAME);
		
		classNameList = new List(parent, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(classNameList);
//		classNameList.setItems(new String[]{"dd", "ddf","dddddddddddddddddddddddddd", "ggg","yyy", "eee","jjj","333"});
		
	}
	
	
	protected void createMainRightContents(Composite parent){
		
	}

	
	protected void regListeners(){
		regLocateBtnListener();
		regKeyWordTxtListener();
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
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {

	}

	public List getClassNameList() {
		return classNameList;
	}

}
