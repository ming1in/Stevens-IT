'''
Ming Lin
I pledge my honor that i have abided by the stevens honor system
'''

DB_file = 'musicrecplus.txt'

def main():
    """Main function of the file. First loads the users from the musicrecplus.txt file and then prompts
    the user for his/her name. Then, if the user is new, asks for their preferences"""
    db_Dict = userDict(DB_file)
    username = input("Enter your name (put a $ symbol after your name if you wish your preferences to remain private):")
    if username not in db_Dict:
        enter_preferences(username, db_Dict, DB_file)
    menu(username, db_Dict)

def userDict(file_name):
    '''Takes in as input a database with users and their preferences and returns a dictionary with
    the users as keys and their preferences as values'''
    try:
        file = open(DB_file, 'r')
    except:
        file = open(DB_file, 'w')
        db_Dict = {}
        file.close
        return db_Dict
    db_Dict = {}   
    for line in file:
        username, artists = line.strip().split(':')
        artists = artists.split(',')
        db_Dict[username] = artists
    file.close()
    return db_Dict

def enter_preferences(username, db_Dict, file_name):
    """Gets preferences from the user and then saves them. Will rewrite any preferences in the database for the user"""
    preferences = []
    new_pref = input("Enter an artist that you like (Enter to finish):")
    while new_pref != '':
        preferences.append(new_pref)
        new_pref = input("Enter an artist that you like (Enter to finish):")
    preferences.sort()
    save(username, db_Dict, file_name, preferences)
    
def save(username, db_Dict, file_name, prefs):
    """Writes information put into to program to musicrecplus.txt file"""
    db_Dict[username] = prefs
    file = open(file_name, 'w')
    new_user_list = []
    for username in db_Dict:
        new_user_list.append(username)
        new_user_list.sort()
    for user in new_user_list:
        new_line = str(user) + ':' + ','.join(db_Dict[user]) + '\n'
        file.write(new_line)
    file.close
    
def menu(username, db_Dict):
    """Function for the menu of the program. Prints the menu and then proceeds based on the users choice"""
    while True:
        print('Enter a letter to choose an option:' + '\n' +
            'e - Enter preferences' + '\n' + 
            'r - Get recommendations' + '\n' +
            'p - Show most popular artists' + '\n' +
            'h - How popular is the most popular' +'\n' +
            'm - Which user has the most likes' + '\n' + 
            'q - Save and quit')
        choice = input()
        if choice == 'e':
            enter_preferences(username, db_Dict, DB_file)
        elif choice == 'r':
            recs = get_recs(username, db_Dict)
            print_recs(recs, username)
            prefs = db_Dict[username]
            save(username, db_Dict, DB_file, prefs)
        elif choice == 'p':
            best_artists(db_Dict)
        elif choice == 'h':
            liked_num(db_Dict)
        elif choice == 'm':
            most_likes(db_Dict)
        elif choice == 'q':
            try:
                save(username, db_Dict, DB_file, db_Dict[username])
                break
            except:
                break  

def num_matches(list1, list2):
    """Returns the number of elements that the two lists have in common"""
    list1.sort()
    list2.sort()
    matches = i = j = 0
    while i < len(list1) and j < len(list2):
        if list1[i] == list2[j]:
            matches += 1
            i += 1
            j += 1
        elif list1[i] < list2[j]:
            i += 1
        else:
            j += 1
    return matches

def drop_matches(list1, list2):
    """Returns a new list that contains only the elements in list 2
    that are not in list 1"""
    list1.sort()
    list2.sort()
    i = j = 0
    result = []
    while i < len(list1) and j < len(list2):
        if list1[i] == list2[j]:
            i += 1
            j += 1
        elif list1[i] < list2[j]:
            i += 1
        else:
            result.append(list2[j])
            j += 1
    while j < len(list2):
        result.append(list2[j])
        j += 1
    return result

def remove_duplicates(lst):
    """Creates a new list without any duplicates"""
    new_list = []
    for i in lst:
        if i not in new_list:
            new_list.append(i)
    return new_list

def get_recs(username, db_Dict):
    """Returns a list of artist recommendations for the user""" 
    users = db_Dict.keys()
    best_users = []
    best_score = 0
    for user in users:
        if user[-1] == '$':
            continue
        if db_Dict[user] == ['']:
            continue
        if db_Dict[user] != db_Dict[username]:
            current_prefs = db_Dict[user]
            main_prefs = db_Dict[username]
            matches = num_matches(current_prefs, main_prefs)
            if matches > best_score:
                best_score = matches
                best_users = [user]
            elif matches == best_score:
                best_users.append(user)
    new_list = []
    for user in best_users:
        new_list = new_list + drop_matches(db_Dict[username], db_Dict[user])
    rec_list = remove_duplicates(new_list)
    rec_list.sort()
    return rec_list

def print_recs(recs, username):
    """Prints each artist from the list of recommendations"""
    
    if len(recs) == 0:
        print('No recommendations available at this time.')
    else:
        for artist in recs:
            print(artist)
            
def count_occurences(artist, lst):
    """Counts how many times an artist occurs in a list"""
    
    count = 0
    for artist_name in lst:
        if artist == artist_name:
            count += 1
    return count
 
def best_artists(db_Dict):
    """Returns the artist that is liked by the most users. If there is a tie, prints all artists
    with the most likes"""
    
    all_artists = []
    users = db_Dict.keys()
    for user in users:
        if user[-1] == '$':
            continue
        if db_Dict[user] == ['']:
            continue
        all_artists = all_artists + db_Dict[user]
    all_artists.sort()
    top_likes = 0
    top_artist = []
    for artist in all_artists:
        likes = count_occurences(artist, all_artists) 
        if likes > top_likes:
            top_likes = likes
            top_artist = [artist]
        elif likes == top_likes:
            top_artist.append(artist)
    top_artist.sort()
    best_artist = remove_duplicates(top_artist)
    if len(best_artist) == 0:
        print('Sorry, no artists found.')
    else: 
        for artist in best_artist:
            print(artist)
                
def liked_num(db_Dict):
    """Prints the number of likes the most popular artist received"""
    all_artists = []
    users = db_Dict.keys()
    for user in users:
        if user[-1] == '$':
            continue
        if db_Dict[user] == ['']:
            continue
        all_artists = all_artists + db_Dict[user]
    all_artists.sort()
    top_likes = 0
    for artist in all_artists:
        likes = count_occurences(artist, all_artists) 
        if likes > top_likes:
            top_likes = likes
    if top_likes == 0:
        print('Sorry, no artists found.')
    else: 
        print(top_likes)

def most_likes(db_Dict):
    """Prints the name(s) of the user(s) who likes the most artists"""
    users = db_Dict.keys()
    top_prefs = 0
    best_users = []
    for user in users:
        if user[-1] == '$':
            continue
        if db_Dict[user] == ['']:
            continue
        if len(db_Dict[user]) > top_prefs:
            top_prefs = len(db_Dict[user])
            best_users = [user]
        elif len(db_Dict[user]) == top_prefs:
            best_users.append(user)
    most_likes_users = remove_duplicates(best_users)
    if top_prefs == 0 or len(most_likes_users) == 0:
        print('Sorry, no user found.')
    else:
        for user in most_likes_users:
            print(user)
    
if __name__ == '__main__':
    main()
           
        
