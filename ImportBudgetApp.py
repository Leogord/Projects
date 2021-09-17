import BudgetApp as ap



food = ap.BudgetApp("food")
films = ap.BudgetApp("films")
treats = ap.BudgetApp("treats")

while True:
    print("Select from the items below")
    print("1        Transfer")
    print("2        Deposit")
    print("3        Withdraw")
    print("4        Balance")
    print("5        Exit")
    choice = int(input(": "))
    #Transfer-----------------------------------------------------------
    if choice == 1:
        print("Select category transfer from: ")
        print("1        food")
        print("2        treats")
        print("3        films")
        SChoice = int(input(": "))
        if SChoice == 1:
            print("Select category to transfer to : ")
            print("1        treats")
            print("2        films")    
            TChoice = int(input(": "))
            if TChoice == 1:
                food.transfer(treats)
            elif TChoice == 2:
                food.transfer(films)
        elif choice == 2:
            print("Select category to transfer to : ")
            print("1        food")
            print("2        films")    
            TChoice = int(input(": "))
            if TChoice == 1:
                treats.transfer(food)
            elif TChoice == 2:
                treats.transfer(films)
        elif choice == 3:
            print("Select category to transfer to : ")
            print("1        treats")
            print("2        food")    
            TChoice = int(input(": "))
            if TChoice == 1:
                films.transfer(treats)
            elif TChoice == 2:
                films.transfer(food)
#Deposit----------------------------------------------------------------------------------------
    elif choice == 2:
        print("Select category: ")
        print("1        food")
        print("2        treats")
        print("3        films")
        SChoice = int(input(": "))
        if SChoice == 1:
            food.deposit()
        elif SChoice == 2:
            treats.deposit()
        elif SChoice == 3:
            treats.deposit()
#Withdraw---------------------------------------------------------------------------------------            
    elif choice == 3:
        print("Select category: ")
        print("1        food")
        print("2        treats")
        print("3        films")
        SChoice = int(input(": "))
        if SChoice == 1:
            food.withdraw()
        elif SChoice == 2:
            treats.withdraw()
        elif SChoice == 3:
            treats.withdraw()
#Balance------------------------------------------------------------------------------------------
    elif choice == 4:
        print("Select category: ")
        print("1        food")
        print("2        treats")
        print("3        films")
        SChoice = int(input(": "))
        if SChoice == 1:
            food.bal()
            print(f"Items bought so far: {food.transHis}")
        elif SChoice == 2:
            treats.bal()
        elif SChoice == 3:
            films.bal()
    elif choice == 5:
        exit()
    
