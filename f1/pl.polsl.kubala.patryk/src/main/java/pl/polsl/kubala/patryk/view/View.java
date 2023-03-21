package pl.polsl.kubala.patryk.view;
import java.util.Scanner;

/**
 * Class responsible for displaying outbound and error streams and using scanners to collect in streams
 *
 * @author Patryk Kubala
 * @version 1.1
 */
public class View {



   

    /**
     * A function that asks the user and takes the answer what is the Key Seed they want to use.
     * Used in {@link pl.polsl.kubala.patryk.controller.Controller} if incomplete information is provided in command line
     *
     * @return Seed provided by the user
     */
    public int getKeySeed()
    {
        int seedNumber;

        System.out.println("What is the seed for your key?");
        Scanner seedScanner = new Scanner(System.in);
        while(!seedScanner.hasNextInt())
        {
            System.out.println("Please enter a number");
            seedScanner.nextLine();

        }
        seedNumber = seedScanner.nextInt();
        return seedNumber;
    }

    /**
     * A function that collects a response from the user whether he wants to encode or decode.
     * Used in {@link pl.polsl.kubala.patryk.controller.Controller} if incomplete information is provided in command line
     *
     * @return Choice provided by the user
     */
    public int getChoice()
    {
        int choiceNumber =0;
        do
        {
            Scanner choiceScanner = new Scanner(System.in);
            System.out.println("Please enter a number from 0 to 1");
            while(!choiceScanner.hasNextInt())
            {
                System.out.println("Please enter a number from 0 to 1");
                choiceScanner.nextLine();

            }
            choiceNumber = choiceScanner.nextInt();
        }
        while(choiceNumber<0||choiceNumber>1);
        return choiceNumber;
    }

    /**
     *
     * @return
     */
    public String toEncode()
    {
        System.out.println("Enter the text to be encoded");
        Scanner scannedEncode = new Scanner(System.in);
        String toEncode = scannedEncode.nextLine();
        return toEncode;
    }

    /**
    * The function asks the user and collects the answer what text he wants to decode.
    * Used in {@link pl.polsl.kubala.patryk.controller.Controller} if incomplete information is provided on the command line
    *
    * @return User-supplied text
     */
    public String toDecode()
    {
        System.out.println("Enter text in XXX XXX XXX(..) form where X is a number");
        Scanner scannedDecode = new Scanner(System.in);
        String toDecode = scannedDecode.nextLine();
        return toDecode;
    }
    
    /**
     * A function that prints the exact content of the string supplied to it
     *
     * @param Input The string that is to be printed
     */
    public void printString(String Input)
    {
        System.out.println(Input);
    }


    /**
     * Function showing a simple text asking user whether he wants to encode or decode
     */
    public void showMenu()
    {
        System.out.println("Do you want to\n0. Encode\n1. Decode\n");
    }

    /**
     *
     * @param Before Code printed before encoding / decoding
     * @param After Code printed after encoding / decoding
     */
    public void printTextBeforeAfter(String Before,String After)
    {
        System.out.println("Orginal text: "+Before+"\n"+"encode/decoded text: "+After+"\n");
    }
    /**
     * Exception Print function error message
     *
     * @param e Exception needing its error message printed
     */
    public void printErrorMsg(Exception e)
    {
        System.err.println(e.getMessage());
    }
}
