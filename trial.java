package joelproject1;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;


public class trial 
{
	static int texlength;
	static int keylength;
	static int asciic[]; //to store message in ASCII code
	static int asciikey[]; //to store key in ASCII code
	static String rearr[]; //to store rearranged array
	static String hex[]; //to store message in hexadecimal code
	static String hexkey[]; //to store key in hexadecimal code
	static String dnacode[];//to store DNA values
	static String revdnacode[];//to store 3142 DNA order
	static String intermedcode="";//to store the intermediate code
	
	
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
	 
	//rearranging columns in the order 2,4,1,3
	 static void rearrange(String arr[],String hexadeci[],int len) 
	 {
		 for(int i=0;i<len;i=i+4) 
		 {
			 arr[i]=hexadeci[i+1];
		 }
		 for(int i=1;i<len;i=i+4) 
		 {
			 arr[i]=hexadeci[i+2];
		 }
		 for(int i=2;i<len;i=i+4) 
		 {
			 arr[i]=hexadeci[i-2];
		 }
		 for(int i=3;i<len;i=i+4) 
		 {
			 arr[i]=hexadeci[i-1];
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
	 
	 //function to convert to DNA code
	 static String dna(String a) 
	 {
		 String ans="";
		 int alength=a.length();
		 for(int i=0;i<alength;i=i+2) 
		 {
			 if(a.substring(i,i+2).equals("00"))
				 ans+="A";
			 else if(a.substring(i,i+2).equals("01"))
				 ans+="C";
			 else if(a.substring(i,i+2).equals("10"))
				 ans+="G";
			 else if(a.substring(i,i+2).equals("11"))
				 ans+="T";
		 }
		return ans;
	 }
	 
	 
	 //beginning of main function
	//@SuppressWarnings("resource")
	public void starter() throws FileNotFoundException 
	{
		File file = new File("C:\\Users\\DELL\\eclipse-workspace\\joelproject1\\src\\joelproject1\\input.txt");
		Scanner sc=new Scanner(file);
		sc.useDelimiter("\\z");
		String plaint=sc.next();//to store message from input
		texlength=plaint.length();
		sc.close();
		
		//addition of padding
		if(texlength%4!=0) 
		{
			int p=texlength%4;
			for(int i=p;i<4;i++) 
			{
				texlength+=1;
				plaint=plaint+" ";
			}
		}
		
		File file1 = new File("C:\\Users\\DELL\\eclipse-workspace\\joelproject1\\src\\joelproject1\\key.txt");
		Scanner st=new Scanner(file1);
		st.useDelimiter("\\z");
		String key=st.next();//to store key
		keylength=key.length();
		st.close();
		
		
		//converting to ASCII
		System.out.println("ASCII Sentence:");
		asciic=new int[texlength];
		ASCIISentence(plaint,asciic);
		System.out.println("\n");
		
		
		//convert to hexadecimal and printing
		hex=new String[texlength];
		toHexadecimal(hex,asciic,texlength);
		System.out.println("Hexadecimal matrix:");
		for(int i=0;i<texlength;i++) 
		{
			if(i%4==0)
				System.out.println();
			System.out.print(hex[i]+" ");
		}
		System.out.println("\n");
		
		
		//rearranging columns in the order 2,4,1,3
		rearr= new String[texlength];
		rearrange(rearr,hex,texlength);
		System.out.println("rearranged matrix:");
		for(int i=0;i<texlength;i++) 
		{
			if(i%4==0)
				System.out.println();
			System.out.print(rearr[i]+" ");	
		}
		System.out.println("\n");
		
		
		//converting key to ASCII and printing->which is to be removed
		System.out.println("key ASCII value:");
		asciikey=new int[keylength];
		ASCIISentence(key,asciikey);
		System.out.println("\n");
		
		
		//convert key to hexadecimal and printing->which is to be removed
		hexkey=new String[keylength];
		toHexadecimal(hexkey,asciikey,keylength);
		System.out.println("key in Hexadecimal format:");
		for(int i=0;i<keylength;i++) 
		{
			System.out.print(hexkey[i]);
		}
		System.out.println("\n");
		
		
		//converting to DNA code
		dnacode=new String[texlength];
		int ctr=0;
		for(int i=0;i<texlength;i++) 
		{
			if(i%4==0)
				ctr=0;
			
			String sptr=rearr[i];
			String keyptr=hexkey[ctr];
			trial ob=new trial();
			String dut=ob.hextoBCD(sptr);
			String dut1=ob.hextoBCD(keyptr);
			int k=dut.length();
			String xorval=xoring(dut,dut1,k);
			dnacode[i]=dna(xorval);
			ctr++;
		}
		
		System.out.println("dna matrix:");
		for(int i=0;i<texlength;i++) 
		{
			if(i%4==0)
				System.out.println();
			System.out.print(dnacode[i]+" ");	
		}
		System.out.println("\n");
		
		
		//reordering DNA code in 3,1,4,2 order
		revdnacode=new String[texlength];
		for(int i=0;i<texlength;i++) 
		{
			if(i%4==0)
				revdnacode[i]=dnacode[i+3];
			else if(i%4==1)
				revdnacode[i]=dnacode[i+1];
			else if(i%4==2)
				revdnacode[i]=dnacode[i-1];
			else if(i%4==3)
				revdnacode[i]=dnacode[i-3];
		}
		System.out.println("3,1,4,2 dna matrix:");
		for(int i=0;i<texlength;i++) 
		{
			if(i%4==0)
				System.out.println();
			System.out.print(revdnacode[i]+" ");
		}
		System.out.println("\n");
		
		//reading the text column by column (top to bottom)
		for(int i=0;i<texlength;i=i+4) 
		{
			intermedcode+=revdnacode[i];
		}
		for(int i=1;i<texlength;i=i+4) 
		{
			intermedcode+=revdnacode[i];
		}
		for(int i=2;i<texlength;i=i+4) 
		{
			intermedcode+=revdnacode[i];
		}
		for(int i=3;i<texlength;i=i+4) 
		{
			intermedcode+=revdnacode[i];
		}	
		 
		System.out.println("Final DNA encrypted code");
		System.out.println(intermedcode);
	}
}
