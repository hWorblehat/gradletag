package org.uulib.gradletag.core

import org.gradle.api.Named
import org.gradle.api.provider.ProviderFactory
import org.gradle.api.NamedDomainObjectFactory

class NamedTagSpec(private val name: String, providerFactory: ProviderFactory)
		: TagSpec by TagSpecImpl(providerFactory), Named {
	
	override fun getName() : String = name
	
}