package org.uulib.gradletag.core

import static org.gradle.testkit.runner.TaskOutcome.*

import org.gradle.testkit.runner.GradleRunner
import org.junit.Rule
import org.junit.rules.TemporaryFolder

import spock.lang.*

class GradleTagSpec extends Specification {
	
	@Shared List<String> compatibleGradleVersions =
			System.getProperty('org.uulib.gradletag.compatibleGradleVersions').split(',')
	
	@Rule TemporaryFolder dummyProjectDir = new TemporaryFolder()
	File buildFile
	
	def setup() {
		buildFile = dummyProjectDir.newFile('build.gradle')
		
		buildFile <<
"""
plugins {
	id 'org.uulib.gradletag'
}

import org.uulib.gradletag.VcsTagger

class DummyTagger implements VcsTagger {

	@Override
	void tag(String tag, String comment) {
		println '+++' + tag + '+++' + comment + '+++'
	}

}

gradletag {
	vcs = new DummyTagger()

	tags {
		dummy {
			tag = "Boo"
			comment = 'This is scary'
		}
	}
}

"""
		
	}
	
	@Unroll
	def "A tagging task is created when a tag is defined in Gradle #gradleVersion"(String gradleVersion) {

		when:
		def result = createRunner(gradleVersion).withArguments('tasks').build()
		
		then:
		result.output.contains('tagVcsWithDummy')
		
		where:
		gradleVersion << compatibleGradleVersions
	}
	
	@Unroll
	def "The tagging task invokes the vcs tagger in Gradle #gradleVersion"(String gradleVersion) {
		when:
		def result = createRunner(gradleVersion).withArguments('tagVcsWithDummy', '--stacktrace').build()
		
		then:
		result.output.contains('+++Boo+++This is scary+++')
		result.task(':tagVcsWithDummy').outcome == SUCCESS
		
		where:
		gradleVersion << compatibleGradleVersions
	}
	
	@Unroll
	def "The tagging task successfully runs twice in Gradle #gradleVersion"(String gradleVersion) {
		given: "a build already run once"
		createRunner(gradleVersion).withArguments('tagVcsWithDummy').build()
		
		when: "it is run again"
		def result = createRunner(gradleVersion).withArguments('tagVcsWithDummy', '--stacktrace').build()
		
		then: "it is successful"
		result.output.contains('+++Boo+++This is scary+++')
		result.task(':tagVcsWithDummy').outcome == SUCCESS
		
		where: "various versions of Gradle are used"
		gradleVersion << compatibleGradleVersions
	}
	
	private GradleRunner createRunner(String gradleVersion) {
		return GradleRunner.create()
				.withPluginClasspath()
				.withGradleVersion(gradleVersion)
				.withProjectDir(dummyProjectDir.root)
	}

}
