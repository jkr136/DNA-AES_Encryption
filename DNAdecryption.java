package joelproject1;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

public class DNAdecryption extends AESdecryptor {
	
	static String tempstring;//temporary variable to store DNA code
	static String revdnacode[];
	static String dnacode[];
	static int DNAlength;
	static int keylenth;
	static int asciikey[]; //to store key in ASCII code
	static String hexkey[]; //to store key in hexadecimal code
	static String rearr[]; //to store array rearranged in 2,4,1,3 order
	static String hex[]; //to store message in hexadecimal code in 1,2,3,4 order
	static String optex=""; //to store plain text message
	
	    //function to convert to ASCII
		static void ASCIISentence(String sentence,int ascii[])
		    {
		        int l = sentence.length();
		        int convert;
		        
		        for (int i = 0; i < l; i++)
		        {
		            convert = sentence.charAt(i);//converting to ASCII
		            ascii[i]=convert;
		            System.out.print(ascii[i]);
		        }
		    }
		
		 //converting ASCII to hexadecimal
		 static void toHexadecimal(String arr[],int asciistr[],int len)
		 {
			 for (int i=0;i<len;i++) 
			 { 
				 arr[i]=Integer.toHexString(asciistr[i]); //converting to hexadecimal 
			 }
		 }
		 
		 //function to convert hexadecimal to binary coded decimal
		 public String hextoBCD(String s) 
		 {
			 String result="";
			 int len=s.length();
			 for(int i=0;i<len;i++) 
			 {
				 if(Character.isDigit(s.charAt(i))) 
				 {
					 String res=Integer.toBinaryString((int)s.charAt(i));
					 //System.out.print(res.substring(res.length() - 4) + " ");
					 result=result.concat(res.substring(res.length()-4));
				 }
				 else 
				 {
					 String res=Integer.toBinaryString((int)s.charAt(i)-55);
					 //System.out.print(res.substring(res.length() - 4) + " ");
					 result=result.concat(res.substring(res.length()-4));
				 }
			 }
			 return result;
		 }

		//function to x-or two numbers
		 static String xoring(String a,String b,int n) 
		 {
			 String ans="";
			 for(int i=0;i<n;i++) 
			 {
				if(a.charAt(i) == b.charAt(i)) 
					ans+="0";
				else
					ans+="1";
			 }
			return ans; 
		 }
		 
		 //function to convert DNA code to BCD
		 public String dnatobcd(String a) 
		 {
			 String ans="";
			 int alength=a.length();
			 for(int i=0;i<alength;i++) 
			 {
				 if(a.charAt(i)=='A')
					 ans+="00";
				 else if(a.charAt(i)=='C')
					 ans+="01";
				 else if(a.charAt(i)=='G')
					 ans+="10";
				 else if(a.charAt(i)=='T')
					 ans+="11";
			 }
			return ans;
			 
		 }
		 
		 //function to convert BCD to hex
		 public static String bcdtohex(String binaryString) {
			    String hexString = "";
			    int len = binaryString.length();
			    // pad the binary string with zeros to make its length a multiple of 4
			    while (len % 4 != 0) {
			        binaryString = "0" + binaryString;
			        len++;
			    }
			    // convert 4 binary digits at a time to a hexadecimal digit
			    for (int i = 0; i < len; i += 4) {
			        int decimalValue = Integer.parseInt(binaryString.substring(i, i + 4), 2);
			        hexString += Integer.toHexString(decimalValue).toUpperCase();
			    }
			    return hexString;
			}
		//rearranging columns in the order 1,2,3,4
		 static void rearrange(String arr[],String hexadeci[],int len) 
		 {
			 for(int i=0;i<len;i=i+4) 
			 {
				 arr[i+1]=hexadeci[i];
			 }
			 for(int i=1;i<len;i=i+4) 
			 {
				 arr[i+2]=hexadeci[i];
			 }
			 for(int i=2;i<len;i=i+4) 
			 {
				 arr[i-2]=hexadeci[i];
			 }
			 for(int i=3;i<len;i=i+4) 
			 {
				 arr[i-1]=hexadeci[i];
			 }
		 }
		 
