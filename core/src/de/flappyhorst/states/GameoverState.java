package de.flappyhorst.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameoverState extends State {

    private Texture backgroundImage;
    private Texture endImage;
    private BitmapFont font;
    Preferences prefs = Gdx.app.getPreferences("My Preferences");
    private int highscore;
    private int currentScore;


    public GameoverState(StateManager stateManager){
        super(stateManager);
        backgroundImage = new Texture("background_small.JPG");
        endImage = new Texture("gameoverFont.png");
        font = new BitmapFont();
        font.setColor(Color.ORANGE);
        font.getData().setScale(5);

        Gdx.app.log("Highscore", String.valueOf(highscore));
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            stateManager.set(new InitialState(stateManager));
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput(); //Checkt den Input des Users
    }

    @Override
    public void render(SpriteBatch batch) {
        //Repositioniert die Kamera
        batch.getProjectionMatrix().setToOrtho2D(0, 0, Gdx.graphics.getWidth(),  Gdx.graphics.getHeight());

        batch.begin();

        batch.draw(backgroundImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(endImage,  50 , Gdx.graphics.getHeight()-500,Gdx.graphics.getWidth()-100, 200 );

        highscore = prefs.getInteger("highscore");
        currentScore = prefs.getInteger("currentscore");
        prefs.flush();

        font.draw(batch, "Du hast folgende Punktzahl\nerreicht: " + currentScore, 50, Gdx.graphics.getHeight()-900);
        font.draw(batch, "Dein aktueller Highscore\nliegt bei: " + highscore, 50, Gdx.graphics.getHeight()-1200);

        //Beende den Batch
        batch.end();
    }

    /**
     * Wenn zu einem anderen State gewechselt wird, rufen wir diese Methode auf, um die Textures zu entfernen
     */
    @Override
    public void dispose() {
        Gdx.app.log("GameOverState","GameOverState disposed");
    }
}
