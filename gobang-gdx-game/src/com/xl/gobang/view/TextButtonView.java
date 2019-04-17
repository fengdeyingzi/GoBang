package com.xl.gobang.view;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class TextButtonView extends TextButton{

	private int id;
	private Object tag;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Object getTag() {
		return tag;
	}

	public void setTag(Object tag) {
		this.tag = tag;
	}


	
	
	public TextButtonView(String text, Skin skin) {
		super(text, skin);
		// TODO Auto-generated constructor stub
	}
	
	public TextButtonView(String text, Skin skin, String styleName) {
		super(text, skin,styleName);
	}

	public TextButtonView (String text, TextButtonStyle style){
		super(text, style);
	}
	
	
	
}
