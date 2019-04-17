package com.xl.gsnkxq;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.xl.gdx.XLScreen;
 

/*
mesh研究
*/

public class MeshScreen extends XLScreen
{

	private Mesh mesh;
	
	public MeshScreen(int width,int height)
	{
		super(width,height);
		
	if (mesh == null) {
	mesh = new Mesh(true, 3, 3, new VertexAttribute(Usage.Position, 3,
																									"a_position"));

	mesh.setVertices(new float[] { -0.5f, -0.5f, 0, 
										 0.5f, -0.5f, 0, 
										 0, 0.5f, 0 });

	mesh.setIndices(new short[] { 0, 1, 2 });
	create();
	}
	
}

	private Mesh squareMesh;// 那些点坐标，颜色，纹理，材质，光照等等的一个集合
	private Mesh nearSquare;


	
	public void create() {
	if (squareMesh == null) {
	squareMesh = new Mesh(true, 4, 4, new VertexAttribute(
// 表示：储存顶点的数据，每个顶点的数据由三个坐标数组成(x, y ,z ),
													Usage.Position, 3, "a_position"),
// 表示：如果想添加颜色
												new VertexAttribute(Usage.ColorPacked, 4, "a_color"));
	squareMesh.setVertices(new float[] { -0.5f, -0.5f, 0,
													 Color.toFloatBits(128, 0, 0, 255),  0,-0.5f, 0,
													 Color.toFloatBits(192, 0, 0, 255), -0.5f, 0.5f, 0,
													 Color.toFloatBits(192, 0, 0, 255), 0, 0.5f, 0,
													 Color.toFloatBits(255, 0, 0, 255) });
	squareMesh.setIndices(new short[] { 0, 1, 2, 3 });
	}
	if (nearSquare == null) {
	nearSquare = new Mesh(true, 4, 4, new VertexAttribute(
													Usage.Position, 3, "a_position"), new VertexAttribute(
													Usage.ColorPacked, 4, "a_color"));


	nearSquare.setVertices(new float[] {  0, -0.5f, 0,
													 Color.toFloatBits(0, 0, 128, 255),0.5f, -0.5f, 0,
													 Color.toFloatBits(0, 0, 192, 255), 0, 0.5f, 0,
													 Color.toFloatBits(0, 0, 192, 255),0.5f, 0.5f, 0,
													 Color.toFloatBits(0, 0, 255, 255) });
	nearSquare.setIndices(new short[] { 0, 1, 2, 3 });
	}
	}


	@Override
	public void dispose() {


	}


	@Override
	public void pause() {


	}


	
	public void render() {
	
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);// 清空屏幕
	//XLDEBUG 未解决的问题，可能是libgdx版本差异
	//squareMesh.render(GL10.GL_TRIANGLE_STRIP, 0, 4);// 绘制矩形
	//nearSquare.render(GL10.GL_TRIANGLE_STRIP, 0, 4);// 绘制矩形


	}


	


	@Override
	public void resume() {


	}


	}


