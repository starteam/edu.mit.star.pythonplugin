package edu.mit.star.pythonplugin.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SampleHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public SampleHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		javax.swing.JFrame f = new javax.swing.JFrame( "test" ) ;
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		MessageDialog.openInformation(
				window.getShell(),
				"Pythonplugin",
				"Display frame");
		Object ret ;
		try
		{
		PySystemState.initialize();
		PythonInterpreter interp = new PythonInterpreter();
		f.setDefaultCloseOperation( javax.swing.JFrame.DISPOSE_ON_CLOSE) ;
		interp.set( "test" , f ) ;
		ret = interp.eval( "test.show()" ) ;
		}
		catch( RuntimeException ex )
		{
			ret = ex ;
		}
		catch( Exception ex )
		{
			ret = ex ;
		}
		MessageDialog.openInformation(
				window.getShell(),
				"Pythonplugin",
				"Dispose frame" );
		f.dispose();
		return null;
	}
}
