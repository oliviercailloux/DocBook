package io.github.oliviercailloux.docbook;

import com.google.common.collect.ImmutableSet;
import com.google.common.io.Resources;
import java.net.URI;
import javax.xml.catalog.Catalog;
import javax.xml.catalog.CatalogFeatures;
import javax.xml.catalog.CatalogFeatures.Feature;
import javax.xml.catalog.CatalogManager;
import javax.xml.catalog.CatalogResolver;
import org.docbook.schemas.docbook.DocBook;

/**
 * Static resources found in the class path.
 */
public class DocBookResources {
  /** The URI of the DocBook 5.0 XSD in the class path */
  public static final URI XSD_5_0_URI =
      java.net.URI.create(Resources.getResource(DocBookResources.class, DocBook.DOCBOOK_5_0_XSD_PATH).toString());
  /** The URI of the DocBook 5.1 XSD in the class path */
  public static final URI RNG_5_1_URI =
      java.net.URI.create(Resources.getResource(DocBookResources.class, DocBook.DOCBOOK_5_1_RNG_PATH).toString());

  /** The URI of the DocBook XSLT 1 Catalog in the class path */
  public static URI CATALOG_URI =
      java.net.URI.create(Resources.getResource(DocBookResources.class, "catalog.xml").toString());
  /** The DocBook XSLT 1 Catalog, read from the class path */
  public static Catalog CATALOG = CatalogManager
      .catalog(CatalogFeatures.builder().with(Feature.RESOLVE, "continue").build(), CATALOG_URI);
  /** The DocBook XSLT 1 Catalog resolver, read from the class path */
  public static CatalogResolver RESOLVER = CatalogManager.catalogResolver(CATALOG);
}
