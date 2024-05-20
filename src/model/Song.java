package model;

/**
 * Author : Stephin Tomson and Andrew Jensen
 */
import java.io.Serializable;

public class Song implements Serializable {

	private String title;
	private String artist;
	private int playtime;
	private String playtimeString;
	private String filename;

	public Song(String title, String artist, int playtime, String playtimeString, String filepath) {
		this.title = title;
		this.artist = artist;
		this.playtime = playtime;
		this.playtimeString = playtimeString;
		this.filename = filepath;
	}

	public String getTitle() {
		return title;
	}

	public String getArtist() {
		return artist;
	}

	public int getPlaytime() {
		return playtime;
	}

	public String getPlaytimeString() {
		return playtimeString;
	}

	public String getFilename() {
		return filename;
	}
}
