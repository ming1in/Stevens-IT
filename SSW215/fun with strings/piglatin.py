'''
Ming Lin
I pledge my honor that i have abided by the stevens honor system

vowel: A E I O U
'''

vowel = "aeiou"
tail = "ay"

def pig_latin(word):
    if word[0] in vowel:
        return word + tail
    else:
        new_word = word[1:] + word[0]
        return pig_latin(new_word)

sentence = "Software engineers shall ensure that their products meet the highest professional standards possible They shall maintain integrity and independence in their professional judgment"

print(' '.join(pig_latin(word) for word in sentence.split()))

