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
	
	val vcsProperty = project.property(VcsTagger::class.java)
	var vcs : VcsTagger by PropertyStateDelegate(vcsProperty)
	
	val tags = project.container(NamedTagSpec::class.java, {name -> NamedTagSpec(name, project.providers)})
	
	private val rootProjectVcsProperty = project.property(VcsTagger::class.java)
	
	init {
		val root = project.rootProject
		if(!root.equals(project)) {
			root.pluginManager.withPlugin(GradleTagPlugin.PLUGIN_NAME) {
				rootProjectVcsProperty.set(root.extensions.getByType(GradleTagExtension::class.java).vcsProperty)
			}
		}
		
		vcsFromRootProject()
	}
	
	fun setVcs(vcsProvider : Provider<VcsTagger>) = vcsProperty.set(vcsProvider)
	
	fun vcsFromRootProject() {
		setVcs(rootProjectVcsProperty)
	}
	
	fun tags(action: Action<in NamedDomainObjectContainer<NamedTagSpec>>) = action.execute(tags)
	
}