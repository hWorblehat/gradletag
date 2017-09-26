package org.uulib.gradle

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty
import org.gradle.api.provider.Provider

class ProviderDelegate<T>(private val delegate : Provider<T>) : ReadOnlyProperty<Any, T> {
	
	override fun getValue(thisRef: Any, property: KProperty<*>): T {
		return delegate.get()
	}
	
}