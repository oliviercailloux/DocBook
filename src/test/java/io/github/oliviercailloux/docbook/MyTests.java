package io.github.oliviercailloux.docbook;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.google.common.io.Resources;
import org.docbook.schemas.docbook.DocBook;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyTests {
  @SuppressWarnings("unused")
  private static final Logger LOGGER = LoggerFactory.getLogger(MyTests.class);

  @Test
  void testResources() throws Exception {
    assertNotNull(DocBookResources.XSD_5_0_URI);
    assertNotNull(DocBookResources.RNG_5_1_URI);
    assertNotNull(MyTests.class.getResource("/io/github/oliviercailloux/docbook/catalog.xml"));
    assertNotNull(DocBookResources.CATALOG_URI);
    assertNotNull(MyTests.class.getResource("catalog.xml"));
    assertNotNull(MyTests.class.getResource("fo/fo.xsl"));
  }
}
