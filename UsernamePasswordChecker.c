/*******************************
	Name: Aaron Ambrose
	Program: Homework 5
	Program Description: Prompt user for user name and passwords.  Tell user if it is valid or invalid
						 and the reasons for it being invalid.  Do this until EXIT is entered.
	Date: 11/1/2014
*******************************/

#include<stdio.h>
#include<string.h>
#include<ctype.h>

int alphabetic(char username[]);
int capital(char password[]);
int contain(char upperusername[], char upperpassword[]);

int main(void)
{
	char username[100] = {0};
	char password[100] = {0};
	char upperusername[100] = {0};
	char upperpassword[100] = {0};
	char exit[100] = "EXIT";
	int a,length,plength,b,c,i,character,alphabet,wrong,cap,copy,valid;

	while(strcmp(username,exit)!=0) //Compares the username string to the string called EXIT
	{

		printf("Enter a username.\n");
		scanf("%s", username);
		for(length=0; username[length]!='\0'; length++); //finds the length of the username

		if(length!=4)  //character is set depending on weather or not length is correct
		{
			character = 1;
		}
		else
		{
			character = 0;
		}

		a = alphabetic(username);//calls alphabetic function
		if(a==0)
		{
			alphabet = 1;
		}
		else
		{
			alphabet = 0;
		}

		printf("Enter a password\n");
		scanf("%s", password);
		for(plength=0; password[plength]!='\0'; plength++);//finds length of password

		if(plength<6 || plength>12)//makes sure length is correct
		{
			wrong = 1;
		}
		else
		{
			wrong = 0;
		}

		b = capital(password);//calls capital function
		if(b==1)
		{
			 cap = 1;
		}
		else if(b == 2)
		{
			cap = 2;
		}
		else if(b == 3)
		{
			cap = 3;
		}

		for(i = 0; i < strlen(username); i++)//changes the username to all uppercase for comparison
		{
			upperusername[i] = toupper(username[i]);
		}

		for(i = 0; i < strlen(password); i++)//changes the password to all upercase for comparison
		{
			upperpassword[i] = toupper(password[i]);
		}

		c = contain(upperusername,upperpassword);//calls the contain function

		if(c==1)
		{
			copy = 1;
		}

		//the following if statements take the variables found earlier and will print their corresponding problems with the username and password

		if(character==1)//if username is not correct length, this runs
		{
			printf("Username must be 4 characters long.\n");
			valid = valid + 1;
		}
		if(alphabet==1)//if non alphabetic letters are included in the username, this runs
		{
			printf("Only include alphabetic letters in the username\n");
			valid = valid + 1;
		}
		if(wrong==1)//if the password is not the correct length, this runs
		{
			printf("Password must be between 6 and 12 characters.\n");
			valid = valid + 1;
		}
		if(cap==2)//if capitol letter is not included in password, this runs
		{
			printf("Password must include a capitol letter.\n");
			valid = valid + 1;
		}
		if(cap==1)//if number is not included in password, this runs
		{
			printf("Password must include a number.\n");
			valid = valid + 1;
		}
		if(copy==1)//if username is found in the password, this runs
		{
			printf("Password cannot contain username, case in-sensitive.\n");
			valid = valid + 1;
		}
		if(valid==0)//if all the constraints are met, this runs
		{
			printf("Combination Correct!\n");
		}

		length = 0;//resets all used variables back to 0
		plength = 0;
		a = 0;
		b = 0;
		c = 0;
		i = 0;
		character = 0;
		alphabet = 0;
		wrong = 0;
		cap = 0;
		copy = 0;
		valid = 0;
	}
	return 0;
}

/**************************************************
	Function: Checks if the username contains only letters
	Inputs: Username String
	Outputs: 0 if the string is valid, 1 if it is invalid
***************************************************/
int alphabetic(char username[])
{
	int length = 0;
	int a = 1;
	char num1 = 65;
	char num2 = 90;
	char num3 = 97;
	char num4 = 122;

	for(length=0; username[length]!='\0'; length++)//calculates the length of the username
	{
		if(username[length]<num1 || (username[length]>num2 && username[length]<num3) || username[length]>num4)//makes sure only letters are used in the username
		{
			a = 0;
		}
	}
	return a;
}
/**************************************************
	Function: Checks if the password has a capital letter and a number
	Inputs: Password String
	Outputs: 3 if both are valid, 2 if password does not contain a
			 capitol letter, and 1 if password does not contain a number
***************************************************/
int capital(char password[])
{
	int length = 0;
	int a = 0;
	int b = 0;
	char num1 = 64;
	char num2 = 90;
	char num3 = 47;
	char num4 = 58;

	for(length=0; password[length]!='\0'; length++)//finds length of the password
	{
		if(password[length]>num1 && password[length]<num2 )//calculates if there are capitol letters in the password
		{
			a = a + 1;
		}

		if(password[length]>num3 && password[length]<num4)//calculates if there are numbers in the password
		{
			b = b +1;
		}
	}
	if(a>0 && b>0)//if there is both capitols and numbers, this is returned
	{
		a = 3;
	}
	else if(a==0)//if there is not a capitol letter, this is returned
	{
		a = 2;
	}
	else if(b==0)//if there is not a number, this is returned
	{
		a = 1;
	}
	return a;
}

/**************************************************
	Function: Checks if the password contains the username
	Inputs: Password and username String
	Outputs: 0 if it does not contain, 1 if it contains
***************************************************/
int contain (char upperusername[],char upperpassword[])
{
	int passcount=0,usercount=0,i;
	int k = 0;

	for(passcount=0; upperpassword[passcount]!='\0'; passcount++)//calculates the length of the password

	for(usercount=0; upperusername[usercount]!='\0'; usercount++)//calculates the length of the username

	for(i=0;i<=passcount-usercount; i++)//if a letter of the password matches a letter of the username, it checks if the next three letters match as well
	{
		if(upperpassword[i]==upperusername[0]&&upperpassword[i+1]==upperusername[1]&&upperpassword[i+2]==upperusername[2]&&upperpassword[i+3]==upperusername[3])
		{
			k = 1;//if the username matches a section in the password, k is set to one
		}
	}
	return k;
}
