/*****************************
	Name: Aaron Ambrose
	Program: Homework 2
	Program Description: Program to calculate the number of cables, force on each cable, and the diameter of the cables
						 when a road deck length and a tower height are scanned into the program.
	Date: 9/17/14
******************************/

	#include <stdio.h>

	int main (void)
{
	float length = 0;
	float height = 0;
	int cables = 0;
	float force = 0;
	float diameter = 0;
	float halfcables = 0;
	float newforce = 0;

	while (length<20) //Waiting for user to scan in a valid road deck length
	{
		printf("Insert a road deck length in meters >20\n");
		scanf("%f", &length);
	}

	printf("Insert a tower height between 1/3 and 1/5 the size of the road deck length\n");
	scanf("%f", &height);

	while (height>=length*(1./3.)||(height<=length*(1./5.))) //Getting user to enter a valid tower height
	{

		printf("Insert a tower height between 1/3 and 1/5 the size of the road deck length\n");
		scanf("%f", &height);
	}

	while (length>10) //Cables are each 5m apart so i am subtracting 10 from the road deck length and adding two cables, one from each side, until there is less than 10m between the two inner cables
	{
		length=length-10;
		cables=cables+2;
	}

	printf("The number of cables needed is %d \n", cables);

	halfcables=cables/2;

	while (halfcables!=0) //I am calculating the forces for each of the cables on one half of the bridge
	{
		newforce

	return 0;
}
