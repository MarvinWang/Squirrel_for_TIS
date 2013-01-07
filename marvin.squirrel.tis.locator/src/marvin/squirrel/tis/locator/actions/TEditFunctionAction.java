/**
 * 
 */
package marvin.squirrel.tis.locator.actions;

import marvin.squirrel.tis.locator.data.handler.TModelProxyFactory;
import marvin.squirrel.tis.locator.data.model.TFunctionModel;
import marvin.squirrel.tis.locator.editors.TCodeLocatorEditor;
import marvin.squirrel.tis.locator.editors.TCodeLocatorEditorInput;
import marvin.squirrel.tis.locator.views.TCodeLocatorView;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * @author Marvin Wang
 * @email mwang@talend.com
 * @date Jan 7, 2013
 */
public class TEditFunctionAction extends Action {
	
	private TFunctionModel functionModel;
	
	private TCodeLocatorView view;

	/**
	 * 
	 */
	public TEditFunctionAction() {
	}

	/**
	 * @param text
	 */
	public TEditFunctionAction(String text) {
		super(text);
	}

	/**
	 * @param text
	 * @param image
	 */
	public TEditFunctionAction(String text, ImageDescriptor image) {
		super(text, image);
	}

	/**
	 * @param text
	 * @param style
	 */
	public TEditFunctionAction(String text, int style) {
		super(text, style);
	}
	
	public void run(){
		TFunctionModel functionModel = fetchFunctionModel();
		if(functionModel != null){
			IEditorInput editorInput = new TCodeLocatorEditorInput(functionModel);
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getActivePage();
			TCodeLocatorEditor editor = null;
			try {
				editor =  (TCodeLocatorEditor)page.openEditor(editorInput, TCodeLocatorEditor.ID);
				functionModel.addPropertyChangeListener(editor);
			} catch (PartInitException e) {
				e.printStackTrace();
			}
		}
	}

	protected TFunctionModel fetchFunctionModel(){
		String[] functionNames = view.getFunctionNameList().getSelection();
		if(functionNames != null && functionNames.length > 0){
			return TModelProxyFactory.getInstance().getFunctionModelByName(functionNames[0]);
		}
		return null;
	}
	
	public TCodeLocatorView getView() {
		return view;
	}

	public void setView(TCodeLocatorView view) {
		this.view = view;
	}

}
