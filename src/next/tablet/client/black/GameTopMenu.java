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

import next.tablet.client.ls.event.ShowGameCategoryEvent;
import next.tablet.client.ui.CUtil;
import next.tablet.client.ui.GlobalResources;
import next.tablet.client.ui.di.TabletEventBus;
import next.tablet.client.ui.di.UiGinjector;
import next.tablet.client.ui.di.UiSingleton;
import next.tablet.common.client.ui.ImageButton;

import org.adamtacy.client.ui.effects.core.NMorphStyle;
import org.adamtacy.client.ui.effects.events.EffectCompletedEvent;
import org.adamtacy.client.ui.effects.events.EffectCompletedHandler;
import org.adamtacy.client.ui.effects.impl.Fade;
import org.adamtacy.client.ui.effects.impl.NShow;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.PopupPanel;

public class GameTopMenu extends PopupPanel {

  final static GlobalResources RES = GlobalResources.INSTANCE;

  private final static UiSingleton CTX = UiGinjector.INSTANCE.getUiSingleton();

  private TabletEventBus eventBus;

  private boolean isHidden = true;

  // final ImageButton hAll;
  final ImageButton hSocial;
  final ImageButton hSports;
  final ImageButton hInteractive;

  final ArrayList<ImageButton> btns = new ArrayList<ImageButton>();

  public GameTopMenu(final TabletEventBus eventBus) {
    show();

    this.eventBus = eventBus;
    setStyleName("bTopCategoryMenu");
    setSize("690", "60px");
    setPopupPosition(336, 5);
    show();

    final FlexTable content = new FlexTable();
    content.setStyleName("content");
    setWidget(content);

    hSocial = new ImageButton(RES.hSocial());
    hSports = new ImageButton(RES.hSports());
    hInteractive = new ImageButton(RES.hInteractive());

    content.setWidget(0, 0, hSocial);
    content.setWidget(0, 1, hSports);
    content.setWidget(0, 2, hInteractive);

    btns.add(hSocial);
    btns.add(hSports);
    btns.add(hInteractive);

    hSocial.addMouseDownHandler(new MouseDownHandler() {
      @Override
      public void onMouseDown(MouseDownEvent event) {
        // Command afterCmd = new Command() {
        // @Override
        // public void execute() {
        // fadeOutButton(hSocial, null);
        // }
        // };
        // repaintBtns(afterCmd);

        hSocial.getElement().getStyle().setOpacity(0.4);
        hSports.getElement().getStyle().setOpacity(1);
        hInteractive.getElement().getStyle().setOpacity(1);

        eventBus.fireEvent(new ShowGameCategoryEvent(ShowGameCategoryEvent.Type.SOCIAL));
      }
    });

    hSports.addMouseDownHandler(new MouseDownHandler() {
      @Override
      public void onMouseDown(MouseDownEvent event) {
        // Command afterCmd = new Command() {
        // @Override
        // public void execute() {
        // fadeOutButton(hSports, null);
        // }
        // };
        // repaintBtns(afterCmd);

        hSocial.getElement().getStyle().setOpacity(1);
        hSports.getElement().getStyle().setOpacity(0.4);
        hInteractive.getElement().getStyle().setOpacity(1);

        eventBus.fireEvent(new ShowGameCategoryEvent(ShowGameCategoryEvent.Type.SPORT));
      }
    });

    hInteractive.addMouseDownHandler(new MouseDownHandler() {
      @Override
      public void onMouseDown(MouseDownEvent event) {
        // Command afterCmd = new Command() {
        // @Override
        // public void execute() {
        // fadeOutButton(hInteractive, null);
        // }
        // };
        // repaintBtns(afterCmd);

        hSocial.getElement().getStyle().setOpacity(1);
        hSports.getElement().getStyle().setOpacity(1);
        hInteractive.getElement().getStyle().setOpacity(0.4);

        eventBus.fireEvent(new ShowGameCategoryEvent(ShowGameCategoryEvent.Type.INTERACTIVE));
      }
    });

  }

