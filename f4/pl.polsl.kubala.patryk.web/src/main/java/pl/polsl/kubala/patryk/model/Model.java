package pl.polsl.kubala.patryk.model;
import java.util.*;

/**
 * Class containing all variables and methods responsible for checking and actually encoding / decoding the text
 *
 * @author Patryk Kubala
 * @version 1.1
 */
public class Model {

    /**
     * Encoding key. Initially empty, then values are given using the setKey method
     */
    private final List<List<Integer>> key = new ArrayList<>(96);

    /**
     * An enumerated type designed to better show the user's decision whether to encode or decode
     */
    public enum EnumChoice
    {

       /**
         * Selected value shows that user selected encodeion as his steep, it corresponds to choice value of 1
         */
        ENCODE,

        /**
         * Selected value shows that user selected decodeion as his steep, it corresponds to choice value of 2
         */
        DECODE,
    }


    /**
     * Choice of user want to encode or decode, given via command line or scanner changed from int to enumChoice enumerated value
     */
    private EnumChoice choiceEnum;

    /**
     * Grain for generating keys, given via command line or scanner
     */
    private int seed;

    /**
     * Choose whether the user wants to encode or decode using the command line or scanner
     */
    private int choice;

    /**
     * The text to encode or decode, given either through command line or scanner
     */
    private String text;

    /**
     * Boolean used in  to check if there is a error in loop
     */
    private boolean noError = true;

    /**
     * Boolean used in  to check a loop
     */
    private boolean fromCL = true;

    /**
     * Takes a value from and sets its enum counterpart as choiceEnum
     *
     * @param choiceArg the value given
     */
    public void setchoiceEnum(int choiceArg)
    {
        EnumChoice[] choices = EnumChoice.values();
        choiceEnum = choices[choiceArg];
       
    }

    /**
     * Takes a value from  and sets it as the noError
     *
     * @param boolArg the value given
     */
    public void setNoError(boolean boolArg)
    {
        noError=boolArg;
    }

    /**
     * Takes a value from and sets it as the fromCL
     *
     * @param boolArg the value given
     */
    public void setFromCL(boolean boolArg)
    {
        fromCL=boolArg;
    }

    /**
     * Takes a value from and sets it as the seed
     *
     * @param seedArg the value given
     */
    public void setSeed(int seedArg)
    {
        seed=seedArg;
    }

    /**
     * Takes a value from and sets it as the choice
     *
     * @param choiceArg the value given
     */
    public void setChoice(int choiceArg)
    {
        
        choice=choiceArg;
        
    }

    /**
     * Takes a String from and sets it as the text
     *
     * @param textArg the String given
     */
    public void setText(String textArg)
    {
        text=textArg;
    }

    /**
     * Returns the value of the private field "choiceEnum"
     *
     * @return enumChoice containing the value of the private field in {@link Model}.
     */
    public EnumChoice getchoiceEnum()
    {
        return choiceEnum;
    }

    /**
     * Returns the value of the private field "seed"
     *
     * @return int containing the value of the private field in {@link Model}.
     */
    public int getSeed()
    {
        return seed;
    }

    /**
     * Returns the value of the private field "choice"
     *
     * @return int containing the value of the private field in {@link Model}.
     */
    public int getChoice()
    {
        return choice;
    }

    /**
     * Returns the value of the private field "Text"
     *
     * @return String containing the value of the private field in {@link Model}.
     */
    public String getText()
    {
        return text;
    }

    /**
     * Returns the value of the private field "noError"
     *
     * @return boolean containing the value of the private field in {@link Model}.
     */
    public boolean getNoError()
    {
        return noError;
    }

    /**
     * Returns the value of the private field "fromCL"
     *
     * @return boolean containing the value of the private field in {@link Model}.
     */
    public boolean getFromCL()
    {
        return fromCL;
    }

    /**
     * Returns the value of the private field "key"
     *
     * @return List containing the value of the private field in {@link Model}.
     */
    public List<List<Integer>> getKey()
    {
        return key;
    }
    
