/**
 * 
 */
package marvin.squirrel.tis.patcher.views;


import marvin.squirrel.tis.patcher.i18n.Messages;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

/**
 * @author Marvin Wang
 * @email mwang@talend.com
 * @date Jan 22, 2013
 */
public class UpdateSitePatcherView extends ViewPart {

	private Text patchNumTxt;//Like TPS-322
	
	private Text outputDirTxt;//A directory that is a root. 
	
	private Text patchFile;// 
	
	private Text patchDateTxt;
	
	private Text patchVersionTxt;
	
	private Text patchCommentTxt;
	
	private Button outputDirBtn;
	
	private Button patchFileBtn;
	
	private Button showAllJarsBtn;
	
	private SashForm mainSashForm;
	
	
	
	/**
	 * 
	 */
	public UpdateSitePatcherView() {
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
		
		createUpperPart(basicComposite);
		createPathInfoControl(basicComposite);
	}

	protected void createUpperPart(Composite parent){
		mainSashForm = new SashForm(parent, SWT.HORIZONTAL);
		GridDataFactory.fillDefaults().span(1, 1).grab(true, true).applyTo(mainSashForm);
		createBasicPartControl(mainSashForm);
	}
	
	protected void createBasicPartControl(Composite parent){
		Group basicGrp = new Group(parent, SWT.NONE);
		basicGrp.setText(Messages.getString("UpdateSitePatcherView.grp.basic"));
		GridDataFactory.fillDefaults().span(1, 1).grab(true, true).applyTo(basicGrp);
		
		basicGrp.setLayout(new GridLayout(3,false));
		
//		Plugin directory
		Label outputDirLbl = new Label(basicGrp, SWT.NONE);
		outputDirLbl.setText(Messages.getString("UpdateSitePatcherView.lbl.output.dir"));
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).span(1, 1).grab(false, false).applyTo(outputDirLbl);
		
		outputDirTxt = new Text(basicGrp, SWT.BORDER);
		GridDataFactory.fillDefaults().span(1, 1).grab(true, false).applyTo(outputDirTxt);
		
		outputDirBtn = new Button(basicGrp, SWT.BORDER);
		outputDirBtn.setText(Messages.getString("UpdateSitePatcherView.btn.output.dir"));
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).span(1, 1).grab(false, false).applyTo(outputDirBtn);
//		Patching directory
		Label patchFileLabl = new Label(basicGrp, SWT.NONE);
		patchFileLabl.setText(Messages.getString("UpdateSitePatcherView.lbl.patchFile"));
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).span(1, 1).grab(false, false).applyTo(patchFileLabl);
		
		patchFile = new Text(basicGrp, SWT.BORDER);
		GridDataFactory.fillDefaults().span(1, 1).grab(true, false).applyTo(patchFile);
		
		patchFileBtn = new Button(basicGrp, SWT.BORDER);
		patchFileBtn.setText(Messages.getString("UpdateSitePatcherView.btn.patchFile"));
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).span(1, 1).grab(false, false).applyTo(patchFileBtn);
		
//		Patch Number
		Label patchNumLbl = new Label(basicGrp, SWT.NONE);
		patchNumLbl.setText(Messages.getString("UpdateSitePatcherView.lbl.patch.number"));
		
		patchNumTxt = new Text(basicGrp, SWT.BORDER);
		GridDataFactory.fillDefaults().span(1, 1).grab(true, false).applyTo(patchNumTxt);
		
		showAllJarsBtn = new Button(basicGrp, SWT.BORDER);
		showAllJarsBtn.setText(Messages.getString("UpdateSitePatcherView.btn.showAllJars"));
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).span(1, 1).grab(false, false).applyTo(showAllJarsBtn);
	}
	
	protected void createPathInfoControl(Composite parent){
		Group patcherInfoGrp = new Group(parent, SWT.NONE);
		patcherInfoGrp.setText(Messages.getString("UpdateSitePatcherView.grp.patcher"));
		GridDataFactory.fillDefaults().span(1, 1).grab(true, true).applyTo(patcherInfoGrp);
		
		patcherInfoGrp.setLayout(new GridLayout(2,false));
	}
	
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {

	}

}
