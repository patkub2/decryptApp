/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.polsl.kubala.patryk.model;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
        
/**
 * Class responsible for testing program purposes and Model methods
 *
 * @author Patryk Kubala
 * @version 1.1
 */
public class ModelTest {

    /**
     * {@link pl.polsl.kubala.patryk.model.Model} It also initializes the objects present inside of this class.
     */
    private final Model model = new Model();

    
   

    /**
     * Before each setSeed is implemented so the program runs correctly
     */
    @BeforeEach
    public void setUp() {
        model.setSeed(123);
    }
    

    /**
     * A simple Enmu test to check if it runs correctly
     */
    @Test 
    public void testSetchoiceEnum() {
        System.out.println("setchoiceEnum");
       
            model.setchoiceEnum(0);
            assertEquals("ENCODE",String.valueOf(model.getchoiceEnum()));
            model.setchoiceEnum(1);
            assertEquals("DECODE",String.valueOf(model.getchoiceEnum()));
            
        
    }
    
    /**
     *
     * @param expected Passes expected value in arguments to be truth
     * @param provided Passes provided value in arguments to check if it is the same as expected
     * @param exp agrument that shows if the program should fail or not
     * @throws Exception
     */
    @ParameterizedTest
    @MethodSource
    public void testTakeFromCommandLineSeed(int expected, int provided, boolean exp) throws Exception {
        System.out.println("takeFromCommandLine");
        String[] commandLineArgs = {"-s",String.valueOf(provided),"-d","elo"};
        
        model.takeFromCommandLine(commandLineArgs);
        if(exp){
            assertEquals(expected,model.getSeed(), "Error"); 
        }else{
            assertNotSame(expected,model.getSeed(), "Error"); 
        }
       
          
    }
    
    /**
     *
     * @return Stream of values for testTakeFromCommandLineSeed Test
     */
    private static Stream<Arguments> testTakeFromCommandLineSeed(){
        return Stream.of(
        Arguments.of(123,123, true),
        Arguments.of(234,123, false),
        Arguments.of(124555,124555, true),
        Arguments.of(0,0, true)
        );
        
    
    
    }
     
    /**
     *
     * @param expected Passes expected value in arguments to be truth
     * @param arg argument before passing value
     * @param provided Passes provided value in arguments to check if it is the same as expected
     * @param exp agrument that shows if the program should fail or not
     * @throws Exception
     */
    @ParameterizedTest
    @MethodSource
    public void testTakeFromCommandLineDecrypt(String expected,String arg, String provided, boolean exp) throws Exception {
        System.out.println("takeFromCommandLine");
        String[] commandLineArgs = {"-s","123",arg,provided};
        
        model.takeFromCommandLine(commandLineArgs);
        if(exp){
            assertEquals(expected,model.getText(), "Error"); 
        }else{
            assertNotSame(expected,model.getText(), "Error"); 
        }
       
          
    }
    
    /**
     *
     * @return Stream of values for testTakeFromCommandLineDecrypt Test
     */
    private static Stream<Arguments> testTakeFromCommandLineDecrypt(){
        return Stream.of(
        Arguments.of("591 180 949 730 847 411","-d","591 180 949 730 847 411",true),
        Arguments.of("234 345","-d","234 345",true),
        Arguments.of("234 345","-d","345 234",false),
        Arguments.of("591 180 949 730 847 411","-e","591 180 949 730 847 411",false),
        Arguments.of("591 180 949 730 847 411","-d","591180949730847411",false)
        );
    }
    

    
    /**
     * Test function that uses the other two functions to check if the key works correctly using various seeds
     * 
     * @param seed the seed we want to use to test the key it produces
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 10, Integer.MAX_VALUE, Integer.MIN_VALUE, -19, -1000432432})
    public void setSeedTest(int seed)
    {
        model.setSeed(seed);
        model.setKey();
         List<List<Integer>> testKey=model.getKey();
        assertFalse(testKey.isEmpty());
        assertTrue(testKey.size()==96);
        testKey.forEach(sublist -> {
            assertTrue(sublist.size()==5);
         });
        ////
         
        for(int i=0; i<testKey.size(); i++)
        {
            for(int q=0; q<testKey.get(i).size(); q++)
            {
                for(int e=0; e<testKey.size(); e++)
                {
                    for(int t=0; t<testKey.get(e).size(); t++)
                    {
                        if(i!=e&&q!=t)
                        {
                            assertFalse(Objects.equals(testKey.get(i).get(q), testKey.get(e).get(t)));
                        }
                    }
                }
            }
        }
        
    }

    /**
     * Test of decodeText method, of class Model.
     * @param expected Passes expected value in arguments to be truth
     * @param provided Passes provided value in arguments to check if it is the same as expected
     * @param exp agrument that shows if the program should fail or not
     * @throws java.lang.Exception
     */
    @ParameterizedTest
    @MethodSource
    public void testDecodeText(String expected, String provided, boolean exp) throws Exception {
        System.out.println("decodeText");
        model.setKey();
        model.setText(provided);
         
         
        if(exp){
            assertEquals(expected,model.decodeText(), "Error"); 
        }else{
            assertNotSame(expected,model.decodeText(), "Error"); 
        }
       
        
    }
  
    /**
     *
     * @return Stream of values for testDecodeText Test
     */
    private static Stream<Arguments> testDecodeText(){
        return Stream.of(
        Arguments.of("patryk","591 180 949 730 847 411",true),
        Arguments.of("patryk","591 180 949 730 847 412",false),
        Arguments.of("p","591",true)
        //Arguments.of("pa","5g1 2g0",false)
        );
    }

    /**
     * Test of encodeText method, of class Model.
     * @param expected Passes expected value in arguments to be truth
     * @param provided Passes provided value in arguments to check if it is the same as expected
     * @param exp agrument that shows if the program should fail or not
     * @throws java.lang.Exception
     */
    @ParameterizedTest
    @MethodSource
    public void testEncodeText(String expected, String provided, boolean exp) throws Exception {
        System.out.println("encodeText");
          model.setKey();
        model.setText(provided);
         
         
        if(exp){
            assertEquals(expected,model.encodeText(), "Error"); 
        }else{
            assertNotSame(expected,model.encodeText(), "Error"); 
        }
       
        
        
    }
    
    /**
     *
     * @return Stream of values for testEncodeText Test
     */
    private static Stream<Arguments> testEncodeText(){
        return Stream.of(
        Arguments.of("591 180 949 730 847 411","patryk",true),
        Arguments.of("591 180 949 730 847 412","patryk",false),
        Arguments.of("591","p",true),
        Arguments.of("5g1 2g0","pa",false)
        );
    }


   
    
}
