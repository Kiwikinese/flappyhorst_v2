package de.flappyhorst;

//========================================================================//
//                            Imports                                     //
//========================================================================//
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.flappyhorst.States.InitialState;
import de.flappyhorst.States.StateManager;

//=========================================================================================//
//                               FlappyHorst                                               //
//=========================================================================================//

public class FlappyHorstMain extends ApplicationAdapter {

	//========================================================================//
	//                         Globale Variablen                              //
	//========================================================================//

	/**
	 * SpriteBatch
	 */
	private SpriteBatch batch;

	/**
	 * Hintergrundbild
	 */
	private Texture backgroundImage;

	/**
	 * Icon des Vogels bzw. Studenten
	 */
	private Texture bird;

	private StateManager stateManager;


	//========================================================================//
	//                             Methoden                                   //
	//========================================================================//

	@Override
	public void create () {
		batch = new SpriteBatch();
		backgroundImage = new Texture("flappy_horst_background.png");
		bird = new Texture("flappy_horst_icon.png");

		//Initialisiere den StateManager
		stateManager = new StateManager();
		stateManager.push(new InitialState(stateManager));

	}

	@Override
	public void render () {
		//Zeichnet alles neu (Refresh)
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//Unterschied zwischen deltaTime und der Render Time
		stateManager.update(Gdx.graphics.getDeltaTime());
		stateManager.render(batch);

		//Initialisiere den Batch
		batch.begin();

		//Zeichne das Hintergrundbild an die Position 0, 0 und setze die Breite je nach Größe des Bildschirms
		batch.draw(backgroundImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		//Zeichne den Vogel
		batch.draw(bird, Gdx.graphics.getWidth()/2 - 100, Gdx.graphics.getHeight()/2, 200, 200);

		//Beende den Batch
		batch.end();
	}
	
	@Override
	public void dispose () {

	}
}
