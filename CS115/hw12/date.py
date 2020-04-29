'''
Created on April 29, 2020
@author:   Ming Lin
Pledge:    I plegde my honor that i have abided by the stevens honor code

CS115 - Hw 12 - Date class
'''
DAYS_IN_MONTH = (0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

week = ('Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday', 'Monday', 'Tuesday')

class Date(object):
    '''A user-defined data structure that stores and manipulates dates.'''

    # The constructor is always named __init__.
    def __init__(self, month, day, year):
        '''The constructor for objects of type Date.'''
        self.month = month
        self.day = day
        self.year = year

    # The 'printing' function is always named __str__.
    def __str__(self):
        '''This method returns a string representation for the
           object of type Date that calls it (named self).

             ** Note that this _can_ be called explicitly, but
                it more often is used implicitly via the print
                statement or simply by expressing self's value.'''
        return '%02d/%02d/%04d' % (self.month, self.day, self.year)

    def __repr__(self):
        '''This method also returns a string representation for the object.'''
        return self.__str__()

    # Here is an example of a 'method' of the Date class.
    def isLeapYear(self):
        '''Returns True if the calling object is in a leap year; False
        otherwise.'''
        if self.year % 400 == 0:
            return True
        if self.year % 100 == 0:
            return False
        if self.year % 4 == 0:
            return True
        return False

    def copy(self):
        '''Returns a new object with the same month, day, year
        as the calling object (self).'''
        dnew = Date(self.month, self.day, self.year)
        return dnew

    def equals(self, d2):
        '''Decides if self and d2 represent the same calendar date,
        whether or not they are the in the same place in memory.'''

        return self.year == d2.year and self.month == d2.month and \
               self.day == d2.day

    def tomorrow(self):
        '''Returns one calendar day after the date it originally represented'''
        if self.isLeapYear():
            if self.month == 2: 
                if self.day == 29:
                    self.day = 1
                    self.month = 3
                else: self.day +=1
            elif self.day == DAYS_IN_MONTH[self.month]:
                if self.month == 12:
                    self.day = 1
                    self.month = 1
                    self.year+=1
                else:
                    self.day = 1
                    self.month+=1
            else:
                self.day+=1
        else:
            
            if self.day == DAYS_IN_MONTH[self.month]:
                if self.month == 12:
                    self.day = 1
                    self.month = 1
                    self.year+=1
                else:
                    self.day = 1
                    self.month+=1
            else:
                self.day+=1

    def yesterday(self):
        '''it should change the calling object so that it represents one calendar
        day before the date it originally represented. '''
        if self.isLeapYear():
            if self.month == 3:
                if self.day == 1:
                    self.day = 29
                    self.month= 2
                else:
                    self.day -=1
            elif self.day == 1:
                if self.month == 1:
                    self.day = 31
                    self.month = 12
                    self.year -=1
                else:
                    self.month -=1
                    self.day = DAYS_IN_MONTH[self.month]
            else:
                self.day -=1
        elif self.day == 1:
            if self.month == 1:
                self.day = 31
                self.month = 12
                self.year -=1
            else:
                self.month -=1
                self.day = DAYS_IN_MONTH[self.month]
        else:
            self.day -= 1

    def addNDays(self,N):
        '''Does not return anything, changes the calling object so that it represents
        N calendar days after the date it originally represented'''
        for i in range(N):
            print(self)
            self.tomorrow()
        print(self)

    def subNDays(self, N):
        '''Does not return anything, changes the calling object so that it represents
        N calendar days after the date it originally represented'''
        for i in range(N):
            print(self)
            self.yesterday()
        print(self)

    def isBefore(self, d2):
        '''Returns True if the calling object is a calendar date before the input named d2.
        If self and d2 represent the same day, or is after, method should return False'''
        if self.year < d2.year:
            return True
        if self.month < d2.month and self.year == d2.year:
            return True
        if self.day < d2.day and self.month == d2.month and self.year == d2.year:
            return True
        return False
    
    def isAfter(self, d2):
        '''Returns True if the calling object is a calendar date after the input named d2.
        If self and d2 represent the same day, or is before, method should return False'''
        if self.year > d2.year:
            return True
        if self.month > d2.month and self.year == d2.year:
            return True
        if self.day > d2.day and self.month == d2.month and self.year == d2.year:
            return True
        return False
    
    def diff(self, d2):
        '''Returns an integer representing the number of days between self and d2'''
        n = 0
        selfcopy = self.copy()
        if self.isBefore(d2):
            while selfcopy.isBefore(d2):
                selfcopy.tomorrow()
                n -= 1
        elif self.isAfter(d2):
            while selfcopy.isAfter(d2):
                selfcopy.yesterday()
                n +=1
        return n
    
    def dow(self):
        '''Returns a string that indicates the day of the week of the object that calls it.'''
        md = Date(11,9,2011)
        n = self.diff(md) % 7
        return week[n]
            

            
        

    
