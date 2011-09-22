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
package next.tablet.client.black;

import next.tablet.client.ls.event.ShowFacebookOptionEvent;
import next.tablet.client.ls.event.ShowFacebookOptionHandler;
import next.tablet.client.ui.GlobalResources;
import next.tablet.client.ui.di.TabletEventBus;
import next.tablet.client.ui.di.UiGinjector;
import next.tablet.client.ui.di.UiSingleton;

public class PageMediator {

  final GlobalResources RES = GlobalResources.INSTANCE;
  final UiSingleton CTX = UiGinjector.INSTANCE.getUiSingleton();

  public PageMediator(TabletEventBus eventBus) {

    eventBus.addHandler(ShowFacebookOptionEvent.TYPE, new ShowFacebookOptionHandler() {
      @Override
      public void onExecute(ShowFacebookOptionEvent e) {
//        CTX.getTopMenu().
      }
    });

  }

}
