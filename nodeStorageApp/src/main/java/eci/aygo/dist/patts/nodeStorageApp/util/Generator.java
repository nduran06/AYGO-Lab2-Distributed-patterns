package eci.aygo.dist.patts.nodeStorageApp.util;

import java.util.concurrent.atomic.AtomicLong;

public class Generator {

	public static AtomicLong getIdGenerator() {

		return new AtomicLong(0);

	}
}
