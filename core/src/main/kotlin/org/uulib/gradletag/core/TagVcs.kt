package org.uulib.gradletag.core

import org.gradle.api.DefaultTask
import org.gradle.api.provider.Provider
import org.gradle.api.provider.ProviderFactory
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.uulib.gradle.PropertyStateDelegate
import javax.inject.Inject

open class TagVcs @Inject constructor(providers : ProviderFactory) : DefaultTask(), TagSpec by TagSpecImpl(providers) {
	
	private val taggerState = project.property(VcsTagger::class.java)
	@get:Input
	var tagger : VcsTagger by PropertyStateDelegate(taggerState)
	fun setTagger(tagger: Provider<VcsTagger>) = taggerState.set(tagger)
	
	init {
		group = "Publishing"
	}
	
	@TaskAction
	@Throws(VcsTagException::class)
	fun tag() {
		tagger.tag(tag, comment)
	}
	
}