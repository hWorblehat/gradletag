package org.uulib.gittag

import org.gradle.api.Project
import java.io.File
import javax.inject.Inject
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.storage.file.FileRepositoryBuilder
import org.eclipse.jgit.lib.RepositoryBuilder
import org.eclipse.jgit.lib.BaseRepositoryBuilder

open class GitExtension @Inject constructor(private val project: Project) {
	
	companion object {
		val EXTENSION_NAME = "git"
	}
	
	private fun repository(builder: BaseRepositoryBuilder<*, *>) : GitTagger {
		return GitTagger(Git(builder.build()))
	}
	
	fun repository(dir: Any) : GitTagger {
		return repository(RepositoryBuilder().setWorkTree(project.file(dir)))
	}
	
	fun bareRepository(dir: Any) : GitTagger {
		return repository(RepositoryBuilder().setGitDir(project.file(dir)))
	}
	
	fun repositoryFromProjectDir() : GitTagger {
		return repository(project.projectDir)
	}
	
	fun repositoryFromRootDir() : GitTagger {
		return repository(project.rootDir)
	}
	
}