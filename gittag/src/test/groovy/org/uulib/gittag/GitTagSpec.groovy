package org.uulib.gittag

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.api.InitCommand
import org.eclipse.jgit.lib.Ref
import org.eclipse.jgit.revwalk.RevTag
import org.eclipse.jgit.revwalk.RevWalk
import org.gradle.testkit.runner.GradleRunner
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import org.uulib.gradletag.core.NamedTagSpec

import spock.lang.*

import static org.gradle.testkit.runner.TaskOutcome.*

class GitTagSpec extends Specification {
	
	@Rule TemporaryFolder projectDir = new TemporaryFolder()
	Git git
	
	def setup() {
		git = new InitCommand().setDirectory(projectDir.root).call()
		
		projectDir.newFile('build.gradle') <<
"""
plugins {
	id 'org.uulib.gittag'
}

gradletag {
	tags {
		testTag {
			tag = 'xxTAGxx'
			comment = 'A dummy tag'
		}
	}
}
"""

		git.add().addFilepattern('.').call()
		git.commit()
				.setMessage('Initial commit')
				.setAuthor('Mr Blobby', 'blobby@noelshouseparty.xxx')
				.setCommitter('Mr Blobby', 'blobby@noelshouseparty.xxx')
				.call()
	}
	
	def "The git repository has been properly set up"() {
		expect:
		new File(projectDir.root, ".git").directory
	}
	
	@Unroll
	def "Applying the gittag plugin and running a tag task creates a tag on the repository in Gradle #gradleVersion"(
			String gradleVersion) {
		setup:
		RevWalk walk = new RevWalk(git.repository)

		when:
		def result = GradleRunner.create()
				.withPluginClasspath()
				.withProjectDir(projectDir.root)
				.withGradleVersion(gradleVersion)
				.withArguments('tagVcsWithTestTag', '--stacktrace')
				.build()
				
		then:
		result.task(':tagVcsWithTestTag').outcome == SUCCESS
		git.tagList().call().find { Ref ref ->
			RevTag tag = walk.parseTag(ref.objectId)
			return tag.tagName == 'xxTAGxx' && tag.shortMessage == 'A dummy tag'
		}
		
		where:
		gradleVersion << System.getProperty('org.uulib.gradletag.compatibleGradleVersions').split(',')
	}
	
}
