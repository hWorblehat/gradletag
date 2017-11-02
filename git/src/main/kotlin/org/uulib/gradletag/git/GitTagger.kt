package org.uulib.gradletag.git

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.api.errors.GitAPIException
import org.uulib.gradletag.VcsTagException
import org.uulib.gradletag.VcsTagger

/**
 * A [VcsTagger] that tags a Git repository.
 * @property git A [Git] object pointing to the repository to tag.
 */
class GitTagger(private val git: Git) : VcsTagger {
	
	@Throws(VcsTagException::class)
	override fun tag(tag: String, comment: String?) {
		try {
			git.tag().setName(tag).setMessage(comment).call()
		} catch (e: GitAPIException) {
			throw VcsTagException(cause = e)
		}
	}
	
}