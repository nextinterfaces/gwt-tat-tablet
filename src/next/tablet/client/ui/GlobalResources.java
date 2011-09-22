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
package next.tablet.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * This class keep all Image and CSS resources for used tablet
 * <p>
 * 
 * @author Atanas Roussev
 */
public interface GlobalResources extends ClientBundle {

  public static final GlobalResources INSTANCE = GWT.create(GlobalResources.class);

  // @NotStrict
  // @Source("resources/ui.css")
  // public CssResource cssAddons();
  //  
  // @NotStrict
  // @Source("resources/main.css")
  // public CssResource cssMain();

  @Source("resources/location.png")
  public ImageResource location();

  @Source("resources/hBack.png")
  public ImageResource btnBack();

  @Source("resources/hBlank.png")
  public ImageResource hBlank();

  @Source("resources/wallet.png")
  public ImageResource mWallet();

  @Source("resources/account.png")
  public ImageResource mAccount();

  @Source("resources/community.png")
  public ImageResource mFacebook();

  @Source("resources/favorites.png")
  public ImageResource mTwitter();

  @Source("resources/location.png")
  public ImageResource mLocation();

  @Source("resources/hBalance.png")
  public ImageResource hBalance();

  @Source("resources/hAccount.png")
  public ImageResource hAccount();

  @Source("resources/hCommunity.png")
  public ImageResource hFacebook();

  @Source("resources/hFavorites.png")
  public ImageResource hFavorites();

  @Source("resources/hProfile.png")
  public ImageResource hProfile();

  @Source("resources/hFriends.png")
  public ImageResource hFriends();

  @Source("resources/hNewsFeed.png")
  public ImageResource hNewsFeed();

  @Source("resources/hTwitter.png")
  public ImageResource hTwitter();

  @Source("resources/hTwitter2.png")
  public ImageResource hTwitter2();

  @Source("resources/hLocation.png")
  public ImageResource hLocation();

  @Source("resources/hAccountEdit.png")
  public ImageResource hAccountEdit();

  @Source("resources/hWalletTopup.png")
  public ImageResource hWalletTopup();

  @Source("resources/hPromotions.png")
  public ImageResource hPromotions();

  @Source("resources/hTour.png")
  public ImageResource hTour();

  @Source("resources/hResponsive.png")
  public ImageResource hResponsive();

  @Source("resources/hContact.png")
  public ImageResource hContact();

  @Source("resources/hFaq.png")
  public ImageResource hFaq();

  @Source("resources/hGames.png")
  public ImageResource hGames();

  @Source("resources/hBack.png")
  public ImageResource hBackImg();

  @Source("resources/hBackGames.png")
  public ImageResource hBackGames();

  @Source("resources/hBackCommunity.png")
  public ImageResource hBackComminuty();

  @Source("resources/hBackAccount.png")
  public ImageResource hBackAccount();

  @Source("resources/hBackWallet.png")
  public ImageResource hBackWallet();

  @Source("resources/hBackLocation.png")
  public ImageResource hBackLocation();

  @Source("resources/hBackFavorites.png")
  public ImageResource hBackFavorites();

  @Source("resources/profile.jpg")
  public ImageResource cProfile();

  @Source("resources/games.jpg")
  public ImageResource cGames();

  @Source("resources/friends.jpg")
  public ImageResource cFriends();

  @Source("resources/facebook.png")
  public ImageResource cFacebook();

  @Source("resources/twitter.png")
  public ImageResource cTwitter();

  @Source("resources/cGames.jpg")
  public ImageResource iGames();

  @Source("resources/cNewsFeed.jpg")
  public ImageResource iNewsFeed();

  @Source("resources/cFriends.jpg")
  public ImageResource iFriends();

  @Source("resources/cProfile.jpg")
  public ImageResource iProfile();

  @Source("resources/iPromotions.jpg")
  public ImageResource iPromotions();

  @Source("resources/iTour.jpg")
  public ImageResource iTour();

  @Source("resources/iResponsive.jpg")
  public ImageResource iResponsive();

  @Source("resources/iContact.jpg")
  public ImageResource iContact();

  @Source("resources/iFaq.png")
  public ImageResource iFaq();

  @Source("resources/_wallet.png")
  public ImageResource _wallet();

  @Source("resources/_account.jpg")
  public ImageResource _account();

  @Source("resources/_accountEdit.jpg")
  public ImageResource _accountEdit();

  @Source("resources/_favorites.jpg")
  public ImageResource _favorites();

  @Source("resources/_favoritesTwitter.gif")
  public ImageResource _favoritesTwitter();

  @Source("resources/gamesBg.png")
  public ImageResource gamesBg();

  @Source("resources/iWalletTopup.png")
  public ImageResource iWalletTopup();

  @Source("resources/bTour.jpg")
  public ImageResource bTour();

  @Source("resources/bPromotions.jpg")
  public ImageResource bPromotions();

  @Source("resources/bContact.jpg")
  public ImageResource bContact();

  @Source("resources/bResponsive.jpg")
  public ImageResource bGoogleMaps();

  @Source("resources/bFaq.png")
  public ImageResource bFaq();

  @Source("resources/notif1.png")
  public ImageResource notif1();

  @Source("resources/notif2.png")
  public ImageResource notif2();

  @Source("resources/notif3.png")
  public ImageResource notif3();

  @Source("resources/notifIcon1.png")
  public ImageResource notifIcon1();

  @Source("resources/notifIcon2.png")
  public ImageResource notifIcon2();

  @Source("resources/notifIcon3.png")
  public ImageResource notifIcon3();

  @Source("resources/notificationAll.png")
  public ImageResource notifAll();

  @Source("resources/hAll.png")
  public ImageResource hAll();

  @Source("resources/hSocial.png")
  public ImageResource hSocial();

  @Source("resources/hSports.png")
  public ImageResource hSports();

  @Source("resources/hInteractive.png")
  public ImageResource hInteractive();

  @Source("resources/game0.png")
  public ImageResource game0();

  @Source("resources/game1.png")
  public ImageResource game1();

  @Source("resources/game2.png")
  public ImageResource game2();

  @Source("resources/game3.png")
  public ImageResource game3();

  @Source("resources/game4.png")
  public ImageResource game4();

  @Source("resources/gameText.png")
  public ImageResource gameText();

  @Source("resources/gameCover.png")
  public ImageResource gameCover();

  @Source("resources/gameVideo.jpg")
  public ImageResource gameVideo();

}
