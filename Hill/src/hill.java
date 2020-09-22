import java.util.*;
import java.math.*;

public class hill {
		public static void main(String[] args)
		{
			Scanner input=new Scanner(System.in);
			
			String plaintext="";
			System.out.println("Input your plaintext:");
			plaintext=input.nextLine();
			
			int [] K=new int[9];
			System.out.println("Please input your matrix(which has 9 numbers)");
			for(int i=0; i<9; i++)
			{
				K[i]=input.nextInt();
			}
			
			int [][] Key=Matrix(K);
			printmatrix(Key);
			
			String C=Encrypt(Key, plaintext);
			System.out.println(C);
	
			input.close();
		}
	
	  public static int[][] Matrix(int[] K)
	  {
		  int[][] matrix=new int[3][3];
		  int count=0;
		  for(int i=0; i<3; i++)
		  {
			  for(int j=0; j<3; j++)
			  {
				  if(count<K.length)
				  matrix[i][j]=K[count++];
				  else matrix[i][j]=0;
			  }
		  }
		  return matrix;
	  }
	  
	  public static void printmatrix(int [][] K)
		{	
			for(int i=0; i<K.length; i++)//打印打印测试不要管不要看
			{
				for(int j=0; j<K[i].length; j++)
				{
					System.out.print(K[i][j]);
				}
				System.out.println();
			}
		}
	  
	  public static String dealPlaintext(String P)
		{
			P=P.toUpperCase();
			P=P.replaceAll("[^A-Z]","");
			StringBuilder p=new StringBuilder(P);
			
			while(p.length()%3!=0)
			{p.append('X');}
			
			String Plaintext=p.toString();
			
			return Plaintext;
		}
	  
	  public static String encryption(int[][] K, char ch1, char ch2, char ch3)
	  {
		  int cip1=0, cip2=0, cip3=0;
		  
		  cip1=(K[0][0]*(ch1-'A')+K[0][1]*(ch2-'A')+K[0][2]*(ch3-'A'))%26;
		  cip2=(K[1][0]*(ch1-'A')+K[1][1]*(ch2-'A')+K[1][2]*(ch3-'A'))%26;
		  cip3=(K[2][0]*(ch1-'A')+K[2][1]*(ch2-'A')+K[2][2]*(ch3-'A'))%26;

		  StringBuilder cipher=new StringBuilder();
		  cipher.append((char)(cip1+'A'));
		  cipher.append((char)(cip2+'A'));
		  cipher.append((char)(cip3+'A'));
		  System.out.println(cipher.toString());
		
		  return cipher.toString();
	  }
	  
	  public static String Encrypt(int [][] K, String P)
	  {
		  	char[] plaintext=dealPlaintext(P).toCharArray();
		  	int length=dealPlaintext(P).length();
			
			StringBuilder cipher= new StringBuilder();
			for(int i=0; i<length; i+=3)
			{
				cipher.append(encryption(K, plaintext[i], plaintext[i+1], plaintext[i+2])); 
			}
			return cipher.toString();
	  }
	  
	  

	  
}
