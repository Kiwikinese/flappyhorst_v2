package de.flappyhorst;

//========================================================================//
//                            Imports                                     //
//========================================================================//

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

//=========================================================================================//
//                                     AndroidLauncher                                     //
//=========================================================================================//

/**
 * Die Klasse AndroidLauncher verweist auf die Klasse {@link FlappyHorstMain}, die wiederum auf den
 * Startscreen des Spiels {@link de.flappyhorst.states.InitialState} pusht.
 * Somit dient diese Klasse AndroidLauncher lediglich als Bindeglied für das Framework LibGdx.
 *
 */
public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new FlappyHorstMain(), config);
	}
}
