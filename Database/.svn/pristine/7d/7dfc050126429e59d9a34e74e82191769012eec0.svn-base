# -*- coding: utf-8 -*-
'''
Created on Feb 11, 2013

@author: bheidkamp3

Used to parse XML file for drug data
'''
from parser.splParser import *
import os
import drug
import databaseconnection
path = 'prescription/'
listing = os.listdir(path)
for infile in listing:
    print 'current file is:' + infile
    parser = SPLParser(path+infile)
    
    #print 'SET ID'
    setIDString = parser.setID()
    #print setIDString
    #print 'URL'
    #print parser.url()
    #print 'DRUG NAME'
    nameString = parser.drugName()
    #print name
    
    #print 'Generic Names'
    generics = parser.genericNames()
    genericsString=""
    for name in generics:
        genericsString+=name.text+"\n"
        #print name.text
    print genericsString
    #print 'USES'
    usesString = parser.uses()
    #print usesString
        
    #print 'DESCRIPTION'
    descriptionString = parser.description()
    #print descriptionString
        
    #print 'CONTRAINDICTIONS'
    contraindictionsString = parser.contraindictions()
    #print contraindictionsString
        
    #print 'WARNINGS'
    warningsString = parser.warnings()
    #print  warningsString
        
    #print 'PRECAUTIONS'
    precautionsString = parser.precautions()
    #print  precautionsString
        
    #print 'OVERDOSE INFORMATION'
    overdoseString = parser.overdose()
    #print overdoseString
        
    #print 'BOXED WARNING'
    boxedWarningString = parser.boxedWarning()
    #print boxedWarningString
        
    #print 'ADVERSE REACTIONS'
    adverseReactionsString = parser.adverseReactions()
    #print adverseReactionsString
        
    #print 'MEDICATION GUIDE'
    guideString = parser.medicationGuide()
    #print guideString
    
    currDrug = drug.Drug(nameString, setIDString, "", warningsString, genericsString,\
                         usesString, adverseReactionsString, boxedWarningString,\
                         precautionsString ,contraindictionsString, guideString)
    #print currDrug
    databaseconnection.write_to_database(currDrug)
