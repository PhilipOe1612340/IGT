# IGT
Übungsaufgaben für die Vorlesung Integrationstechnologien WS18/19 der Hochschule Mannheim

https://docs.jboss.org/hibernate/ogm/4.0/reference/en-US/html_single/#ogm-neo4j

## Api Doku

### Endpoints
  localhost:\<port\>/api
    
| Database 	| Port 	| Type            	|
|----------	|------	|-----------------	|
| MySQL    	| 6001 	| SQL Database    	|
| MongoDB  	| 6002 	| Document Store  	|
| Ehcache  	| 6003 	| Cache           	|
| Neo4j    	| 6004 	| Graph Database  	|
| Redis    	| 6005 	| Key-value Store 	|
  
**Example:** http://localhost:6001/api for the MongoDB instance


### List all customers
*GET* \<endpoint\>/customers

**response:**
```json
  {
    "customers":
    [
      {
        "customerId": 5,
        "name" : "Max Musterman",
        "phones": ["00491234567", "0038839284"]
      },
      {
        "customerId": 6,
        "name" : "Max Musterman",
        "phones": ["00491234567", "0038839284"]
      },
      
      ...
    ]
  }
```

### Get one customer
*GET* \<endpoint\>/customer/\<id\>

**response:**
```json
      {
        "customerId": 5,
        "name" : "Max Musterman",
        "phones": ["00491234567", "0038839284"]
      }
```

### Update a customer
*POST* \<endpoint\>/customer/\<id\>
If a field is not specified than it wont get updated.

**request:** the fields to update
```json
     {
        "name" : "Max Musterman",
        "phones": ["00491234567", "0038839284"]
      }
```


**response:** the changed customer with its id
```json
      {
        "customerId": 5,
        "name" : "Max Musterman",
        "phones": ["00491234567", "0038839284"]
      }
```

### Create a new customer
*POST* \<endpoint\>/customer/new

**request:** the fields to update
```json
      {
        "name" : "Max Musterman",
        "phones": ["00491234567", "0038839284"]
      }
```


**response:** the created customer with its id
```json
     {
        "customerId": 5,
        "name" : "Max Musterman",
        "phones": ["00491234567", "0038839284"]
      }
```

### Delete a customer
*DELETE* \<endpoint\>/customer/\<id\>

**response:**
```json
      {
        "customerId": 5,
        "name" : "Max Musterman",
        "phones": ["00491234567", "0038839284"]
      }
```

### Create an airport
*POST* \<endpoint\>/airport/new

**request:** the fields to update
```json
      {
        "name": "Some Airport",
        "short": "SAP"
      }
```


**response:** the created airport with its id
```json
     {
        "airportId": 5,
        "name": "Some Airport",
        "short": "SAP"
      }
```

### Get all airports
*GET* \<endpoint\>/airport/all

**response:** 
```json
    { 
       "airports": [
           {
              "airportId": 5,
              "name": "Some Airport",
              "short": "SAP"
            },
             {
              "airportId": 6,
              "name": "Another Airport",
              "short": "AAP"
            },
            ...
        ]
    }
```
### Get all Flights in a date range
*POST* \<endpoint\>/flight/filter

Filter all Flights that go from start to finish on the specified day (or in a range sooner or later).

**request:**
```json
     {
        "date": "YYYY-MM-DD",
        "duration": 500,
        "range": "1",
        "startAirport": 5,
        "arrivalAirport": 6,
     }
```


**response:** the created flight with its id
```json
     [
       {
         "flightId": 5,
         "miles": 500,
         "date": "YYYY-MM-DD",
         "duration": 500,
         "airportsList": [5, 6, 8]
       },
       ...
    ]
```


### Create a Flight
*POST* \<endpoint\>/flight/new

**request:**
```json
     {
        "miles": 500,
        "date": "YYYY-MM-DD",
        "duration": 500,
        "airportsList": [5, 6, 8]
      }
```


**response:** the created flight with its id
```json
     {
        "flightId": 5,
        "miles": 500,
        "date": "YYYY-MM-DD",
        "duration": 500,
        "airportsList": [5, 6, 8]
      }
```

