package io.github.oliviercailloux.docbook;

import com.google.common.io.Resources;
import java.net.URI;

public class DocBookCatalog {
  public static URI URI =
      java.net.URI.create(Resources.getResource(DocBookCatalog.class, "catalog.xml").toString());
}
