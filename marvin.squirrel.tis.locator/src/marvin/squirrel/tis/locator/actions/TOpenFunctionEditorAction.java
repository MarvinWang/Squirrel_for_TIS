/**
 * 
 */
package marvin.squirrel.tis.locator.actions;

import marvin.squirrel.tis.locator.data.handler.TModelProxyFactory;
import marvin.squirrel.tis.locator.data.model.TFunctionModel;
import marvin.squirrel.tis.locator.editors.TCodeLocatorEditor;
import marvin.squirrel.tis.locator.editors.TCodeLocatorEditorInput;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * @author Marvin Wang
 * @email mwang@talend.com
 * @date Dec 27, 2012
 */
public class TOpenFunctionEditorAction extends Action {

	/**
	 * 
	 */
	public TOpenFunctionEditorAction() {
	}

	/**
	 * @param text
	 */
	public TOpenFunctionEditorAction(String text) {
		super(text);
	}

	/**
	 * @param text
	 * @param image
	 */
	public TOpenFunctionEditorAction(String text, ImageDescriptor image) {
		super(text, image);
	}

	/**
	 * @param text
	 * @param style
	 */
	public TOpenFunctionEditorAction(String text, int style) {
		super(text, style);
	}

	public void run() {
		TFunctionModel functionModel = TModelProxyFactory.getInstance().newFunctionModel();
		TModelProxyFactory.getInstance().getGlobalModel().addFunctionModel(functionModel);
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
