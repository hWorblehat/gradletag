package org.uulib.gradletag.core

import org.gradle.api.provider.Provider
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.Input

interface TagSpec {
	
	@get:Input
	val tagProperty : Provider<Any>
	
	@get:Internal
	var tag : Any
	fun setTag(tagProvider : Provider<Any>)
	
	@get:Internal
	val commentProperty : Provider<Any>
	
	@get:Internal
	var comment : Any?
	fun setComment(commentProvider : Provider<Any>)
	
}