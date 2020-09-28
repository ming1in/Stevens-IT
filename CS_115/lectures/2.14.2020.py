def LCS(s1,s2):
    if s1 == " " or s2 == " ":
        return 0
    elif s1[0] == s2[0]:
        return 1 + LCS(s1[1:], s2[1:])
    else:
        return max(LCS(s1[1:], s2), LCS(s1, s2[1:]))

