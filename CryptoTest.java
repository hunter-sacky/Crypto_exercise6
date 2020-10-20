/*
  Name: Hunter Sacky
  Student ID: jop757 @01681704
  Title: CryptoTest.java
*/
public class CryptoTest {

    public static void main(String[] args) throws Exception {
        // What's going on in the two lines below?
        // Explain with a comment.
        // I've not handled the error in which the args[] array is null.
        // So, if you don't call the program correctly at the command line, you'll get an error.
        // Try to handle this in some way.
  	
	/*
	  The code below takes two arguments that are passed from when the user runs this class
	  using the terminal. The command syntax it is expecting is: java CryptoTest.java arg1 arg2
	  where arg1 and arg2 are filenames used for this program. In order to prevent errors in the case
	  that a user doesn't send arguments, I surrounded the provided code in a try-catch block which
	  catches the ArrayIndexOutOfBoundsException which is the exception I encountered when trying to
	  run this program without any arguments.
	*/
	try{
        	String inFile = args[0];
    		String outFile = args[1];    			    
    		
		CryptoEngine cryptoEngine = new CryptoEngine(inFile, outFile);
        	cryptoEngine.encrypt();
        	cryptoEngine.decrypt();
	} catch (ArrayIndexOutOfBoundsException e){
		System.out.println(e);

	}
    }
}
