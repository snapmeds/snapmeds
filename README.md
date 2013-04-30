snapmeds
========

Snap Meds Mobile Application Repository

SnapMeds Team:
Joel Dodge, Bobby Heidkamp, Caleb Johnson, Onur Karaman, Matt Omori, Zach Wand, 
Tom Zhang, Terence Zhao


Description:
“Snap Meds” is an android application that aims to reduce the anxiety and confusion often associated with taking prescription medications.  This confusion can be associated with dosage amounts, frequency of dosages, medication names, medication intents, and side effects.  To alleviate this confusion, “Snap Meds” utilizes a manual drug search option combined with an automatic barcode search option. Drug results can be stored as a prescription in a digital medicine cabinet view for later access. For each stored prescription, users can add and save a related picture and note.  “Snap Meds” also allows users to create dosage calendar entries manually or from the dosage information on  a physical prescription. These entries will remind users to take their drugs at the right time and in the correct quantity. Overall “Snap Meds” aims to simplify the prescription drug process.

Installation:
SnapMeds is the main project and can be run through the eclipse android menu. Eclipse with the android developer tools needs to be installed. 
SnapMeds.androidTest contains all of the android tests for our code and can be run as an android junit test through eclipse.
SnapMeds.test contains all of the java test code. These tests cover the utilities class and are the majority of our tests. We extensively tested the non android utilities because they act as our model.  This project can be run as a junit test through eclipse.

The Dabase folder contains all the python used to populate our database. 
The WebPages folder contains all the php used on our database for the restful API.
All database usernames and passwords are removed from these files and they are designed primarily for setting up your own database

License
========

Copyright 2013 Snap Meds Team


Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