### Book a Flight
*POST* \<endpoint\>/booking/new

List all airports in the correct sequence.

**request:**
```json
      {
        "customerId": 5,
        "flightId": 6
      }
```


**response:** the booking id
```json
     {
        "bookingId": 5
      }
```

### Cancel a Booking
*DELETE* \<endpoint\>/booking/\<id\>

List all airports in the correct sequence.

**response:**
```json
      {
        "bookingId": 5,
        "customerId": 5,
        "flightId": 6
      }
```


### Delete a Flight
*DELETE* \<endpoint\>/flight/\<id\>

**response:**
```json
      {
        "customerId": 5,
        "name" : "Max Musterman",
        "phones": ["00491234567", "0038839284"]
      }
```


### Delete an Airport
*DELETE* \<endpoint\>/airport/\<id\>

**response:**
```json
      {
        "customerId": 5,
        "name" : "Max Musterman",
        "phones": ["00491234567", "0038839284"]
      }
```

### clear all

*GET* \<endpoint\>/dropdb
dont do this




Aufgabe 1:
1. Ist das Flugbuchung Szenario via BPMN für Sie ausreichend? (Siehe Anhang)
folgende Fragen hätte ich zum Modell: 
- welche Reisedaten genau werden von wem angegeben?
- welche Flüge werden von wem ausgewählt (automatisch von einem System vorgeschlagen, manuell vom Benutzer)?
- wo sind die Flüge gespeichert? 
- wann erfolgt die Unterscheidung ob Direktflug oder mit 1…n Zwischenlandungen?
- wer gibt die persönlichen Infos an (OK, wahrscheinlich der Anwender) aber wo werden die Daten gespeichert?
- wer erstellt die Bestätigung?
- was für Daten sind in der Bestätigung enthalten?
- was passiert, wenn ein gebuchter Flug ausfällt?
- kann ein gebuchter Flug storniert werden, wie?

Will sagen: bitte das Modell noch ein wenig genauer ausarbeiten :-)

Aufgabe 2:

2. Um den Impedance Mismatch zu überbrücken haben wir das Datenmodell in der 3. Normalform implementiert. Ist dies der richtige Ansatz?
auf jeden Fall, genau so.

3. Reichen die entsprechenden Fehlermeldungen um zu beweisen das geschachtelte Transaktionen in Hibernate nicht möglich sind?
wunderbar gelöst, super.

1. Benötigen wir mehrere Datenmodelle oder ist das sql Datenmodell auch für die NoSQL Datenbanken gültig?
ich würde gerne sehen, in welcher Form die Daten bei den jeweiligen NoSQL-DBs gespeichert werden. Da gibt es noch
keine Standardisierten Modelle, da dürft ihr Eurer Kreativität freien Lauf lassen, was die Darstellung angeht.

2. Soll das Interface / Modell eine Flugbuchung simulieren oder lediglich die CRUD Methoden implementieren (sprich. Einen customer anlegen, auslesen, anpassen, löschen)
das Modell soll eine komplette Flugbuchung simulieren (vom Anlegen der Kundendaten (CRUD) bis zum Versenden der Bestätigung).
Wenn der Benutzer seinen Reisezeitraum eingegeben hat, soll das System alle möglichen Flüge vorschlagen, damit er den für sich passenden
raussuchen kann. Dann gibt er seine pers. Infos ein, bezahlt und bekommt eine Bestätigung. Soweit konnte ich das aus Eurem Prozess entnehmen. Im Prinzip modelliert ihr einmal Euren Prozess, wie er standardmäßig ablaufen soll (also ohne Ausnahmen und Schleifen, etc.).

 3. Wie genau soll der Client implementiert werden? (Main Methode? Command line? Grafisch?)
Main-Methode und CommandLine reicht mir völlig, ich lasse mich aber auch gerne überraschen, z.B. grafisch mit SpringBoot oder 
mit Groovy on Grails, oder so was.

4. Haben wir richtig verstanden, dass die Tabelle auf Seite 143 in Ihrem Skript um die 4 NoSQL Datenbanken erweitert werden soll?
jep, genau so.
