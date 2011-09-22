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

import next.tablet.client.ls.event.ShowGameCategoryEvent;
import next.tablet.client.ls.event.ShowGameCategoryHandler;
import next.tablet.client.ui.CUtil;
import next.tablet.client.ui.GlobalResources;
import next.tablet.client.ui.di.TabletEventBus;

import org.adamtacy.client.ui.effects.core.NMorphStyle;
import org.adamtacy.client.ui.effects.events.EffectCompletedEvent;
import org.adamtacy.client.ui.effects.events.EffectCompletedHandler;
import org.adamtacy.client.ui.effects.impl.Fade;
import org.adamtacy.client.ui.effects.impl.NShow;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;

public class GamesPage extends PopupPanel implements IPage {

  final static GlobalResources RES = GlobalResources.INSTANCE;
  private boolean isHidden = true;
  private boolean isVideoHidden = true;

  final Image imgGame0;
  final Image imgGame1;
  final Image imgGame2;
  final Image imgGame3;
  final Image imgGame4;
  final Image imgTxt;
  final Image imgVideo;

  public GamesPage(final TabletEventBus eventBus) {
    setStyleName("bGamesPage");
    setWidth("700px");

    this.getElement().getStyle().setOpacity(0);
    setPopupPosition(336, 88);
    show();

    Image imgBg = new Image(RES.gamesBg());
    imgGame0 = new Image(RES.game0());
    imgGame1 = new Image(RES.game1());
    imgGame2 = new Image(RES.game2());
    imgGame3 = new Image(RES.game3());
    imgGame4 = new Image(RES.game4());
    imgTxt = new Image(RES.gameText());
    imgVideo = new Image(RES.gameVideo());
    Image imgCover = new Image(RES.gameCover());

    imgBg.setStyleName("gameBg");
    imgGame0.setStyleName("game0");
    imgGame1.setStyleName("game1");
    imgGame2.setStyleName("game2");
    imgGame3.setStyleName("game3");
    imgGame4.setStyleName("game4");
    imgTxt.setStyleName("gameTxt");
    imgCover.setStyleName("gameCover");
    imgVideo.setStyleName("gameVideo");

    HorizontalPanel imgHolder = new HorizontalPanel();
    imgHolder.add(imgBg);
    imgHolder.add(imgGame0);
    imgHolder.add(imgGame1);
    imgHolder.add(imgGame2);
    imgHolder.add(imgGame3);
    imgHolder.add(imgGame4);
    imgHolder.add(imgTxt);
    imgHolder.add(imgCover);
    imgHolder.add(imgVideo);

    imgTxt.getElement().getStyle().setPropertyPx("left", 0);
    imgTxt.getElement().getStyle().setPropertyPx("top", 0);
    imgTxt.getElement().getStyle().setOpacity(0);

    setWidget(imgHolder);

    imgTxt.addMouseDownHandler(new MouseDownHandler() {
      @Override
      public void onMouseDown(MouseDownEvent event) {
        showVideo();
      }
    });
    imgVideo.addMouseDownHandler(new MouseDownHandler() {
      @Override
      public void onMouseDown(MouseDownEvent event) {

        //Window.open("river.html", "_self", "");

        // HTML objectFrame = new HTML("<iframe scrolling='no' height='" + Window.getClientHeight()
        // + "' frameborder='0' width='100%' src='v.htmlideo1' border='0' style='overflow: hidden;'></iframe>");
        // // Object appears with Scrollbar while iframe this is removed.
        // // "<object type='text/html' data='_river.html' width='100%' height='"
        // // + Window.getClientWidth() + "'></object>");
        //
        // objectFrame.setStyleName("b_gameFrame");
        // objectFrame.getElement().getStyle().setProperty("opacity", "0");
        // RootPanel.get("root").add(objectFrame);
        // NShow eShow = new NShow(objectFrame.getElement());
        // eShow.setDuration(0.3);
        // eShow.play();
      }
    });

    NMorphStyle eff = CUtil.moveHorizEffectAbs(-336, -336, imgCover.getElement(), null, 100, 100, 0.1);
    eff.play();

    eventBus.addHandler(ShowGameCategoryEvent.TYPE, new ShowGameCategoryHandler() {
      @Override
      public void onExecute(ShowGameCategoryEvent e) {

        Command slideInCmd = new Command() {
          @Override
          public void execute() {
            slideInImages(0);
          }
        };

        if (e.getType() == ShowGameCategoryEvent.Type.SOCIAL) {
          slideOutImages(slideInCmd, 0);

        } else if (e.getType() == ShowGameCategoryEvent.Type.SPORT) {
          slideOutImages(slideInCmd, 0);

        } else if (e.getType() == ShowGameCategoryEvent.Type.INTERACTIVE) {
          slideOutImages(slideInCmd, 0);

        }
      }
    });
  }

