public class Main {
    public static void main(String[] args) {
        HuffmanCoding encoder = new HuffmanCoding();

        String message = "One of the earliest forms of data compression was through Morse code, which allowed for the transmission of textual information over a wire. Each letter has been encoded based on its typical frequency of occurrence, with more common letters receiving shorter messages (such as the letter 'e' being represented as a single dot). A later version of data compression, called Huffman encoding, arose with the rise of the internet in the 1970s. Being dynamically generated on input data, Huffman encoding was an efficient means of data compression until the invention of the LZ77 algorithm in 1977.";
        String encoded = encoder.encode(message);
        String key = encoder.getKey();

        int meslen = message.length() * 16;
        int strlen = encoded.length() ;
        int keylen = key.length() * 16;

        System.out.println("Our message would be " + meslen + "bits long and encoded it would be " + strlen + keylen + "bits long.");

        System.out.println(encoded);
        System.out.println(key);

        HuffmanInterface decoder = new HuffmanCoding(key);
        String decoded = decoder.decode(encoded);
        System.out.println(decoded);
    }
}


// Noah Marino
//
// N 1
// O 2
// a 3
//
//
//
//
//
//
//
