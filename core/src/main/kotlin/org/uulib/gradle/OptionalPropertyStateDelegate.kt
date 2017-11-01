package org.uulib.gradle

import org.gradle.api.provider.Property
import kotlin.properties.ReadWriteProperty
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class OptionalPropertyStateDelegate<T>(private val delegate : Property<T>)
		: ReadWriteProperty<Any, T?>, ReadOnlyProperty<Any, T?> by OptionalProviderDelegate(delegate) {
	
	override fun setValue(thisRef: Any, property: KProperty<*>, value: T?) {
		delegate.set(value)
	}
}