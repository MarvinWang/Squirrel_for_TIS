/**
 * 
 */
package marvin.squirrel.tis.locator.editors;

import marvin.squirrel.tis.locator.data.model.TFunctionModel;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

/**
 * @author Marvin Wang
 * @email mwang@talend.com
 * @date Dec 28, 2012
 */
public class FunctionLocatorEditorInput implements IEditorInput {
	
	private TFunctionModel functionModel;

	/**
	 * 
	 */
	public FunctionLocatorEditorInput(TFunctionModel functionModel) {
		this.functionModel = functionModel;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 */
	@Override
	public Object getAdapter(Class adapter) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IEditorInput#exists()
	 */
	@Override
	public boolean exists() {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IEditorInput#getImageDescriptor()
	 */
	@Override
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IEditorInput#getName()
	 */
	@Override
	public String getName() {
		return functionModel.getName();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IEditorInput#getPersistable()
	 */
	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IEditorInput#getToolTipText()
	 */
	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return functionModel.getName();
	}

	/**
	 * @return the functionModel
	 */
	public TFunctionModel getFunctionModel() {
		return functionModel;
	}

	/**
	 * @param functionModel the functionModel to set
	 */
	public void setFunctionModel(TFunctionModel functionModel) {
		this.functionModel = functionModel;
	}

	
	
}
