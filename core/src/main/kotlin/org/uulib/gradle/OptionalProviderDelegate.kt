package org.uulib.gradle

import org.gradle.api.provider.Provider
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class OptionalProviderDelegate<T>(private val delegate : Provider<T>) : ReadOnlyProperty<Any, T?> {
	
	override fun getValue(thisRef: Any, property: KProperty<*>): T? {
		return delegate.getOrNull()
	}
	
}