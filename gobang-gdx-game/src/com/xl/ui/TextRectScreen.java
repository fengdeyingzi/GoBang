package com.xl.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.xl.gdx.XLScreen;
/*
显示多行text

*
*/

public class TextRectScreen extends XLScreen
 {

    Stage stage;

	public TextRectScreen(int w,int h){
		super(w,h);
		create();
	}

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void render(float i) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int arg0, int arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {

    }

    
    public void create() {
		Viewport stretchViewport = new StretchViewport(getWidth(), getHeight());
        stage = new Stage(stretchViewport);

        Gdx.input.setInputProcessor(stage);

        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < 258; i++) {
            buf.append("hei" + i).append("\n");
        }

        Label lable = new Label(buf,
                                new Label.LabelStyle(new BitmapFont(), Color.YELLOW));

        ScrollPane scroll = new ScrollPane(lable);
        scroll.setStyle(new ScrollPane.ScrollPaneStyle());
        scroll.setSize(400,300);
        scroll.setColor(Color.BLUE);
        scroll.setVisible(true);
        scroll.setPosition(20,80);


        stage.addActor(scroll);
    }


	}


	
