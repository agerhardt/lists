package de.age.lists;

public class DoubleLinkDecorator<T> {
	
	private final T object;
	private DoubleLinkDecorator<T> next;
	private DoubleLinkDecorator<T> previous;
	
	public <R extends T> DoubleLinkDecorator(R object) {
		this.object = object;
	}

	public T getObject() {
		return object;
	}
	
	public DoubleLinkDecorator<T> next() {
		return next;
	}
	
	public DoubleLinkDecorator<T> previous() {
		return previous;
	}

	public DoubleLinkDecorator<T> append(T nextObject) {
		DoubleLinkDecorator<T> temp = next;
		next = new DoubleLinkDecorator<>(nextObject);
		next.next = temp;
		next.previous = this;
		if (temp != null) {
			temp.previous = next;
		}
		return next;
	}
	
	public DoubleLinkDecorator<T> insert(T previousObject) {
		DoubleLinkDecorator<T> temp = previous;
		previous = new DoubleLinkDecorator<>(previousObject);
		previous.previous = temp;
		previous.next = this;
		if (temp != null) {
			temp.next = previous;
		}
		return previous;
	}

}
