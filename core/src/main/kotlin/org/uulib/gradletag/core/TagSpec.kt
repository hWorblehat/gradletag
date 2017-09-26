package org.uulib.gradletag.core

import org.gradle.api.provider.Provider

interface TagSpec {
	
	val tagProperty : Provider<String>
	var tag : String
	fun setTag(tagProvider : Provider<String>)
	
	val commentProperty : Provider<String>
	var comment : String?
	fun setComment(commentProvider : Provider<String>)
	
}