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
import next.tablet.client.ls.event.ShowAccountEditHandler;
import next.tablet.client.ls.event.ShowFacebookOptionEvent;
import next.tablet.client.ls.event.ShowFacebookOptionHandler;
import next.tablet.client.ls.event.ShowLocationOptionEvent;
import next.tablet.client.ls.event.ShowLocationOptionHandler;
import next.tablet.client.ls.event.ShowTwitterEvent;
import next.tablet.client.ls.event.ShowTwitterHandler;
import next.tablet.client.ls.event.ShowWalletTopupEvent;
import next.tablet.client.ls.event.ShowWalletTopupHandler;
import next.tablet.client.ui.CUtil;
import next.tablet.client.ui.GlobalResources;
import next.tablet.client.ui.di.TabletEventBus;

import org.adamtacy.client.ui.effects.core.NMorphStyle;
import org.adamtacy.client.ui.effects.events.EffectCompletedEvent;
import org.adamtacy.client.ui.effects.events.EffectCompletedHandler;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;

public class ImagePage extends PopupPanel implements IPage {

  final static GlobalResources RES = GlobalResources.INSTANCE;
  private boolean isHidden = true;

  final Image content;

  public ImagePage(final TabletEventBus eventBus) {
    setStyleName("bCommunityPage");
    // setSize("1024px", "600px");
    // setPopupPosition(0, 0);
    setWidth("700px");

    this.getElement().getStyle().setOpacity(0);
    setPopupPosition(2000, 2000);
    show();

    content = new Image();

    setWidget(content);

    eventBus.addHandler(ShowFacebookOptionEvent.TYPE, new ShowFacebookOptionHandler() {
      @Override
      public void onExecute(final ShowFacebookOptionEvent e) {
        Timer t = new Timer() {
          public void run() {
            if (e.getType() == ShowFacebookOptionEvent.Type.PROFILE) {
              setResource(RES.iProfile());

            } else if (e.getType() == ShowFacebookOptionEvent.Type.FRIENDS) {
              setResource(RES.iFriends());

            } else if (e.getType() == ShowFacebookOptionEvent.Type.NEWS_FEED) {
              setResource(RES.iNewsFeed());

            } else if (e.getType() == ShowFacebookOptionEvent.Type.GAMES) {
              setResource(RES.iGames());
            }
            doShow();
          }
        };
        t.schedule(500);
      }
    });

    eventBus.addHandler(ShowLocationOptionEvent.TYPE, new ShowLocationOptionHandler() {
      @Override
      public void onExecute(final ShowLocationOptionEvent e) {
        Timer t = new Timer() {
          public void run() {
            if (e.getType() == ShowLocationOptionEvent.Type.PROMOTIONS) {
              setResource(RES.iPromotions());

            } else if (e.getType() == ShowLocationOptionEvent.Type.TOUR) {
              setResource(RES.iTour());

            } else if (e.getType() == ShowLocationOptionEvent.Type.RESPONSIVE) {
              setResource(RES.iResponsive());

            } else if (e.getType() == ShowLocationOptionEvent.Type.CONTACT) {
              setResource(RES.iContact());

            } else if (e.getType() == ShowLocationOptionEvent.Type.FAQ) {
              setResource(RES.iFaq());
            }
            doShow();
          }
        };
        t.schedule(500);
      }
    });

    eventBus.addHandler(ShowAccountEditEvent.TYPE, new ShowAccountEditHandler() {
      @Override
      public void onExecute(final ShowAccountEditEvent e) {
        Timer t = new Timer() {
          public void run() {
            setResource(RES._accountEdit());
            doShow();
          }
        };
        t.schedule(500);
      }
    });

    eventBus.addHandler(ShowTwitterEvent.TYPE, new ShowTwitterHandler() {
      @Override
      public void onExecute(final ShowTwitterEvent e) {
        Timer t = new Timer() {
          public void run() {
            setResource(RES._favoritesTwitter());
            doShow();
          }
        };
        t.schedule(500);
      }
    });

    eventBus.addHandler(ShowWalletTopupEvent.TYPE, new ShowWalletTopupHandler() {
      @Override
      public void onExecute(final ShowWalletTopupEvent e) {
        Timer t = new Timer() {
          public void run() {
            setResource(RES.iWalletTopup());
            doShow();
          }
        };
        t.schedule(500);
      }
    });
  }

  private void setResource(ImageResource res) {
    content.setResource(res);
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
