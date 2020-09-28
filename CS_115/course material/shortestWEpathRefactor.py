# shortestWEpathRefactor
# A solution for 'west-to-east' Giigle-maps example showing an
# intermediate version of the program that could serve as stepping
# stone to the complete solution.
# Dave N. 3 Oct 2012, rev Sep 2018, rev Feb 2020
from cs115 import *

Inf = float("inf")

FiveDists = {
    ("A","A"):0, ("A","B"):1, ("A","C"):3, ("A","D"):7 , ("A","E"):Inf,
    ("B","A"):Inf, ("B","B"):0, ("B","C"):42, ("B","D"):6, ("B","E"):27,
    ("C","A"):Inf, ("C","B"):Inf, ("C","C"):0, ("C","D"):2, ("C","E"):13,
    ("D","A"):Inf, ("D","B"):Inf, ("D","C"):Inf, ("D","D"):0, ("D","E"):5,
    ("E","A"):Inf, ("E","B"):Inf, ("E","C"):Inf, ("E","D"):Inf, ("E","E"):0
     }

FiveCities = ["A", "B", "C", "D", "E"]

# PART 1 length of shortest path

def shortestPath(Cities, Dists):
    '''Assume Cities is a non-empty list of cities in West-to-East order,
    and Dists is the table of their distances.
    Return the length of the shortest path from the first in Cities to the
    last city, going via only cities in the list.
    West-to-East order means that if Y comes after X in the list, the distance
    from Y to X is Inf.'''

    if len(Cities) == 1: return 0
    else:
        return min(
                map(lambda nxtCit:
                      Dists[( Cities[0], Cities[nxtCit])]
                      + shortestPath(Cities[nxtCit:], Dists),
                    range(1,len(Cities))
                ))
             
def testFirstVsn():     
    print('Testing first version; should be True True True DontCare')
    print(shortestPath(FiveCities, FiveDists) == 10)
    print(shortestPath(["C", "D", "E"], FiveDists) == 7)
    print( shortestPath(["E"], FiveDists) == 0)
    print(shortestPath(["B", "A", "E"], FiveDists)) # violates W-to-E list order

def shortestPathTrace(Cities, Dists):
    '''Same as shortestPath, but printing recursion trace,
    using helper with extra argument for recursion depth.'''
    def spTrace(Cities, Dists, depth):
        '''Same as shortestPath but tracing; assume recursion depth n.'''
        print ((depth*'  ') + "spTrace(",Cities,")")
        if len(Cities) == 1: return 0
        else:
            return min(
                    map(lambda nxtCit:
                          Dists[( Cities[0], Cities[nxtCit])] 
                          + spTrace(Cities[nxtCit:], Dists, depth+1),
                        range(1,len(Cities))
                    ))
    print("Tracing...")
    spTrace(Cities, Dists, 0)
               
def testTraceVsn():     
    print(shortestPathTrace(FiveCities, FiveDists) == 10)

               
# PART 1 - alternate version, refactored into smaller parts
# as a step towards returning the actual path. 

def shortestPathAlt(Cities, Dists):
    '''Same as findShortestPath, just coded differently'''
    
    def oneDist(citIdx):
        '''The distance with first step to Cities[citIdx];
        assume 0<=citIdx<len(Cities)'''
        theRest = shortestPathAlt(Cities[citIdx:], Dists)
        dist = Dists[(Cities[0], Cities[citIdx])] + theRest
        return dist

    if len(Cities) == 1: return 0
    else:
        distances = map(oneDist, range(1, len(Cities)))
        return min(distances)

# PART 2 finding the actual shortest path

def findShortestPath(Cities, Dists):
    '''Assume Cities is a non-empty list of cities in West-to-East order.  
    Return tuple (n, L) where L is a shortest path from the first
    to the last city, going via only cities in the list; and n is len(L).'''
    
    def onePath(citIdx):
        '''The path and distance with first step to Cities[citIdx];
        assume 0<=citIdx<len(Cities)'''
        theRest = findShortestPath(Cities[citIdx:], Dists)
        path = [Cities[0]] + theRest[1]
        dist = Dists[(Cities[0], Cities[citIdx])] + theRest[0]
        return (dist, path)

    def minOf2(path1, path2):
        '''Shorter of two (dist,path) pairs'''
        if path1[0] <= path2[0]: return path1
        else: return path2
        
    if len(Cities) == 1: return [0, [Cities[0]]]
    else:
        paths = map(onePath, range(1, len(Cities)))
        return reduce(minOf2, paths)

def testSecondVsn():
    print("Testing findShortestPath")
    print(findShortestPath(FiveCities, FiveDists))
    print(findShortestPath(["C", "D", "E"], FiveDists))
    print(findShortestPath(["E"], FiveDists))         
 
