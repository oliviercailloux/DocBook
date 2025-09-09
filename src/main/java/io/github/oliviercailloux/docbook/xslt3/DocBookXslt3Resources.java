package io.github.oliviercailloux.docbook.xslt3;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.io.Resources;
import io.github.oliviercailloux.jaris.io.PathUtils;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import org.docbook.xsltng.XslTNG;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Static resources found in the class path and public ids for use with a catalog, specific to
 * DocBook XSLT 3, also known as xslTNG.
 */
public class DocBookXslt3Resources {
  @SuppressWarnings("unused")
  private static final Logger LOGGER = LoggerFactory.getLogger(DocBookXslt3Resources.class);

  /** The public id URI of the DocBook to HTML XSLT 3 (xslTNG) document. */
  public static final URI XSLT_3_HTML_URI =
      URI.create("https://cdn.docbook.org/release/xsltng/2.6.0/xslt/docbook.xsl");

  /** The URI of the DocBook XSLT 3 (xslTNG) catalog in the class path */
  public static URI CATALOG_XSLT_3_URI =
      URI.create(Resources.getResource(XslTNG.class, "catalog.xml").toString());

  /** The URI of the XSLT 3 (xslTNG) resources folder in the class path */
  public static URI RESOURCES_FOLDER_URI =
      URI.create(DocBookXslt3Resources.class.getResource("resources/").toString());

  /**
   * Copies the XSLT 3 (xslTNG) resources to the specified directory. The given folder and its
   * parent folders will be created if required. Sub-folders <code>css</code> and <code>js</code>
   * will be created if they do not exist already.
   *
   * @param targetDirectory the directory to which the resources are copied
   * @throws IOException if an I/O error occurs
   */
  public static void copyResourcesTo(Path targetDirectory) throws IOException {
    try (FileSystem fs = FileSystems.newFileSystem(RESOURCES_FOLDER_URI, ImmutableMap.of())) {
      onceOpenedCopyTo(targetDirectory);
    } catch (@SuppressWarnings("unused") IllegalArgumentException e) {
      onceOpenedCopyTo(targetDirectory);
    }
  }

  private static void onceOpenedCopyTo(Path targetDirectory) throws IOException {
    Path root = Path.of(RESOURCES_FOLDER_URI);
    Files.createDirectories(targetDirectory);
    LOGGER.info("Copying: {}.", Files.list(root).collect(ImmutableSet.toImmutableSet()));
    PathUtils.copyRecursively(root, targetDirectory);
  }
}
