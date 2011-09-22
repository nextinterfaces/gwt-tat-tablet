/*
 * Copyright 2011 Vancouver Ywebb Consulting Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package next.tablet.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import next.tablet.client.server.LsService;
import next.tablet.shared.LsProps;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Sample server side implementation of a RPC service.
 */
@SuppressWarnings("serial")
public class LsServiceImpl extends RemoteServiceServlet implements LsService {

  static String FILE_PATH = "C:/ls.txt";

  public LsProps getProps() throws IllegalArgumentException {

    return getLsProps(new File(FILE_PATH));
  }

  public static LsProps getLsProps(File aFile) {

    LsProps lsProps = new LsProps();

    try {
      BufferedReader input = new BufferedReader(new FileReader(aFile));
      try {
        String line = null;
        while ((line = input.readLine()) != null) {
          if (line == null) {
            throw new IllegalArgumentException("Unsupported live sport text file");
          }
          setState(line, "1", lsProps);
          setState(line, "2", lsProps);
          setState(line, "3", lsProps);
          setState(line, "4", lsProps);
          setState(line, "5", lsProps);
        }
      } finally {
        input.close();
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    }

    return lsProps;
  }

  private static void setState(String line, String id, LsProps lsProps) {
    if (line.startsWith(id)) {

      if (line.contains("s")) {
        lsProps.setProp(id, "s"); // .setState(PropState.STARTED);

      } else if (line.contains("e")) {
        lsProps.setProp(id, "e"); // setState(PropState.ENDED);

      }
      // else {
      // lsProps.setProp(id, "s"); //setState(PropState.WAITING);
      //
      // }
    }
  }

  public static void main(String[] args) {
    LsProps lsProps = getLsProps(new File(FILE_PATH));
    System.out.println(lsProps);
  }

}
