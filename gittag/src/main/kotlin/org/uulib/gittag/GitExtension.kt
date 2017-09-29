package org.uulib.gittag

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.lib.Repository
import org.eclipse.jgit.lib.RepositoryBuilder
import org.gradle.api.Project
import javax.inject.Inject

open class GitExtension @Inject constructor(private val project: Project) {
	
	companion object {
		val EXTENSION_NAME = "git"
	}
	
	fun repository(repository: Repository) : GitTagger {
		return GitTagger(Git(repository))
	}
	
	fun repository(dir: Any) : GitTagger {
		return repository(RepositoryBuilder().setWorkTree(project.file(dir)).build())
	}
	
	fun bareRepository(dir: Any) : GitTagger {
		return repository(RepositoryBuilder().setGitDir(project.file(dir)).build())
	}
	
	fun repositoryFromProjectDir() : GitTagger {
		return repository(project.projectDir)
	}
	
	fun repositoryFromRootDir() : GitTagger {
		return repository(project.rootDir)
	}
	
}