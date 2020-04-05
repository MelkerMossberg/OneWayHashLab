import java.math.BigInteger;

public class BitCounter {
    static int[] binaryMD5;
    static int[] binaryChangedMD5;
    static int[] binarySHA256;
    static int[] binaryChangedSHA256;

    public static void main(String [] args){
    binaryMD5 = hexStringToBinaryArray("75d6654deb5bdced2ce590b25f366010");
    binaryChangedMD5 = hexStringToBinaryArray("d791611b6e66f3947a9244c8159bd5ea");
    binarySHA256 = hexStringToBinaryArray("7b7b128b11b45f91122dea6fe1ed01472ee184a7c0d04e50e570ca6d563bd893");
    binaryChangedSHA256 = hexStringToBinaryArray("f6bdd064e076ba8be4a34549a22188bd4c840cb230caa307d15dde21ed64c364");

    int MD5BitsInCommon = compareSimilarBits(binaryMD5, binaryChangedMD5);
    int SHA256BitsInCommon = compareSimilarBits(binarySHA256, binaryChangedSHA256);
    int MD5FractionInCommon = (int)(((float)MD5BitsInCommon / binaryMD5.length)*100);
    int SHA256FractionInCommon = (int)(((float)SHA256BitsInCommon / binarySHA256.length)*100);

    System.out.println("MD5BitsInCommon:\t" + MD5BitsInCommon +"/" + binaryMD5.length + "\t" + MD5FractionInCommon + "%");
    System.out.println("SHA256BitsInCommon:\t" + SHA256BitsInCommon +"/" + binarySHA256.length + "\t" + SHA256FractionInCommon + "%");
    }

    private static int compareSimilarBits(int[] arr1, int[] arr2) {
        int bitsInCommon = 0;
        for (int i = 0; i < arr1.length ; i++){
            if (arr1[i] == arr2[i]) bitsInCommon++;
        }
        return bitsInCommon;
    }

    public static int[] hexStringToBinaryArray(String hex) {
        String[] binaryString = new BigInteger(hex, 16).toString(2).split("");
        int[] binaryInts = new int[binaryString.length];
        for (int i = 0; i < binaryString.length; i++) {
            binaryInts[i] = Integer.parseInt(binaryString[i]);
        }
        return binaryInts;
    }
}
