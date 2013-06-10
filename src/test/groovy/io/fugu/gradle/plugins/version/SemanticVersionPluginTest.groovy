package io.fugu.gradle.plugins.version

import static org.junit.Assert.*
import io.fugu.gradle.plugns.version.SemanticVersion;

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Before
import org.junit.Test

/**
 * Provides semantic version plugin tests.
 *
 * @author Scott Rossillo
 */
final class SemanticVersionPluginTest {

	private Project project;

	@Before
	public void setUp() {
		project = ProjectBuilder.builder().build()
	}

	@Test
	public void testVersionPluginAssignment() {
		project.apply  plugin: 'semver'
		assertTrue(project.version instanceof SemanticVersion)
	}

	@Test
	public void testTasksDefined() {
		assertNull(project.tasks.findByName('release'))
		assertNull(project.tasks.findByName('printVersion'))
		project.apply  plugin: 'semver'
		assertNotNull(project.tasks.findByName('release'))
		assertNotNull(project.tasks.findByName('printVersion'))
	}
}
