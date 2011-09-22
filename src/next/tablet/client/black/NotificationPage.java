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

import next.tablet.client.ui.CUtil;
import next.tablet.client.ui.GlobalResources;
import next.tablet.client.ui.di.TabletEventBus;

import org.adamtacy.client.ui.effects.core.NMorphStyle;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;

public class NotificationPage extends PopupPanel/* implements IPage */{

  final static GlobalResources RES = GlobalResources.INSTANCE;
  private boolean isHidden = true;

  final static int POSITION = 530;

  final NotifIcon notifIcon1;
  final NotifIcon notifIcon2;
  final NotifIcon notifIcon3;
  final NotifAll notifAll;

  public NotificationPage(final TabletEventBus eventBus) {
    setStyleName("bNotificationPage");
    setWidth("1024px");

    // this.getElement().getStyle().setOpacity(1);
    setPopupPosition(0, POSITION);
    setSize("1024px", "200px");
    show();

    // // final Image img1 = new Image(RES.notif1());
    // // Image img2 = new Image();
    // FlowPanel holder = new FlowPanel();
    // holder.add(img1);
    // holder.add(img2);

    HTML area = new HTML();
    area.setSize("1024px", "200px");
    setWidget(area);

    area.addMouseDownHandler(new MouseDownHandler() {
      @Override
      public void onMouseDown(MouseDownEvent event) {
        notifAll.doShow();
      }
    });

    notifAll = new NotifAll();
    notifIcon1 = new NotifIcon(eventBus, notifAll, RES.notif1(), RES.notifIcon1(), 985);
    notifIcon2 = new NotifIcon(eventBus, notifAll, RES.notif2(), RES.notifIcon2(), 950);
    notifIcon3 = new NotifIcon(eventBus, notifAll, RES.notif3(), RES.notifIcon3(), 915);
  }

  public void doHide() {
    if (isHidden) {
      return;
    }

    Timer t1 = new Timer() {
      public void run() {
        notifIcon1.doHide(null);
        Timer t2 = new Timer() {
          public void run() {
            notifIcon2.doHide(null);
            Timer t3 = new Timer() {
              public void run() {
                notifIcon3.doHide(null);
                Timer t4 = new Timer() {
                  public void run() {

                    NMorphStyle eff = CUtil.moveVerticalEffect(POSITION, 601, NotificationPage.this.getElement(), null);
                    eff.play();

                    Timer t5 = new Timer() {
                      public void run() {
                        doShow();
                      }
                    };
                    t5.schedule(10000);
                  }
                };
                t4.schedule(400);
              }
            };
            t3.schedule(400);
          }
        };
        t2.schedule(400);
      }
    };
    t1.schedule(400);

    isHidden = true;
  }

  public void doShow() {
    if (!isHidden) {
      return;
    }
    // this.getElement().getStyle().setOpacity(1);
    setPopupPosition(0, 601);

    // NMorphStyle eff = CUtil.opacityEffect(this.getElement(), null, 0, 100);
    // eff.play();
    NMorphStyle eff = CUtil.moveVerticalEffect(600, POSITION, this.getElement(), null);
    eff.play();

    notifIcon1.doShow();

    Timer t = new Timer() {
      public void run() {
        notifIcon2.doShow();

        Timer t = new Timer() {
          public void run() {
            notifIcon3.doShow();
          }
        };
        t.schedule(10000);
      }
    };
    t.schedule(13000);

    isHidden = false;
  }

  class NotifAll extends PopupPanel {

    private boolean allIsHidden = true;
    final Image allImg;

    public NotifAll() {
      setStyleName("bNotifAll");
      setPopupPosition(0, 601);
      show();

      allImg = new Image(RES.notifAll());
      allImg.getElement().getStyle().setPropertyPx("top", 601);
      // img.getElement().getStyle().setOpacity(1);
      setWidget(allImg);

      allImg.addMouseDownHandler(new MouseDownHandler() {
        @Override
        public void onMouseDown(MouseDownEvent event) {
          notifAll.doHide(null);
        }
      });
    }

    public void doShow() {
      if (!allIsHidden) {
        return;
      }
      NMorphStyle eff = CUtil.moveVerticalEffect(601, 480, NotifAll.this.getElement(), null);
      eff.play();

      // allImg.getElement().getStyle().setPropertyPx("top", 500);

      allIsHidden = false;
    }

    public void doHide(Command afterCmd) {
      if (allIsHidden) {
        return;
      }

      // allImg.getElement().getStyle().setPropertyPx("top", 601);
      NMorphStyle eff = CUtil.moveVerticalEffect(480, 601, NotifAll.this.getElement(), null);
      eff.play();

      allIsHidden = true;
    }

  }

  class NotifIcon extends PopupPanel {
    final Image img;
    private boolean iconIsHidden = true;

    final ImageResource txtResource;
    final ImageResource iconResource;
    final int endPosition;

    public NotifIcon(final TabletEventBus eventBus, final NotifAll notificationPage, ImageResource txtResource,
        ImageResource iconResource, int endPosition) {
      setStyleName("bNotifIcon");
      this.txtResource = txtResource;
      this.iconResource = iconResource;
      this.endPosition = endPosition;
      // this.getElement().getStyle().setOpacity(0);
      setPopupPosition(6, 566);
      // setSize("1024px", "200px");
      show();

      img = new Image();
      img.getElement().getStyle().setPropertyPx("left", 1025);
      img.getElement().getStyle().setOpacity(0);
      setWidget(img);

      img.addMouseDownHandler(new MouseDownHandler() {
        @Override
        public void onMouseDown(MouseDownEvent event) {
        	notificationPage.doShow();
//          doHide(new Command() {
//            @Override
//            public void execute() {
//              notificationPage.doHide();
//            }
//          });
        }
      });
    }

    public void doShow() {
      if (!iconIsHidden) {
        return;
      }
      NotifIcon.this.getElement().getStyle().setOpacity(1);
      img.setResource(txtResource);

      NMorphStyle eff = CUtil.moveHorizEffect(1025, 0, img.getElement(), null, 30, 100, .5);
      eff.play();

      doExpire();

      iconIsHidden = false;
    }

    public void doExpire() {
      if (!iconIsHidden) {
        return;
      }

      Timer t = new Timer() {
        public void run() {
          if (iconIsHidden) {
            return;
          }
          Command afterCmd = new Command() {
            @Override
            public void execute() {
              img.setResource(iconResource);
              img.getElement().getStyle().setOpacity(1);
            }
          };
          NMorphStyle eff = CUtil.moveHorizEffect(0, endPosition, img.getElement(), afterCmd, 100, 0, .5);
          eff.play();
        }
      };
      t.schedule(8000);
    }

    public void doHide(Command afterCmd) {
      if (iconIsHidden) {
        return;
      }
      NMorphStyle eff = CUtil.moveHorizEffect(img.getAbsoluteLeft(), 1025, img.getElement(), afterCmd, 100, 0, .5);
      eff.play();

      iconIsHidden = true;
    }

  }

}
