package org.uulib.gradletag.core

/**
 * Indicates a problem when trying to add a tag to a VCS repository.
 *
 * @property message A message detailing the error that occurred.
 * @property cause The Throwable that caused this exception to be raised.
 * @constructor Creates a new VcsTagException
 */
class VcsTagException(message: String? = null, cause: Throwable? = null) : Exception(message, cause) {}
