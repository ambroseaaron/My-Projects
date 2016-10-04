/*******************************
	Name: Aaron Ambrose
	Program: Homework 4
	Program Description: This program creates a menu that will do calculations of many different math functions.
	Date: 10/7/2014
*******************************/

#include <stdio.h>

float factorial(int y);
double power(double x, int y);
double sine(double angle);
double arctan(double angle);
double height(double f, double l);
double angles(double towerheight, double length);

int main(void)
{
	int fact = 0;
	int menu = 0;
	float result = 0;
	double base = 0;
	int exponent = -1;
	double angle = 0;
	double answer = 0;
	double pi = 0;
	double f = 0;
	double length = 0;
	double towerheight = 0;
	double l = 0;
	double opposite = 0;
	double adjacent = 0;

	while (menu!=8)
	{
		printf("1: Factorial (y=x!)\n");
		printf("2: Power (y=base^exponent)\n");
		printf("3: Sine (y=sine(x))\n");
		printf("4: Arctangent (y=arctan(x))\n");
		printf("5: Calculation of Pi\n");
		printf("6: Calculation of tower height given angle (in degrees) and length of cable\n");
		printf("7: Calculation of angles of cables when given tower height and road deck length\n");
		printf("8: Exit\n");
		scanf("%d", &menu);

		switch (menu)
		{
			case 1:                                 //Finds the factorial of the inputs and determines if it is real or undefined
				printf("Enter in a number\n");
				scanf("%d", &fact);
				result = factorial(fact);

				if(result>=0)
				{
					printf("The factorial of %d is %f\n", fact, result);
				}

				else
				{
					printf("The factorial of %d is undefined.\n", fact);
				}

			break;

			case 2:                                  //Calculates the power of a base and its exponent
				printf("Enter a base.\n");           //Also makes sure that the exponent is not negative
				scanf("%lf", &base);
				printf("Enter an exponent.\n");
					scanf("%d", &exponent);

				while(exponent<0)
				{
					printf("Enter an exponent.\n");
					scanf("%d", &exponent);
				}

				result = power(base, exponent);
				printf("The value of %lf to the %d is %lf\n", base, exponent, result);

			break;

			case 3:
				printf("Enter an angle to find the sine of.\n");  //Calls the sine function to calculate the sine of an angle
				scanf("%lf", &angle);
				answer = sine(angle);
				printf("The sine of %lf is %lf\n", angle, answer);

			break;

			case 4:
				printf("Enter the opposite side length.\n");  //Calls the arctan function to calculate the arctan of the ratio
				scanf("%lf", &opposite);
				printf("Enter the adjacent side length.\n");
				scanf("%lf", &adjacent);
				angle = opposite/adjacent;
				answer = arctan(angle);
				answer = answer * 180 / 3.14159;
				printf("The arctan of %lf is %lf\n", angle, answer);

			break;

			case 5:
				pi = 4 * (6.*arctan(1./8.) + 2.*arctan(1./57.) + arctan(1./239.));  //Calculates pi
				printf("Pi is equal to %lf\n", pi);

			break;

			case 6:
				printf("Enter in an angle that a cable makes with the road.\n");  //Calls the function to find the tower height
				scanf("%lf", &f);
				printf("Enter in the length of that cable.\n");
				scanf("%lf", &l);
				answer = height(f, l);
				printf("The height of the tower is %lf\n", answer);

			break;

			case 7:

				printf("Insert a road deck length in meters >20\n");
				scanf("%lf", &length);

				while (length<20) //Waiting for user to scan in a valid road deck length
				{
					printf("Insert a road deck length in meters >20\n");
					scanf("%lf", &length);
				}

				printf("Insert a tower height between 1/3 and 1/5 the size of the road deck length\n");
				scanf("%lf", &towerheight);

				while (towerheight>=length*(1./3.)||(towerheight<=length*(1./5.))) //Getting user to enter a valid tower height
				{

					printf("Insert a tower height between 1/3 and 1/5 the size of the road deck length\n");
					scanf("%lf", &towerheight);
				}

				answer = angles(towerheight, length);

			break;

		}
	}
	return 0;
}

/**************************************************
	Function: Computes the factorial of a number.
	Inputs: int y - the scanned number
	Outputs: float f - the factorial of the number
***************************************************/
float factorial(int y)
{
	int i = 0;
	float f = 1;
	if(y>0)  //Calculates the factorial unless the factorial is undefined
	{
		for(i=1; i<=y; i++)
		{
			f = f * i;
		}
		return f;
	}
	else if(y==0)
	{
		return 0;
	}
	else
	{
		return -1;
	}
}

/***************************************************
	Function: Takes a base and an exponent and finds the power of the two
	Inputs: double x - the base
			int y - the exponent
	Outputs: double p - the power of the base and exponent
***************************************************/
double power(double x, int y)
{
	double p = 0;
	double number = 0;
	p = x;

	if(y==0)  //If inputed power is zero, you return 0
	{
		return 0;
	}

	else if(y==1)  //If inputed power is 1, return the inputed base
	{
		number = x;
		return number;
	}

	else
	{
		while (y!=1)  //Multiplies the base by itself and subtracts from the exponent until the exponent is zero
		{
			p = p*x;
			y = y-1;
		}
		return p;
	}
}

