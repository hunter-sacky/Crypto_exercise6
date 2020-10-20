/*
  Name: Hunter Sacky
  Student ID: jop757 @01681704
  Title: CryptoEngine.java


  Citations:
	https://docs.oracle.com/javase/7/docs/api/java/io/IOException.html
	https://docs.oracle.com/javase/8/docs/api/java/nio/package-summary.html
	https://docs.oracle.com/javase/7/docs/api/java/nio/file/Files.html
	https://docs.oracle.com/javase/7/docs/api/javax/crypto/package-summary.html
*/


// Why are these packages needed in this program?
// Comment above each one with your answer. 

//This package has a lot of utilies for java such as reading from an input 
import java.util.*;
//This is needed in order to read and write to files.
import java.nio.file.Files;
//This is needed for getting the path of files.
import java.nio.file.Paths;
//This imports the whole crypto package which is used for cryptographic operations.
import javax.crypto.*;
//This exception is needed for when the file is not found.
import java.io.FileNotFoundException;
//This exception is needed for when there is a failure in the input or output operations.
import java.io.IOException;
//This exception is needed for when the key used for encryption has something wrong with it. I.e., wrong length.
import java.security.InvalidKeyException;
//This exception occurs when the program is requesting to use an algorithm that is not available for use by the progran.
import java.security.NoSuchAlgorithmException;

public class CryptoEngine {
	// What is the function of these four variables?
        // Comment above each one with your answer.
        
	//This variable creates a KeyGenerator object which is used to generaye keys for encryption algorithms
	KeyGenerator keyGen;
	//These two string's are used to hold the path for the encrypted and decrypted files.
	String encFile, decFile;
	//This SecretKey object is used to make a secret key to be used for the cryptography.
	SecretKey secKey;
	//This Cipher object is used to encrypt/decrypt the file
	Cipher aesCipher; 
	
	CryptoEngine(String inFile, String outFile) throws NoSuchAlgorithmException, NoSuchPaddingException {
	   encFile = inFile;
	   decFile = outFile;
	  
	   keyGen = KeyGenerator.getInstance("AES");
           keyGen.init(128);
           secKey = keyGen.generateKey();
           aesCipher = Cipher.getInstance("AES");
	}
	
	public void encrypt() throws InvalidKeyException, IOException {
           /*I changed this from the default in order to encrypt the first file passed as an argument.
	     The default was to read the bytes from a string, but I felt that reading from a file would
	     be better than the default.
	   */
	   byte[] byteText = Files.readAllBytes(Paths.get(encFile));
        
           aesCipher.init(Cipher.ENCRYPT_MODE, secKey);
	
           byte[] byteCipherText = null;
	   
           try {
	       byteCipherText = aesCipher.doFinal(byteText);
     	   } catch (IllegalBlockSizeException | BadPaddingException e) {
	       e.printStackTrace();
           }
	   Files.write(Paths.get(encFile), byteCipherText);
	}
	
        // You will decrypt the encrypted file using the same general principles
        // For each line that you supply, leave a detailed comment of what that line is doing, including the functionality of the methods and variables 
	public void decrypt() throws IOException, InvalidKeyException {
           // assign "Files.readAllBytes(Paths.get(encFile))" to the cipherText array (fill in the blank) 
		
	   /*This line creates a byte array which has the contents of the encrypted file placed inside. 
	     It uses the Files.readAllBytes() which takes a file and converts it into a byte array.
	   */
           byte[] cipherText = Files.readAllBytes(Paths.get(encFile));
   
           // call aesCipher.init as in the encrypt method, but this time, you will use DECRYPT_MODE!  
           // _______________________________________________
	   /* This code initialzes the aesCipher object of type Cipher to change it to the decrypt mode
	      it passes the secret key so that the class can decrypt the message properly.
	    */
	   aesCipher.init(Cipher.DECRYPT_MODE, secKey);
   
           // declare and initialize a byte array just like in the encrypt method, but this time, call it bytePlainText
           // _______________________________________________
	   //This line instantiates a byte array called bytePlainText which is set to null so that I can send the decrypted text to it.
	   byte[] bytePlainText = null;

           // I have commented out this try-catch block to make your code compilable, but you'll need to decomment it after filling in the code
           try {
                // Decrypt the cipherText byte array with the same aesCipher.doFinal method as in encrypt method
                // But this time, the byte arrays will be reversed!
                // __________________________________________
		//This attempts to set the byte array to the output byte array created by calling the doFinal() of the Cipher class.
		bytePlainText = aesCipher.doFinal(cipherText);

	   } catch (IllegalBlockSizeException | BadPaddingException e) {
		//This catches the errors that can be generated by the cipher class.
	   	e.printStackTrace();
	   }
           
           // Write your output to a file, similar to the Files.write method in the encrypt method (but be careful to use the correct byteArray and file)  
           // _________________________________________________
	   //This code writes the bytePlanText byte array to the file.
           Files.write(Paths.get(decFile), bytePlainText);
	}
	
}
