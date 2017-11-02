package org.uulib.gradletag.git

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.uulib.gradletag.core.GradleTagPlugin
import org.uulib.gradletag.core.GradleTagExtension
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider

class GitTagPlugin : Plugin<Project> {
	
	override fun apply(project: Project) {
		project.pluginManager.apply(GradleTagPlugin::class.java)
		
		val gradletag = project.extensions.getByType(GradleTagExtension::class.java)
		
		val rootGrgit = project.objects.property(Any::class.java)
		val grgit = project.objects.property(Any::class.java)
		grgit.setProvider(rootGrgit)
		
		project.rootProject.pluginManager.withPlugin("org.ajoberstar.grgit") { _ ->
			rootGrgit.set(project.rootProject.property("grgit"))
			gradletag.vcs.setProvider(grgit)
		}
		
		project.pluginManager.withPlugin("org.ajoberstar.grgit") { _ ->
			grgit.set(project.property("grgit"))
			gradletag.vcs.setProvider(grgit)
		}
	}
	
}

private fun <T> Property<T>.setProvider(provider: Provider<out T>) = set(provider)
