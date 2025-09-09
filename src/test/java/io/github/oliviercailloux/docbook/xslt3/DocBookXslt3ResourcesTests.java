package io.github.oliviercailloux.docbook.xslt3;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.jimfs.Jimfs;
import io.github.oliviercailloux.jaris.io.PathUtils;
import java.nio.file.FileSystem;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DocBookXslt3ResourcesTests {
@SuppressWarnings("unused")
private static final Logger LOGGER = LoggerFactory.getLogger(DocBookXslt3ResourcesTests.class);

  @Test
  void testCopyResources() throws Exception {
    // assertThrows(FileSystemNotFoundException.class, () -> Path.of(DocBookXslt3Resources.RESOURCES_FOLDER_URI));
    try (FileSystem target = Jimfs.newFileSystem()) {
      Path root = Path.of(DocBookXslt3Resources.RESOURCES_FOLDER_URI);
      Path copied = target.getPath("copied/");
      PathUtils.copyRecursively(root, copied);
      assertTrue(Files.exists(copied.resolve("css/").resolve("docbook.css")));
      assertTrue(Files.exists(
          copied.resolve("js/").resolve("controls.js")));
    }
  }
}
