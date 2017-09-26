package org.uulib.gradle

import org.gradle.api.provider.PropertyState
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
import kotlin.properties.ReadOnlyProperty

class PropertyStateDelegate<T>(private val delegate: PropertyState<T>)
		: ReadWriteProperty<Any, T>, ReadOnlyProperty<Any, T> by ProviderDelegate(delegate) {

	override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
		delegate.set(value)
	}
}