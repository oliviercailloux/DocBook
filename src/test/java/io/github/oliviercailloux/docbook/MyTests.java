package io.github.oliviercailloux.docbook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.collect.ImmutableList;
import javax.xml.transform.URIResolver;
import javax.xml.transform.sax.SAXSource;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmlresolver.XMLResolver;
import org.xmlresolver.XMLResolverConfiguration;

public class MyTests {
  @SuppressWarnings("unused")
  private static final Logger LOGGER = LoggerFactory.getLogger(MyTests.class);

  @Test
  void testResources() throws Exception {
    assertNotNull(DocBookResources.XSD_5_0_URI);
    assertNotNull(DocBookResources.RNG_5_1_URI);
    assertNotNull(MyTests.class.getResource("/io/github/oliviercailloux/docbook/catalog.xml"));
    assertNotNull(DocBookResources.CATALOG_1_URI);
    assertNotNull(MyTests.class.getResource("catalog.xml"));
    assertNotNull(MyTests.class.getResource("fo/fo.xsl"));
    assertTrue(DocBookResources.CATALOG_3_URI.toString()
        .matches("jar:file:/.*/docbook-xslTNG-2.6.0.jar!/org/docbook/xsltng/catalog.xml"));
  }

  @Test
  void testResolver1() throws Exception {
    assertTrue(DocBookResources.RESOLVER_1
        .resolve("http://cdn.docbook.org/release/xsl/1.79.2/fo/docbook.xsl", null).getSystemId()
        .matches("file:/.*/fo/docbook.xsl"));
    assertTrue(DocBookResources.RESOLVER_1
        .resolve("http://cdn.docbook.org/release/xsl/1.79.2/anythingreally", null).getSystemId()
        .matches("file:/.*/anythingreally"));
  }

  @Test
  void testXmlResolver0() throws Exception {
    XMLResolverConfiguration config = new XMLResolverConfiguration();
    URIResolver uriResolver = new XMLResolver(config).getURIResolver();

    assertEquals("https://cdn.docbook.org/release/xsl/1.79.2/fo/docbook.xsl", uriResolver
        .resolve("http://cdn.docbook.org/release/xsl/1.79.2/fo/docbook.xsl", null).getSystemId());
    assertNull(
        uriResolver.resolve("http://cdn.docbook.org/release/xsl/1.79.2/anythingreally", null));
  }

  @Test
  void testXmlResolver1() throws Exception {
    XMLResolverConfiguration config =
        new XMLResolverConfiguration(ImmutableList.of(DocBookResources.CATALOG_1_URI.toString()));
    URIResolver uriResolver = new XMLResolver(config).getURIResolver();

    assertTrue(uriResolver.resolve("http://cdn.docbook.org/release/xsl/1.79.2/fo/docbook.xsl", null)
        .getSystemId().matches("file:/.*/fo/docbook.xsl"));
    assertNull(
        uriResolver.resolve("http://cdn.docbook.org/release/xsl/1.79.2/anythingreally", null));
  }

  @Test
  void testXmlResolver3() throws Exception {
    XMLResolverConfiguration config =
        new XMLResolverConfiguration(ImmutableList.of(DocBookResources.CATALOG_3_URI.toString()));
    URIResolver uriResolver = new XMLResolver(config).getURIResolver();

    assertEquals("https://cdn.docbook.org/release/xsl/1.79.2/fo/docbook.xsl", uriResolver
        .resolve("http://cdn.docbook.org/release/xsl/1.79.2/fo/docbook.xsl", null).getSystemId());
    assertNull(uriResolver
        .resolve("http://cdn.docbook.org/release/xsl/1.79.2/anythingreally", null));

    SAXSource resolved = (SAXSource) uriResolver
        .resolve("https://cdn.docbook.org/release/xsltng/2.6.0/xslt/docbook.xsl", null);
    assertEquals("https://cdn.docbook.org/release/xsltng/2.6.0/xslt/docbook.xsl",
        resolved.getSystemId());
    assertNull(resolved.getXMLReader());
    assertNull(resolved.getInputSource().getPublicId());
    assertNull(resolved.getInputSource().getCharacterStream());
    assertEquals(21052, resolved.getInputSource().getByteStream().readAllBytes().length);
  }

  @Test
  void testXmlResolver1And3() throws Exception {
    URIResolver uriResolver = DocBookResources.XML_RESOLVER.getURIResolver();
    assertNull(uriResolver
        .resolve("http://cdn.docbook.org/release/xsl/1.79.2/anythingreally", null));

    assertTrue(uriResolver.resolve("http://cdn.docbook.org/release/xsl/1.79.2/fo/docbook.xsl", null)
        .getSystemId().matches("file:/.*/fo/docbook.xsl"));
    assertNull(
        uriResolver.resolve("http://cdn.docbook.org/release/xsl/1.79.2/anythingreally", null));

    SAXSource resolved = (SAXSource) uriResolver
        .resolve("https://cdn.docbook.org/release/xsltng/2.6.0/xslt/docbook.xsl", null);
    assertEquals("https://cdn.docbook.org/release/xsltng/2.6.0/xslt/docbook.xsl",
        resolved.getSystemId());
    assertNull(resolved.getXMLReader());
    assertNull(resolved.getInputSource().getPublicId());
    assertNull(resolved.getInputSource().getCharacterStream());
    assertEquals(21052, resolved.getInputSource().getByteStream().readAllBytes().length);
  }
}
