#include<stdio.h>
#include<stdlib.h>
char encryption(char *message)
{
	for(int i=0; message[i]!='\n'; i++)
	{
		if(message[i]>='a'&&message[i]<='z')
		{
			message[i]=(message[i]-97+3)%26+97;
		}
		if(message[i]>='A'&&message[i]<='Z')
		{
			message[i]=(message[i]-65+3)%26+65;
		}
		
	}
	
}
 
char decryption(char *cipher) 
{
	for(int i=0; cipher[i]!='\n'; i++)
	{
		if(cipher[i]>='a'&&cipher[i]<='z')
		{
			cipher[i]=(cipher[i]-97+20)%26+97;
		}
		if(cipher[i]>='A'&&cipher[i]<='Z')
		{
			cipher[i]=(cipher[i]-65+20)%26+65;
		}
	}
}
 
void main()
{	
	printf("Please input your message\n");
	char *message;
	message=(char*)malloc(sizeof(char));
	scanf("%s",message);
	for(int i=0;message[i]!='\0' ;i++)
	printf("%s\n",message); 
	
	encryption(message);
	printf("%s\n",message);
	decryption(message); 
	printf("%s\n",message);
}
