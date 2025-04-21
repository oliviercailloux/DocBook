package io.github.oliviercailloux.docbook;

import com.google.common.io.Resources;
import java.net.URI;
import javax.xml.catalog.Catalog;
import javax.xml.catalog.CatalogFeatures;
import javax.xml.catalog.CatalogFeatures.Feature;
import javax.xml.catalog.CatalogManager;
import javax.xml.catalog.CatalogResolver;

public class DocBookCatalog {
  public static URI URI =
      java.net.URI.create(Resources.getResource(DocBookCatalog.class, "catalog.xml").toString());
  public static Catalog CATALOG = CatalogManager
      .catalog(CatalogFeatures.builder().with(Feature.RESOLVE, "continue").build(), URI);
  public static CatalogResolver RESOLVER = CatalogManager.catalogResolver(CATALOG);
}
