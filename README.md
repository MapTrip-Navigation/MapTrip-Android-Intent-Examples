# MapTrip Android Intent interface #

The MapTrip intent interface for Android is available since MapTrip 4.1 and offers extended possibilities when starting [MapTrip](https://maptrip.de). The purpose of this project is to provide examples how to start different types of route guidance in MapTrip from your own application.
This also means that more control options are now also available in WebApps. Especially routes (like FollowMe or reference routes) can now be started easily.

## Requirements ##

In order to benefit from this application you will need to have a working version of MapTrip on your device. In case you do not have MapTrip yet, do not hesitate to [contact us](https://www.infoware.de/en/contact-us/).

## Calling the MapTrip Intent from JAVA ##
```
Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("maptrip://navigate?latitude=50.738912&longitude=7.106772"));
startActivity(intent);
```

## Start of a navigation with a WGS84 coordinate ##
*maptrip://navigate?latitude=7.123423&longitude=50.1279*
## Start of a navigation with an address ##
*maptrip://navigate?country=DEU&city=Bonn&zip=53175&street=Riemenschneiderstr&houseno=11*

The abbreviation of street names (e.g. Riemenschneiderstr) is possible. The dot at the end of the word must not be set. It is possible to omit the postal code. However, it is not guaranteed which coordinates the system will use if the address exists multiple times in the destination. Typically, this applies to streets such as Hauptstraße or Bahnhofstraße in larger cities in Germany. The country code is given according to *ISO 31-66-1 Alpha-3*, i.e. with three capital letters.
## Start of a simulation ##
Each navigation start can also be simulated. Here "simulate" must be used instead of "navigate", e.g.:

*maptrip://simulate?latitude=7.123423&longitude=50.1279*
## Start of a route ##
*maptrip://navigate?route=<path_url_encoded>&type=*

possible types: refroute, followme, maptrip
### Parameter route: ###
Here the absolute path (URL encoded) must be passed to the file.
### Parameter type: ###
Type of the route file. The following parameters are possible:
-	refroute    -  for a reference route
-	followme   -  for a followme route
-	maptrip   -   for a MapTrip tour file (XML)

Please find example route files within the *example_data* folder of this project.
## Start of a multi-stopp navigation (up to 10 intermediate destinations) ##
*maptrip://navigate?posx0=50.2374&posy0=7.123423&posx1=50.2374&posy1=7.123423..&posx9=50.2374&posy9=7.123423*
