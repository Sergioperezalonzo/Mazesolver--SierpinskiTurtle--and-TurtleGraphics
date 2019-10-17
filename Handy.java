// "Storehouse" class: Handy  
// This class is not a program, just a storehouse for subroutines written
//   for Handy 
// Author:Sergio Perez
// 03/March/2016  

import java.awt.Color;
import javax.swing.*;

public class Handy {

   ///subroutine 1
   
   //randomInt(): this subroutine takes two integer parameters.Then calculates a    //number between the two numbers. then returns a random integer. 

   public static int randomInt(int intF, int intS) {
   
   
      int random; 
      
      random = (int)(((intS-intF)+1)*Math.random()+intF); 
      
      
      return random;
   }
   //end of randomInt()
   
   
   
   ///subroutine 2
   
   
   
   //randomColor(): this subroutine takes no parameters.Instead it does             //calculation to find the RGB.Then randomColor is privided with a new color.
   //This subroutine returns a random color.
   
   public static Color randomColor() {
    
    
      int r,g,b;
    
      r = randomInt(0,255);
      g = randomInt(0,255);
      b = randomInt(0,255);
     
     
  
     
      Color radomColor = new Color(r,g,b);
      
      
      
      return radomColor;
      
    
    }
    
    //end of randomColor()
    
   ///Subroutine 3
   
   
   
   //randomWord(): this subroutine dosen't take a parameter. The subroutine         //calcualtes a number between 1 - 17 then uses the number to indetify a          //string.The the subroutine returns a random word.
   


   public static String randomWord() {
   
      String[] vocab = {"rhythm", "turbulence", "pseudonym", "fruit", "cellar",
                        "psychology", "quixotic", "hyperbole", "exclude", 
                        "bridge", "whisper", "egregious", "hyperbolic",
                        "weekly", "engagement", "calendar", "facetious"};
   
   
      int location;
      
      location = (int) (17*Math.random());
   
  
      return vocab[location];
   
   
   
   
   }
  
   //end of randomWord()
  
  ///subroutine 4
  
  //changeLabels(): this subroutine takes a JLabel array and two strings. The      //subroutine checks every single JLabel then conpares it to the first string.     //If they are the same JLabel will be replace with string 2. If not it will      //stay the same. 
  
   public static void changeLabels(JLabel[] x, String s1, String s2) {
   
   
         String phrase;
         
         for(int i = 0; i < x.length; i++) {
   
            
            phrase = x[i].getText();
         
            if(phrase.equals(s1)) {
         
         
               x[i].setText(s2);
         
        
            }
   
   
   
         }


   }
   
   
   //end of changeLabels()
   
   //addCols(): this subroutine takes a 2 dimensinal double array.The subroutine    //adds every value in each colum and stores the sum of each colum in double      //array total.
   
   public static double[] addCols(double[][] design) {
   
   
   
   
      double[] total;
      
      
      total = new double[design[0].length];
      
      
      double subTotal;
      double tot;
      
      subTotal = 0;
      tot = 0;
      
     
      
      for(int i = 0; i < design.length; i++) {
      
         
         for(int ii = 0; ii < design[0].length; ii++) {
         
            
            total[ii] = design[i][ii] + total[ii] ;
            
         }
            
            
         
      }
      
      
      return total;
       
       
   }
   
   
   
    public static Color randomColor(int parameter) {
    
    
      int r,g,b;
      
      if (parameter < 0 || parameter > 255) {
      
         r = randomInt(1,255);
         g = randomInt(1,255);
         b = randomInt(1,255);
         
      
      
      } else {
      
  
    
         r = randomInt(1,parameter);
         g = randomInt(1,parameter);
         b = randomInt(1,parameter);
      
      
      }
     
     
  
     
      Color radomColor = new Color(r,g,b);
      
      
      
      return radomColor;
      
    
    }
    
    
    
    public static Color randomPastel() {
    
    
       int r,g,b;
    
       r = randomInt(1,255);
       g = randomInt(1,255);
       b = randomInt(1,255);
    
    
      Color randomPastel = new Color(r,g,b,100);
      
      
      
      return randomPastel;
    
    
    
    
    
    
    }
    
    
    
   
   
   

}//end of Handy