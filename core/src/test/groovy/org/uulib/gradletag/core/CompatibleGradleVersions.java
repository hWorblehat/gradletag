package org.uulib.gradletag.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface CompatibleGradleVersions {
	
	public static final List<String> VERSIONS = Collections.unmodifiableList(Arrays.asList(
			"4.0", "4.1", "4.2"
	));

}
