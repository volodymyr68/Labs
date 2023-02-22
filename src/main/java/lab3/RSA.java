package lab3;

import java.security.SecureRandom;

import java.math.BigInteger;
import java.sql.SQLOutput;

public class RSA {

    private final static BigInteger one = new BigInteger("1");
    private final static SecureRandom random = new SecureRandom();

    private BigInteger privateKey;
    private BigInteger publicKey;
    private BigInteger modulus;

    public RSA(int bitLength) {
        BigInteger p = BigInteger.probablePrime(bitLength/2, random);
        BigInteger q = BigInteger.probablePrime(bitLength/2, random);
        BigInteger phi = (p.subtract(one)).multiply(q.subtract(one));
        modulus = p.multiply(q);
        publicKey = new BigInteger("65537"); // commonly used value for e
        privateKey = publicKey.modInverse(phi);
    }

    public byte[] encrypt(byte[] message) {
        BigInteger m = new BigInteger(message);
        BigInteger c = m.modPow(publicKey, modulus);
        return c.toByteArray();
    }

    public byte[] decrypt(byte[] message) {
        BigInteger c = new BigInteger(message);
        BigInteger m = c.modPow(privateKey, modulus);
        return m.toByteArray();
    }

    public static void main(String[] args) {
        RSA rsa = new RSA(1024);
        String message = "Hello, world!";
        byte[] encrypted = rsa.encrypt(message.getBytes());
        byte[] decrypted = rsa.decrypt(encrypted);
        System.out.println("Original message: " + message);
        System.out.println("Encrypted message: " + new String(encrypted));
        System.out.println("Decrypted message: " + new String(decrypted));
    }
}