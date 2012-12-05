package com.me4502.MAPL;

/*
 * Tuple2 - Copied from CraftBook, Copyright Lymia
 */
public class Tuple2<A, B> {

	public final A a;
	public final B b;

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
		return (a.hashCode() * 1103515245 + 12345 ^
				b.hashCode() * 1103515245 + 12345) * 1103515245 + 12345;
	}
}