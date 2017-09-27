package org.uulib.gradletag.core

import org.gradle.api.provider.Provider
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.Input

interface TagSpec {
	
	@get:Internal
	val tagProperty : Provider<String>
	
	@get:Input
	var tag : String
	fun setTag(tagProvider : Provider<String>)
	
	@get:Internal
	val commentProperty : Provider<String>
	
	@get:Internal
	var comment : String?
	fun setComment(commentProvider : Provider<String>)
	
}