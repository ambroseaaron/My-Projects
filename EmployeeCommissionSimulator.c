/*******************************
	Name: Aaron Ambrose
	Program: Homework 3
	Program Description: This program simulates the sales in a store for 16 employees paid on commission
	Date: 10/7/2014
*******************************/

#include <stdio.h>
#include<time.h>
#include<stdlib.h>

int main(void)
{
	int dollars[16]={0};
	int sales[16] ={0};
	int scannedsales = 0;
	int employee;
	int menu = 0;
	int dollar = 0;
	float average;
	int i = 0;
	int big = 0;
	int counter = 0;
	int small = 0;

	srand(time(NULL));

	printf("Enter the number of sales to be simulated\n");
	scanf("%d", &scannedsales);

	while (scannedsales>10000) //Makes sure the sales to be calculated is less than 10000.
	{
		printf("Enter a number less than 10,000\n");
		scanf("%d", &scannedsales);
	}

	while (scannedsales!=0)		//Choses a random employee, random dollar amount, and adds the dollar
	{							//amounts to each employee until the amount of sales scanned reaches 0.
		employee = rand( ) % 16;

		dollar = rand( ) % 1001;

		dollars[employee] = dollars[employee] + dollar;

		sales[employee] = sales[employee] + 1;

		scannedsales = scannedsales-1;
	}

	while (menu!='E')
	{
		printf("1: ID number and the total dollar amount of the sales\n");
		printf("2: ID number and the total number of sales\n");
		printf("3: ID number and the average dollar amount of each sale\n");
		printf("4: ID numbers of the employees with the most and least sales\n");
		printf("5: Histogram of employees\n");
		printf("6: EXIT\n");
		scanf("%c", &menu);

		switch (menu)
		{
			case 'T':

				printf("%s%13s\n", "Employee     ", "Dollar Amount of Sales");

					for(employee=0; employee<16; employee++)	//Prints the employee ID and the total
					{											//dollar amount of sales of the employee
						printf("%7u%13d\n", employee, dollars[employee]);
					}
					break;

			case 'D':

				printf("%s%13s\n", "Employee     ", "Total Number of Sales");

					for(employee=0; employee<16; employee++)	//Prints the employee ID and the total
					{											//number of sales for the employee
						printf("%7u%13d\n", employee, sales[employee]);
					}
					break;

			case 'R':

				printf("%s%13s\n", "Employee     ", "Average of Each Sale");

					for(employee=0; employee<16; employee++)
					{
						if(sales[employee]==0)	//if employee doesnt have any sales, print the average dollar
						{						//amount per sale to be 0
							average=0;
							printf("%7u%13lf\n", employee, average);
						}

						else
						{
							average = (float) (dollars[employee])/(sales[employee]);	//If employee has sales,
							printf("%7u%13lf\n", employee, average);					//calculate the average by
						}															//dividing dollars of sales by sales
					}
					break;

			case 'S':

				big = 0;

				for(employee=0; employee<16; employee++)	//Counts down each employee to determine if that
				{											//employee's sales is bigger than the current
					if(big<=(sales[employee]))				//big number of sales
					{
						big=(sales[employee]);
					}
				}

				for(employee=15; employee!=-1; employee--)	//Finds out which employee, starting from the highest ID number,
				{											//matches the amount of sales for the big sale and then prints
					if(big==sales[employee])				//that ID number
					{
						printf("The employee with the most sales is employee number %u.\n", employee);
						break;
					}
				}

				small = sales[0];

				for(employee=0; employee<16; employee++)	//The next two for loops do the same as above, only looking
				{											//for the small sale instead of the big sale
					if(small>=(sales[employee]))
					{
						small=(sales[employee]);
					}
				}

				for(employee=15; employee!=-1; employee--)
				{
					if(small==sales[employee])
					{
						printf("The employee with the least sales is employee number %u.\n", employee);
						break;
					}
				}
				break;

			default:

				printf("Incorrect input\n");	//Printed when a number not on the menu is pressed
				break;
		}
	}

  return 0;

}
