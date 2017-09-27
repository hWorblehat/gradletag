package org.uulib.gradletag.core

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder

import spock.lang.Specification

class TagVcsSpec extends Specification {
	
	def "Calling 'tag' on the task invokes the tagger"() {
		given: "a Gradle project, with a TagVcs tas defined"
		
		Project project = ProjectBuilder.builder().build()
		VcsTagger taggerMock = Mock()
		
		TagVcs task = project.tasks.create('dummyTag', TagVcs) {
			tag = 'Boo'
			tagger = taggerMock
		}
		
		when: "the TagVcs task is invoked"
		task.tag()
		
		then: "the tagger is called"
		1 * taggerMock.tag('Boo', null)
	}

}
