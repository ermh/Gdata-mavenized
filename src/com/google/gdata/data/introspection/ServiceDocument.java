/* Copyright (c) 2006 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.google.gdata.data.introspection;

import com.google.gdata.util.common.xml.XmlWriter;
import com.google.gdata.data.ExtensionPoint;
import com.google.gdata.data.ExtensionProfile;
import com.google.gdata.util.Namespaces;
import com.google.gdata.util.ParseException;
import com.google.gdata.util.XmlParser;

import org.xml.sax.Attributes;

import java.io.InputStream;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;


/**
 * The ServiceDocument class defines the basic Java object model 
 * representation and XML parsing/generation support for an
 * Atom Publishing Protocol introspection document
 *
 * 
 */
public class ServiceDocument extends ExtensionPoint {


  /** The list of workspaces associated with the service */
  List<Workspace> workspaces = new ArrayList<Workspace>();
  public List<Workspace> getWorkspaces() { return workspaces; }


  /**
   * Generates XML.
   *
   * @param   w
   *            output writer
   *
   * @throws  IOException
   */
  public void generate(XmlWriter w, ExtensionProfile extProfile) 
      throws IOException {

    w.startElement(Namespaces.atomPubNs, "service", null, null);

    w.startRepeatingElement();
    for (Workspace workspace : workspaces) {
      workspace.generate(w, extProfile);
    }
    w.endRepeatingElement();

    generateExtensions(w, extProfile);
    
    w.endElement(Namespaces.atomPubNs, "service");
  }


  /**
   * Parses XML from a Reader.
   *
   * @param   extProfile
   *            extension profile
   *
   * @param   reader
   *            XML reader
   *
   * @throws   IOException
   *
   * @throws   ParseException
   */
  public void parse(ExtensionProfile extProfile, Reader reader)
      throws IOException, ParseException {

    new XmlParser().parse(reader, new Handler(extProfile),
                          Namespaces.atomPub, "service");
  }


  /**
   * Parses XML from an InputStream.
   *
   * @param   extProfile
   *            extension profile
   *
   * @param   inputStream
   *            XML input stream
   *
   * @throws   IOException
   *
   * @throws   ParseException
   */
  public void parse(ExtensionProfile extProfile, InputStream inputStream)
      throws IOException, ParseException {

    new XmlParser().parse(inputStream, new Handler(extProfile),
                          Namespaces.atomPub, "service");
  }



  /*
   * XmlParser ElementHandler for {@code app:service}
   */
  public class Handler extends ExtensionPoint.ExtensionHandler {


    public Handler(ExtensionProfile extProfile) throws IOException {
      super(extProfile, ServiceDocument.class);
    }


    public XmlParser.ElementHandler getChildHandler(String namespace,
                                                    String localName,
                                                    Attributes attrs)
        throws ParseException, IOException {

      if (namespace.equals(Namespaces.atomPub)) {

        if (localName.equals("workspace")) {

          String title = attrs.getValue("", "title");
          if (title == null) {
            throw new ParseException("Title missing for app:workspace element");
          }
          Workspace workspace = new Workspace(title);
          workspaces.add(workspace);
          return workspace.new Handler(extProfile);

        } else {
          throw new ParseException("Unrecognized element: " +
                                   "namespace: " + namespace + 
                                   ",localName: " + localName);
        }
      } else {

        return super.getChildHandler(namespace, localName, attrs);

      }
    }
  }

  public void processEndElement() throws ParseException {
    if (workspaces.size() == 0) {
      throw new ParseException("Service must contain at least one workspace");
    }
  }
}