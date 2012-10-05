package de.age.lists;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkDecorator<T> {
	
	private final T object;
	private DoubleLinkDecorator<T> next;
	private DoubleLinkDecorator<T> previous;
	
	public <R extends T> DoubleLinkDecorator(R object) {
		this.object = object;
	}

	public T get() {
		return object;
	}
	
	public DoubleLinkDecorator<T> next() {
		return next;
	}
	
	public DoubleLinkDecorator<T> previous() {
		return previous;
	}

	public DoubleLinkDecorator<T> insertAfter(T nextObject) {
		DoubleLinkDecorator<T> temp = next;
		next = new DoubleLinkDecorator<>(nextObject);
		next.next = temp;
		next.previous = this;
		if (temp != null) {
			temp.previous = next;
		}
		return next;
	}
	
	public DoubleLinkDecorator<T> insertBefore(T previousObject) {
		DoubleLinkDecorator<T> temp = previous;
		previous = new DoubleLinkDecorator<>(previousObject);
		previous.previous = temp;
		previous.next = this;
		if (temp != null) {
			temp.next = previous;
		}
		return previous;
	}

	public DoubleLinkDecorator<T> remove() {
		if (previous != null) {
			previous.next = next;
		}
		if (next != null) {
			next.previous = previous;
		}
		previous = null;
		next = null;
		return this;
	}

	public DoubleLinkDecorator<T> head() {
		DoubleLinkDecorator<T> current = this;
		while (current.previous != null) {
			current = current.previous;
		}
		return current;
	}

	public DoubleLinkDecorator<T> tail() {
		DoubleLinkDecorator<T> current = this;
		while (current.next != null) {
			current = current.next;
		}
		return current;
	}

	public Iterator<T> forward() {
		return new DLDIterator<>(this, DLDIterator.Direction.forward);
	}

	public Iterator<T> backward() {
		return new DLDIterator<>(this, DLDIterator.Direction.backward);
	}

	public DoubleLinkDecorator<T> find(T elementToFind) {
		DoubleLinkDecorator<T> current = this;
		while (current != null && current.get() != elementToFind) {
			current = current.next();
		}
		if (current != null) {
			return current;
		}
		current = this;
		while (current != null && current.get() != elementToFind) {
			current = current.previous();
		}
		return current;
	}
	
	private static final class DLDIterator<T> implements Iterator<T> {
		private static enum Direction {
			forward, backward;
		}

		private DoubleLinkDecorator<T> current;
		private final Direction direction;
		
		public DLDIterator(DoubleLinkDecorator<T> start, Direction direction) {
			current = start;
			this.direction = direction;
		}
		
		@Override
		public boolean hasNext() {
			if (direction == Direction.forward) {
				return current.next() != null;
			} else {
				return current.previous() != null;
			}
		}

		@Override
		public T next() {
			DoubleLinkDecorator<T> next;
			if (direction == Direction.forward) {
				next = current.next();
			} else {
				next = current.previous();
			}
			if (next == null) {
				throw new NoSuchElementException();
			}
			current = next;
			return current.get();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}

}
