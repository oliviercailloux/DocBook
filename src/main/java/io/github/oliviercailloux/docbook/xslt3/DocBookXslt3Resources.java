package io.github.oliviercailloux.docbook.xslt3;

import com.google.common.io.Resources;
import java.net.URI;
import org.docbook.xsltng.XslTNG;

/**
 * Static resources found in the class path and public ids for use with a catalog, specific to
 * DocBook XSLT 3, also known as xslTNG.
 */
public class DocBookXslt3Resources {
  /** The public id URI of the DocBook to HTML XSLT 3 (xslTNG) document. */
  public static final URI XSLT_3_HTML_URI =
      URI.create("https://cdn.docbook.org/release/xsltng/2.6.0/xslt/docbook.xsl");

  /** The URI of the DocBook XSLT 3 (xslTNG) catalog in the class path */
  public static URI CATALOG_XSLT_3_URI =
      URI.create(Resources.getResource(XslTNG.class, "catalog.xml").toString());
}
