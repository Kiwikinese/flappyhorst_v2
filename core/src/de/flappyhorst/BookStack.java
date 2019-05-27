package de.flappyhorst;

//========================================================================//
//                            Imports                                     //
//========================================================================//

import com.badlogic.gdx.graphics.Texture;

//=========================================================================================//
//                                       BookStack                                         //
//=========================================================================================//

public class BookStack {

    //========================================================================//
    //                         Globale Variablen                              //
    //========================================================================//

    /**
     * Texture für den oberen Bücherstapel
     */
    private Texture topBookStack;

    /**
     * Texture für den unteren Bücherstapel
     */
    private Texture bottomBookStack;


    //========================================================================//
    //                            Konstruktor/en                              //
    //========================================================================//

    /**
     * Konstruktor
     *
     * @param x x
     */
    public BookStack(float x){
        topBookStack = new Texture("toptube.png");
        bottomBookStack = new Texture("bottomtube.png");
    }

}
