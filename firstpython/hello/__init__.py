people={'Alice'
        :{'phone':'2341','addr':'Foo drive 23'},
        'Beth':{'phone':'9102','addr':'Bar Street 42'}
        }

labels={'phone':'phone number','addr':'address'}


a=raw_input()
print "Phone number (p) or address (a)? p"

request='p'


name='Alice'

if request=='p':key='phone'
if request=='a':key='addr'

if name in people : print "%s's %s is %s." % \
(name,labels[key],people[name][key])