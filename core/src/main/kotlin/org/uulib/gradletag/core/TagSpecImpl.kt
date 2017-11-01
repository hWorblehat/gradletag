package org.uulib.gradletag.core

import org.gradle.api.provider.Provider
import org.gradle.api.provider.ProviderFactory
import org.uulib.gradle.OptionalPropertyStateDelegate
import org.uulib.gradle.PropertyStateDelegate
import javax.inject.Inject
import java.util.Objects
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property

internal class TagSpecImpl @Inject constructor(objectFactory: ObjectFactory) : TagSpec {
	
	override val tagProperty = objectFactory.property(Any::class.java)
	override val commentProperty = objectFactory.property(Any::class.java)
	
	override var tag : Any by PropertyStateDelegate(tagProperty)
	override fun setTag(tagProvider : Provider<Any>) = tagProperty.setProvider(tagProvider)
	
	override var comment : Any? by OptionalPropertyStateDelegate(commentProperty)
	override fun setComment(commentProvider : Provider<Any>) = commentProperty.setProvider(commentProvider)
	
	private fun <T> Property<T>.setProvider(provider: Provider<out T>) {
		set(provider)
	}
	
}