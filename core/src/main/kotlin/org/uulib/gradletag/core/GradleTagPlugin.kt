package org.uulib.gradletag.core

import org.gradle.api.Plugin
import org.gradle.api.Project

class GradleTagPlugin : Plugin<Project> {
	
	companion object {
		val PLUGIN_NAME = "org.uulib.gradletag"
	}
	
	override fun apply(project: Project) {
		val ex = project.extensions.create(GradleTagExtension.EXTENSION_NAME, GradleTagExtension::class.java, project)
		
		ex.tags.all { tag ->
			project.tasks.create(tag.tagTaskName, TagVcs::class.java) { tagTask ->
				tagTask.setTag(tag.tagProperty)
				tagTask.setComment(tag.commentProperty)
				tagTask.description = "Tags the version control repository with the '${tag.name}' tag."
			}
		}
		
		project.tasks.withType(TagVcs::class.java) { tagTask ->
			tagTask.vcs.setProvider(ex.vcs)
		}
	}
	
}