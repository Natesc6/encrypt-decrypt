
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    static String[][] S = new String[][]{
        {"01100011", "01111100", "01110111", "01111011", "11110010", "01101011", "01101111", "11000101", "00110000", "00000001", "01100111", "00101011", "11111110", "11010111", "10101011", "01110110"},
        {"11001010", "10000010", "11001001", "01111101", "11111010", "01011001", "01000111", "11110000", "10101101", "11010100", "10100010", "10101111", "10011100", "10100100", "01110010", "11000000"},
        {"10110111", "11111101", "10010011", "00100110", "00110110", "00111111", "11110111", "11001100", "00110100", "10100101", "11100101", "11110001", "01110001", "11011000", "00110001", "00010101"},
        {"00000100", "11000111", "00100011", "11000011", "00011000", "10010110", "00000101", "10011010", "00000111", "00010010", "10000000", "11100010", "11101011", "00100111", "10110010", "01110101"},
        {"00001001", "10000011", "00101100", "00011010", "00011011", "01101110", "01011010", "10100000", "01010010", "00111011", "11010110", "10110011", "00101001", "11100011", "00101111", "10000100"},
        {"01010011", "11010001", "00000000", "11101101", "00100000", "11111100", "10110001", "01011011", "01101010", "11001011", "10111110", "00111001", "01001010", "01001100", "01011000", "11001111"},
        {"11010000", "11101111", "10101010", "11111011", "01000011", "01001101", "00110011", "10000101", "01000101", "11111001", "00000010", "01111111", "01010000", "00111100", "10011111", "10101000"},
        {"01010001", "10100011", "01000000", "10001111", "10010010", "10011101", "00111000", "11110101", "10111100", "10110110", "11011010", "00100001", "00010000", "11111111", "11110011", "11010010"},
        {"11001101", "00001100", "00010011", "11101100", "01011111", "10010111", "01000100", "00010111", "11000100", "10100111", "01111110", "00111101", "01100100", "01011101", "00011001", "01110011"},
        {"01100000", "10000001", "01001111", "11011100", "00100010", "00101010", "10010000", "10001000", "01000110", "11101110", "10111000", "00010100", "11011110", "01011110", "00001011", "11011011"},
        {"11100000", "00110010", "00111010", "00001010", "01001001", "00000110", "00100100", "01011100", "11000010", "11010011", "10101100", "01100010", "10010001", "10010101", "11100100", "01111001"},
        {"11100111", "11001000", "00110111", "01101101", "10001101", "11010101", "01001110", "10101001", "01101100", "01010110", "11110100", "11101010", "01100101", "01111010", "10101110", "00001000"},
        {"10111010", "01111000", "00100101", "00101110", "00011100", "10100110", "10110100", "11000110", "11101000", "11011101", "01110100", "00011111", "01001011", "10111101", "10001011", "10001010"},
        {"01110000", "00111110", "10110101", "01100110", "01001000", "00000011", "11110110", "00001110", "01100001", "00110101", "01010111", "10111001", "10000110", "11000001", "00011101", "10011110"},
        {"11100001", "11111000", "10011000", "00010001", "01101001", "11011001", "10001110", "10010100", "10011011", "00011110", "10000111", "11101001", "11001110", "01010101", "00101000", "11011111"},
        {"10001100", "10100001", "10001001", "00001101", "10111111", "11100110", "01000010", "01101000", "01000001", "10011001", "00101101", "00001111", "10110000", "01010100", "10111011", "00010110"}
    };

    // Converts binary string to decimal
    public static String binaryToDecimal(String binaryString) {
        int decimalValue = Integer.parseInt(binaryString, 2);
        String value = Integer.toString(decimalValue);
        return value;
    }

   // Reads encrypted blocks from file
    public static List<String> readEncryptedBlocksFromFile(String fileName) {
        List<String> blocks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().length() > 0) {
                    blocks.add(line.trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return blocks;
    }

    // Converts file to 64-bit blocks
    public static List<String> fileTo64BitBlocks(String fileName) {
        List<String> blocks = new ArrayList<>();
        StringBuilder buffer = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (char c : line.toCharArray()) {
                    String bin = Integer.toBinaryString(c);
                    while (bin.length() < 8) {
                        bin = "0" + bin;
                    }
                    buffer.append(bin);
                }

                while (buffer.length() >= 64) {
                    blocks.add(buffer.substring(0, 64));
                    buffer.delete(0, 64);
                }
            }

            if (buffer.length() > 0) {
                while (buffer.length() < 64) {
                    buffer.append('0');
                }
                blocks.add(buffer.toString());
            }

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        return blocks;
    }

    // Converts binary string to text
    public static String binaryToText(String binary) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < binary.length(); i += 8) {
            if (i + 8 <= binary.length()) {
                String byteBinary = binary.substring(i, i + 8);

                if (!byteBinary.equals("00000000")) {
                    int charCode = Integer.parseInt(byteBinary, 2);

                    if (charCode >= 32 && charCode <= 126) {
                        text.append((char) charCode);
                    }
                }
            }
        }
        return text.toString();
    }

    // Converts string to 56-bit
    public static String stringTo56Bit(String input) {
        int hash = input.hashCode();
        String binaryString = Integer.toBinaryString(hash);
        int length = binaryString.length();
        if (length < 56) {
            binaryString = "0".repeat(56 - length) + binaryString;
        } else if (length > 56) {
            binaryString = binaryString.substring(length - 56);
        }
        return binaryString;
    }

    // Splits input string into two equal parts
    public static String[] splitStringInHalf(String input) {
        int mid = input.length() / 2;
        String firstHalf = input.substring(0, mid);
        String secondHalf = input.substring(mid);
        return new String[]{firstHalf, secondHalf};
    }

    // Splits input string into four equal parts
    public static Stack<String> splitStackFunction(Stack<String> inputStack) {
        if (inputStack == null || inputStack.isEmpty()) {
            throw new IllegalArgumentException("Input stack cannot be null or empty");
        }

        String input = inputStack.pop();

        int quarter = input.length() / 4;
        int mid = input.length() / 2;
        int threeQuarters = input.length() * 3 / 4;

        String firstQuarter = input.substring(0, quarter);
        String secondQuarter = input.substring(quarter, mid);
        String thirdQuarter = input.substring(mid, threeQuarters);
        String fourthQuarter = input.substring(threeQuarters);

        Stack<String> resultStack = new Stack<>();

        resultStack.push(fourthQuarter);
        resultStack.push(thirdQuarter);
        resultStack.push(secondQuarter);
        resultStack.push(firstQuarter);

        return resultStack;
    }

    // Shifts input string
    public static String shiftIt(String input) {
        return input.substring(1) + input.charAt(0);
    }

    // Performs XOR (exclusive or) operation
    public static String xorit(String x, String y) {
        if (x == null || y == null || x.length() != y.length()) {
            throw new IllegalArgumentException("Invalid input");
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < x.length(); i++) {
            if (x.charAt(i) != y.charAt(i)) {
                result.append("1");
            } else {
                result.append("0");
            }
        }
        return result.toString();
    }

    // Performs substitution operation
    public static String substitutionS(String x) {
        String[] split = splitStringInHalf(x);
        String row = binaryToDecimal(split[0]);
        String col = binaryToDecimal(split[1]);

        int rowIndex = Integer.parseInt(row);
        int colIndex = Integer.parseInt(col);

        rowIndex = Math.min(Math.max(rowIndex, 0), 15);
        colIndex = Math.min(Math.max(colIndex, 0), 15);

        String s = S[rowIndex][colIndex];
        return s;
    }

    // Performs permutation operation
    public static String permuteIt(String x) {
        List<Integer> values = Arrays.asList(
                16, 7, 30, 11, 29, 12, 28, 17,
                1, 15, 23, 26, 5, 18, 31, 10,
                2, 8, 24, 14, 32, 27, 3, 9,
                19, 13, 20, 6, 22, 21, 4, 25
        );
        String p = "";
        for (int i = 0; i < 32; i++) {
            p += x.charAt(values.get(i) - 1);
        }
        return p;
    }

    // Performs function F as described in the specification
    public static Stack<String> functionF(Stack<String> rightHalfStack, Stack<String> subkeyStack) {
        if (rightHalfStack == null || rightHalfStack.isEmpty()
                || subkeyStack == null || subkeyStack.isEmpty()) {
            throw new IllegalArgumentException("Invalid input: right half or subkey stack");
        }

        String rightHalf = rightHalfStack.pop();
        String subkey = subkeyStack.pop();

        String xorResult = xorit(rightHalf, subkey);

        String[] quarters = splitStringFunction(xorResult);

        String one = substitutionS(quarters[0]);
        String two = substitutionS(quarters[1]);
        String three = substitutionS(quarters[2]);
        String four = substitutionS(quarters[3]);

        String permutedResult = permuteIt(one + two + three + four);

        Stack<String> resultStack = new Stack<>();
        resultStack.push(permutedResult);
        return resultStack;
    }

    // Splits input string into four equal parts
    public static String[] splitStringFunction(String input) {
        int quarter = input.length() / 4;
        int mid = input.length() / 2;
        int threeQuarters = input.length() * 3 / 4;

        return new String[]{
            input.substring(0, quarter),
            input.substring(quarter, mid),
            input.substring(mid, threeQuarters),
            input.substring(threeQuarters)
        };
    }

    // Encrypts a 64-bit block using function f network
    public static String encryptBlock(String block, Queue<String> subkeys) {
        if (block == null || subkeys == null || subkeys.isEmpty()) {
            throw new IllegalArgumentException("Invalid input: block or subkeys");
        }

        String[] half = splitStringInHalf(block);
        String leftHalf = half[0];
        String rightHalf = half[1];

        Queue<String> subkeysCopy = new LinkedList<>(subkeys);

        for (int i = 0; i < 10; i++) {
            String subkey = subkeysCopy.poll();

            Stack<String> rightStack = new Stack<>();
            rightStack.push(rightHalf);
            Stack<String> subkeyStack = new Stack<>();
            subkeyStack.push(subkey);

            Stack<String> f = functionF(rightStack, subkeyStack);

            String xorResult = xorit(leftHalf, f.peek());

            leftHalf = rightHalf;
            rightHalf = xorResult;
        }

        return rightHalf + leftHalf;
    }

    // Decrypts a 64-bit block using function f network
    public static String decryptBlock(String block, Stack<String> subkeys) {
        if (block == null || subkeys == null || subkeys.isEmpty()) {
            throw new IllegalArgumentException("Invalid input: block or subkeys");
        }
        String[] half = splitStringInHalf(block);
        String leftHalf = half[0];
        String rightHalf = half[1];

        Stack<String> subkeysCopy = new Stack<>();
        subkeysCopy.addAll(subkeys);

        for (int i = 0; i < 10; i++) {
            String subkey = subkeysCopy.pop();

            Stack<String> rightStack = new Stack<>();
            rightStack.push(rightHalf);
            Stack<String> subkeyStack = new Stack<>();
            subkeyStack.push(subkey);

            Stack<String> f = functionF(rightStack, subkeyStack);

            String xorResult = xorit(leftHalf, f.peek());

            leftHalf = rightHalf;
            rightHalf = xorResult;
        }

        return rightHalf + leftHalf;
    }

    // Creates 10 subkeys
    public static String[] keyScheduleTransform(String key56bit) {
        String[] subkeys = new String[10];
        String C = key56bit.substring(0, 28);
        String D = key56bit.substring(28);

        for (int i = 0; i < 10; i++) {
            C = shiftIt(C);
            D = shiftIt(D);
            String combined = C + D;
            subkeys[i] = combined.substring(0, 32);
        }

        return subkeys;
    }

    // Main encryption wrapper function
    public static String encryption(String longBinaryInput, String inputKey) {
        String key56bit = stringTo56Bit(inputKey);
        String[] subkeys = keyScheduleTransform(key56bit);

        Queue<String> subkeyQueue = new LinkedList<>(Arrays.asList(subkeys));

        return encryptBlock(longBinaryInput, subkeyQueue);
    }

   // Main decryption wrapper function
    public static String decryption(String longBinaryInput, String inputKey) {
        String key56bit = stringTo56Bit(inputKey);
        String[] subkeys = keyScheduleTransform(key56bit);

        Stack<String> subkeyStack = new Stack<>();
        subkeyStack.addAll(Arrays.asList(subkeys));

        return decryptBlock(longBinaryInput, subkeyStack);
    }

    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Do you want to encrypt or decrypt (E/D): ");
        String choice = scanner.nextLine().toUpperCase();

        System.out.print("Filename: ");
        String inputFile = scanner.nextLine();

        System.out.print("Secret key: ");
        String secretKey = scanner.nextLine();

        System.out.print("Output file: ");
        String outputFile = scanner.nextLine();

        try {
            if (choice.equals("E")) {
                List<String> blocks = fileTo64BitBlocks(inputFile);
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                    for (String block : blocks) {
                        String encryptedBlock = encryption(block, secretKey);
                        writer.write(encryptedBlock);
                        writer.newLine();
                    }
                }
            } else if (choice.equals("D")) {
                List<String> encryptedBlocks = readEncryptedBlocksFromFile(inputFile);
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                    StringBuilder decryptedText = new StringBuilder();
                    for (String block : encryptedBlocks) {
                        String decryptedBlock = decryption(block, secretKey);
                        decryptedText.append(decryptedBlock);
                    }
                    String text = binaryToText(decryptedText.toString());
                    writer.write(text);
                }
            } else {
                System.out.println("Invalid choice. Please enter E for encryption or D for decryption.");
            }
        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
