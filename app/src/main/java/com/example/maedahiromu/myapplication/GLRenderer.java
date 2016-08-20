package com.example.maedahiromu.myapplication;

/**
 * Created by Maeda Hiromu on 2016/08/11.
 */
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import com.example.maedahiromu.myapplication.GLES;
import com.example.maedahiromu.myapplication.ObjLoader;
import com.example.maedahiromu.myapplication.Object3D;
import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

//レンダラー
public class GLRenderer implements
        GLSurfaceView.Renderer {
    //システム
    private float aspect;//アスペクト比
    private int   angle; //回転角度

    //モデル
    private Object3D model=new Object3D();

    //コンストラクタ
    public GLRenderer(Context context) {
        GLES.context=context;
    }

    //サーフェイス生成時に呼ばれる
    @Override
    public void onSurfaceCreated(GL10 gl10,EGLConfig eglConfig) {
        //プログラムの生成
        GLES.makeProgram();

        //デプスバッファの有効化
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);

        //光源の有効化
        GLES20.glUniform1i(GLES.useLightHandle,1);

        //光源色の指定
        GLES20.glUniform4f(GLES.lightAmbientHandle,0.2f,0.2f,0.2f,1.0f);
        GLES20.glUniform4f(GLES.lightDiffuseHandle,0.7f,0.7f,0.7f,1.0f);
        GLES20.glUniform4f(GLES.lightSpecularHandle,0.9f,0.9f,0.9f,1.0f);

        //モデルの読み込み
        try {
            model.figure=ObjLoader.load("box.obj");
        } catch (Exception e) {
            android.util.Log.e("debug",e.toString());
            for (StackTraceElement ste:e.getStackTrace()) {
                android.util.Log.e("debug","    "+ste);
            }
        }
    }

    //画面サイズ変更時に呼ばれる
    @Override
    public void onSurfaceChanged(GL10 gl10,int w,int h) {
        //ビューポート変換
        GLES20.glViewport(0,0,w,h);
        aspect=(float)w/(float)h;
    }

    //毎フレーム描画時に呼ばれる
    @Override
    public void onDrawFrame(GL10 gl10) {
        //画面のクリア
        GLES20.glClearColor(1.0f,1.0f,1.0f,1.0f);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT|
                GLES20.GL_DEPTH_BUFFER_BIT);

        //射影変換
        Matrix.setIdentityM(GLES.pMatrix,0);
        GLES.gluPerspective(GLES.pMatrix,
                45.0f,  //Y方向の画角
                aspect, //アスペクト比
                0.01f,  //ニアクリップ
                100.0f);//ファークリップ

        //光源位置の指定
        GLES20.glUniform4f(GLES.lightPosHandle,5.0f,5.0f,5.0f,1.0f);

        //ビュー変換
        Matrix.setIdentityM(GLES.mMatrix,0);
        GLES.gluLookAt(GLES.mMatrix,
                0.0f,0.8f,5.0f, //カメラの視点
                0.0f,0.8f,0.0f, //カメラの焦点
                0.0f,1.0f,0.0f);//カメラの上方向

        //モデル変換
        Matrix.rotateM(GLES.mMatrix,0,angle++,0,1,0);

        //モデルの描画
        model.draw();
    }
}
