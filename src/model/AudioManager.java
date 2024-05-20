package model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class AudioManager {
	private List<MediaPlayer> players = new ArrayList<>();
	private int maxPlayers = 10; // Max number of simultaneous sounds

	public AudioManager() {
		// Keep the list initially empty or with nulls
		for (int i = 0; i < maxPlayers; i++) {
			players.add(null); // Initialize with null, allocate on demand
		}
	}

	public void playSound(String filePath) {
		MediaPlayer currentPlayer = findAvailablePlayer();
		if (currentPlayer == null) {
			System.out.println("All media players are busy.");
			return;
		}

		// Re-initialize the media for the player
		URI uri = new File(filePath).toURI();
		Media media = new Media(uri.toString());
		if (currentPlayer.getStatus() != MediaPlayer.Status.DISPOSED) {
			currentPlayer.stop();
			currentPlayer.dispose(); // Dispose previous media player if not disposed
		}

		// Create a new MediaPlayer for the current media
		MediaPlayer newPlayer = new MediaPlayer(media);
		newPlayer.play();

		// Reset player in the list to null after playing is done
		newPlayer.setOnEndOfMedia(() -> {
			newPlayer.stop();
			newPlayer.dispose();
			players.set(players.indexOf(newPlayer), null); // Reset the slot to null
		});

		// Update the list with the new player
		players.set(players.indexOf(currentPlayer), newPlayer);
	}

	private MediaPlayer findAvailablePlayer() {
		for (int i = 0; i < players.size(); i++) {
			MediaPlayer player = players.get(i);
			if (player == null || player.getStatus() == MediaPlayer.Status.DISPOSED) {
				try {
					String filePath = "src/images/spaceinvaders1.mpeg"; // Ensure this is the correct relative or
																		// absolute
																		// path
					Media media = new Media(new File(filePath).toURI().toString());
					MediaPlayer newPlayer = new MediaPlayer(media);
					players.set(i, newPlayer);
					return newPlayer;
				} catch (Exception e) {
					System.out.println("Error initializing media player with file: " + e.getMessage());
					return null;
				}
			} else if (player.getStatus() == MediaPlayer.Status.STOPPED) {
				return player; // Reuse a stopped player
			}
		}
		return null; // No available player
	}

}
