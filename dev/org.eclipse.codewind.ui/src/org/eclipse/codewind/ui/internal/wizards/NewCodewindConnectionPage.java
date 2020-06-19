/*******************************************************************************
 * Copyright (c) 2018, 2020 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.codewind.ui.internal.wizards;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.codewind.ui.internal.messages.Messages;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * This simple page allows the user to add new Codewind connections, by entering a hostname and port and
 * validating that Codewind is indeed reachable at the given address.
 */
public class NewCodewindConnectionPage extends WizardPage implements CompositeContainer {

	private CodewindConnectionComposite composite;

	protected NewCodewindConnectionPage() {
		super(Messages.NewConnectionPage_ShellTitle);
		setTitle(Messages.NewConnectionPage_WizardTitle);
		setDescription(Messages.NewConnectionPage_WizardDescription);
	}

	@Override
	public void createControl(Composite parent) {
		Composite outer = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		layout.horizontalSpacing = 5;
		layout.verticalSpacing = 7;
		outer.setLayout(layout);
		outer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		composite = new CodewindConnectionComposite(outer, this);
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.widthHint = 250;
		composite.setLayoutData(data);
		setControl(outer);
	}
	
	@Override
	public boolean canFlipToNextPage() {
		return canFinish();
	}
	
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible) {
			composite.setFocus();
		}
	}

	public boolean canFinish() {
		return composite.canFinish();
	}
	
	public String getConnectionName() {
		return composite.getConnectionName();
	}
	
	public IStatus createConnection(IProgressMonitor monitor) {
		return composite.createConnection(monitor);
	}

	@Override
	public void validate() {
		composite.validate();
		getWizard().getContainer().updateButtons();
	}
	
	@Override
	public void run(IRunnableWithProgress runnable) throws InvocationTargetException, InterruptedException {
		getWizard().getContainer().run(true, true, runnable);
	}
}
