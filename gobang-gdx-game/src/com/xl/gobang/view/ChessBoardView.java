package com.xl.gobang.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * 棋盘
 * 16*16
 * 1黑子 2白子
 * @author Administrator
 *
 */
public class ChessBoardView extends Actor{
	public static final int BOARD_BLACK=1,
			BOARD_WHITE=2;
	int board[]; //棋盘
	int dx,dy;
	int board_w; //一个格子的宽度
	int board_h; //一个格子的高度
	
	Texture img_board_black;
	Texture img_board_white;
	
	public ChessBoardView(){
		img_board_black= new Texture(Gdx.files.internal("board_black.png"));
		img_board_white = new Texture(Gdx.files.internal("board_white.png"));
		board= new int[16*16];
		//for(int i=0;i<board.length;i++)
		//board[i]=2;
	}
	
	
	//设置棋盘黑子 白子的图片
	public void setBoardImg(Texture img_black, Texture img_white) {
		this.img_board_black= img_black;
		this.img_board_white= img_white;
	}
	
	
	@Override
	public void setPosition(float x, float y) {
		// TODO Auto-generated method stub
		super.setPosition(x, y);
	}
	
	
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		super.draw(batch, parentAlpha);
		
		/*
		ShapeRenderer render = new ShapeRenderer();
		render.setAutoShapeType(true);
		render.setColor(Color.BLACK);
		render.setProjectionMatrix(batch.getProjectionMatrix());
		render.begin(ShapeRenderer.ShapeType.Filled);
		
		
		//画棋盘
		for(int ii=0;ii<16;ii++){
			render.line(dx+ii*board_w, dy ,dx+ii*board_w, dy+board_h*16);
			render.line(dx, dy+ii*board_h,dx, dy+ii*board_h);
			System.out.println("draw"+dx+" "+dy+" "+board_w+" "+board_h);
		}
		
		render.end();
		*/
		
		int type;
		for(int ix=0;ix<16;ix++){
			for(int iy=0;iy<16;iy++){
				type= board[iy*16+ix];
				if(type!=0){
					if(type==1)
					batch.draw(img_board_black, dx+ix*board_w, dy-board_h-board_h*iy, board_w,board_h);
					else if(type==2){
						batch.draw(img_board_white, dx+ix*board_w, dy-board_h-board_h*iy,board_w,board_h);
					}
				}
			}
		}
		
	}
	
	@Override
	protected void sizeChanged() {
		// TODO Auto-generated method stub
		super.sizeChanged();
		int width= (int) getWidth();
		int height= (int) getHeight();
		int board_width= Math.min(width, height);
		board_w = board_width/17;
		board_h = board_width/17;
		dx= (int) ((getWidth()- board_w*15)/2);
		dy = (int) (getHeight()-((getHeight()-board_h*15)/2)) ;
		System.out.println("sizeChange"+width +" "+ height);
	}
	
	
	
	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		super.act(delta);
	}
	
	//通过坐标获取棋盘位置
	public int getBoardX(int screen_x){
		return (screen_x-dx)/board_w;
	}
	
	public int getBoardY(int screen_y){
		return (dy-screen_y)/board_h;
	}
	
	//落子
	
	public void drop(int x,int y,int type) {
		board[y*16+x] = type;
	}
	
	//更新棋盘数据
	public void setBoard(int data[]) {
		this.board= data;
	}
	
	//设置棋盘数据
	
	
	//清空棋盘
	public void clearBoard() {
		for(int i=0;i<board.length;i++){
			board[i] = 0;
		}
	}

}
