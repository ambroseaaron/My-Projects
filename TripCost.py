# tripCost function that calculates the cost of a road trip based on vehicle and costs
def tripCost(distance, vehSpeed, vehMPG, gasCostPerGallon, hotelCostPerNight):
    
    #determines how many hours the trip will take
    hours = distance / vehSpeed
    hotelNights = 0

    while(hours > 8.0): #determines how many hotel nights will be needed
        hours -= 8.0
        hotelNights += 1

    #adds the cost based on any hotel nights
    cost = hotelCostPerNight * hotelNights

    #calculates how many gallons of gas will be needed
    gallons = distance / vehMPG

    #adds the cost of the gas to the total cost
    cost += (gallons * gasCostPerGallon)
    return cost

# chooseVehicleForTrip calculates the lowest cost of a road trip comparing two vehicles
def chooseVehicleForTrip(distance, veh1Name, veh1Speed, veh1MPG, veh2Name, veh2Speed, veh2MPG, gasCostPerGallon, hotelCostPerNight):

    #calculate the cost of the trip using the tripCost function for both cars
    cost1 = tripCost(distance, veh1Speed, veh1MPG, gasCostPerGallon, hotelCostPerNight)
    cost2 = tripCost(distance, veh2Speed, veh2MPG, gasCostPerGallon, hotelCostPerNight)

    #calculate the hours for the trip for both cars
    hours1 = distance / veh1Speed
    hours2 = distance / veh2Speed

    #print the information for each car and compare which one is more cost efficient
    print(str(distance) + " miles in vehicle '" + str(veh1Name) + "' will take " + str(hours1) + " hours and cost $" + str(cost1))
    print(str(distance) + " miles in vehicle '" + str(veh2Name) + "' will take " + str(hours2) + " hours and cost $" + str(cost2))
    if(cost1 < cost2):
        print("The best car for the trip is " + str(veh1Name))
    if(cost2 < cost1):
        print("The best car for the trip is " + str(veh2Name))
    if(cost1 == cost2):
        print("Both cars cost the same for the trip")
