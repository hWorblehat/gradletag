package org.uulib.gradletag

/**
 * Provides the means to tag a particular version control system.
 */
interface VcsTagger {
	
	/**
	 * Tags the current head of the version control repository.
	 * @param tag The tag to add.
	 * @param comment An optional comment to attach to the tag, if the VCS supports it. May be {@code null}.
	 * @throws VcsTagException If an error occurrs when tagging.
	 */
	@Throws(VcsTagException::class)
	fun tag(tag: String, comment: String? = null)
}