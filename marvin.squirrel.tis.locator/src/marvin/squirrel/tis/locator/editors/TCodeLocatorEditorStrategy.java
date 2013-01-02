/**
 * 
 */
package marvin.squirrel.tis.locator.editors;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorMatchingStrategy;
import org.eclipse.ui.IEditorReference;

/**
 * @author Marvin Wang
 * @email mwang@talend.com
 * @date Dec 28, 2012
 */
public class TCodeLocatorEditorStrategy implements IEditorMatchingStrategy {

	/**
	 * 
	 */
	public TCodeLocatorEditorStrategy() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IEditorMatchingStrategy#matches(org.eclipse.ui.IEditorReference, org.eclipse.ui.IEditorInput)
	 */
	@Override
	public boolean matches(IEditorReference editorRef, IEditorInput input) {
		return false;
	}

}