		 //function to convert hexadecimal number to ASCII
		 public static String hextoascii(String hex) {
			    StringBuilder output = new StringBuilder();
			    for (int i = 0; i < hex.length(); i+=2) {
			        String str = hex.substring(i, i+2);
			        output.append((char) Integer.parseInt(str, 16));
			    }
			    return output.toString();
			}
		 
		 
	public static void main(String[] args) throws IOException, Exception {
		// TODO Auto-generated method stub
		DNAdecryption rt=new DNAdecryption();
    	rt.recall();
    	tempstring=AESdecryptor.plaintext;
    	DNAlength=tempstring.length();
    	int tlen=DNAlength/4;
    	revdnacode=new String[tlen];
    	
    	
    	File file2 = new File("C:\\Users\\DELL\\eclipse-workspace\\joelproject1\\src\\joelproject1\\key.txt");
		Scanner vr=new Scanner(file2);
		vr.useDelimiter("\\z");
		String skey=vr.next();//to store key
		keylenth=skey.length();
		vr.close();
    	
    	//code to store DNA code in an array
    	int ctr=0;//counter variable
    	for(int i=0;i<tlen;i=i+4) 
    	{
    		revdnacode[i]=tempstring.substring(ctr,ctr+4);	
    		ctr+=4;
    	}
    	for(int i=1;i<tlen;i=i+4) 
    	{
    		revdnacode[i]=tempstring.substring(ctr,ctr+4);
    		ctr+=4;
    	}
    	for(int i=2;i<tlen;i=i+4) 
    	{
    		revdnacode[i]=tempstring.substring(ctr,ctr+4);	
    		ctr+=4;
    	}
    	for(int i=3;i<tlen;i=i+4) 
    	{
    		revdnacode[i]=tempstring.substring(ctr,ctr+4);
    		ctr+=4;
    	}
		//for displaying output
    	System.out.println("1st output");
    	for(int i=0;i<tlen;i++) 
		{
			if(i%4==0)
				System.out.println();
			System.out.print(revdnacode[i]+" ");
		}
		System.out.println("\n");
		
		//DNA code in 2,4,1,3 order
		dnacode=new String[tlen];
		for(int i=0;i<tlen;i++) 
		{
			if(i%4==0)
				dnacode[i+3]=revdnacode[i];
			else if(i%4==1)
				dnacode[i+1]=revdnacode[i];
			else if(i%4==2)
				dnacode[i-1]=revdnacode[i];
			else if(i%4==3)
				dnacode[i-3]=revdnacode[i];
		}
		//for displaying output
    	System.out.println("2nd output");
    	for(int i=0;i<tlen;i++) 
		{
			if(i%4==0)
				System.out.println();
			System.out.print(dnacode[i]+" ");
		}
		System.out.println("\n");
		
		
		//converting key to ASCII and printing->which is to be removed
		System.out.println("key ASCII value:");
		asciikey=new int[keylenth];
		ASCIISentence(skey,asciikey);
		System.out.println("\n");
		
		
		//convert key to hexadecimal and printing->which is to be removed
		hexkey=new String[keylenth];
		toHexadecimal(hexkey,asciikey,keylenth);
		System.out.println("key in Hexadecimal format:");
		for(int i=0;i<keylenth;i++) 
		{
			System.out.print(hexkey[i]);
		}
		System.out.println("\n");
		
		//converting to rearranged code
		rearr=new String[tlen];
		int btr=0;
		for(int i=0;i<tlen;i++)
		{
			if(i%4==0)
				btr=0;
			String sptr=dnacode[i];
			String keyptr=hexkey[btr];
			DNAdecryption op=new DNAdecryption();
			String dut=op.dnatobcd(sptr);
			String dut1=op.hextoBCD(keyptr);
			int k=dut.length();
			String xorval=xoring(dut,dut1,k);
			rearr[i]=bcdtohex(xorval);
			btr++;
		}
		
		System.out.println("rearranged hex matrix:");
		for(int i=0;i<tlen;i++) 
		{
			if(i%4==0)
				System.out.println();
			System.out.print(rearr[i]+" ");	
		}
		System.out.println("\n");
		
		
		//rearranging columns in the order 1,2,3,4
		hex= new String[tlen];
		rearrange(hex,rearr,tlen);
		System.out.println("Hexadecimal matrix:");
		for(int i=0;i<tlen;i++) 
		{
			if(i%4==0)
				System.out.println();
			System.out.print(hex[i]+" ");	
		}
		System.out.println("\n");
		
		//converting hex to ASCII
		for(int i=0;i<tlen;i++)
		{
			String t=hextoascii(hex[i]);
			optex+=t;
		}
		
		System.out.println("Output message:"+optex);
	}

}
