package de.flappyhorst;

//========================================================================//
//                            Imports                                     //
//========================================================================//

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.flappyhorst.states.InitialState;
import de.flappyhorst.states.StateManager;

//=========================================================================================//
//                               FlappyHorst                                               //
//=========================================================================================//

/**
 * FlappyHorstMain ist die Hauptklasse des Spiels. Hier wird das Spielfeld des Spiels
 * mit einer festgelegten Höhe und Breite, sowie der Song des Spiels initialisiert.
 * Darüber hinaus wird hier auch der StateManger initialisiert, um zwischen den verschiedenen
 * States
 * {@link de.flappyhorst.states.InitialState},
 * {@link de.flappyhorst.states.PlayState},
 * {@link de.flappyhorst.states.HighscoreState} und
 * {@link de.flappyhorst.states.GameoverState}
 * zu switchen.
 *
 */
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
	 * SpriteBatch, um auf dem Spielfeld sämtliche Objekte zu zeichnen
	 */
	private SpriteBatch batch;

	/**
	 * StateManager, um entsprechende Methoden (pop(), push(), update(), render() und set())
	 * auf dem Stack aufzurufen
	 */
	private StateManager stateManager;

	/**
	 * Lied des Spiels
	 */
	public static Music SONG; //Quelle: https://www.youtube.com/watch?v=wR3gaYTqkDQ

	//========================================================================//
	//                             Methoden                                   //
	//========================================================================//

	/**
	 * create Methode, um die Variablen/Objekte zu initialisieren
	 */
	@Override
	public void create () {
		//Initialisiere den Batch
		batch = new SpriteBatch();

		//Initialisiere den StateManager und pushe auf den InitialState
		stateManager = new StateManager();
		stateManager.push(new InitialState(stateManager));

		initializeSong();
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
	 * Initialisiere die Musik des Spiels
	 */
	public void initializeSong(){
		SONG = Gdx.audio.newMusic(Gdx.files.internal("_pokemon_theme.mp3"));
		SONG.setLooping(true);
		SONG.setVolume(0.1f);	//10% von max. 100% Lautstärke
	}

	/**
	 * Wenn zu einem anderen State gewechselt wird, rufen wir diese Methode auf, um dem State zu
	 * clearen um somit Speicherplatz zu sparen
	 *
	 */
	@Override
	public void dispose () {
		batch.dispose();
	}

}
