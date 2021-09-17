class BudgetApp:

    def __init__(self,name):
        self.category = name
        self.balance = float(input(f"Enter budget for {name}: "))
        self.transHis = []

    def withdraw(self):
        addedItem = input("Enter name of purchase: ")
        spent = float(input("Enter price: "))
        if spent > self.balance:
            return print("Error not enough money") 
        self.transHis.append(F"{addedItem} cost £{spent}")
        self.balance -= spent

    def deposit(self,):
        depos = float(input("Enter amount to deposit: "))
        self.balance += depos

    def transfer(self,otherObj):
        amount = float(input("Enter price: "))
        if amount > self.balance:
            return print("Error not enough money")
        self.balance -= amount
        otherObj.balance += amount

    def bal(self):
        print(f"£{self.balance}")




