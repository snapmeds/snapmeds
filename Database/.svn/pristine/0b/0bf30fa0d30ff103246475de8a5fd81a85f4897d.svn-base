# -*- coding: utf-8 -*-

import urllib2, urllib
import drug
def write_to_database(drug):
    mydata = get_array_of_tuples_from_drug(drug)
    mydata = mydata + [("username","YOURUSERNAMEHERE"),("password","YOURPASSWORDHERE")]
    mydata=urllib.urlencode(mydata)
    print mydata
    path='YOURDATABASEURL'
    req=urllib2.Request(path, mydata)
    req.add_header("Content-type", "application/x-www-form-urlencoded")
    page=urllib2.urlopen(req).read()
    return convert(page)

def convert(input):
    if isinstance(input, dict):
        return {convert(key): convert(value) for key, value in input.iteritems()}
    elif isinstance(input, list):
        return [convert(element) for element in input]
    elif isinstance(input, unicode):
        return input.encode('utf-8')
    else:
        return input
        
def get_array_of_tuples_from_drug(drug):
    ret = []
    ret.append( ('setid',drug.setid))
    ret.append( ('ndc',drug.ndc))
    ret.append( ('warnings',drug.warnings))
    ret.append( ('genericnames',drug.genericnames))
    ret.append( ('uses',drug.uses))
    ret.append( ('adversereactions',drug.adversereactions))
    ret.append( ('boxwarnings',drug.boxwarnings))
    ret.append( ('precautions',drug.precautions))
    ret.append( ('conflictingconditions',drug.conflictingconditions))
    ret.append( ('name', drug.name))
    ret.append( ('medicationguide', drug.medicationguide))
    return ret

    