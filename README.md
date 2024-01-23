# Large-scale models and simulation methods

Course at Université Gustave Eiffel, Spring 2024

## Useful links

- [Link to the course slides](https://slides.com/sebastianhorl/lsms24)
- [Link to the course project description](https://docs.google.com/document/d/1sMYVaGqwVbmRKfHDJAn_-pFiAFWPNdxSdWQrvOFN3lo/edit?usp=sharing)

## Data needed

**Detailed census data**

- Go to https://www.insee.fr/fr/statistiques/6544333
- Download "Individus localisés au canton-ou-ville" as CSV

**Aggregated census data**

- Go to https://www.insee.fr/fr/statistiques/6543200
- Download "Population en 2019 - IRIS - France hors Mayotte " as CSV

**IRIS spatial data**

- Go to https://geoservices.ign.fr/contoursiris
- Download the 2021 edition

**Commuting data**

- Go to https://www.insee.fr/fr/statistiques/6456056
- Download as CSV

**Employment data**

- Go to https://open.urssaf.fr/explore/dataset/etablissements-et-effectifs-salaries-au-niveau-commune-x-ape-last/information/
- Then go to "Export" and download as CSV

**OpenStreetMap data**

- Go to http://download.geofabrik.de/europe/france/ile-de-france.html
- Download the data in `.osm.pbf` format

## Tools needed

**Java JDK**

- To run the Java code, you need to have a Java JDK installed on your system. A good choice is the Adpot OpenJDK, at least in version 17: https://adoptopenjdk.net/releases.html

**osmosis**

- *osmosis* is a Java-based tool to extract and convert OpenStreetMap data. It can be downloaded here: https://github.com/openstreetmap/osmosis/releases

**Java IDE**

- While you can run the Java example from the command line, it is easier using a graphical environment. You may work with *Eclipse Java*, *IntelliJ*, *VisualStudio Code* with the respective extensions or any other.