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

import java.util.ArrayList;

import next.tablet.client.ls.event.ShowAccountEditEvent;
import next.tablet.client.ls.event.ShowAccountEditHandler;
import next.tablet.client.ls.event.ShowFacebookOptionEvent;
import next.tablet.client.ls.event.ShowFacebookOptionHandler;
import next.tablet.client.ls.event.ShowLocationOptionEvent;
import next.tablet.client.ls.event.ShowLocationOptionHandler;
import next.tablet.client.ls.event.ShowMenuOptionEvent;
import next.tablet.client.ls.event.ShowTwitterEvent;
import next.tablet.client.ls.event.ShowTwitterHandler;
import next.tablet.client.ls.event.ShowWalletTopupEvent;
import next.tablet.client.ls.event.ShowWalletTopupHandler;
import next.tablet.client.ls.event.ShowMenuOptionEvent.Type;
import next.tablet.client.ui.CUtil;
import next.tablet.client.ui.di.TabletEventBus;
import next.tablet.client.ui.di.UiGinjector;
import next.tablet.client.ui.di.UiSingleton;
import next.tablet.common.client.ui.ImageButton;

import org.adamtacy.client.ui.effects.core.NMorphStyle;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.PopupPanel;

public abstract class LeftMenu extends PopupPanel {

  final ArrayList<ImageButton> buttons = new ArrayList<ImageButton>();
  private final static UiSingleton CTX = UiGinjector.INSTANCE.getUiSingleton();

  private TabletEventBus eventBus;

  public LeftMenu(final TabletEventBus eventBus) {
    this.eventBus = eventBus;
    setStyleName("bLeftMenu");
    setWidth("300px");
    setPopupPosition(0, 100);

    listenEvents();
  }

  public void addButton(ImageButton button) {
    buttons.add(button);
  }

  abstract Type getType();

  public void slideOutButton(final ImageButton btn, final Command beforeCmd, final Command afterCmd,
      final Type actionType) {

    btn.addMouseDownHandler(new MouseDownHandler() {
      @Override
      public void onMouseDown(MouseDownEvent event) {

        if (beforeCmd != null) {
          beforeCmd.execute();
        }
        CTX.getPageManager().hidePages();
        eventBus.fireEvent(new ShowMenuOptionEvent(actionType));

        // String top = btn.getElement().getStyle().getProperty("top");
        // int abstop = btn.getAbsoluteTop();
        // abstop = abstop - favorites.getOffsetHeight();

        NMorphStyle eff = CUtil.moveHorizEffect(0, -btn.getOffsetWidth(), btn.getElement(), afterCmd, 100, 50);
        eff.play();
        btn.setEnabled(false);
      }
    });
  }

  private void listenEvents() {
    // eventBus.addHandler(ShowBackButtonEvent.TYPE, new ShowBackButtonHandler() {
    // @Override
    // public void onExecute(final ShowBackButtonEvent e) {
    // if (e.getType() == ShowBackButtonEvent.Type.COMMUNITY) {
    // resetButtons(null); // all buttons
    //          
    // } else {
    // resetButtons(null); // all buttons
    // }
    // }
    // });

    eventBus.addHandler(ShowFacebookOptionEvent.TYPE, new ShowFacebookOptionHandler() {
      @Override
      public void onExecute(final ShowFacebookOptionEvent e) {
        clearLeftMenuRandom();
      }
    });

    eventBus.addHandler(ShowAccountEditEvent.TYPE, new ShowAccountEditHandler() {
      @Override
      public void onExecute(final ShowAccountEditEvent e) {
        clearLeftMenuRandom();
      }
    });

    eventBus.addHandler(ShowWalletTopupEvent.TYPE, new ShowWalletTopupHandler() {
      @Override
      public void onExecute(final ShowWalletTopupEvent e) {
        clearLeftMenuRandom();
      }
    });

    eventBus.addHandler(ShowTwitterEvent.TYPE, new ShowTwitterHandler() {
      @Override
      public void onExecute(final ShowTwitterEvent e) {
        clearLeftMenuRandom();
      }
    });

    eventBus.addHandler(ShowLocationOptionEvent.TYPE, new ShowLocationOptionHandler() {
      @Override
      public void onExecute(final ShowLocationOptionEvent e) {
        clearLeftMenuRandom();
      }
    });
  }

  private void clearLeftMenuRandom() {
    for (final ImageButton btn : buttons) {
      int timeout = Random.nextInt(1000);
      Timer t = new Timer() {
        public void run() {
          btn.setEnabled(false);
          NMorphStyle eff = CUtil.moveHorizEffect(0, -btn.getOffsetWidth(), btn.getElement(), null, 100, 0);
          eff.play();
        }
      };
      t.schedule(timeout);
    }
  }

  public void resetButtons(final ImageButton selectedBtn) {
    for (final ImageButton btn : buttons) {
      if (!btn.equals(selectedBtn) && !btn.isEnabled()) {

        int timeout = Random.nextInt(1000);
        Timer t = new Timer() {
          public void run() {
            NMorphStyle eff = CUtil.moveHorizEffect(-btn.getOffsetWidth(), 0, btn.getElement(), new Command() {
              @Override
              public void execute() {
                btn.setEnabled(true);
              }
            }, 50, 100);
            eff.play();
          }
        };
        t.schedule(timeout);

      }
    }
  }

}
