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
package next.tablet.shared;

import java.io.Serializable;
import java.util.HashMap;

public class LsProps implements Serializable {

  private static final long       serialVersionUID = -7075168525593610690L;

  private HashMap<String, String> props            = new HashMap<String, String>();

  // Who will get first penalty?
  // Will Vancouver score during this penalty?
  // Who will get the next penalty?
  // Will there be another goal before end of period 1?
  // Who will score the next goal?
  public LsProps() {
    props.put("1", null);
    props.put("2", null);
    props.put("3", null);
    props.put("4", null);
    props.put("5", null);
  }

  public HashMap<String, String> getProps() {
    return props;
  }

  public void setProp(String id, String value) {
    props.put(id, value);
  }

  public String getProp(String id) {
    return props.get(id);
  }

  @Override
  public String toString() {
    return "LsProps{" + props + "}";
  }

}
