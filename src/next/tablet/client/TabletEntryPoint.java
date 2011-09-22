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
package next.tablet.client;

import next.tablet.client.ls.event.ShowBackButtonEvent;
import next.tablet.client.ls.event.ShowBackButtonEvent.Type;
import next.tablet.client.ui.JSNIEventBus;
import next.tablet.client.ui.di.UiGinjector;
import next.tablet.common.client.ui.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * This class is the main entry poitn for tablet module.
 * <p>
 * 
 * @author Atanas Roussev
 */
public class TabletEntryPoint implements EntryPoint {

	public void onModuleLoad() {
		initModuleLoad();
		;
	}

	public void initModuleLoad() {
		final RootPanel rootPanel = RootPanel.get("root");
		rootPanel.setVisible(false);

		final UiGinjector ctx = UiGinjector.INSTANCE;

		// init and make it visible
		ctx.getUiSingleton().getWalletLeftMenu().show();
		ctx.getUiSingleton().getTopMenu().show();
		ctx.getUiSingleton().getTopMenu().getBackCommand().execute();
		ctx.getUiSingleton().getImagePage(); // init
		ctx.getEventBus().fireEvent(new ShowBackButtonEvent(Type.GAMES));

		rootPanel.setVisible(true);

		RootPanel loadingPanel = RootPanel.get("loading");
		loadingPanel.setVisible(false);

		// Used for debuggin only
		Window.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				int cH = Window.getClientHeight();
				int cW = Window.getClientWidth();

				int scH = Window.getScrollTop();
				int scL = Window.getScrollLeft();
				Logger.debug("clientHeight:" + cH + ", clientWidth:" + cW + "| scrollTop:" + scH + ", scrollLeft:"
						+ scL);
			}
		});

		// listenForEventUpdates();

		// ctx.getEventBus().fireEvent(new LsShowSportsEvent(true));
		// RootPanel.get("root").setVisible(false);

		Timer t2 = new Timer() {
			public void run() {
				ctx.getUiSingleton().getNotificationPage().doShow();
			}
		};
		t2.schedule(5000);

	}

	private native void initJSNIEventBus(JSNIEventBus obj) /*-{
		$wnd.__fireJSNIEventBus = function (eventId) {
		obj.@next.tablet.client.ui.JSNIEventBus::fireEvent(Ljava/lang/String;)(eventId);
		};
	}-*/;

}
