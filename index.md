## Idea

Implementation of weighted Levensthein and Damerau-Levensthein distances integrated into the Solr platform. The exemplefied weights setup is based on misspelling tendencies in the Danish written language. It is possible to setup insertion, deletion, substitution or transposition rules in whatever language is preferred by changing the "hardcoded" values in the CustomWeightSetup class. 

In relation to information about the distance metrics I refer to [Levenshtein distance](https://en.wikipedia.org/wiki/Levenshtein_distance) and [Damerau-Levenshtein distance](https://en.wikipedia.org/wiki/Damerau%E2%80%93Levenshtein_distance). 

### Distance metrics
Three versions of the distance metrics have been implemented:

* Levenshtein distance
* Restricted Damerau-Levenshtein distance
* True Damerau-Levenshtein distance

The distance metrics have been used to test spell checker performance in Solr. The True Damerau-Levenshtein distance metrics is in average 3,5 times as slow as the std. Levensthein method and I can therefore not recommend using this method in a spell checker relation. 


### Weights
The weights are based on extensive research in the Danish spelling tendencies but also by analysing log data of the customer the product was developed for. As the user segment show a tendency to mistake some letters for others, there is a specific emphasis on substitution errors in the weights setup. 

### Installation
The following steps should be made in order to use the distance metrics in your Solr application:

* In order to compile the module it is nessesary to add lucene-suggest-VERSIONNO.jar to the build path
  * The jar can be found online or in your current Solr solution in the ..Solr-VERSIONNO\server\solr-webapp\webapp\WEB-INF\lib\ folder
* Export the project as jar file to ..Solr-VERSIONNO\contrib\extraction\lib\
* Change the setup, specificly the "distanceMeasure" parameter, in solrconfig.xml or solrconfig_extra.xml (or where ever your spell checker setup is located)
* Restart Solr (evt. re-build the spell checker index)
