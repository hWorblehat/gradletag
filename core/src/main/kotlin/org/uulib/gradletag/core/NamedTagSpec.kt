package org.uulib.gradletag.core

import org.gradle.api.Named
import org.gradle.api.model.ObjectFactory

class NamedTagSpec(private val name: String, objectFactory: ObjectFactory)
		: TagSpec by TagSpecImpl(objectFactory), Named {
	
	val tagTaskName = "tagVcsWith${name.capitalize()}"
	
	override fun getName() : String = name
	
}