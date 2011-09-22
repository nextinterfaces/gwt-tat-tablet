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

import next.tablet.client.ls.event.ShowBackButtonEvent;
import next.tablet.client.ls.event.ShowBackButtonHandler;
import next.tablet.client.ls.event.ShowMenuOptionEvent.Type;
import next.tablet.client.ui.GlobalResources;
import next.tablet.client.ui.di.TabletEventBus;
import next.tablet.client.ui.di.UiGinjector;
import next.tablet.client.ui.di.UiSingleton;
import next.tablet.common.client.ui.ImageButton;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.FlowPanel;

public class LeftMenuImpl extends LeftMenu {

  final static GlobalResources RES = GlobalResources.INSTANCE;
  private final static UiSingleton CTX = UiGinjector.INSTANCE.getUiSingleton();

  private TabletEventBus eventBus;

  final ImageButton btnWallet;
  final ImageButton btnAcc;
  final ImageButton btnCommun;
  final ImageButton btnTwitter;
  final ImageButton btnAll;

  public LeftMenuImpl(final TabletEventBus eventBus) {
    super(eventBus);
    this.eventBus = eventBus;

    btnWallet = new ImageButton(RES.mWallet());
    btnAcc = new ImageButton(RES.mAccount());
    btnCommun = new ImageButton(RES.mFacebook());
    btnTwitter = new ImageButton(RES.mTwitter());
    btnAll = new ImageButton(RES.mLocation());

    FlowPanel panel = new FlowPanel();
    addButton(btnWallet);
    addButton(btnAcc);
    addButton(btnCommun);
    addButton(btnTwitter);
    addButton(btnAll);

    panel.add(btnWallet);
    panel.add(btnAcc);
    panel.add(btnCommun);
    panel.add(btnTwitter);
    panel.add(btnAll);

    setWidget(panel);

    listenEvents();

    slideOutButton(btnWallet, new Command() {
      @Override
      public void execute() {
        resetButtons(btnWallet);
      }
    }, null, Type.WALLET);

    slideOutButton(btnAcc, new Command() {
      @Override
      public void execute() {
        resetButtons(btnAcc);
      }
    }, null, Type.ACCOUNT);

    slideOutButton(btnCommun, new Command() {
      @Override
      public void execute() {
        resetButtons(btnCommun);
      }
    }, null, Type.FACEBOOK);

    slideOutButton(btnTwitter, new Command() {
      @Override
      public void execute() {
        resetButtons(btnTwitter);
      }
    }, null, Type.TWITTER);

    slideOutButton(btnAll, new Command() {
      @Override
      public void execute() {
        resetButtons(btnAll);
      }
    }, null, Type.LOCATION);

  }

  @Override
  Type getType() {
    return Type.WALLET;
  }

  private void listenEvents() {
    eventBus.addHandler(ShowBackButtonEvent.TYPE, new ShowBackButtonHandler() {
      @Override
      public void onExecute(final ShowBackButtonEvent e) {
        if (e.getType() == ShowBackButtonEvent.Type.COMMUNITY) {
          resetButtons(btnCommun);

        } else if (e.getType() == ShowBackButtonEvent.Type.ACCOUNT) {
          resetButtons(btnAcc);

        } else if (e.getType() == ShowBackButtonEvent.Type.WALLET) {
          resetButtons(btnWallet);

        } else if (e.getType() == ShowBackButtonEvent.Type.BCLC) {
          resetButtons(btnAll);

        } else if (e.getType() == ShowBackButtonEvent.Type.FAVORITES) {
          resetButtons(btnTwitter);

        } else if (e.getType() == ShowBackButtonEvent.Type.GAMES) {
          resetButtons(null); // all buttons
          CTX.getGamesPage().doShow();
          CTX.getGameTopMenu().doShow();

        } else {
          resetButtons(null); // all buttons
        }

      }
    });
  }

}
