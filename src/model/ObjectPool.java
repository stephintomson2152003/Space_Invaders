package model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ObjectPool<T> {
	private final Supplier<T> creator;
	private final Consumer<T> resetter;
	private final Queue<T> pool;

	public ObjectPool(Supplier<T> creator, Consumer<T> resetter, int initialSize) {
		this.creator = creator;
		this.resetter = resetter;
		this.pool = new LinkedList<>();
		for (int i = 0; i < initialSize; i++) {
			pool.offer(creator.get());
		}
	}

	public T borrow() {
		if (pool.isEmpty()) {
			return creator.get();
		}
		return pool.poll();
	}

	public void giveBack(T object) {
		resetter.accept(object);
		pool.offer(object);
	}
}
