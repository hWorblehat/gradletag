package org.uulib.gradletag.core

import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal

interface TagSpec {
	
	@get:Input val tag : Property<Any>
	@get:Internal val comment : Property<Any?>
	
}