package org.uulib.gradletag.core

import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider

internal fun <T> Property<T>.setProvider(provider: Provider<T>) {
	set(provider)
}