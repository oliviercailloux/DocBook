package io.github.oliviercailloux.docbook;

import com.google.common.collect.ImmutableList;
import com.google.common.io.Resources;
import java.net.URI;
import javax.xml.catalog.Catalog;
import javax.xml.catalog.CatalogFeatures;
import javax.xml.catalog.CatalogFeatures.Feature;
import javax.xml.catalog.CatalogManager;
import javax.xml.catalog.CatalogResolver;
import org.docbook.schemas.docbook.DocBook;
import org.docbook.xsltng.Main;
import org.docbook.xsltng.XslTNG;
import org.xmlresolver.XMLResolver;
import org.xmlresolver.XMLResolverConfiguration;

/**
 * Static resources found in the class path and public ids for use with a catalog.
 */
public class DocBookResources {
  /** The URI of the DocBook 5.0 XSD in the class path */
  public static final URI XSD_5_0_URI = java.net.URI.create(
      Resources.getResource(DocBookResources.class, DocBook.DOCBOOK_5_0_XSD_PATH).toString());
  /** The URI of the DocBook 5.1 RNG in the class path */
  public static final URI RNG_5_1_URI = java.net.URI.create(
      Resources.getResource(DocBookResources.class, DocBook.DOCBOOK_5_1_RNG_PATH).toString());

  /** The public id URI of the main DocBook to FO stylesheet. */
  public static final URI XSLT_1_FO_URI =
      java.net.URI.create("http://cdn.docbook.org/release/xsl/1.79.2/fo/docbook.xsl");
  /** The public id URI of the main DocBook to HTML stylesheet. */
  public static final URI XSLT_1_HTML_URI =
      java.net.URI.create("http://cdn.docbook.org/release/xsl/1.79.2/html/docbook.xsl");

  /** The URI of the DocBook XSLT 1 Catalog in the class path */
  public static URI CATALOG_1_URI =
      java.net.URI.create(Resources.getResource(DocBookResources.class, "catalog.xml").toString());
  /** The URI of the DocBook XSLT 3 (xslTNG) Catalog in the class path */
  public static URI CATALOG_3_URI =
      java.net.URI.create(Resources.getResource(XslTNG.class, "catalog.xml").toString());
  /** The DocBook XSLT 1 Catalog, read from the class path */
  public static Catalog CATALOG_1 = CatalogManager
      .catalog(CatalogFeatures.builder().with(Feature.RESOLVE, "continue").build(), CATALOG_1_URI);
  /** The DocBook XSLT 1 Catalog resolver, reading from the class path */
  public static CatalogResolver RESOLVER_1 = CatalogManager.catalogResolver(CATALOG_1);
  /** The DocBook XSLT XML resolver, reading from the XSLT 1 and 3 catalogs from the class path */
  public static XMLResolver XML_RESOLVER = new XMLResolver(new XMLResolverConfiguration(ImmutableList.of(DocBookResources.CATALOG_1_URI.toString(), DocBookResources.CATALOG_3_URI.toString())));
}
