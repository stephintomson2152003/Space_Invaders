package model;

/**
 * Author : Stephin Tomson and Andrew Jensen
 */
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

//import demoMediaPlayer.PlayAnMP3.Waiter;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Playlist {

	List<Song> songlist = Collections.synchronizedList(new ArrayList<>());
	private ObservableList<String> observable = FXCollections.observableArrayList();
	private Thread songThread;

	public boolean addSong(Song s) {
		observable.add(s.getTitle() + "-" + s.getArtist());
		return songlist.add(s);
	}

	public void setQueue(ArrayList<Song> list) {
		songlist = list;

		observable.clear();
		for (Song s : list) {
			observable.add(s.getTitle() + "-" + s.getArtist());
		}
	}

	public ObservableList<String> getObservable() {
		return observable;
	}

	public void play() {

		if (songThread == null || !songThread.isAlive()) {
			songThread = new Thread(() -> {
				while (!songlist.isEmpty()) {
					Song curSong = songlist.get(0);
					playSong(curSong);
					songlist.remove(0);
					Platform.runLater(() -> observable.remove(0));
				}
			});
			songThread.start();
		}
	}

	public ArrayList<Song> getSongs() {
		ArrayList<Song> arrayList = new ArrayList<Song>();
		for (Song song : songlist) {
			arrayList.add(song);
		}
		return arrayList;
	}

	private void playSong(Song s) {
		try {
			String path = "./jukebox/songfiles/" + s.getFilename();

			Media media = new Media(new File(path).toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(media);

			mediaPlayer.setOnEndOfMedia(mediaPlayer::dispose);
			mediaPlayer.play();

			Thread.sleep(s.getPlaytime() * 1000 + 2000);

		} catch (Exception e) {
			System.out.println("Error playing song:" + e.getMessage());
		}
	}
}
