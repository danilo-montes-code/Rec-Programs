#######################################################################################
####################################Counter Object#####################################
#######################################################################################
from collections import Counter
print('Collections Object\n')

l = [1,1,1,1,1,2,2,3,4,4,5,6,7,7,8]
print(Counter(l))
s = 'assssbdfdffebtggafsfssss'
print(Counter(s))
w = 'How many times does each word show up in this sentence word word shoe up up show'
w = w.split()
print(Counter(w))

c = Counter(w)
print(c.most_common(2))

'''
sum(c.values())                 # total of all counts
c.clear()                       # reset all counts
list(c)                         # list unique elements
set(c)                          # convert to a set
dict(c)                         # convert to a regular dictionary
c.items()                       # convert to a list of (elem, cnt) pairs
Counter(dict(list_of_pairs))    # convert from a list of (elem, cnt) pairs
c.most_common()[:-n-1:-1]       # n least common elements
c += Counter()                  # remove zero and negative counts
'''
print('\n---------------------------------------------------------------------------------------------------------------------------')
#######################################################################################
#################################DefaultDict Object####################################
#######################################################################################
from collections import defaultdict
print('DefaultDict Object\n')

d = {'k1':1}
print(d['k1'])
# print(d['k2'])    #KeyError, no 'k2' key

d2 = defaultdict(object)
print(d2['one'])
for item in d2:
    print(item)

d3 = defaultdict(lambda:0)
print(d3['one'])
d3['two'] = 2
print(d3)

print('\n---------------------------------------------------------------------------------------------------------------------------')
#######################################################################################
#################################OrderedDict Object####################################
#######################################################################################
from collections import OrderedDict
print('OrderedDict Object\n')

d4 = {}
d4['a'] = 1
d4['b'] = 2
d4['c'] = 3
d4['d'] = 4
d4['e'] = 5
print(d4)
for k,v in d4.items():
    print(k,v)
