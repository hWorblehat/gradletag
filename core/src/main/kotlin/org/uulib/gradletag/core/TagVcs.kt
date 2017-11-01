package org.uulib.gradletag.core

import org.gradle.api.DefaultTask
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import org.uulib.gradle.PropertyStateDelegate
import javax.inject.Inject
import org.uulib.gradletag.VcsTagException

open class TagVcs @Inject constructor(objects : ObjectFactory) : DefaultTask(), TagSpec by TagSpecImpl(objects) {
	
	@get:Internal
	val vcs = objects.property(Any::class.java)
	
	init {
		group = "Publishing"
	}
	
	@TaskAction
	@Throws(VcsTagException::class)
	fun tag() {
		TaggerResolver.resolveTagger(vcs.get()).tag(tag.toString(), comment?.toString())
	}
	
}