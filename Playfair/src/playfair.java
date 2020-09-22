import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class playfair {
	public static char[][] matrix(String K)//K=��Կ
	{
		K=K.toUpperCase();
		K=K.replaceAll("[^A-Z]", "");
		K=K.replaceAll("I", "J");//��������û��I
		List<Character> key= new ArrayList<Character>();
		
		char[] Keyword=K.toCharArray();//װ��Կ
		int len= Keyword.length;
		for(int i=0; i<len; i++)
		{
			if(!key.contains(Keyword[i]))
			{key.add(Keyword[i]);}
		}
		
		List<Character> Matrix= new ArrayList<Character>(key);//װ������������
		for(char ch='A'; ch<='Z'; ch++)
		{
			if(ch!='I' && !Matrix.contains(ch))
			{Matrix.add(ch);}
		}
		
		char[][] matrix= new char[5][5];//װ������ά����
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
		
		for(int i=0; i<matrix.length; i++)//��ӡ��ӡ���Բ�Ҫ�ܲ�Ҫ��
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
		
		for(int i=1; i<p.length(); i+=2)//��Ҫ����ڣ�i�ߵ�p.length��ʱ������е���������һ�飬���˱���
		{
			if(p.charAt(i)==p.charAt(i-1))
			{p.insert(i, 'K');}//K�����ظ���ĸ
		}
		if(p.length()%2!=0)//��ż�����Ĵ���
		{p.append(('X'));}
		
		String Plaintext=p.toString();
		return Plaintext;
	}
	
	public static String encryption(char[][] matrix, char ch1, char ch2)//��ĸ�滻
	{
		int row1=0, row2=0,col1=0, col2=0;
		for(int i=0; i<matrix.length; i++)//��λ��
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
		if(row1==row2)//ͬ��
		{
			cipher.append(matrix[row1][(col1+1)%5]);
			cipher.append(matrix[row2][(col2+1)%5]);
		}
		else if (col1==col2)//ͬ��
		{
			cipher.append(matrix[(row1+1)%5][col1]);
			cipher.append(matrix[(row2+1)%5][col2]);
		}
		else//��ͬ�в�ͬ��
		{
			cipher.append(matrix[row1][col2]);
			cipher.append(matrix[row2][col1]);
		}
		return cipher.toString();
	}
	
	public static String Encrypt(String P, String K)//�������
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
	
	
	

	//����
	public static String decryption(char[][] matrix, char ch1, char ch2)
	{
		int row1=0, row2=0,col1=0, col2=0;
		for(int i=0; i<matrix.length; i++)//��λ��
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
		if(row1==row2)//ͬ��
		{
			plain.append(matrix[row1][(col1-1+5)%5]);
			plain.append(matrix[row2][(col2-1+5)%5]);
		}
		else if (col1==col2)//ͬ��
		{
			plain.append(matrix[(row1-1+5)%5][col1]);
			plain.append(matrix[(row2-1+5)%5][col2]);
		}
		else//��ͬ�в�ͬ��
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
