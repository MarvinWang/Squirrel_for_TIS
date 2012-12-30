/**
 * 
 */
package marvin.squirrel.tis.locator.actions;

import marvin.squirrel.tis.locator.editors.FunctionLocatorEditor;
import marvin.squirrel.tis.locator.editors.FunctionLocatorEditorInput;

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
public class OpenFunctionEditorAction extends Action {

	/**
	 * 
	 */
	public OpenFunctionEditorAction() {
	}

	/**
	 * @param text
	 */
	public OpenFunctionEditorAction(String text) {
		super(text);
	}

	/**
	 * @param text
	 * @param image
	 */
	public OpenFunctionEditorAction(String text, ImageDescriptor image) {
		super(text, image);
	}

	/**
	 * @param text
	 * @param style
	 */
	public OpenFunctionEditorAction(String text, int style) {
		super(text, style);
	}

	public void run() {
		IEditorInput editorInput = new FunctionLocatorEditorInput();
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
			.getActivePage();
		IEditorPart editor = page.findEditor(editorInput);
		try {
			page.openEditor(editorInput, FunctionLocatorEditor.ID);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
	
}