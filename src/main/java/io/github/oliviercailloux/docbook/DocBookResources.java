package io.github.oliviercailloux.docbook;

import com.google.common.collect.ImmutableList;
import com.google.common.io.Resources;
import io.github.oliviercailloux.docbook.xslt1.DocBookXslt1Resources;
import io.github.oliviercailloux.docbook.xslt3.DocBookXslt3Resources;
import java.net.URI;
import org.docbook.schemas.docbook.DocBook;
import org.xmlresolver.XMLResolver;
import org.xmlresolver.XMLResolverConfiguration;

/**
 * Static resources found in the class path and public ids for use with a catalog.
 */
public class DocBookResources {
  /** The URI of the DocBook 5.0 XSD in the class path */
  public static final URI XSD_5_0_URI = URI.create(
      Resources.getResource(DocBookResources.class, DocBook.DOCBOOK_5_0_XSD_PATH).toString());
  /** The URI of the DocBook 5.1 RNG in the class path */
  public static final URI RNG_5_1_URI = URI.create(
      Resources.getResource(DocBookResources.class, DocBook.DOCBOOK_5_1_RNG_PATH).toString());

  /**
   * The DocBook XML resolver, reading from the XSLT 1 and 3 (xslTNG) catalogs from the class path
   */
  public static XMLResolver XML_RESOLVER = new XMLResolver(new XMLResolverConfiguration(
      ImmutableList.of(DocBookXslt1Resources.CATALOG_XSLT_1_URI.toString(),
          DocBookXslt3Resources.CATALOG_XSLT_3_URI.toString())));
}
