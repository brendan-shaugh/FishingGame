/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.bss.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	
	public static BitmapFont font;
	
	public static TextureAtlas ta;
	public static TextureRegion backgroundRegion, soundOn, soundOff, logo, play, quit, highscores, slappySeal, hook, line, gameOver, help, resume, ready, pause, arrow, arrowFlipped, greenPatch, redPatch;
	public static TextureRegion hook2, alligator, angel, big, bigOrange, blowFish, blueFish, clown, dolphin, goldie, greenFish, oldFish, perch, plainFish, rare, shark, swordfish, trout, whale;
	
	public static Music music;
	public static Sound clickSound;
	
	public static Preferences prefs;

	public static Texture loadTexture (String file) {
		return new Texture(Gdx.files.internal(file));
	}

	public static void load () {
		
		font = new BitmapFont(Gdx.files.internal("rlfont.fnt"), Gdx.files.internal("rlfont.png"), false);
		
		ta = new TextureAtlas(Gdx.files.internal("madfishing.pack"));
		
		backgroundRegion = ta.findRegion("background2");
		soundOn = ta.findRegion("soundon");
		soundOff = ta.findRegion("soundoff");
		logo = ta.findRegion("title");
		play = ta.findRegion("play");
		quit = ta.findRegion("quit");
		help = ta.findRegion("helpscreen");
		ready = ta.findRegion("ready");
		resume = ta.findRegion("resume");
		pause = ta.findRegion("pause2");
		highscores = ta.findRegion("highscores");
		slappySeal = ta.findRegion("coloredslappysealwrods");
		hook = ta.findRegion("hooksmaller");
		gameOver = ta.findRegion("gameover");
		arrow = ta.findRegion("arrow");
		arrowFlipped = ta.findRegion("arrow");
		arrowFlipped.flip(true, false);
		
		greenPatch = ta.findRegion("greenpatch");
		redPatch = ta.findRegion("redpatch");
		
		hook2 = ta.findRegion("hookcircle");
		
		alligator = ta.findRegion("alligatorbad");
		angel = ta.findRegion("angelfishGood");
		big = ta.findRegion("biggestGoodFish");
		bigOrange = ta.findRegion("bigGoodFish");
		blowFish = ta.findRegion("blowbadfish");
		blueFish = ta.findRegion("blueGoodFish");
		clown = ta.findRegion("clownGoodFish");
		dolphin = ta.findRegion("dolphinGood");
		goldie = ta.findRegion("goldieGoodFish");
		greenFish = ta.findRegion("greenGoodFish");
		oldFish = ta.findRegion("oldFishBad");
		perch = ta.findRegion("perchGoodFish");
		plainFish = ta.findRegion("plainGoodFish");
		rare = ta.findRegion("rareGoodFish");
		shark = ta.findRegion("sharkBad");
		swordfish = ta.findRegion("swordfishGood");
		trout = ta.findRegion("troutGoodFish");
		whale = ta.findRegion("whalebad");
		
		
		//font = new BitmapFont(Gdx.files.internal("data/font.fnt"), Gdx.files.internal("data/font.png"), false);
		
		// Create (or retrieve existing) preferences file
		prefs = Gdx.app.getPreferences("MadFishing");

		// Provide default high score of 0
		if (!prefs.contains("highScore")) {
		    prefs.putLong("highScore", 0l);
		}
		if(!prefs.contains("sound")){
			prefs.putBoolean("sound", true);
		}
		
		music = Gdx.audio.newMusic(Gdx.files.internal("ObservingTheStar.ogg"));
		music.setLooping(true);
		music.setVolume(0.5f);
		if (getSound()) music.play();
		clickSound = Gdx.audio.newSound(Gdx.files.internal("pop.ogg"));
		
	}
	
	// Receives an integer and maps it to the String highScore in prefs
	public static void setHighScore(long val) {
		prefs.putLong("highScore", val);
	    prefs.flush();
	}

	// Retrieves the current high score
	public static long getHighScore() {
	    return prefs.getLong("highScore");
	}
	
	// Receives an integer and maps it to the String highScore in prefs
	public static void setSound(boolean val) {
	    prefs.putBoolean("sound", val);
	    prefs.flush();
	}

	// Retrieves the current high score
	public static boolean getSound() {
	    return prefs.getBoolean("sound");
	}

	public static void playSound (Sound sound) {
		if (getSound()) sound.play(1);
	}
}
