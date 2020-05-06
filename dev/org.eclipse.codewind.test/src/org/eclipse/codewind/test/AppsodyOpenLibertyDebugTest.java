/*******************************************************************************
 * Copyright (c) 2020  IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.codewind.test;

import org.eclipse.codewind.core.internal.constants.AppStatus;
import org.eclipse.codewind.test.util.CodewindUtil;
import org.eclipse.core.runtime.IPath;

public class AppsodyOpenLibertyDebugTest extends BaseAppsodyDebugTest {

	@Override
	protected void doSetup() throws Exception {
		projectName = "appsodyopenlibertydebugtest";
		projectType = APPSODY_PROJECT_TYPE;
		templateId = APPSODY_OPEN_LIBERTY_ID;
		relativeURL = "/starter/hello";
		srcPath = "src/main/java/dev/appsody/starter/hello/Hello.java";
		currentText = "World";
		newText = "Earth";
		
		super.doSetup();
		
		IPath destPath = project.getLocation();
		destPath = destPath.append(srcPath);
		copyFile("appsodyOpenLiberty/Hello.java", destPath);
		refreshProject(project);
    	
		// No build states for Appsody, check that app goes into starting state
		// (may not for some project types so don't do an assert)
		CodewindUtil.waitForAppState(app, AppStatus.STARTING, 15, 1);
		assertTrue("App should be in started state", CodewindUtil.waitForAppState(app, AppStatus.STARTED, 300, 1));
	}
}
