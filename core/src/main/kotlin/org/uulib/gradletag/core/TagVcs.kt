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
	
	private val taggerState = objects.property(VcsTagger::class.java)
	
	@get:Internal
	var tagger : VcsTagger by PropertyStateDelegate(taggerState)
	fun setTagger(tagger: Provider<VcsTagger>) = taggerState.set(tagger)
	
	init {
		group = "Publishing"
	}
	
	@TaskAction
	@Throws(VcsTagException::class)
	fun tag() {
		tagger.tag(tag.toString(), comment?.toString())
	}
	
}