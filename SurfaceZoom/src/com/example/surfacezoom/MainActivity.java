package com.example.surfacezoom;

import com.example.surfacezoom.PanAndZoomListener.Anchor;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends Activity implements SurfaceHolder.Callback,
		OnPreparedListener {
	private SurfaceView mSurfaceView;
	private SurfaceHolder mSurfaceHolder;
	private String videoUrl = "http://tv.zing.vn/html5/video/LmJnTicDiDybnLm?device=web_embed_html5";
	//private String videoUrl ="rtsp://93.120.27.78:1935/live/tencrick ";
	private MediaPlayer mMediaPlayer;
	private FrameLayout mFrameLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mFrameLayout = (FrameLayout) this.findViewById(R.id.relMainView);
		mSurfaceView = (SurfaceView) this.findViewById(R.id.surfaceview);
		
		
/*		FrameLayout.LayoutParams fp = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT, Gravity.CENTER);
		FrameLayout view = new FrameLayout(this);
		setContentView(view);

		mSurfaceView = new SurfaceView(this);		
		view.addView(mSurfaceView, fp);
*/
		
		mSurfaceHolder = mSurfaceView.getHolder();
		mSurfaceHolder.addCallback(this);

		mSurfaceView.setOnTouchListener(new PanAndZoomListener(mFrameLayout,
				mSurfaceView, Anchor.CENTER));
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		try {
			mMediaPlayer = new MediaPlayer();
			mMediaPlayer.setDisplay(mSurfaceHolder);
			mMediaPlayer.setDataSource(videoUrl);
			mMediaPlayer.prepare();
			mMediaPlayer.setOnPreparedListener(this);
			mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {

	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		mMediaPlayer.start();
	}

}
