import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class playfair {
	public static char[][] matrix(String K)//K=密钥
	{
		K=K.toUpperCase();
		K=K.replaceAll("[^A-Z]", "");
		K=K.replaceAll("I", "J");//矩阵里面没有I
		List<Character> key= new ArrayList<Character>();
		
		char[] Keyword=K.toCharArray();//装密钥
		int len= Keyword.length;
		for(int i=0; i<len; i++)
		{
			if(!key.contains(Keyword[i]))
			{key.add(Keyword[i]);}
		}
		
		List<Character> Matrix= new ArrayList<Character>(key);//装完整矩阵内容
		for(char ch='A'; ch<='Z'; ch++)
		{
			if(ch!='I' && !Matrix.contains(ch))
			{Matrix.add(ch);}
		}
		
		char[][] matrix= new char[5][5];//装入矩阵二维数组
		int temp=0;
		for(int i=0; i<5; i++)
		{
			for(int j=0; j<5; j++)
			 {
				matrix[i][j]=Matrix.get(temp++);
			}
		}
		
		return matrix;
	}
	
	public static void printmatrix(String K)
	{
		char[][] matrix= new char[5][5];
		matrix=matrix(K);
		
		for(int i=0; i<matrix.length; i++)//打印打印测试不要管不要看
		{
			for(int j=0; j<matrix[i].length; j++)
			{
				System.out.print(matrix[i][j]);
			}
			System.out.println();
		}
	}
	
	public static String dealPlaintext(String P)
	{
		P=P.toUpperCase();
		P=P.replace("[^A-Z]", "");
		StringBuilder p=new StringBuilder(P);
		
		for(int i=1; i<p.length(); i+=2)//不要打等于，i走到p.length的时候如果有等于能再走一遍，打了必死
		{
			if(p.charAt(i)==p.charAt(i-1))
			{p.insert(i, 'K');}//K插入重复字母
		}
		if(p.length()%2!=0)//非偶数明文处理
		{p.append(('X'));}
		
		String Plaintext=p.toString();
		return Plaintext;
	}
	
	public static String encryption(char[][] matrix, char ch1, char ch2)//字母替换
	{
		int row1=0, row2=0,col1=0, col2=0;
		for(int i=0; i<matrix.length; i++)//找位置
		{
			for(int j=0; j<matrix[i].length ; j++)
			{
				if(ch1==matrix[i][j])
				{
					row1=i;
					col1=j;
				}
				if(ch2==matrix[i][j])
				{
					row2=i;
					col2=j;
				}
			}
		}
		
		StringBuilder cipher=new StringBuilder();
		if(row1==row2)//同行
		{
			cipher.append(matrix[row1][(col1+1)%5]);
			cipher.append(matrix[row2][(col2+1)%5]);
		}
		else if (col1==col2)//同列
		{
			cipher.append(matrix[(row1+1)%5][col1]);
			cipher.append(matrix[(row2+1)%5][col2]);
		}
		else//不同行不同列
		{
			cipher.append(matrix[row1][col2]);
			cipher.append(matrix[row2][col1]);
		}
		return cipher.toString();
	}
	
	public static String Encrypt(String P, String K)//整体加密
	{
		char[] plaintext=dealPlaintext(P).toCharArray();
		char[][] Matrix=matrix(K);
		
		StringBuilder cipher= new StringBuilder();
		for(int i=0; i<P.length(); i+=2)
		{
			cipher.append(encryption(Matrix, plaintext[i], plaintext[i+1])); 
		}
		return cipher.toString();
	}
	
	
	

	//解密
	public static String decryption(char[][] matrix, char ch1, char ch2)
	{
		int row1=0, row2=0,col1=0, col2=0;
		for(int i=0; i<matrix.length; i++)//找位置
		{
			for(int j=0; j<matrix[i].length ; j++)
			{
				if(ch1==matrix[i][j])
				{
					row1=i;
					col1=j;
				}
				if(ch2==matrix[i][j])
				{
					row2=i;
					col2=j;
				}
			}
		}
		
		StringBuilder plain=new StringBuilder();
		if(row1==row2)//同行
		{
			plain.append(matrix[row1][(col1-1+5)%5]);
			plain.append(matrix[row2][(col2-1+5)%5]);
		}
		else if (col1==col2)//同列
		{
			plain.append(matrix[(row1-1+5)%5][col1]);
			plain.append(matrix[(row2-1+5)%5][col2]);
		}
		else//不同行不同列
		{
			plain.append(matrix[row1][col2]);
			plain.append(matrix[row2][col1]);
		}
		return plain.toString();
	}
	
	public static String Decrypt(String C, String K)
	{
		C=C.toUpperCase();
		C=C.replace("[^A-Z]", "");
		
		char[] ciphertext=C.toCharArray();
		char[][] Matrix=matrix(K);
		
		StringBuilder plain= new StringBuilder();
		for(int i=0; i<C.length(); i+=2)
		{
			plain.append(decryption(Matrix, ciphertext[i], ciphertext[i+1])); 
		}
		return plain.toString();
	}
	
	public static void main(String[] args)
	{
		Scanner input=new Scanner(System.in);
		String message="",K="",TypeofWork="";
		System.out.println("Input your message:");
		message=input.nextLine();
		System.out.println("keyword is necessary:");
		K=input.nextLine();
		if(K.length()<=25) 
		{
			printmatrix(K);
		}else {System.out.print("Keyword is illeagle");}
		
		System.out.println("Which type of work to do?(Encrypt) or (Decrypt(in this case, message must has even number of characters)");
		TypeofWork=input.nextLine();
		if(TypeofWork.equals("Encrypt"))
		{
			String C=Encrypt(message,K);
			System.out.println(C);
		}
		else if(TypeofWork.equals("Decrypt")) 
		{
			if(message.length()%2==0)
			{
				String P=Decrypt(message,K);
				System.out.println(P);
			}else {System.out.println("Illeagle message. In this case, message must has even number of characters");}
			
		}
		else
		{
			System.out.println("Error, cannot do this type of work.");
		}
		input.close();
	}
}
