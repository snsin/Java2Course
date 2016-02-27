package ru.geekbrains.finmanager.controllers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import ru.geekbrains.finmanager.models.SysErrs;

public class PassMD5 implements Password {

	@Override
	public String passHash(String password) {
		MessageDigest md5sum = null;
		try {
			md5sum = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.exit(SysErrs.PASS_GENERATE_ERR.ordinal());
		}
		byte[] rawSum = md5sum.digest(password.getBytes());
		StringBuilder passHash = new StringBuilder();
		for (byte b : rawSum) {
			passHash.append(String.format("%02x", b));
		}
		return passHash.toString();

	}

}
