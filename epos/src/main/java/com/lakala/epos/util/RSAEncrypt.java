/*
 * Copyright 2013-2014 the original author or authors.
 */

package com.lakala.epos.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.apache.log4j.Logger;

/**
 * TODO 类描述
 * <p>
 * Created on 2017年4月6日
 * <p>
 * 
 * @author liuyinlong<liuyinlong@lakala.com>
 * @since 2017年4月6日
 */
public class RSAEncrypt {
	private static Logger logger = Logger.getLogger(RSAEncrypt.class);
	private static final String DEFAULT_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCjTuSRErEquzrKcGt35XdOtHWq\rWB94XU70XZs2qb+Ae4A+21qPW2kD0yRggyiY53DjX/jfzZD4r4Nj8qcBeuXr4dUh\r2bLcQRHfMdV/5fg3jLmLjhSHabVVarURc`0HoFOJuXxjSPrn/2r/l5O42x0UszhyW\rpIigj4QKNp8MD3JGRQIDAQAB\r";
	public static final String DEFAULT_PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKNO5JESsSq7Ospw\ra3fld060dapYH3hdTvRdmzapv4B7gD7bWo9baQPTJGCDKJjncONf+N/NkPivg2Py\rpwF65evh1SHZstxBEd8x1X/l+DeMuYuOFIdptVVqtRFzQegU4m5fGNI+uf/av+Xk\r7jbHRSzOHJakiKCPhAo2nwwPckZFAgMBAAECgYBMwueS/VtDf7A5ua/6MmTrHM32\rHOfl9s4G9PzhK9b0+Jgmc4rUuZ3aC2Qc7WmmvZsFdXYRFJohGrbCwysW+gKaJkLC\rqePu4893Ih4+0woOsyp8cnY8N/0ZDrcVeUqRT5zv70syzuvxDJYoTktYdxJMym+T\raR3jZn2nUNvIN67mHQJBANMYTMSl7mERDfboaVvfboB9YDaapNyX6a2J0J7vF6WF\rmmxHmi60+04oWmmcx0fV49GdEtX1Y14ra4+AdPlGEfcCQQDGDD8gA+OuK+PoL2vx\rQyl0RUfYur3Tc3UZEmMbPluBzRq3qcTa9+3h67q/Rk9bteQRWH/FSj66HgZvtrGV\rnFqjAkArjRrhg15f9lXMN/zssHcq2TMNcPBMdqcGX6WD9M4LHzpR1p/vvPUQFfE5\rUtgmcPF0oJRvQhZDKnAhhh4cA6HXAkEAly8QHTN64iG1KDDTOOipQ44Dq6nacQmJ\rUWtc5pYyqqLVC3F4yiNLoBQgihV1TTBL+wXQ+92ShueWyD7xz6D8+wJBAJsQPLR8\rYNc4h8opTT6AbT66sADg54At+b6s46+MNZ8v0z7oWXsQknvPKCeCQ/V035dvkD4H\rbl2IJkchQq4gu/o=\r";
	private RSAPrivateKey privateKey;
	private RSAPublicKey publicKey;
	private static final char[] HEX_CHAR = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public RSAPrivateKey getPrivateKey() {
		return this.privateKey;
	}

	public RSAPublicKey getPublicKey() {
		return this.publicKey;
	}

	public void genKeyPair() {
		KeyPairGenerator keyPairGen = null;
		try {
			keyPairGen = KeyPairGenerator.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		keyPairGen.initialize(1024, new SecureRandom());
		KeyPair keyPair = keyPairGen.generateKeyPair();
		this.privateKey = ((RSAPrivateKey) keyPair.getPrivate());
		this.publicKey = ((RSAPublicKey) keyPair.getPublic());
	}

	public void loadPublicKey(InputStream in) throws Exception {
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(in));
			String readLine = null;
			StringBuilder sb = new StringBuilder();
			while (true) {
				while (true) {
					if ((readLine = br.readLine()) == null)
						break label67;
					if (readLine.charAt(0) != '-')
						break;
				}
				sb.append(readLine);
				sb.append('\r');
			}

			label67: loadPublicKey(sb.toString());
		} catch (IOException e) {
			throw new Exception("公钥数据流读取错误");
		} catch (NullPointerException e) {
			throw new Exception("公钥输入流为空");
		}
	}

	public static String getKeyString(Key key) throws Exception {
		byte[] keyBytes = key.getEncoded();
		String s = Base64.encode(keyBytes);
		return s;
	}

	public void loadPublicKey(String publicKeyStr) throws Exception {
		byte[] buffer;
		try {
			buffer = Base64.decode(publicKeyStr);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
			this.publicKey = ((RSAPublicKey) keyFactory.generatePublic(keySpec));
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			throw new Exception("公钥非法");
		} catch (NullPointerException e) {
			throw new Exception("公钥数据为空");
		}
	}

	public boolean verifySign(String message, String signStr, PublicKey key)
			throws Exception {
		if ((message == null) || (signStr == null) || (key == null))
			return false;

		Signature signetcheck = Signature.getInstance("MD5withRSA");
		signetcheck.initVerify(key);
		signetcheck.update(message.getBytes("UTF-8"));
		return signetcheck.verify(toBytes(signStr));
	}

	public byte[] sign(String message, PrivateKey key) throws Exception {
		Signature signetcheck = Signature.getInstance("MD5withRSA");
		signetcheck.initSign(key);
		signetcheck.update(message.getBytes("UTF-8"));
		return signetcheck.sign();
	}

	public static final byte[] toBytes(String s) {
		byte[] bytes = new byte[s.length() / 2];
		for (int i = 0; i < bytes.length; ++i)
			bytes[i] = (byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2),
					16);

		return bytes;
	}

	public void loadPrivateKey(InputStream in) throws Exception {
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(in));
			String readLine = null;
			StringBuilder sb = new StringBuilder();
			while (true) {
				while (true) {
					if ((readLine = br.readLine()) == null)
						break label67;
					if (readLine.charAt(0) != '-')
						break;
				}
				sb.append(readLine);
				sb.append('\r');
			}

			label67: loadPrivateKey(sb.toString());
		} catch (IOException e) {
			throw new Exception("私钥数据读取错误");
		} catch (NullPointerException e) {
			throw new Exception("私钥输入流为空");
		}
	}

	public void loadPrivateKey(String privateKeyStr) throws Exception {
		byte[] buffer;
		try {
			buffer = Base64.decode(privateKeyStr);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			this.privateKey = ((RSAPrivateKey) keyFactory
					.generatePrivate(keySpec));
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			throw new Exception("私钥非法");
		} catch (NullPointerException e) {
			throw new Exception("私钥数据为空");
		}
	}

	public byte[] encrypt(RSAPublicKey publicKey, byte[] plainTextData)
			throws Exception {
		if (publicKey == null)
			throw new Exception("加密公钥为空, 请设置");

		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

			cipher.init(1, publicKey);
			byte[] output = cipher.doFinal(plainTextData);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此加密算法");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("加密公钥非法,请检查");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("明文长度非法");
		} catch (BadPaddingException e) {
			throw new Exception("明文数据已损坏");
		}
	}

	public byte[] decrypt(RSAPrivateKey privateKey, byte[] cipherData)
			throws Exception {
		if (privateKey == null)
			throw new Exception("解密私钥为空, 请设置");

		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(2, privateKey);
			byte[] output = cipher.doFinal(cipherData);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此解密算法");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("解密私钥非法,请检查");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("密文长度非法");
		} catch (BadPaddingException e) {
			throw new Exception("密文数据已损坏");
		}
	}

	public static String byteArrayToString(byte[] data) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < data.length; ++i) {
			stringBuilder.append(HEX_CHAR[((data[i] & 0xF0) >>> 4)]);

			stringBuilder.append(HEX_CHAR[(data[i] & 0xF)]);
			if (i < data.length - 1)
				;
		}

		return stringBuilder.toString();
	}

	public static String toHexString(byte[] b) {
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; ++i) {
			sb.append(HEX_CHAR[((b[i] & 0xF0) >>> 4)]);
			sb.append(HEX_CHAR[(b[i] & 0xF)]);
		}
		return sb.toString();
	}

	public static byte[] hex2byte(byte[] b) {
		if (b.length % 2 != 0) {
			throw new IllegalArgumentException("长度不是偶数");
		}
		byte[] b2 = new byte[b.length / 2];

		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);

			b2[(n / 2)] = (byte) Integer.parseInt(item, 16);
		}

		return b2;
	}

	public static void main(String[] args) throws Exception {
		RSAEncrypt rsaEncrypt = new RSAEncrypt();
		try {
			rsaEncrypt.loadPublicKey(RSAEncrypt.class
					.getResourceAsStream("/rsa_public_key.pem"));
			System.out.println("加载公钥成功");
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.err.println("加载公钥失败");
		}

		try {
			rsaEncrypt.loadPrivateKey(RSAEncrypt.class
					.getResourceAsStream("/pkcs8_rsa_private_key.pem"));
			System.out.println("加载私钥成功");
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.err.println("加载私钥失败");
		}

		String encryptStr = "lkl123";
		try {
			byte[] cipher = rsaEncrypt.encrypt(rsaEncrypt.getPublicKey(),
					encryptStr.getBytes());

			byte[] plainText = rsaEncrypt.decrypt(rsaEncrypt.getPrivateKey(),
					cipher);
			System.out.println(new StringBuilder().append("密文长度:")
					.append(cipher.length).toString());
			System.out.println(byteArrayToString(cipher));
			System.out.println(toHexString(cipher));
			System.out.println(new StringBuilder().append("明文长度:")
					.append(plainText.length).toString());
			System.out.println(byteArrayToString(plainText));
			System.out.println(new String(plainText));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
