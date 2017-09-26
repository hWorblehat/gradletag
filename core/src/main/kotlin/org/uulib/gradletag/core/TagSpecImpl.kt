package org.uulib.gradletag.core

import org.gradle.api.provider.Provider
import org.gradle.api.provider.ProviderFactory
import org.gradle.api.tasks.Input
import org.uulib.gradle.OptionalPropertyStateDelegate
import org.uulib.gradle.PropertyStateDelegate
import javax.inject.Inject

internal class TagSpecImpl @Inject constructor(providerFactory: ProviderFactory) : TagSpec {
	
	override val tagProperty = providerFactory.property(String::class.java)
	override val commentProperty = providerFactory.property(String::class.java)
	
	@get:Input
	override var tag : String by PropertyStateDelegate(tagProperty)
	override fun setTag(tagProvider : Provider<String>) = tagProperty.set(tagProvider)
	
	@get:Input
	override var comment : String? by OptionalPropertyStateDelegate(commentProperty)
	override fun setComment(commentProvider : Provider<String>) = tagProperty.set(commentProvider)
	
}