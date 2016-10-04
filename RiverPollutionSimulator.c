/*******************************
	Name: Aaron Ambrose
	Program: Homework 6
	Program Description: Estimate how long it takes the oxygen level to return to baseline
						 after a bolus injection of a pollutant is added to the river stream.
	Date: 11/12/2014
*******************************/

#include<stdio.h>
#include<math.h>

double reaeration(double numbers[]);
void deOxygen(double result[], double numbers[], double times[], double kr);

int main(void)
{
	double kr = 0;
	int counter = 0;
	double num = 0;
	double numbers[6] = {0};
	double result[501] = {0};
	double times[501] = {0};
	double t = 0;
	int i = 0;

	FILE *numbersPtr;
	FILE *resultsPtr;

	numbersPtr = fopen("PollutionData.txt", "r");

	if (numbersPtr == NULL)
	{
		printf("File does not exist\n");
	}

	else
	{
		while(counter!=6)
		{
			fscanf(numbersPtr, "%lf", &num);
			numbers[counter] = num;
			counter = counter + 1;

		}
	}

	fclose(numbersPtr);

	for(i = 0; i < 501; i++)
	{
		times[i] = t;
		t = t + .5;
	}

	numbers[2] = numbers[2] * 3600;
	numbers[3] = numbers[3] * 30.48;
	numbers[4] = numbers[4] * 3600 * 30.48;

	kr = reaeration(numbers);

	deOxygen(result, numbers, times, kr);

	i = 1;
	while(result[i]>5)
	{
		i = i + 1;
	}

	printf("Oxygen deficit returned to the initial deficit at %lf hours", times[i]);

	resultsPtr = fopen("results.dat", "w");

	if(resultsPtr == NULL)
	{
		printf("Error reading file!");
	}

	i = 0;

	while(i<501)
	{
		fprintf(resultsPtr, "%lf", times[i]);
		fprintf(resultsPtr, " " );
		fprintf(resultsPtr, "%lf", result[i]);
		fprintf(resultsPtr, "\n");
		i = i + 1;
	}

	fclose(resultsPtr);

	return 0;
}

/**************************************
	Function: Reaeration: Calculates what the constant k is.
	Inputs: V(average velocity of river), S(Diffusivity of oxygen in water), H(Depth of flow of river)
	Output: Reaeration constant k
***************************************/
double reaeration(double numbers[])
{
	double a = 0;
	double b = 0;
	double c = 0;
	a = (numbers[4] * numbers[2]);
	a = pow(a, .5);
	b = pow(numbers[3], 1.5);
	c = a / b;
	return c;
}

/**************************************
	Function: deOxygen: Simulates dissolved oxygen deficit
	Inputs: Result array, times array, pollution values array, counting scalar
	Output: void
***************************************/
void deOxygen(double result[], double numbers[], double times[], double kr)
{

	double a = 0;
	double b = 0;
	double c = 0;
	double e = 2.718281828459;
	double exp1 = 0;
	double exp2 = 0;
	int scalar = 0;

	a = numbers[5] * numbers[1];
	b = kr - numbers[5];
	c = a / b;

	while(scalar < 501)
	{
		exp1 = (-1) * (numbers[5]) * (times[scalar]);
		exp2 = (-1) * (kr) * (times[scalar]);
		result[scalar] = c * ((pow(e,exp1)) - pow(e,exp2)) + (numbers[0] * (pow(e,exp2)));
		exp1 = 0;
		exp2 = 0;
		scalar = scalar + 1;
	}
}
