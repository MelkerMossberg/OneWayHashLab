import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;

public class HashForce {
    static byte[] originalHash;
    static int byteLength = 3;
    static long startTime;

    public static void main(String [] args) {
        String inputString = args[0];
        originalHash = Arrays.copyOf(hexStringToByteArray(inputString), byteLength);
        for (byte b : originalHash) System.out.print(b + " ");
        System.out.println();
        String digestAlgorithm = "SHA-256";

        try {
            MessageDigest md = MessageDigest.getInstance(digestAlgorithm);
            int attempts = bruteForceHash(originalHash, md);
            System.out.println("Num of attempts: " + attempts);

        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static int bruteForceHash(byte[] originalHash, MessageDigest md) {
        int numberOfAttempts = 0;
        startTime = System.currentTimeMillis();
        for (int a = 0; a < 256; a++){
            for (int b = 0; b < 256; b++){
                for(int c = 0; c < 256; c ++){
                    numberOfAttempts++;
                    byte[] generatedMsg = {(byte)a,(byte)b,(byte)c};
                    md.update(generatedMsg);
                    byte[] msgDigest = Arrays.copyOf(md.digest(),byteLength);
                    for (byte x : msgDigest) System.out.print(x + " ");
                    System.out.println();
                    if (Arrays.equals(msgDigest, originalHash)){
                        System.out.println("GOT IT:");
                        for (byte x : msgDigest) System.out.print(x + " ");
                        System.out.println("\n" + new String(msgDigest, StandardCharsets.UTF_8));
                        int timePassed = getTimeDiff();
                        System.out.println("Brute force took: " + timePassed + "seconds");
                        return numberOfAttempts;
                    }
                }
            }
        }
        return -1;
    }

    private static int getTimeDiff() {
        long nowMillis = System.currentTimeMillis();
        return (int)((nowMillis - startTime) / 1000);
    }


    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

}
