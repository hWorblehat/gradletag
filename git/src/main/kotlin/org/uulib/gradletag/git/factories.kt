package org.uulib.gradletag.git

import org.ajoberstar.grgit.Grgit
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.lib.Repository
import org.uulib.gradletag.VcsTagger
import org.uulib.gradletag.VcsTaggerFactory

class JGitTaggerFactory: VcsTaggerFactory<Git> {
	
	override val parameterType = Git::class.java
	
	override fun getTaggerFor(param: Git): VcsTagger {
		return GitTagger(param)
	} 
	
}

class JGitRepoTaggerFactory: VcsTaggerFactory<Repository> {
	
	override val parameterType = Repository::class.java
	
	override fun getTaggerFor(param: Repository): VcsTagger {
		return GitTagger(Git(param))
	} 
	
}

class GrgitTaggerFactory: VcsTaggerFactory<Grgit> {
	
	override val parameterType = Grgit::class.java
	
	override fun getTaggerFor(param: Grgit): VcsTagger {
		return GitTagger(param.repository.jgit)
	} 
	
}