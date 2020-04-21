class Account:
    def __init__(self, name,
                 accountNumber,
                 balance,
                 limit = 0,
                 amountOfWithdrawals = 0,
                 amountOfDeposits = 0,
                 amountOfPenalties = 0):
        
        self.name = name
        self.accountNumber = accountNumber
        self.balance = balance
        self.limit = limit
        self.amountOfWithdrawals = amountOfWithdrawals
        self.amountOfDeposits = amountOfDeposits
        self.amountOfPenalties = amountOfPenalties

    def withdraw(self, amount):
        self.limit = self.balance * .1
    
        if amount > self.limit:
            print("Name: " + self.name +
                  " | Attempted Withdrawal: " + str(amount) +
                  " | Withdraw Limit: " + str(self.limit) +
                  " | Penalty: -$5")
            self.balance -= 5
            scrooge.balance += 5
            self.amountOfPenalties += 1

            getStatement()
        else:
            self.balance = self.balance - amount
            print("Name: " + self.name +
                  " | Withdrawn: " + str(amount) +
                  " | Balance: " + str(self.balance))

            getStatement()
            self.amountOfWithdrawals += 1

            nameChecker(self, amount)

    def deposit(self, amount):
        self.balance += amount

        print("Name: " + self.name +
              " | Deposited: " + str(amount) +
              " | Balance: " + str(self.balance))

        getStatement()
        

def getStatement():
    print("Huey Duck's Balance: " + str(huey.balance))
    print("Dewey Duck's Balance: " + str(dewey.balance))
    print("Louie Duck's Balance: " + str(louie.balance))
    print("Scrooge McDuck's Balance:" + str(scrooge.balance))

def nameChecker(person,amount):

    scroogeAmount = 2 * amount
    
    if person.name == "Huey Duck":
        scrooge.withdraw(scroogeAmount)
        dewey.deposit(amount)
        louie.deposit(amount)
        
    elif person.name == "Dewey Duck":
        scrooge.withdraw(scroogeAmount)
        huey.deposit(amount)
        louie.deposit(amount)
    elif person.name == "Louie Duck":
        scrooge.withdraw(scroogeAmount)
        huey.deposit(amount)
        dewey.deposit(amount)
    else:
        return None

huey = Account("Huey Duck", 700007, 150)
dewey = Account("Dewey Duck", 800008, 350)
louie = Account("Louie Duck", 900009, 25)
scrooge = Account("Scrooge McDuck", 100001, 1000000)

louie.withdraw(2)
dewey.withdraw(20)
huey.withdraw(20)
louie.withdraw(10)
dewey.withdraw(20)
huey.withdraw(30)
louie.withdraw(40)


