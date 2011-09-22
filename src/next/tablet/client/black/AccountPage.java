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

import next.tablet.client.ls.event.ShowAccountEditEvent;
import next.tablet.client.ui.CUtil;
import next.tablet.client.ui.GlobalResources;
import next.tablet.client.ui.di.TabletEventBus;

import org.adamtacy.client.ui.effects.core.NMorphStyle;
import org.adamtacy.client.ui.effects.events.EffectCompletedEvent;
import org.adamtacy.client.ui.effects.events.EffectCompletedHandler;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;

public class AccountPage extends PopupPanel implements IPage {

  final static GlobalResources RES = GlobalResources.INSTANCE;
  private boolean isHidden = true;

  public AccountPage(final TabletEventBus eventBus) {
    setStyleName("bAccountPage");
    setWidth("700px");

    this.getElement().getStyle().setOpacity(0);
    setPopupPosition(336, 88);
    show();

    Image accImg = new Image(RES._account());
    setWidget(accImg);

    accImg.addMouseDownHandler(new MouseDownHandler() {
      @Override
      public void onMouseDown(MouseDownEvent event) {
        doHide();
        eventBus.fireEvent(new ShowAccountEditEvent());
      }
    });
  }

  @Override
  public void doHide() {
    if (isHidden) {
      return;
    }
    NMorphStyle eff = CUtil.opacityEffect(this.getElement(), null, 100, 0);
    eff.addEffectCompletedHandler(new EffectCompletedHandler() {
      @Override
      public void onEffectCompleted(EffectCompletedEvent event) {
        setPopupPosition(1025, 0);
      }
    });
    eff.play();

    isHidden = true;
  }

  @Override
  public void doShow() {
    if (!isHidden) {
      return;
    }
    this.getElement().getStyle().setOpacity(0);
    setPopupPosition(336, 88);

    NMorphStyle eff = CUtil.opacityEffect(this.getElement(), null, 0, 100);
    eff.play();

    isHidden = false;
  }

}
