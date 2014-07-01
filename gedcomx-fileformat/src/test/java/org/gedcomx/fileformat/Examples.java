package org.gedcomx.fileformat;

import org.gedcomx.Gedcomx;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.jar.JarFile;

/**
 * @author Ryan Heaton
 */
public class Examples {

  public void write() throws IOException {
    Gedcomx gx = null;
    File file = new File("/path/to/file.gedx");

    GedcomxOutputStream out = new GedcomxOutputStream(new FileOutputStream(file));
    out.addResource(gx);
    out.close();
  }

  public void read() throws IOException {
    File file = new File("/path/to/file.gedx");
    GedcomxFile gxFile = new GedcomxFile(new JarFile(file));
    Iterable<GedcomxFileEntry> entries = gxFile.getEntries();
    for (GedcomxFileEntry entry : entries) {
      //for each entry, read the model.
      Gedcomx gx = (Gedcomx) gxFile.readResource(entry);
    }
  }
}
