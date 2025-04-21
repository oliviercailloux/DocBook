package io.github.oliviercailloux.docbook;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyTests {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(MyTests.class);

	@Test
	void testSomething() throws Exception {
    assertNotNull(MyTests.class.getResource("/io/github/oliviercailloux/docbook/catalog.xml"));
    assertNotNull(DocBookCatalog.URI);
    assertNotNull(MyTests.class.getResource("catalog.xml"));
    assertNotNull(MyTests.class.getResource("fo/fo.xsl"));
	}
}
