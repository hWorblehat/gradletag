package org.uulib.gradletag.core

import org.gradle.api.provider.Provider
import org.gradle.api.provider.ProviderFactory
import org.uulib.gradle.PropertyStateDelegate
import javax.inject.Inject
import org.gradle.api.Project
import org.gradle.api.Action
import org.gradle.api.NamedDomainObjectContainer

open class GradleTagExtension @Inject constructor(project : Project) {
	
	companion object {
		val EXTENSION_NAME = "gradletag"
	}
	
	val vcs = project.objects.property(Any::class.java)
	val tags = project.container(NamedTagSpec::class.java, {name -> NamedTagSpec(name, project.objects)})
	
	private val rootProjectVcsProperty = project.objects.property(Any::class.java)
	
	init {
		val root = project.rootProject
		if(!root.equals(project)) {
			root.pluginManager.withPlugin(GradleTagPlugin.PLUGIN_NAME) {
				rootProjectVcsProperty.setProvider(root.extensions.getByType(GradleTagExtension::class.java).vcs)
			}
		}
		
		vcsFromRootProject()
	}
	
	fun vcsFromRootProject() {
		vcs.setProvider(rootProjectVcsProperty)
	}
	
	fun tags(action: Action<in NamedDomainObjectContainer<NamedTagSpec>>) = action.execute(tags)
	
}