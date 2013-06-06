package io.fugu.gradle.plugns.version

/**
 * Provides a semantic version bean.
 * 
 * @author Scott Rossillo
 *
 */
class SemanticVersion {
	int major = 0, minor = 0, revision = 0
	boolean release = false
	String releasePostfix = ''
	String snapshotPostfix = "-SNAPSHOT"

	void configure(Map<String,?> map) {
		this.major = map.major ? map.major : 0;
		this.minor = map.minor ? map.minor : 0;
	}

	String toString() {
		"$major.$minor.$revision${release ? releasePostfix : snapshotPostfix }"
	}
}
