package de.flappyhorst;

//========================================================================//
//                            Imports                                     //
//========================================================================//
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.flappyhorst.states.InitialState;
import de.flappyhorst.states.StateManager;

//=========================================================================================//
//                               FlappyHorst                                               //
//=========================================================================================//

public class FlappyHorstMain extends ApplicationAdapter {

	//========================================================================//
	//                         Globale Variablen                              //
	//========================================================================//

	/**
	 * Breite des Spielfelds
	 */
	public static final int WIDTH = 480;

	/**
	 * Höhe des Spielfelds
	 */
	public static final int HEIGHT = 800;

	/**
	 * SpriteBatch
	 */
	private SpriteBatch batch;

	/**
	 * StateManager, um entsprechende Methoden (pop(), push(), update(), render() und set()) auf dem Stack aufzurufen
	 */
	private StateManager stateManager;

	//========================================================================//
	//                             Methoden                                   //
	//========================================================================//

	@Override
	public void create () {
		//Initialisiere den Batch
		batch = new SpriteBatch();

		//Initialisiere den StateManager und pushe auf den InitialState
		stateManager = new StateManager();
		stateManager.push(new InitialState(stateManager));
	}

	@Override
	public void render () {
		//Zeichnet zuerst alles neu (Refresh)
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//Unterschied zwischen deltaTime und der Render Time
		stateManager.update(Gdx.graphics.getDeltaTime());

		//InitialState
		stateManager.render(batch);
	}

	/**
	 * Wenn zu einem anderen State gewechselt wird, rufen wir diese Methode auf, um die Textures zu entfernen
	 */
	@Override
	public void dispose () {

	}
}
