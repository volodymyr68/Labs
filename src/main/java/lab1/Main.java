package lab1;



public class Main {

    public static void main(String[] args) {
        String message = "Hello, world!";
        byte[] hash = Sha256.hash(message.getBytes());

        System.out.println("Message: " + message);
        System.out.println("Hash: " + bytesToHex(hash));
    }

    private static String bytesToHex(byte[] bytes) {
        char[] hexArray = "0123456789abcdef".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xff;
            hexChars[i * 2] = hexArray[v >>> 4];
            hexChars[i * 2 + 1] = hexArray[v & 0x0f];
        }
        return new String(hexChars);
    }
}