package io.fugu.gradle.plugns.version

import org.gradle.api.Project
import org.gradle.api.Plugin

/**
 * Provides a semantic version plugin for Gradle.
 * 
 * @author Scott Rossillo
 *
 */
class SemanticVersionPlugin implements Plugin<Project> {

	void apply(Project project) {

		project.setVersion(new SemanticVersion())

		project.task('release') << { println "Releasing $project.name version $project.version" }

		project.task('printVersion') << { println "Project version is $project.version" }

		project.gradle.taskGraph.whenReady { taskGraph ->
			project.version.release = taskGraph.hasTask(':release')
		}
	}
}
