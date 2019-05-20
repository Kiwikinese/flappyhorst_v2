package de.flappyhorst;

//========================================================================//
//                            Imports                                     //
//========================================================================//
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//=========================================================================================//
//                               FlappyHorst                                               //
//=========================================================================================//

public class FlappyHorstMain extends ApplicationAdapter {

	//========================================================================//
	//                         Globale Variablen                              //
	//========================================================================//

	/**
	 * Draws batched quads using indices
	 */
	SpriteBatch batch;

	/**
	 * Hintergrundbild
	 */
	Texture backgroundImage;

	/**
	 * Icon des Vogels bzw. Studenten
	 */
	Texture bird;


	//========================================================================//
	//                             Methoden                                   //
	//========================================================================//

	@Override
	public void create () {
		batch = new SpriteBatch();
		backgroundImage = new Texture("flappy_horst_background.png");
		bird = new Texture("flappy_horst_icon.png");

	}

	@Override
	public void render () {
		//Initialisiere den Batch
		batch.begin();

		//Zeichne das Hintergrundbild an die Position 0, 0 und setze die Breite je nach Größe des Bildschirms
		batch.draw(backgroundImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		//Zeichne den Vogel
		batch.draw(bird, Gdx.graphics.getWidth()/2 - 100, Gdx.graphics.getHeight()/2, 250, 250);

		//Beende den Batch
		batch.end();

	}
	
	@Override
	public void dispose () {

	}
}
