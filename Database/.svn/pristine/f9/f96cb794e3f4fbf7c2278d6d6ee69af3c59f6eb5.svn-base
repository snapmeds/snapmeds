# -*- coding: utf-8 -*-
'''
Created on Feb 11, 2013

@author: bheidkamp3
'''
import xml.etree.ElementTree as ET
import os
import re

class SPLParser:

    def __init__(self, filename):
        self.filename = filename
        self.tree = ET.parse(filename)
        self.root = self.tree.getroot()
    
    def setID(self):
        return self.root.find(".//{urn:hl7-org:v3}setId").attrib['root']
    
    def drugName(self):
        name= self.root.find(".//{urn:hl7-org:v3}manufacturedProduct/{urn:hl7-org:v3}name")
        if name is not None:
            return name.text
        return ""
    
    def genericNames(self):
        return self.root.findall(".//{urn:hl7-org:v3}genericMedicine/{urn:hl7-org:v3}name")
    
    def uses(self):
        uses = self.root.find(".//{urn:hl7-org:v3}code[@code='34067-9']/..")
        if uses is not None:
            usesString = ET.tostring(uses,method="text",encoding="UTF-8")
            usesString = os.linesep.join([s for s in usesString.splitlines() if s.strip()])
            return usesString
        return ""
    
    def description(self):
        description = self.root.find(".//{urn:hl7-org:v3}code[@code='34089-3']/..")
        if description is not None:
            descriptionString = ET.tostring(description,method="text",encoding="UTF-8").strip() 
            descriptionString = os.linesep.join([s for s in descriptionString.splitlines() if s.strip()])
            return descriptionString
        return ""
    
    def contraindictions(self):
        contraindictions = self.root.find(".//{urn:hl7-org:v3}code[@code='34070-3']/..")
        if contraindictions is not None:
            contraindictionsString = ET.tostring(contraindictions,method="text",encoding="UTF-8").rstrip() 
            contraindictionsString = os.linesep.join([s for s in contraindictionsString.splitlines() if s.strip()])
            return contraindictionsString
        return ""
    
    def warnings(self):
        warnings = self.root.find(".//{urn:hl7-org:v3}code[@code='34071-1']/..")
        if warnings is not None:
            warningsString = ET.tostring(warnings,method="text",encoding="UTF-8").rstrip() 
            warningsString = os.linesep.join([s for s in warningsString.splitlines() if s.strip()])
            return warningsString
        return ""
    
    def precautions(self):
        precautions = self.root.find(".//{urn:hl7-org:v3}code[@code='42232-9']/..")
        if precautions is not None:
            precautionsString = ET.tostring(precautions ,method="text",encoding="UTF-8").rstrip() 
            precautionsString = os.linesep.join([s for s in precautionsString.splitlines() if s.strip()])
            return precautionsString
        return ""
    
    def overdose(self):
        overdose = self.root.find(".//{urn:hl7-org:v3}code[@code='34088-5']/..")
        if overdose is not None:
            overdoseString = ET.tostring(overdose ,method="text",encoding="UTF-8").rstrip() 
            overdoseString = os.linesep.join([s for s in overdoseString.splitlines() if s.strip()])
            return overdoseString
        return ""
    
    def boxedWarning(self):
        boxedWarning = self.root.find(".//{{urn:hl7-org:v3}code[@code='34066-1']/..")
        if boxedWarning is not None:
            boxedWarningString =  ET.tostring(boxedWarning,method="text",encoding="UTF-8").rstrip() 
            boxedWarningString = os.linesep.join([s for s in boxedWarningString.splitlines() if s.strip()])
            return boxedWarningString
        return ""
    
    def adverseReactions(self):
        adverseReactions = self.root.find(".//{urn:hl7-org:v3}code[@code='34084-4']/..")
        if adverseReactions is not None:
            adverseReactionsString = ET.tostring(adverseReactions,method="text",encoding="UTF-8").rstrip() 
            adverseReactionsString = os.linesep.join([s for s in adverseReactionsString.splitlines() if s.strip()])
            return adverseReactionsString
        return ""
    
    def medicationGuide(self):
        guide = self.root.find(".//{urn:hl7-org:v3}code[@code='42231-1']/..")
        if guide is not None:
            guideString = ET.tostring(guide,method="text",encoding="UTF-8").rstrip() 
            guideString = os.linesep.join([s for s in guideString.splitlines() if s.strip()])
            return guideString
        return ""
        
    def url(self):
        setID=self.setID()
        return 'http://dailymed.nlm.nih.gov/dailymed/lookup.cfm?setid='+setID
    
    def ndcs(self):
        fileBytes = open(self.filename,"rb").read()
        fileStr = fileBytes.decode('latin1')
        print(fileStr)
        matches = re.findall('\d+-\d+-\d+',fileStr )
        ndcs=[]
        for match in matches:
            if len(match) == 12:
                ndcs.append(match)
        return ndcs
parser = SPLParser('test.xml')
print(parser.ndcs())
        
        
                    