  public void repaintBtns(Command afterCmd) {
    for (ImageButton b : btns) {
      if (b.isSelected()) {
        fadeInButton(b, afterCmd);
      }
    }
  }

  public void fadeOutButton(final ImageButton btn, final Command afterCmd) {

    btn.setSelected(true);

    Fade eff = new Fade(btn.getElement());
    eff.setEndOpacity(30);
    eff.setDuration(0.3);
    eff.addEffectCompletedHandler(new EffectCompletedHandler() {
      @Override
      public void onEffectCompleted(EffectCompletedEvent event) {
        if (afterCmd != null) {
          afterCmd.execute();
        }
      }
    });
    eff.play();
  }

  public void fadeInButton(final ImageButton btn, final Command afterCmd) {

    btn.setSelected(false);

    NShow eff = new NShow(btn.getElement());
    eff.setStartOpacity(30);
    eff.setDuration(0.3);
    eff.addEffectCompletedHandler(new EffectCompletedHandler() {
      @Override
      public void onEffectCompleted(EffectCompletedEvent event) {
        afterCmd.execute();
      }
    });
    eff.play();
  }

  public void slideOutButton(final ImageButton btn, final Command beforeCmd, final Command afterCmd) {

    if (beforeCmd != null) {
      beforeCmd.execute();
    }

    NMorphStyle eff = CUtil.moveHorizEffectAbs(btn.getAbsoluteLeft(), 690, btn.getElement(), afterCmd, 100, 30, 0.3);
    eff.play();
  }

  public void slideInButton(final ImageButton btn, final Command beforeCmd, final Command afterCmd) {

    NMorphStyle eff = CUtil.moveHorizEffectAbs(690, 0, btn.getElement(), afterCmd, 30, 100, 0.3);
    eff.play();
  }

  public void doHide(final Command afterCmd) {
    if (isHidden) {
      if (afterCmd != null) {
        afterCmd.execute();
      }
      return;
    }

    Timer t = new Timer() {
      public void run() {
        NMorphStyle eff = CUtil.moveHorizEffectAbs(hSocial.getAbsoluteLeft(), 690, hSocial.getElement(), afterCmd, 30,
            100, 0.3);
        eff.play();
      }
    };
    t.schedule(250);

    Timer t2 = new Timer() {
      public void run() {
        NMorphStyle eff2 = CUtil.moveHorizEffectAbs(hSports.getAbsoluteLeft(), 690, hSports.getElement(), null, 30,
            100, 0.3);
        eff2.play();
      }
    };
    t2.schedule(150);

    Timer t3 = new Timer() {
      public void run() {
        NMorphStyle eff3 = CUtil.moveHorizEffectAbs(hInteractive.getAbsoluteLeft(), 690, hInteractive.getElement(),
            null, 30, 100, 0.3);
        eff3.play();
      }
    };
    t3.schedule(50);

    // NMorphStyle eff3 = CUtil.moveHorizEffectAbs(hAll.getAbsoluteLeft(), 690, hAll.getElement(), null, 30, 100, 0.3);
    // eff3.play();

    isHidden = true;
  }

  public void doShow() {
    // Window.alert("doShow 1");
    if (!isHidden) {
      return;
    }

    Timer t = new Timer() {
      public void run() {
        NMorphStyle eff = CUtil.moveHorizEffectAbs(690, 0, hSocial.getElement(), null, 30, 100, 0.3);
        eff.play();
      }
    };
    t.schedule(200);

    Timer t2 = new Timer() {
      public void run() {
        NMorphStyle eff2 = CUtil.moveHorizEffectAbs(690, hSocial.getWidth() + 10, hSports.getElement(), null, 30, 100,
            0.3);
        eff2.play();
      }
    };
    t2.schedule(400);

    Timer t3 = new Timer() {
      public void run() {
        NMorphStyle eff3 = CUtil.moveHorizEffectAbs(690, hSocial.getWidth() + 10 + hSports.getWidth() + 10,
            hInteractive.getElement(), null, 30, 100, 0.3);
        eff3.play();
      }
    };
    t3.schedule(600);

    isHidden = false;
  }

}