    /**
     * Function that checks command line arguments and saves them into appropiate variables
     *
     * @param commandLineArgs command line argument passed from main through 
     * @throws IncorrectInputException if not all of the required fields are filled using command line
     */
    public void takeFromCommandLine(String[] commandLineArgs) throws IncorrectInputException
    {
        
        
        boolean seedChange=false;
        boolean choiceAndText=false;
        int i=0;
        for(String arg : commandLineArgs)
        {
            
            if(i>=commandLineArgs.length)
            {
                break;
            }
            if((!(arg.equals(commandLineArgs[i]))))
            {
                continue;
            }
            if(arg.equals("-s")&& commandLineArgs[i+1]!=null)
            {
                
               seed = Integer.parseInt(String.valueOf(commandLineArgs[i+1]));
                
             
                seedChange=true;
                i++;
            }
            else if((arg.equals("-e")||arg.equals("-d"))&& commandLineArgs[i+1]!=null)
            {
               
                text="";
                switch (arg) {
                    case "-e" :
                        choice =0;
                    case "-d" :
                        choice =1;
                    default : {
                    }
                }
              
                for(int j = i+1; j < commandLineArgs.length; j++)
                {
                    if(commandLineArgs[j].charAt(0) == '-')
                    {
                        break;
                    }
                    text = text + commandLineArgs[j] + " ";
                     
                    i = j;

                }
                text = text.replaceFirst(".$","");
                choiceAndText=true;
                
                
            }
             
            i++;
        }

        if(!choiceAndText||!seedChange)
        {
            throw new IncorrectInputException("Invalid data on the command line");
        }
    }

    /**
     * Function that takes the seed (from the private field in the {@link Model}) and uses it, as the name suggests, as the seed for the semi-random generation of the key.
     * It then checks if any of the values repeats and if they do, it changes them
     */
    public void setKey()
    {
        for(int i=0; i<96; i++)
        {
            List<Integer> subList;
            subList = new ArrayList<>(5);
            for(int q=0; q<5; q++)
            {
                int incr=0;
                int insert=0;
                boolean cont;
                do
                {
                    cont=false;
                    Random rand = new Random(seed+incr);
                    insert=rand.nextInt(899)+100;
                    incr++;
                    for(List<Integer> sub : key)
                    {
                        cont=sub.contains(insert);
                        if(cont)
                        {
                            break;
                        }
                    }
                    if(!cont)
                    {
                        cont=subList.contains(insert);
                    }

                }
                while(cont);
                subList.add(insert);
            }
            key.add(subList);
        }
    }

    /**
     * Function taking encodeed symbol and returning its ASCII code
     *
     * @param input The encodeed symbol
     * @return The ASCII code of the given sybmbol
     */
    private int findDecodeion(String input)
    {
        int inputInt = (((int)input.charAt(0)-48)*100)+(((int)input.charAt(1)-48)*10)+(int)input.charAt(2)-48;
        int out=0;
        int i=0;
        for(List<Integer> subList : key)
        {
            for(int inList : subList)
            {
                if(inputInt==inList)
                {
                    out=i+32;
                    break;
                }
            }
            i++;
        }
        return out;
    }

    /**
     * Function that takes encodeed text (from the private field in {@link Model}), decodes it and returns it decodeed
     *
     * @return Decodeed text
     * @throws IncorrectTextException if the text formatting is incorect for decodeion
     */
    public String decodeText () throws IncorrectTextException
    {
        StringBuilder output= new StringBuilder();
        for(int i=0; i<text.length(); i++)
        {
         
            if(((i+1)%4==0)&&(text.charAt(i)!=' '))
            {
                throw new IncorrectTextException("Wrong format!\nEnter text in XXX XXX XXX(..) form where X is a number");
            }
            else if((text.charAt(i)<'0'||text.charAt(i)>'9')&&text.charAt(i)!=' ')
            {
           
                throw new IncorrectTextException("Wrong format!\nEnter text in XXX XXX XXX(..) form where X is a number");
            } else if((text.length()+1)%4 != 0)
            {
                throw new IncorrectTextException("Wrong format!\nEnter text in XXX XXX XXX(..) form where X is a number");
            }
            
        }
        for(int i=0; i<text.length(); i++)
        {
            StringBuilder full= new StringBuilder();
            for(int q=0; q<3; q++)
            {
                full.append(text.charAt(i));
                i++;
            }
            char symbol = (char)findDecodeion(full.toString());
            output.append(symbol);
        }
        return output.toString();
    }

    /**
     * Function that takes text (from the private field in {@link Model}), encodes it and returns it encodeed
     *
     * @return Encrypted text
     * @throws IncorrectTextException if the text contains forbidden symbols, for example letters outside of english alphabet
     */
    public String encodeText() throws IncorrectTextException
    {
        StringBuilder output= new StringBuilder();
        for(int i=0; i<text.length(); i++)
        {
            final char DELETE= (char)127;
            if((text.charAt(i))< ' ' ||(text.charAt(i))> DELETE )
            {
                throw new IncorrectTextException("Text not supported");
            }
        }
        for(int i=0; i<text.length(); i++)
        {
            Random rand = new Random();
            int rn = rand.nextInt(5);
            int ascii = (int)text.charAt(i)-32;
            output.append(key.get(ascii).get(rn)).append(" ");
        }
        return output.toString();
    }


    /**
     * Clear key.
     */
    public void destroyKey()
    {
        key.clear();
    }
}

