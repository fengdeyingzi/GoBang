package com.xl.gsnkxq.ui;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Danny 姜 on 17/8/22.
 mush画线 进度
 */

public class MyAdapter extends ApplicationAdapter {

	protected Bezier<Vector2> bezier;

	float t;

	float speed = 0.1f;

	final Vector2 tmpV = new Vector2();

	ShapeRenderer shapeRenderer;

	@Override
	public void create() {
		super.create();

		shapeRenderer = new ShapeRenderer();

		bezier = new Bezier<Vector2>(new Vector2(100, 100), new Vector2(500, 500));
	}

	@Override
	public void render() {
		super.render();

		Gdx.gl.glClearColor(0.7f, 0.7f, 0.7f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		if (tmpV.x <= Gdx.graphics.getWidth() || tmpV.y <= Gdx.graphics.getHeight()) {
			t += speed * Gdx.graphics.getDeltaTime();
			bezier.valueAt(tmpV, t);
		}

		Gdx.app.error("DANNY", "tmpV.x is " + tmpV.x + " tmpV.y is " + tmpV.y);

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

		shapeRenderer.rectLine(100, 100, tmpV.x, tmpV.y, 10);

		shapeRenderer.end();
	}
	}
	
