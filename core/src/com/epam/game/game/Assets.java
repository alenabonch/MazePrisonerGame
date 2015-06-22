package com.epam.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;
import com.epam.game.utils.Constants;

public class Assets {
        //implements Disposable, AssetErrorListener {

//    private static final String TAG = Assets.class.getName();
//    public static final Assets instance = new Assets();
//    private AssetManager assetManager;
//
//    public AssetHero hero;
//    public AssetPotion potion;
//    public AssetGold gold;
//    public AssetChest chest;
//    public AssetWall wall;
//    public AssetsFonts fonts;
//    public AssetLevelDecoration levelDecoration;
//
//    private Assets() {
//    }
//
//    public void init(AssetManager assetManager) {
//        this.assetManager = assetManager;
//        // set asset manager error handler
//        assetManager.setErrorListener(this);
//        // load texture atlas
//        assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
//
//
//        Gdx.app.debug(TAG, "# of assets loaded: " + assetManager.getAssetNames().size);
//        for (String asset : assetManager.getAssetNames()) {
//            Gdx.app.debug(TAG, "asset: " + asset);
//        }
//
//        TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);
//        for (Texture texture : atlas.getTextures()) {
//            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
//        }
//
//    }r
//
//    @SuppressWarnings("rawtypes")
//    @Override
//    public void error(AssetDescriptor asset, Throwable throwable) {
//        Gdx.app.error(TAG, "Couldn't load asset '" + asset + "'", throwable);
//    }
//
//    @Override
//    public void dispose() {
//        assetManager.dispose();
//    }
//
//
//
//    public class AssetWall {
//
//    }
//
//    public class AssetGold {
//
//    }
//
//    public class AssetLevelDecoration {
//
//    }
//
//    public class AssetsFonts {
//
//    }


}
