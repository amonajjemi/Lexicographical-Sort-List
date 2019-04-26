/*************************************************
Aaron Monajjemi
amonajje
Programming Assignment 1
*************************************************/
import java.io.*;
import java.util.Scanner;

public class Lex{
	public static void main(String[] args) throws IOException{
		Scanner in = null;
		PrintWriter out = null;
		String line = null;
		//String[] token = null;
		int n = 0;
		int lineNumber = 0;
		
		if(args.length < 2){
            System.err.println("Usage: FileIO infile outfile");
            System.exit(1);
      }
	  in = new Scanner(new File(args[0]));
      out = new PrintWriter(new FileWriter(args[1]));
	  
	  while( in.hasNextLine() ){
          lineNumber++;
          line = in.nextLine();    // add extra space so split works right
          //token = line.split("\\s+");  // split line around white space
          n++;
      }
	  in.close();
	  in = new Scanner(new File(args[0]));
	  String[] token = new String[n];
	  
	  for( int k = 0; in.hasNextLine(); ++k){
		  token[k] = in.nextLine();
	  }
	  
	  List A = new List();
	  A.append(0);
	  for( int i = 1; i < n; ++i){
		  A.moveFront();
		  while( A.index() != -1 && token[i].compareTo(token[A.get()]) > 0 ){
			  A.moveNext();
		  }
		  if(A.index() == -1 ){
			  A.append(i);
		  }
          else if(A.index() == 0){
              A.prepend(i);
          }
		  else{
			  A.insertBefore(i);
		  }
	  }
	  
	  A.moveFront();
	  int count = 0;
	  while(A.index() != -1 && count < n){
		  out.println(token[A.get()]);
		  A.moveNext();
                  count++;
	  }

		out.close();
		in.close();
	}
}
