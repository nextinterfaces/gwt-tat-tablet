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

public class Question implements Serializable {

  private static final long serialVersionUID = -1516119323775635375L;

  // Who will get first penalty?
  // Will Vancouver score during this penalty?
  // Who will get the next penalty?
  // Will there be another goal before end of period 1?
  // Who will score the next goal?

  public enum Result {
    YES, NO
  }

  private Integer   id;
  private String    text;
  private Result    result;
  private PropState state;

  public Question(Integer id, String text) {
    this.text = text;
  }

  public Integer getId() {
    return id;
  }

  public String getText() {
    return text;
  }

  public Result getResult() {
    return result;
  }

  public void setResult(Result result) {
    this.result = result;
  }

  public PropState getState() {
    return state;
  }

  public void setState(PropState state) {
    this.state = state;
  }

  @Override
  public String toString() {
    return "Question{" + id + ", " + state + ", " + result + "}";
  }

}
