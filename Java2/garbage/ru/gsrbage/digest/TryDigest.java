package ru.gsrbage.digest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TryDigest {
	public static void digestTry() {
		MessageDigest md5sum = null;
		try {
			md5sum = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] hash = md5sum.digest("tres".getBytes());
		System.out.println(md5sum.digest("ones".getBytes()));
		System.out.println(md5sum.digest("ones".getBytes()));
		System.out.println(md5sum.digest("tres".getBytes()));
		for (byte b : hash) {
			System.out.printf("%02x", b);
		}
		System.out.println();
		System.out.println(md5sum.digest("fsa".getBytes()));
	}
}
