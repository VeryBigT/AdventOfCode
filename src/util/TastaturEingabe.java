package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TastaturEingabe
{
   static BufferedReader eing =
      new BufferedReader(new InputStreamReader (System.in));

   public static double readDouble (String prompt)
   {
      while (true)
      {
         System.out.print(prompt);
         try
         {
            String zeile = eing.readLine();
            return Double.parseDouble(zeile);
         }
         catch (NumberFormatException e)
         {
            System.out.println ("Bitte eine Zahl eingeben (ggf. mit Dezimalpunkt).");
         }
         catch (IOException e)
         {
            e.printStackTrace();
            System.exit(1);
         }
      }
   }

   public static int readInt (String prompt)
   {
      while (true)
      {
         System.out.print(prompt);
         try
         {
            String zeile = eing.readLine();
            return Integer.parseInt(zeile);
         }
         catch (NumberFormatException e)
         {
            System.out.println ("Bitte eine ganze Zahl eingeben.");
         }
         catch (IOException e)
         {
            e.printStackTrace();
            System.exit(1);
         }
      }
   }

   public static long readLong (String prompt)
   {
      while (true)
      {
         System.out.print(prompt);
         try
         {
            String zeile = eing.readLine();
            return Long.parseLong(zeile);
         }
         catch (NumberFormatException e)
         {
            System.out.println ("Bitte eine ganze Zahl eingeben.");
         }
         catch (IOException e)
         {
            e.printStackTrace();
            System.exit(1);
         }
      }
   }

   public static boolean readBoolean (String prompt)
   {
      while (true)
      {
         System.out.print(prompt);
         try
         {
            String e = eing.readLine();
            if (e.equals("j") || e.equals("J"))
               return true;
            if (e.equals("n") || e.equals("N"))
               return false;
            throw new IllegalArgumentException();
         }
         catch (IllegalArgumentException e)
         {
            System.out.println ("Bitte J oder N eingeben.");
         }
         catch (IOException e)
         {
            e.printStackTrace();
            System.exit(1);
         }
      }
   }

   public static String readString (String prompt)
   {
      while (true)
      {
         System.out.print(prompt);
         try
         {
            return eing.readLine();
         }
         catch (IOException e)
         {
            e.printStackTrace();
            System.exit(1);
         }
      }
   }

   public static char readChar (String prompt)
   {
      while (true)
      {
         System.out.print(prompt);
         try
         {
            String s = eing.readLine();
            if (s.length() == 0)
               throw new IllegalArgumentException();
            return s.charAt(0);
         }
         catch (IllegalArgumentException e)
         {
            System.out.println ("Bitte ein Zeichen eingeben.");
         }
         catch (IOException e)
         {
            e.printStackTrace();
            System.exit(1);
         }
      }
   }


   /**
    *  wartet auf <Return>
    *  @param b true: Meldung "Weiter ..." wird ausgegeben
    */
   public static void warte(boolean b)
   {
      if (b)
      {
         System.out.print("....... Weiter mit <Return>");
         System.out.flush();
      }
      readString("");
   }

   /**
    *  wartet auf <Return>
    *  Meldung "....... Weiter mit <Return>" wird immer ausgegeben
    */
   public static void warte()
   {
      warte(true);
   }
}
