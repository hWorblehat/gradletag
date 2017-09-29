package org.uulib.gittag

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.api.errors.GitAPIException
import org.uulib.gradletag.core.VcsTagException
import org.uulib.gradletag.core.VcsTagger

/**
 * A {@link VcsTagger} that tags a Git repository.
 * @property git A {@link Git} object pointing to the repository to tag.
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