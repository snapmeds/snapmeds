'''
Created on Feb 11, 2013

@author: bheidkamp3
'''
import unittest
from parser.splParser import *

class TestSPL(unittest.TestCase):
    
    def testName(self):
        parser = SPLParser('test.xml')
        actual = parser.drugName()
        expected = 'Atenolol and Chlorthalidone'
        self.assertEqual(actual, expected)
        parser = SPLParser('test2.xml')
        actual = parser.drugName()
        expected = 'Divalproex sodium'
        self.assertEqual(actual, expected)
        parser = SPLParser('test3.xml')
        actual = parser.drugName()
        expected = 'Citranatal Harmony 2.1'
        self.assertEqual(actual, expected)
    def testID(self):
        parser = SPLParser('test.xml')
        actual = parser.setID()
        expected = '1c3c2cbf-e307-4327-a044-69451aef66ae'
        self.assertEqual(actual, expected)
        parser = SPLParser('test2.xml')
        actual = parser.setID()
        expected = '0a67fd2f-7fe8-4573-9be3-69ebe65a3ffe'
        self.assertEqual(actual, expected)
        parser = SPLParser('test3.xml')
        actual = parser.setID()
        expected = '00ecf076-d840-bee7-38d1-48bae1066bff'
        self.assertEqual(actual, expected)
    def testGenerics(self):
        parser = SPLParser('test.xml')
        generics = parser.genericNames()
        actual = generics[0].text
        expected = 'Atenolol and Chlorthalidone'
        self.assertEqual(actual, expected)
        self.assertEqual(len(generics),1)
        parser = SPLParser('test2.xml')
        generics = parser.genericNames()
        actual = generics[0].text
        expected = 'Divalproex sodium'
        self.assertEqual(actual, expected)
        self.assertEqual(len(generics),1)
        parser = SPLParser('test3.xml')
        generics = parser.genericNames()
        actual = generics[0].text
        expected = 'Calcium citrate, Iron pentacarbonyl, Cholecalciferol, .Alpha.-Tocopherol, Dl-, Pyridoxine hydrochloride, Folic Acid, Docusate Sodium, and Doconexent'
        self.assertEqual(actual, expected)
        self.assertEqual(len(generics),1)
    def testUrl(self):
        parser = SPLParser('test.xml')
        actual = parser.url()
        expected = 'http://dailymed.nlm.nih.gov/dailymed/lookup.cfm?setid=1c3c2cbf-e307-4327-a044-69451aef66ae'
        self.assertEqual(actual, expected)
        parser = SPLParser('test2.xml')
        actual = parser.url()
        expected = 'http://dailymed.nlm.nih.gov/dailymed/lookup.cfm?setid=0a67fd2f-7fe8-4573-9be3-69ebe65a3ffe'
        self.assertEqual(actual, expected)
        parser = SPLParser('test3.xml')
        actual = parser.url()
        expected = 'http://dailymed.nlm.nih.gov/dailymed/lookup.cfm?setid=00ecf076-d840-bee7-38d1-48bae1066bff'
        self.assertEqual(actual, expected)
        