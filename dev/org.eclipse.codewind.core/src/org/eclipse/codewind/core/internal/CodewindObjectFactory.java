/*******************************************************************************
 * Copyright (c) 2018, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.codewind.core.internal;

import java.net.URI;

import org.eclipse.codewind.core.internal.connection.CodewindConnection;
import org.eclipse.codewind.core.internal.connection.LocalConnection;
import org.eclipse.codewind.core.internal.connection.RemoteConnection;
import org.eclipse.codewind.core.internal.constants.ProjectLanguage;
import org.eclipse.codewind.core.internal.constants.ProjectType;
import org.eclipse.core.runtime.IPath;

/**
 * Factory for creating the correct Codewind objects.  This is used to keep the Eclipse
 * code and the Codewind code separate.
 * 
 * Currently only CodewindApplication has an Eclipse version.  Rather than let Eclipse
 * code leak into CodewindConnection an Eclipse version of it should be created if necessary.
 */
public class CodewindObjectFactory {
	
	public static CodewindConnection createCodewindConnection(String name, URI uri, boolean isLocal) {
		if (isLocal) {
			return new LocalConnection(name, uri);
		}
		return new RemoteConnection(name, uri);
	}
	
	public static CodewindApplication createCodewindApplication(CodewindConnection connection,
			String id, String name, ProjectType projectType, ProjectLanguage language, IPath localPath) throws Exception {
		return new CodewindEclipseApplication(connection, id, name, projectType, language, localPath);
	}

}
