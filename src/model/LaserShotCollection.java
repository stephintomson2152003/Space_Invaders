package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LaserShotCollection {
	private List<LaserShot> shots;

	public LaserShotCollection() {
		this.shots = new ArrayList<>();
	}

	public void add(LaserShot shot) {
		shots.add(shot);
	}

	public void remove(LaserShot shot) {
		shots.remove(shot);
	}

	public void updateList() {
		Iterator<LaserShot> iterator = this.shots.iterator();
		while (iterator.hasNext()) {
			LaserShot shot = iterator.next();
			shot.propogateShot();
			if (shot.getStatus()) {
				iterator.remove();
			}
		}
		
	}

	public List<LaserShot> getActiveShots() {
		return new ArrayList<>(this.shots);
	}
}
