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
import next.tablet.client.ls.event.ShowBackButtonEvent;
import next.tablet.client.ls.event.ShowFacebookOptionEvent;
import next.tablet.client.ls.event.ShowFacebookOptionHandler;
import next.tablet.client.ls.event.ShowLocationOptionEvent;
import next.tablet.client.ls.event.ShowLocationOptionHandler;
import next.tablet.client.ls.event.ShowMenuOptionEvent;
import next.tablet.client.ls.event.ShowMenuOptionHandler;
import next.tablet.client.ls.event.ShowTwitterEvent;
import next.tablet.client.ls.event.ShowTwitterHandler;
import next.tablet.client.ls.event.ShowWalletTopupEvent;
import next.tablet.client.ls.event.ShowWalletTopupHandler;
import next.tablet.client.ls.event.ShowBackButtonEvent.Type;
import next.tablet.client.ui.CUtil;
import next.tablet.client.ui.GlobalResources;
import next.tablet.client.ui.di.TabletEventBus;
import next.tablet.client.ui.di.UiGinjector;
import next.tablet.client.ui.di.UiSingleton;
import next.tablet.common.client.ui.ImageButton;

import org.adamtacy.client.ui.effects.core.NMorphStyle;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.PopupPanel;

public class TopMenu extends PopupPanel {

  final static GlobalResources RES = GlobalResources.INSTANCE;

  private final static UiSingleton CTX = UiGinjector.INSTANCE.getUiSingleton();

  private TabletEventBus eventBus;

  private static int TITLE_POSITION = 0;

  final ImageButton hBackImg;
  final ImageButton hBackTitle;
  final ImageButton hTitle;

  enum BackBtnState {
    ENABLED, DISABLED
  }

  private Command backCommand;

  private BackBtnState backBtnState = BackBtnState.ENABLED;

