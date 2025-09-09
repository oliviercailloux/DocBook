package io.github.oliviercailloux.docbook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.collect.ImmutableList;
import io.github.oliviercailloux.docbook.xslt1.DocBookXslt1Resources;
import io.github.oliviercailloux.docbook.xslt3.DocBookXslt3Resources;
import javax.xml.transform.URIResolver;
import javax.xml.transform.sax.SAXSource;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmlresolver.XMLResolver;
import org.xmlresolver.XMLResolverConfiguration;

public class DocBookResourcesTests {
  @SuppressWarnings("unused")
  private static final Logger LOGGER = LoggerFactory.getLogger(DocBookResourcesTests.class);

  @Test
  void testResources() throws Exception {
    assertNotNull(DocBookResources.XSD_5_0_URI);
    assertNotNull(DocBookResources.RNG_5_1_URI);
    assertNotNull(DocBookResourcesTests.class
        .getResource("/io/github/oliviercailloux/docbook/xslt1/catalog.xml"));
    assertNotNull(DocBookXslt1Resources.CATALOG_XSLT_1_URI);
    assertNotNull(DocBookResourcesTests.class.getResource("xslt1/catalog.xml"));
    assertNotNull(DocBookResourcesTests.class.getResource("xslt1/fo/fo.xsl"));
    assertTrue(DocBookXslt3Resources.CATALOG_XSLT_3_URI.toString()
        .matches("jar:file:/.*/docbook-xslTNG-2.6.0.jar!/org/docbook/xsltng/catalog.xml"));
  }

  @Test
  void testResolver1() throws Exception {
    assertTrue(DocBookXslt1Resources.RESOLVER_XSLT_1
        .resolve("http://cdn.docbook.org/release/xsl/1.79.2/fo/docbook.xsl", null).getSystemId()
        .matches("file:/.*/fo/docbook.xsl"));
    assertTrue(DocBookXslt1Resources.RESOLVER_XSLT_1
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
    XMLResolverConfiguration config = new XMLResolverConfiguration(
        ImmutableList.of(DocBookXslt1Resources.CATALOG_XSLT_1_URI.toString()));
    URIResolver uriResolver = new XMLResolver(config).getURIResolver();

    assertTrue(uriResolver.resolve("http://cdn.docbook.org/release/xsl/1.79.2/fo/docbook.xsl", null)
        .getSystemId().matches("file:/.*/fo/docbook.xsl"));
    assertNull(
        uriResolver.resolve("http://cdn.docbook.org/release/xsl/1.79.2/anythingreally", null));
  }

  @Test
  void testXmlResolver3() throws Exception {
    XMLResolverConfiguration config = new XMLResolverConfiguration(
        ImmutableList.of(DocBookXslt3Resources.CATALOG_XSLT_3_URI.toString()));
    URIResolver uriResolver = new XMLResolver(config).getURIResolver();

    assertEquals("https://cdn.docbook.org/release/xsl/1.79.2/fo/docbook.xsl", uriResolver
        .resolve("http://cdn.docbook.org/release/xsl/1.79.2/fo/docbook.xsl", null).getSystemId());
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

  @Test
  void testXmlResolver1And3() throws Exception {
    URIResolver uriResolver = DocBookResources.XML_RESOLVER.getURIResolver();
    assertNull(
        uriResolver.resolve("http://cdn.docbook.org/release/xsl/1.79.2/anythingreally", null));

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