  private void showVideo() {
    if (!isVideoHidden) {
      return;
    }
    imgVideo.getElement().getStyle().setOpacity(0);
    Command cmd = new Command() {
      @Override
      public void execute() {
        NShow eff = new NShow(imgVideo.getElement());
        eff.setDuration(0.3);
        eff.play();
      }
    };
    NMorphStyle eff2 = CUtil.moveHorizEffectAbs(0, 0, imgVideo.getElement(), cmd, 0, 0, 0.1);
    eff2.play();

    isVideoHidden = false;
  }

  private void hideVideo() {
    if (isVideoHidden) {
      return;
    }

    Fade eff = new Fade(imgVideo.getElement());
    eff.addEffectCompletedHandler(new EffectCompletedHandler() {
      @Override
      public void onEffectCompleted(EffectCompletedEvent event) {
        imgVideo.getElement().getStyle().setPropertyPx("left", 1024);
      }
    });
    eff.setDuration(0.1);
    eff.play();

    isVideoHidden = true;
  }

  @Override
  public void doHide() {
    if (isHidden) {
      return;
    }

    // slideOutImages(new Command() {
    // @Override
    // public void execute() {
    // NMorphStyle eff = CUtil.opacityEffect(GamesPage.this.getElement(), null, 100, 0);
    // eff.addEffectCompletedHandler(new EffectCompletedHandler() {
    // @Override
    // public void onEffectCompleted(EffectCompletedEvent event) {
    // setPopupPosition(1025, 0);
    // }
    // });
    // eff.play();
    // }
    // });

    NMorphStyle eff = CUtil.opacityEffect(GamesPage.this.getElement(), null, 100, 0);
    eff.addEffectCompletedHandler(new EffectCompletedHandler() {
      @Override
      public void onEffectCompleted(EffectCompletedEvent event) {
        setPopupPosition(1025, 0);
        slideOutImages(null, 0);
        hideVideo();
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

    NMorphStyle eff = CUtil.opacityEffect(this.getElement(), new Command() {
      @Override
      public void execute() {
        slideInImages(300);
      }
    }, 0, 100);
    eff.play();

    isHidden = false;
  }

  private void slideInImages(int timeout) {

    hideVideo();

    new Timer() {
      public void run() {
        slideInButton(imgGame0, 5, null);
      }
    }.schedule(timeout + 1);

    new Timer() {
      public void run() {
        slideInButton(imgGame1, 182, null);
      }
    }.schedule(timeout + 50);

    new Timer() {
      public void run() {
        slideInButton(imgGame2, 335, null);
      }
    }.schedule(timeout + 100);

    new Timer() {
      public void run() {
        slideInButton(imgGame3, 465, null);
      }
    }.schedule(timeout + 170);

    new Timer() {
      public void run() {
        slideInButton(imgGame4, 565, null);

        NMorphStyle eff = CUtil.moveHorizEffectAbs(0, 0, imgTxt.getElement(), new Command() {
          @Override
          public void execute() {
            NShow fadeIn = new NShow(imgTxt.getElement());
            fadeIn.setDuration(0.3);
            fadeIn.play();
          }
        }, 0, 0, 0.1);
        eff.play();

      }
    }.schedule(timeout + 240);
  }

  public void slideOutImages(final Command afterCmd, int initialLatency) {

    Fade fadeOut = new Fade(imgTxt.getElement());
    fadeOut.setDuration(0.3);
    fadeOut.play();

    new Timer() {
      public void run() {
        slideOutButton(imgGame0, 5, null);
      }
    }.schedule(initialLatency + 1);

    new Timer() {
      public void run() {
        slideOutButton(imgGame1, 182, null);
      }
    }.schedule(initialLatency + 150);

    new Timer() {
      public void run() {
        slideOutButton(imgGame2, 335, null);
      }
    }.schedule(initialLatency + 200);

    new Timer() {
      public void run() {
        slideOutButton(imgGame3, 465, null);
      }
    }.schedule(initialLatency + 250);

    new Timer() {
      public void run() {
        slideOutButton(imgGame4, 565, afterCmd);
      }
    }.schedule(initialLatency + 320);
  }

  public void slideInButton(final Image img, int toX, final Command afterCmd) {

    NMorphStyle eff = CUtil.moveHorizEffectAbs(690, toX, img.getElement(), afterCmd, 30, 100, 0.3);
    eff.play();
  }

  public void slideOutButton(final Image img, int fromX, final Command afterCmd) {

    NMorphStyle eff = CUtil.moveHorizEffectAbs(fromX, -450, img.getElement(), afterCmd, 30, 100, 0.3);
    eff.play();
  }

}
