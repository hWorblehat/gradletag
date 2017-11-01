package org.uulib.gradle

import org.gradle.api.provider.Property
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PropertyStateDelegate<T>(private val delegate: Property<T>)
		: ReadWriteProperty<Any, T>, ReadOnlyProperty<Any, T> by ProviderDelegate(delegate) {

	override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
		delegate.set(value)
	}
}