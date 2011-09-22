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
package next.tablet.client.ui.di;

import next.tablet.client.black.AccountPage;
import next.tablet.client.black.FacebookPage;
import next.tablet.client.black.GameTopMenu;
import next.tablet.client.black.GamesPage;
import next.tablet.client.black.ImagePage;
import next.tablet.client.black.LeftMenuImpl;
import next.tablet.client.black.LocationPage;
import next.tablet.client.black.NotificationPage;
import next.tablet.client.black.PageManager;
import next.tablet.client.black.PageMediator;
import next.tablet.client.black.TopMenu;
import next.tablet.client.black.TwitterPage;
import next.tablet.client.black.WalletPage;
import next.tablet.common.client.ui.kbd.KeyboardPopup;
import next.tablet.shared.LsProps;

import com.google.inject.Inject;

/**
 * This class is a workaround as GIN injector creates each time a new instance
 * of the "injectable" classes. Yet for classes such as FormPopup and
 * KeyboardPopup we need to have ONLY one instance.
 * 
 * @author Atanas Roussev
 */
public class UiSingleton {

	private static KeyboardPopup keyboardPopup;

	private static PageMediator pageMediator;
	private static LsProps LsProps;

	private static LeftMenuImpl walletLeftMenu;
	private static TopMenu topMenu;
	private static FacebookPage communityPage;
	private static WalletPage walletPage;
	private static AccountPage accountPage;
	private static TwitterPage favoritesPage;
	private static LocationPage bclcPage;
	private static NotificationPage notificationPage;
	private static GamesPage gamesPage;
	private static GameTopMenu gameTopMenu;

	private static ImagePage imagePage;

	private static PageManager pageManager;

	private TabletEventBus tabletEventBus;

	@Inject
	public UiSingleton(TabletEventBus tabletEventBus) {
		this.tabletEventBus = tabletEventBus;
	}

	public LsProps getLsProps() {
		if (LsProps == null) {
			LsProps = new LsProps();
		}
		return LsProps;
	}

	public PageManager getPageManager() {
		if (pageManager == null) {
			pageManager = new PageManager();
		}
		return pageManager;
	}

	public NotificationPage getNotificationPage() {
		if (notificationPage == null) {
			notificationPage = new NotificationPage(tabletEventBus);
			// getPageManager().bind(notificationPage);
		}
		return notificationPage;
	}

	public GamesPage getGamesPage() {
		if (gamesPage == null) {
			gamesPage = new GamesPage(tabletEventBus);
			getPageManager().bind(gamesPage);
		}
		return gamesPage;
	}

	public ImagePage getImagePage() {
		if (imagePage == null) {
			imagePage = new ImagePage(tabletEventBus);
			getPageManager().bind(imagePage);
		}
		return imagePage;
	}

	public FacebookPage getFacebookPage() {
		if (communityPage == null) {
			communityPage = new FacebookPage(tabletEventBus);
			getPageManager().bind(communityPage);
		}
		return communityPage;
	}

	public LocationPage getLocationPage() {
		if (bclcPage == null) {
			bclcPage = new LocationPage(tabletEventBus);
			getPageManager().bind(bclcPage);
		}
		return bclcPage;
	}

	public WalletPage getWalletPage() {
		if (walletPage == null) {
			walletPage = new WalletPage(tabletEventBus);
			getPageManager().bind(walletPage);
		}
		return walletPage;
	}

	public AccountPage getAccountPage() {
		if (accountPage == null) {
			accountPage = new AccountPage(tabletEventBus);
			getPageManager().bind(accountPage);
		}
		return accountPage;
	}

	public TwitterPage getTwitterPage() {
		if (favoritesPage == null) {
			favoritesPage = new TwitterPage(tabletEventBus);
			getPageManager().bind(favoritesPage);
		}
		return favoritesPage;
	}

	public LeftMenuImpl getWalletLeftMenu() {
		if (walletLeftMenu == null) {
			walletLeftMenu = new LeftMenuImpl(tabletEventBus);
		}
		return walletLeftMenu;
	}

	public TopMenu getTopMenu() {
		if (topMenu == null) {
			topMenu = new TopMenu(tabletEventBus);
		}
		return topMenu;
	}

	public GameTopMenu getGameTopMenu() {
		if (gameTopMenu == null) {
			gameTopMenu = new GameTopMenu(tabletEventBus);
		}
		return gameTopMenu;
	}

	public PageMediator getLsMediator() {
		if (pageMediator == null) {
			pageMediator = new PageMediator(tabletEventBus);
		}
		return pageMediator;
	}

	public KeyboardPopup getKeyboardPopup() {
		if (keyboardPopup == null) {
			keyboardPopup = new KeyboardPopup();
		}
		return keyboardPopup;
	}

}