/***************************************************
	Function: Finds the sine of an angle
	Inputs: angle as a double
	Outputs: sine of angle as a double
***************************************************/
double sine(double angle)  //Changes the angle to the correct form and does the sine formula to calculate it
{
	double sine = 0;
	double x = 0;
	x = angle * 3.14 / 180;
	sine = x - power(x,3)/factorial(3) + power(x,5)/factorial(5) - power(x,7)/factorial(7) + power(x,9)/factorial(9) - power(x,11)/factorial(11) + power(x,13)/factorial(13) - power(x,15)/factorial(15) + power(x,17)/factorial(17) - power(x,19)/factorial(19) + power(x,21)/factorial(21) - power(x,23)/factorial(23) + power(x,25)/factorial(25) - power(x,27)/factorial(27) + power(x,29)/factorial(29) - power(x,31)/factorial(31) + power(x,33)/factorial(33) - power(x,35)/factorial(35) + power(x,37)/factorial(37) - power(x,39)/factorial(39) + power(x,41)/factorial(41) - power(x,43)/factorial(43) + power(x,45)/factorial(45) - power(x,47)/factorial(47) + power(x,49)/factorial(49);
	return sine;
}

/***************************************************
	Function: Finds the arctan of an angle
	Inputs: angle as a double
	Outputs: arctan of angle as a double
***************************************************/
double arctan(double angle)  //Finds out what formula to use and uses it and then returns the answer
{
	double arctan = 0;
	double x = 0;
	double c = 0;
	x = angle;
	c = x * x;
	if(c<=1)
	{
		arctan = x - power(x,3)/3 + power(x,5)/5 - power(x,7)/7 + power(x,9)/9 - power(x,11)/11 + power(x,13)/13 - power(x,15)/15 + power(x,17)/17 - power(x,19)/19 + power(x,21)/21 - power(x,23)/23 + power(x,25)/25 - power(x,27)/27 + power(x,29)/29 - power(x,31)/31 + power(x,33)/33 - power(x,35)/35 + power(x,37)/37 - power(x,39)/39 + power(x,41)/41 - power(x,43)/43 + power(x,45)/45 - power(x,47)/47 + power(x,49)/49;
	}

	else if(x>1)
	{
		arctan = (3.14159/2) - (1/x) + (1)/(3*power(x,3)) - (1)/(5*power(x,5)) + (1)/(7*power(x,7)) - (1)/(9*power(x,9)) + (1)/(11*power(x,11)) - (1)/(13*power(x,13)) + (1)/(15*power(x,15)) - (1)/(17*power(x,17)) + (1)/(19*power(x,19)) - (1)/(21*power(x,21)) + (1)/(23*power(x,23)) - (1)/(25*power(x,25)) + (1)/(27*power(x,27)) - (1)/(29*power(x,29)) + (1)/(31*power(x,31)) - (1)/(33*power(x,33)) + (1)/(35*power(x,35)) - (1)/(37*power(x,37)) + (1)/(39*power(x,39)) - (1)/(41*power(x,41)) + (1)/(43*power(x,43)) - (1)/(45*power(x,45)) + (1)/(47*power(x,47)) - (1)/(49*power(x,49));
	}

	else if(x<-1)
	{
		arctan = (-3.14159/2) - (1/x) + (1)/(3*power(x,3)) - (1)/(5*power(x,5)) + (1)/(7*power(x,7)) - (1)/(9*power(x,9)) + (1)/(11*power(x,11)) - (1)/(13*power(x,13)) + (1)/(15*power(x,15)) - (1)/(17*power(x,17)) + (1)/(19*power(x,19)) - (1)/(21*power(x,21)) + (1)/(23*power(x,23)) - (1)/(25*power(x,25)) + (1)/(27*power(x,27)) - (1)/(29*power(x,29)) + (1)/(31*power(x,31)) - (1)/(33*power(x,33)) + (1)/(35*power(x,35)) - (1)/(37*power(x,37)) + (1)/(39*power(x,39)) - (1)/(41*power(x,41)) + (1)/(43*power(x,43)) - (1)/(45*power(x,45)) + (1)/(47*power(x,47)) - (1)/(49*power(x,49));
	}

	return arctan;
}

/***************************************************
	Function: Finds the height of the tower
	Inputs: angle the cable makes with the road and the cable length in doubles
	Outputs: height of the tower as a double
***************************************************/
double height(double f, double l)  //Uses the length and the angle to calculate the height from formula
{
	double height = 0;
	height = l * sine(f);
	return height;
}

/***************************************************
	Function: Finds the angle that each cable makes with the road
	Inputs: tower height and road deck length
	Outputs: angle of the cables
***************************************************/
double angles(double towerheight, double length)
{
	int cables = 0;
	double newlength = 0;
	double angle = 0;
	double x = 0;

	length = length/2;
	cables = length / 5;
	newlength = length;
	printf("Cables				Angle\n");

	while (newlength>=5)  //Calculates the arctan of the height/length and keeps subtracting 5m and 1 cable until the length is less than 5
	{
		x = towerheight/newlength;
		angle = arctan(x);
		angle = angle * 180 / 3.14159;
		printf("%d				%lf\n", cables, angle);
		cables = cables - 1;
		newlength = newlength - 5;
	}
	return 0;
}