  public TopMenu(final TabletEventBus eventBus) {
    this.eventBus = eventBus;
    setStyleName("bTopMenu");
    setSize("1024px", "70px");
    setPopupPosition(0, 0);

    FlexTable content = new FlexTable();
    content.setStyleName("content");
    setWidget(content);

    hBackImg = new ImageButton(RES.hBackImg());
    hBackTitle = new ImageButton(RES.hBackGames());
    hTitle = new ImageButton(RES.hAccount());

    registerBackEvent();

    content.setWidget(0, 0, hBackImg);
    content.setWidget(0, 1, hBackTitle);
    content.setWidget(0, 2, hTitle);

    content.getCellFormatter().setWidth(0, 0, "80px");
    content.getCellFormatter().setWidth(0, 1, "320px");
    content.getCellFormatter().setWidth(0, 2, "624px");

    content.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
    content.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_LEFT);
    content.getCellFormatter().setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_RIGHT);

    setBackCommand(getBack2GamesCmd());

    listenEvents();
  }

  public Command getBackCommand() {
    return backCommand;
  }

  public void setBackCommand(Command backCommand) {
    this.backCommand = backCommand;
  }

  private void listenEvents() {
    // eventBus.addHandler(ShowBackButtonEvent.TYPE, new ShowBackButtonHandler() {
    // @Override
    // public void onExecute(final ShowBackButtonEvent e) {
    // if (e.getType() == ShowBackButtonEvent.Type.GAMES) {
    // hBackTitle.setResource(RES.hGames());
    // }
    // }
    // });

    eventBus.addHandler(ShowMenuOptionEvent.TYPE, new ShowMenuOptionHandler() {
      @Override
      public void onExecute(final ShowMenuOptionEvent e) {
        setBackCommand(getBack2GamesCmd());

        Command cmd = new Command() {
          @Override
          public void execute() {

            // Window.alert("before slideOutButton");

            slideOutButton(hTitle, null, new Command() {
              @Override
              public void execute() {

                // Window.alert("after slideOutButton");

                if (getBackBtnState() == BackBtnState.DISABLED) {
                  setBackBtnState(BackBtnState.ENABLED);
                  hBackTitle.setResource(RES.hBackGames());
                  hBackImg.setResource(RES.hBackImg());

                  CUtil.moveHorizEffect(-hBackImg.getOffsetWidth(), 0, hBackImg.getElement(), null, 30, 100).play();
                  CUtil.moveHorizEffect(-hBackImg.getOffsetWidth() - hBackTitle.getOffsetWidth(), 0,
                      hBackTitle.getElement(), null, 30, 100).play();
                }

                if (e.getType() == ShowMenuOptionEvent.Type.WALLET) {
                  hTitle.setResource(RES.hBalance());
                  CTX.getWalletPage().doShow();

                } else if (e.getType() == ShowMenuOptionEvent.Type.FACEBOOK) {
                  hTitle.setResource(RES.hFacebook());
                  CTX.getFacebookPage().doShow();

                } else if (e.getType() == ShowMenuOptionEvent.Type.ACCOUNT) {
                  hTitle.setResource(RES.hAccount());
                  CTX.getAccountPage().doShow();

                } else if (e.getType() == ShowMenuOptionEvent.Type.TWITTER) {
                  hTitle.setResource(RES.hFavorites());
                  CTX.getTwitterPage().doShow();

                } else if (e.getType() == ShowMenuOptionEvent.Type.LOCATION) {
                  hTitle.setResource(RES.hLocation());
                  CTX.getLocationPage().doShow();
                }

                slideInButton(hTitle, null, null);

              }
            });
          }
        };
        CTX.getGameTopMenu().doHide(cmd);

      }
    });

    eventBus.addHandler(ShowFacebookOptionEvent.TYPE, new ShowFacebookOptionHandler() {
      @Override
      public void onExecute(final ShowFacebookOptionEvent e) {

        setBackCommand(getBack2CommunityCmd());

        Command cmd = new Command() {
          @Override
          public void execute() {
            slideOutButton(hTitle, null, new Command() {
              @Override
              public void execute() {
                if (e.getType() == ShowFacebookOptionEvent.Type.PROFILE) {
                  hTitle.setResource(RES.hProfile());

                } else if (e.getType() == ShowFacebookOptionEvent.Type.FRIENDS) {
                  hTitle.setResource(RES.hFriends());

                } else if (e.getType() == ShowFacebookOptionEvent.Type.NEWS_FEED) {
                  hTitle.setResource(RES.hNewsFeed());

                } else if (e.getType() == ShowFacebookOptionEvent.Type.GAMES) {
                  hTitle.setResource(RES.hGames());

                }

                slideInButton(hTitle, null, null);
              }
            });
          }
        };

        // if (getBackBtnState() == BackBtnState.DISABLED) {
        setBackBtnState(BackBtnState.ENABLED);
        hBackTitle.setResource(RES.hBackComminuty());
        hBackImg.setResource(RES.hBackImg());

        CUtil.moveHorizEffect(-hBackImg.getOffsetWidth(), 0, hBackImg.getElement(), null, 30, 100).play();
        CUtil.moveHorizEffect(-hBackImg.getOffsetWidth() - hBackTitle.getOffsetWidth(), 0, hBackTitle.getElement(),
            cmd, 30, 100).play();
        // }
      }
    });

    eventBus.addHandler(ShowLocationOptionEvent.TYPE, new ShowLocationOptionHandler() {
      @Override
      public void onExecute(final ShowLocationOptionEvent e) {

        setBackCommand(getBack2BclcCmd());

        Command cmd = new Command() {
          @Override
          public void execute() {
            slideOutButton(hTitle, null, new Command() {
              @Override
              public void execute() {
                if (e.getType() == ShowLocationOptionEvent.Type.PROMOTIONS) {
                  hTitle.setResource(RES.hPromotions());

                } else if (e.getType() == ShowLocationOptionEvent.Type.TOUR) {
                  hTitle.setResource(RES.hTour());

                } else if (e.getType() == ShowLocationOptionEvent.Type.RESPONSIVE) {
                  hTitle.setResource(RES.hResponsive());

                } else if (e.getType() == ShowLocationOptionEvent.Type.CONTACT) {
                  hTitle.setResource(RES.hContact());

                } else if (e.getType() == ShowLocationOptionEvent.Type.FAQ) {
                  hTitle.setResource(RES.hFaq());

                }

                slideInButton(hTitle, null, null);
              }
            });
          }
        };

        setBackBtnState(BackBtnState.ENABLED);
        hBackTitle.setResource(RES.hBackLocation());
        hBackImg.setResource(RES.hBackImg());

        CUtil.moveHorizEffect(-hBackImg.getOffsetWidth(), 0, hBackImg.getElement(), null, 30, 100).play();
        CUtil.moveHorizEffect(-hBackImg.getOffsetWidth() - hBackTitle.getOffsetWidth(), 0, hBackTitle.getElement(),
            cmd, 30, 100).play();
      }
    });

    eventBus.addHandler(ShowAccountEditEvent.TYPE, new ShowAccountEditHandler() {
      @Override
      public void onExecute(final ShowAccountEditEvent e) {

        setBackCommand(getBack2Account());

        Command cmd = new Command() {
          @Override
          public void execute() {
            slideOutButton(hTitle, null, new Command() {
              @Override
              public void execute() {
                hTitle.setResource(RES.hAccountEdit());

                slideInButton(hTitle, null, null);
              }
            });
          }
        };

        // if (getBackBtnState() == BackBtnState.DISABLED) {
        setBackBtnState(BackBtnState.ENABLED);
        hBackTitle.setResource(RES.hBackAccount());
        hBackImg.setResource(RES.hBackImg());

        CUtil.moveHorizEffect(-hBackImg.getOffsetWidth(), 0, hBackImg.getElement(), null, 30, 100).play();
        CUtil.moveHorizEffect(-hBackImg.getOffsetWidth() - hBackTitle.getOffsetWidth(), 0, hBackTitle.getElement(),
            cmd, 30, 100).play();
        // }
      }
    });

    eventBus.addHandler(ShowTwitterEvent.TYPE, new ShowTwitterHandler() {
      @Override
      public void onExecute(final ShowTwitterEvent e) {

        setBackCommand(getBack2Favorites());

        Command cmd = new Command() {
          @Override
          public void execute() {
            slideOutButton(hTitle, null, new Command() {
              @Override
              public void execute() {
                hTitle.setResource(RES.hTwitter2());

                slideInButton(hTitle, null, null);
              }
            });
          }
        };

        setBackBtnState(BackBtnState.ENABLED);
        hBackTitle.setResource(RES.hBackFavorites());
        hBackImg.setResource(RES.hBackImg());

        CUtil.moveHorizEffect(-hBackImg.getOffsetWidth(), 0, hBackImg.getElement(), null, 30, 100).play();
        CUtil.moveHorizEffect(-hBackImg.getOffsetWidth() - hBackTitle.getOffsetWidth(), 0, hBackTitle.getElement(),
            cmd, 30, 100).play();
      }
    });

    eventBus.addHandler(ShowWalletTopupEvent.TYPE, new ShowWalletTopupHandler() {
      @Override
      public void onExecute(final ShowWalletTopupEvent e) {

        setBackCommand(getBack2Wallet());

        Command cmd = new Command() {
          @Override
          public void execute() {
            slideOutButton(hTitle, null, new Command() {
              @Override
              public void execute() {
                hTitle.setResource(RES.hWalletTopup());

                slideInButton(hTitle, null, null);
              }
            });
          }
        };

        // if (getBackBtnState() == BackBtnState.DISABLED) {
        setBackBtnState(BackBtnState.ENABLED);
        hBackTitle.setResource(RES.hBackWallet());
        hBackImg.setResource(RES.hBackImg());

        CUtil.moveHorizEffect(-hBackImg.getOffsetWidth(), 0, hBackImg.getElement(), null, 30, 100).play();
        CUtil.moveHorizEffect(-hBackImg.getOffsetWidth() - hBackTitle.getOffsetWidth(), 0, hBackTitle.getElement(),
            cmd, 30, 100).play();
        // }
      }
    });
  }

  public BackBtnState getBackBtnState() {
    return backBtnState;
  }

  public void setBackBtnState(BackBtnState backBtnState) {
    this.backBtnState = backBtnState;
  }

  private void registerBackEvent() {
    MouseDownHandler handler = new MouseDownHandler() {
      @Override
      public void onMouseDown(MouseDownEvent event) {
        Command cm = getBackCommand();
        if (cm != null) {
          cm.execute();
        }
      }
    };
    hBackImg.addMouseDownHandler(handler);
    hBackTitle.addMouseDownHandler(handler);
  }

  private Command getBack2GamesCmd() {
    Command cmd = new Command() {
      @Override
      public void execute() {
        CTX.getPageManager().hidePages();

        if (getBackBtnState() != BackBtnState.ENABLED) {
          return;
        }
        eventBus.fireEvent(new ShowBackButtonEvent(Type.GAMES));

        Command afterBackCmd = new Command() {
          @Override
          public void execute() {
            CUtil.moveHorizEffect(-hBackImg.getOffsetWidth() - hBackTitle.getOffsetWidth(), -80,
                hBackTitle.getElement(), null, 30, 100).play();
          }
        };
        CUtil.moveHorizEffect(0, -hBackImg.getOffsetWidth(), hBackImg.getElement(), null, 100, 30).play();
        CUtil.moveHorizEffect(0, -hBackImg.getOffsetWidth() - hBackTitle.getOffsetWidth(), hBackTitle.getElement(),
            afterBackCmd, 100, 30).play();

        setBackBtnState(BackBtnState.DISABLED);
        setBackCommand(null);

        slideOutButton(hTitle, null, null);
      }
    };

    return cmd;
  }

  private Command getBack2CommunityCmd() {
    Command cmd = new Command() {
      @Override
      public void execute() {

        CTX.getPageManager().hidePages();
        eventBus.fireEvent(new ShowMenuOptionEvent(ShowMenuOptionEvent.Type.FACEBOOK));

        eventBus.fireEvent(new ShowBackButtonEvent(Type.COMMUNITY));

        Command afterCmd = new Command() {
          @Override
          public void execute() {
            hBackTitle.setResource(RES.hBackGames());
            CUtil.moveHorizEffect(-hBackImg.getOffsetWidth() - hBackTitle.getOffsetWidth(), 0, hBackTitle.getElement(),
                null, 30, 100).play();
          }
        };
        CUtil.moveHorizEffect(0, -hBackImg.getOffsetWidth() - hBackTitle.getOffsetWidth(), hBackTitle.getElement(),
            afterCmd, 100, 30).play();

        setBackBtnState(BackBtnState.ENABLED);
        setBackCommand(getBack2GamesCmd());
      }
    };

    return cmd;
  }

  private Command getBack2BclcCmd() {
    Command cmd = new Command() {
      @Override
      public void execute() {

        CTX.getPageManager().hidePages();
        eventBus.fireEvent(new ShowMenuOptionEvent(ShowMenuOptionEvent.Type.LOCATION));

        eventBus.fireEvent(new ShowBackButtonEvent(Type.BCLC));

        Command afterCmd = new Command() {
          @Override
          public void execute() {
            hBackTitle.setResource(RES.hBackGames());
            CUtil.moveHorizEffect(-hBackImg.getOffsetWidth() - hBackTitle.getOffsetWidth(), 0, hBackTitle.getElement(),
                null, 30, 100).play();
          }
        };
        CUtil.moveHorizEffect(0, -hBackImg.getOffsetWidth() - hBackTitle.getOffsetWidth(), hBackTitle.getElement(),
            afterCmd, 100, 30).play();

        setBackBtnState(BackBtnState.ENABLED);
        setBackCommand(getBack2GamesCmd());
      }
    };

    return cmd;
  }

  private Command getBack2Account() {
    Command cmd = new Command() {
      @Override
      public void execute() {

        CTX.getPageManager().hidePages();
        eventBus.fireEvent(new ShowMenuOptionEvent(ShowMenuOptionEvent.Type.ACCOUNT));

        eventBus.fireEvent(new ShowBackButtonEvent(Type.ACCOUNT));

        Command afterCmd = new Command() {
          @Override
          public void execute() {
            hBackTitle.setResource(RES.hBackGames());
            CUtil.moveHorizEffect(-hBackImg.getOffsetWidth() - hBackTitle.getOffsetWidth(), 0, hBackTitle.getElement(),
                null, 30, 100).play();
          }
        };
        CUtil.moveHorizEffect(0, -hBackImg.getOffsetWidth() - hBackTitle.getOffsetWidth(), hBackTitle.getElement(),
            afterCmd, 100, 30).play();

        setBackBtnState(BackBtnState.ENABLED);
        setBackCommand(getBack2GamesCmd());
      }
    };

    return cmd;
  }

  private Command getBack2Favorites() {
    Command cmd = new Command() {
      @Override
      public void execute() {

        CTX.getPageManager().hidePages();
        eventBus.fireEvent(new ShowMenuOptionEvent(ShowMenuOptionEvent.Type.TWITTER));

        eventBus.fireEvent(new ShowBackButtonEvent(Type.FAVORITES));

        Command afterCmd = new Command() {
          @Override
          public void execute() {
            hBackTitle.setResource(RES.hBackGames());
            CUtil.moveHorizEffect(-hBackImg.getOffsetWidth() - hBackTitle.getOffsetWidth(), 0, hBackTitle.getElement(),
                null, 30, 100).play();
          }
        };
        CUtil.moveHorizEffect(0, -hBackImg.getOffsetWidth() - hBackTitle.getOffsetWidth(), hBackTitle.getElement(),
            afterCmd, 100, 30).play();

        setBackBtnState(BackBtnState.ENABLED);
        setBackCommand(getBack2GamesCmd());
      }
    };

    return cmd;
  }

  private Command getBack2Wallet() {
    Command cmd = new Command() {
      @Override
      public void execute() {

        CTX.getPageManager().hidePages();
        eventBus.fireEvent(new ShowMenuOptionEvent(ShowMenuOptionEvent.Type.WALLET));
        eventBus.fireEvent(new ShowBackButtonEvent(Type.WALLET));

        Command afterCmd = new Command() {
          @Override
          public void execute() {
            hBackTitle.setResource(RES.hBackGames());
            CUtil.moveHorizEffect(-hBackImg.getOffsetWidth() - hBackTitle.getOffsetWidth(), 0, hBackTitle.getElement(),
                null, 30, 100).play();
          }
        };
        CUtil.moveHorizEffect(0, -hBackImg.getOffsetWidth() - hBackTitle.getOffsetWidth(), hBackTitle.getElement(),
            afterCmd, 100, 30).play();

        setBackBtnState(BackBtnState.ENABLED);
        setBackCommand(getBack2GamesCmd());
      }
    };

    return cmd;
  }

  // public void executeBackBtn() {
  // if (getBackBtnState() != BackBtnState.ENABLED) {
  // return;
  // }
  // getBackCommand().execute();
  // // eventBus.fireEvent(new ShowBackButtonEvent(Type.GAMES));
  //
  // Command afterBackCmd = new Command() {
  // @Override
  // public void execute() {
  // CUtil.moveHorizEffect(-hBackImg.getOffsetWidth() - hBackTitle.getOffsetWidth(), -80, hBackTitle.getElement(),
  // null, 30, 100).play();
  // }
  // };
  // CUtil.moveHorizEffect(0, -hBackImg.getOffsetWidth(), hBackImg.getElement(), null, 100, 30).play();
  // CUtil.moveHorizEffect(0, -hBackImg.getOffsetWidth() - hBackTitle.getOffsetWidth(), hBackTitle.getElement(),
  // afterBackCmd, 100, 30).play();
  //
  // setBackBtnState(BackBtnState.DISABLED);
  //
  // slideOutButton(hTitle, null, null);
  // }

  public void slideOutButton(final ImageButton btn, final Command beforeCmd, final Command afterCmd) {

    if (beforeCmd != null) {
      beforeCmd.execute();
    }

    NMorphStyle eff = CUtil.moveHorizEffect(btn.getAbsoluteLeft(), 1024, btn.getElement(), afterCmd, 100, 30);
    eff.play();
  }

  public void slideInButton(final ImageButton btn, final Command beforeCmd, final Command afterCmd) {

    NMorphStyle eff = CUtil.moveHorizEffect(1024, TITLE_POSITION, btn.getElement(), afterCmd, 30, 100);
    eff.play();
  }

}
