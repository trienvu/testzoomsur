package com.example.surfacezoom;

import com.example.surfacezoom.PanAndZoomListener.Anchor;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements SurfaceHolder.Callback,
		OnPreparedListener {
	private SurfaceView mSurfaceView;
	private SurfaceHolder mSurfaceHolder;
	// private String videoUrl =
	// "http://tv.zing.vn/html5/video/LmJnTicDiDybnLm?device=web_embed_html5";
	private String videoUrl = "rtsp://208.77.20.52:1935/dmm1/starplus ";
	private MediaPlayer mMediaPlayer;
	private FrameLayout mFrameLayout;
	private TextView mTvScale;

	private boolean isPause;

	public TextView getTvScale() {
		return this.mTvScale;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mFrameLayout = (FrameLayout) this.findViewById(R.id.frlMainView);
		mSurfaceView = (SurfaceView) this.findViewById(R.id.surfaceview);
		mTvScale = (TextView) this.findViewById(R.id.tvScale);

		mSurfaceHolder = mSurfaceView.getHolder();
		mSurfaceHolder.addCallback(this);

		mSurfaceView.setOnTouchListener(new PanAndZoomListener(MainActivity.this, mFrameLayout,
				mSurfaceView, Anchor.CENTER));

		mTvScale.setVisibility(View.INVISIBLE);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		try {
			String uriPath = "android.resource://com.example.surfacezoom/"
					+ R.raw.dongbanto3;
			Uri uri = Uri.parse(uriPath);
			mMediaPlayer = new MediaPlayer();
			mMediaPlayer.setDisplay(mSurfaceHolder);
			// mMediaPlayer.setDataSource(videoUrl);
			mMediaPlayer.setDataSource(getApplicationContext(), uri);
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

	@Override
	protected void onPause() {
		super.onPause();
		if (mMediaPlayer.isPlaying()) {
			isPause = true;
			mMediaPlayer.pause();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (mTvScale.getVisibility() == View.VISIBLE)
			mTvScale.setVisibility(View.INVISIBLE);
		if (isPause)
			mMediaPlayer.start();
	}
}
