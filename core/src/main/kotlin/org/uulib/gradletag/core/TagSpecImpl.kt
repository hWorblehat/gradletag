package org.uulib.gradletag.core

import org.gradle.api.model.ObjectFactory
import javax.inject.Inject

internal class TagSpecImpl @Inject constructor(objectFactory: ObjectFactory) : TagSpec {
	
	override val tag = objectFactory.property(Any::class.java)
	override val comment = objectFactory.property(Any::class.java)
	
}