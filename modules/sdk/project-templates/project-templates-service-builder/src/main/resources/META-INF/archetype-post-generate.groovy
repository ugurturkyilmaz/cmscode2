/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

Path projectPath = Paths.get(request.outputDirectory, request.artifactId)

Path apiPath = projectPath.resolve(request.artifactId + "-api")
Path buildGradlePath = projectPath.resolve("build.gradle")
Path servicePath = projectPath.resolve(request.artifactId + "-service")

Path apiBuildGradlePath = apiPath.resolve("build.gradle")
Path serviceBuildGradlePath = servicePath.resolve("build.gradle")
Path serviceBuildSettingsPath = projectPath.resolve("settings.gradle")

Files.deleteIfExists apiBuildGradlePath
Files.deleteIfExists buildGradlePath
Files.deleteIfExists serviceBuildGradlePath
Files.deleteIfExists serviceBuildSettingsPath

Properties properties = request.properties

String addOns = properties.get("addOns")

String liferayVersion = properties.get("liferayVersion")

if (addOns.equals("true") && (liferayVersion.startsWith("7.0") || (liferayVersion.startsWith("7.1")))) {
	throw new IllegalArgumentException(
		"Add Ons are not supported in 7.0 or 7.1")
}

Path uadPath = projectPath.resolve(request.artifactId + "-uad")

Path uadBuildGradlePath = uadPath.resolve("build.gradle")
Path uadBndPath = uadPath.resolve("bnd.bnd")
Path uadPomPath = uadPath.resolve("pom.xml")

if (addOns.equals("false")) {
	Files.deleteIfExists uadBuildGradlePath
	Files.deleteIfExists uadBndPath
	Files.deleteIfExists uadPomPath
	Files.deleteIfExists uadPath
}
else {
	Files.deleteIfExists uadBuildGradlePath
}

Path serviceXMLPath = servicePath.resolve("service.xml")

File serviceXMLFile = serviceXMLPath.toFile()

char minorVersion = liferayVersion.charAt(2)

def newserviceXMLContent = serviceXMLFile.text.replace("7.0", "7." + minorVersion).replace("7_0", "7_" + minorVersion)

serviceXMLFile.text = newserviceXMLContent