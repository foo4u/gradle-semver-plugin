package io.fugu.gradle.plugns.version

import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging

/**
 * Provides a semantic version plugin for Gradle.
 * 
 * @author Scott Rossillo
 *
 */
class SemanticVersionPlugin implements Plugin<Project> {

	private final Logger logger = Logging.getLogger("semver")

	void apply(Project project) {

		project.setVersion(new SemanticVersion())

		project.task('release') << { logger.lifecycle("Releasing $project.name version $project.version") }

		project.task('printVersion') << { println "$project.version" }

		project.gradle.taskGraph.whenReady { taskGraph ->
			project.version.release = taskGraph.hasTask(':release')
		}
	}
}
