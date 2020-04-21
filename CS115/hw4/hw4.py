#Ming Lin
#I pledge my honor that I have abided by the Stevens honor system

def pascal_row(n): # returns the values in the nth row of pascals triangle
    def pascal_helper(lst):
        if len(lst) <= 1:
            return []
        return [lst[0] + lst[1]] + pascal_helper(lst[1:])
    if n == 0:
        return [1]
    return [1] + pascal_helper(pascal_row(n - 1)) + [1]

def pascal_triangle(n): # returns a list of lists containing the values of the all the rows up to and including row n
    if n == 0:
        return [[1]]
    return pascal_triangle(n - 1) + [pascal_row(n)]