package org.uulib.gradletag

interface VcsTaggerFactory<T> {
	
	val parameterType: Class<T>
	
	fun getTaggerFor(param: T): VcsTagger
	
}