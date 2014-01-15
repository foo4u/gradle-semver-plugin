# Gradle Semantic Versioning Plugin

Provides a semantic versioning plugin for Gradle.  This plugin enforces compliance with the formal
[semantic versioning specification] [semver] by overriding Gradle's `project.version` with an
implementation of semantic versioning exposes version configuration to the project's build script.

## Why Semantic Versioning?

Software projects often depend on a number of other projects, packages or libraries.  However,
software development is an evolutionary process, projects and their dependencies change over time.
Releasing new versions of your project requires you to communicate how your new version will
affect its users.

Semantic Versioning's authors proposes a solution to this problem by setting forth a simple set of
rules that dictate how version numbers are assigned and incremented.

From the Semantic Versioning guide:
> This is not a new or revolutionary idea. In fact, you probably do something close to this already.
> The problem is that "close" isn't good enough. Without compliance to some sort of formal
> specification, version numbers are essentially useless for dependency management. By giving a name
> and clear definition to the above ideas, it becomes easy to communicate your intentions to the
> users of your software. Once these intentions are clear, flexible (but not too flexible)
> dependency specifications can finally be made.

## Usage

To use this plugin in your Gradle build, include the `repo.fugu.io` plugin repository in your
build script and apply the plugin:

``` groovy
buildscript {
    repositories {
        maven { url "http://repo.fugu.io/artifactory/plugins-release" }
    }
    dependencies {
        classpath "io.fugu.gradle:gradle-semver-plugin:0.1.0"
    }
}

apply plugin: 'semver'

project.version.with { major = 1; minor = 2; revision = 15  }
```

This plugin exposes two additional build tasks to your project: `printVersion` and `release`.
`printVersion` simply prints out the semantic version number for your project.

    > gradle -q printversion
    1.2.15-SNAPSHOT

Note that by default, a build is a snapshot  build, not a release build.  This means, if you're
using [Gradle's Maven plugin] [mvn-plugin] and run `uploadArchives`, your artifacts will be
uploaded to your snapshot repository.

To build a release, simply use the `release` task when you invoke your build:

    > gradle -q release printversion
    1.2.15

By running the release task, this plugin recognizes you're executing a release build
and removes the `-SNAPSHOT` qualifier from the build artifacts.  Running `uploadArchives`
in this manner will build a release version of your software and upload it to your release
repository.


[semver]: http://semver.org
[mvn-plugin]: http://www.gradle.org/docs/current/userguide/maven_plugin.html
