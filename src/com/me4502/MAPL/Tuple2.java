package com.me4502.MAPL;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Tuple2 - Copied from CraftBook. Stores 2 objects.
 * 
 * @author Lymia
 */
public class Tuple2<A, B> {

	@JsonProperty("a")
	public A a;
	@JsonProperty("b")
	public B b;

	public Tuple2() {
	}

	public Tuple2(A a, B b) {

		this.a = a;
		this.b = b;
	}

	@Override
	public boolean equals(Object o) {

		return o instanceof Tuple2<?, ?> && equals((Tuple2<?, ?>) o);
	}

	public boolean equals(Tuple2<?, ?> o) {

		return o.a.equals(a) && o.b.equals(b);
	}

	@Override
	public int hashCode() {
		// Constants correspond to glibc's lcg algorithm parameters
		return (a.hashCode() * 1103515245 + 12345 ^ b.hashCode() * 1103515245 + 12345) * 1103515245 + 12345;
	}
}