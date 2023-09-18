package usuario.example.tpo4.ui.gallery;

import android.content.Context;
import android.media.MediaPlayer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GalleryViewModel extends ViewModel {

    private MediaPlayer mediaPlayer;
    private boolean isPlaying;

    public void startPlaying(Context context, int resId) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, resId);
            mediaPlayer.setOnCompletionListener((MediaPlayer mp) -> {
                stopPlaying();
            });
        }

        if (!isPlaying) {
            mediaPlayer.start();
            isPlaying = true;
        }
    }

    public void pausePlaying() {
        if (mediaPlayer != null && isPlaying) {
            mediaPlayer.pause();
            isPlaying = false;
        }
    }

    public void stopPlaying() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            isPlaying = false;
        }
    }

    public boolean isPlaying() {
        return isPlaying;
    }

}