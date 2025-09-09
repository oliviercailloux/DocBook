package io.github.oliviercailloux.docbook.xslt1;

import com.google.common.io.Resources;
import io.github.oliviercailloux.docbook.DocBookResources;
import java.net.URI;
import javax.xml.catalog.Catalog;
import javax.xml.catalog.CatalogFeatures;
import javax.xml.catalog.CatalogFeatures.Feature;
import javax.xml.catalog.CatalogManager;
import javax.xml.catalog.CatalogResolver;

/**
 * Static resources found in the class path and public ids for use with a catalog, specific to DocBook XSLT 1.
 */
public class DocBookXslt1Resources {
  /** The public id URI of the DocBook to FO XSLT 1 document. */
  public static final URI XSLT_1_FO_URI =
      URI.create("http://cdn.docbook.org/release/xsl/1.79.2/fo/docbook.xsl");
  /** The public id URI of the DocBook to HTML XSLT 1 document. */
  public static final URI XSLT_1_HTML_URI =
      URI.create("http://cdn.docbook.org/release/xsl/1.79.2/html/docbook.xsl");

  /** The URI of the DocBook XSLT 1 catalog in the class path */
  public static URI CATALOG_XSLT_1_URI =
      URI.create(Resources.getResource(DocBookXslt1Resources.class, "catalog.xml").toString());
   /** The DocBook XSLT 1 catalog, read from the class path */
  public static Catalog CATALOG_XSLT_1 = CatalogManager
      .catalog(CatalogFeatures.builder().with(Feature.RESOLVE, "continue").build(), CATALOG_XSLT_1_URI);
  /** The DocBook XSLT 1 catalog resolver, reading from the class path */
  public static CatalogResolver RESOLVER_XSLT_1 = CatalogManager.catalogResolver(CATALOG_XSLT_1);
 
}
