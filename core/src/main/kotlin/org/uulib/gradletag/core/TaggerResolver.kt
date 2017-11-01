package org.uulib.gradletag.core

import java.util.ServiceLoader
import java.lang.IllegalArgumentException
import org.uulib.gradletag.VcsTagger
import org.uulib.gradletag.VcsTaggerFactory
import org.slf4j.Logger
import org.slf4j.LoggerFactory

internal object TaggerResolver {
	private val logger: Logger = LoggerFactory.getLogger(TaggerResolver::class.java)
	private val serviceLoader: ServiceLoader<VcsTaggerFactory<*>> =
			ServiceLoader.load(VcsTaggerFactory::class.java)
	
	fun resolveTagger(param: Any): VcsTagger {
		logger.debug("Looking for tagger factory for '{}' (class: {})", param, param::class.java)
		
		for(taggerFactory in serviceLoader) {
			logger.debug("Checking tagger factory: {}", taggerFactory::class.java)
			if(taggerFactory.parameterType.isInstance(param)) {
				return resolveWith(taggerFactory, param)
			}
		}
		
		throw IllegalArgumentException(
				"Don't know how to create VCS tagger from '${param}' of type: '${param::class.java}'")
	}
	
	private fun <T> resolveWith(taggerFactory: VcsTaggerFactory<T>, param: Any): VcsTagger {
		return taggerFactory.getTaggerFor(taggerFactory.parameterType.cast(param))
	}
	
}

class NoOpTaggerFactory: VcsTaggerFactory<VcsTagger> {
	override val parameterType = VcsTagger::class.java
	override fun getTaggerFor(param: VcsTagger): VcsTagger = param
}
