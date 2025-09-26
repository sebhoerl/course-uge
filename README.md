# Large-scale models and simulation methods

Course at Université Gustave Eiffel, Autumn 2025

## Useful links

- [Link to the course slides](https://slides.com/sebastianhorl/large-scale-models-and-simulation-methods-2025)
- [Link to the course project description](https://docs.google.com/document/d/13DhsXpPvwkl3DJ44_27ggcfgUR4btXn6wOUFkeoF6N4/edit?usp=sharing)

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

## Python envrionment

All require dependencies for the project are packaged in a `mamba` / `conda` environment that can be found in `environment.yml`. You can install it, for instance, using the following command:

```bash
mamba env create -f environment.yml -n course2024
```
