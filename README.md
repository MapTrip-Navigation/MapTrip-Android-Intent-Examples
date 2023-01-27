# MapTrip Android Intent Examples #

The MapTrip intent interface for Android is available since MapTrip 4.1 and offers extended possibilities when starting [MapTrip](https://maptrip.de). The purpose of this project is to provide examples how to start different types of route guidance in MapTrip from your own application.
This also means that more control options are also available in WebApps. Especially routes (like [FollowMe](https://www.maptrip.de/en/gps-app-for-waste-management/) or Reference routes) can be started easily.

<img src="readme_media/main.png" width="250">

## Requirements ##

In order to benefit from this sample application you will need to have a working version of MapTrip on your device. In case you do not have MapTrip yet, do not hesitate to [contact us](https://www.maptrip.de/en/contact-us/).

## Calling the MapTrip Intent from JAVA ##
```
Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("maptrip://navigate?latitude=50.738912&longitude=7.106772"));
startActivity(intent);
```

## Start of a navigation with a WGS84 coordinate ##
*maptrip://navigate?latitude=50.738912&longitude=7.106772*
## Start of a navigation with an address ##
*maptrip://navigate?country=DEU&city=Bonn&zip=53175&street=Riemenschneiderstr&houseno=11*

The abbreviation of street names (e.g. Riemenschneiderstr) is possible. The dot at the end of the word must not be set. It is possible to omit the postal code. However, it is not guaranteed which coordinates the system will use if the address exists multiple times in the area. Typically, this applies to streets such as *Hauptstraße* or *Bahnhofstraße* in larger cities in Germany. The country code is given according to *ISO 31-66-1 Alpha-3*, i.e. with three capital letters.
## Start of a simulation ##
Each navigation can also be simulated by using *simulate* instead of *navigate*, e.g.:

*maptrip://simulate?latitude=50.738912&longitude=7.106772*
## Start of a route ##
*maptrip://navigate?route=<path_url_encoded>&type=*

possible types: refroute, followme, maptrip

## Start of a route file on android 11+ in maptrip 5.5 ##

Since the permission changes in android 11+, a route file has to be transported somewhere inside the maptrip installation folder, 
because you cant access files outside of the application. 

1.
In [AndroidManifest.xml](app/src/main/AndroidManifest.xml) add the following inside the application tags:

<application..

<provider
    android:name="androidx.core.content.FileProvider"
    android:authorities="de.infoware.callingapptest.fileprovider"
    android:exported="false"
    android:grantUriPermissions="true">
    <meta-data
        android:name="android.support.FILE_PROVIDER_PATHS"
        android:resource="@xml/file_paths" />
</provider>

..application>

2.
Create a file inside the res/xml folder named [file_paths.xml](app/src/main/res/xml/file_paths.xml) and add the following
and replace the package name with your own:

<?xml version="1.0" encoding="utf-8"?>
<paths xmlns:android="http://schemas.android.com/apk/res/android">
    <external-path name="file" path="Android/data/de.infoware.callingapptest/files/" />
</paths>

3.
Add the following dependencies to build.gradle:

implementation 'androidx.core:core:1.3.2'
implementation 'androidx.activity:activity:1.2.0-alpha06'

4.
Next we need to implement a FileProvider to create a URI with, 
which takes 3 arguments the context, authority and a file of the type File, again use the
appropriate package name.

in Java that could look a little something like this:
File file = new File(path + "/followme_bonn.nmea");
Uri fileToUri = FileProvider.getUriForFile(this, "de.infoware.callingapptest.fileprovider", file);

5.
Then we need to create a IntentBuilder out of the URI we just made like so:

Intent shareIntent = ShareCompat.IntentBuilder.from(this)
.setStream(fileToUri)
.getIntent();

this Intent can now be used for multiple purposes.

shareIntent.setData(fileToUri);
shareIntent.putExtra(Intent.EXTRA_TEXT, "maptrip://navigate?route=followme_bonn.nmea&type=followme");
shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
shareIntent.setClassName("de.infoware.maptrip.navi.license", "de.infoware.maptrip.StartActivity");

.setData: Set the data to be used with the Intent.
.putExtraAdding: extra text which maptrip in this case can recognize.
.addFlags: Grant permissions to the URI without the need of requesting user permissions.
.setClassName: Set the name of the class that receives the file.

The implementation from this application is now done and a button with a clicklistener can be made to trigger
this intent like so:

button = findViewById(R.id.button_share_intent_navi_followme);
button.setOnClickListener(new Button.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(shareIntent);
    }
});





### Parameter route: ###
The absolute path (URL encoded) must be passed to the file.
### Parameter type: ###
Type of the route file. The following parameters are possible:
-	refroute    -  for a Reference route
-	followme   -  for a FollowMe route
-	maptrip   -   for a MapTrip tour file (XML)

Please find example route files within the *intent_example_data* folder of this project.
## Start of a multi-stopp navigation (up to 10 intermediate destinations) ##
*maptrip://navigate?posx0=50.2374&posy0=7.123423&posx1=50.2374&posy1=7.123423..&posx9=50.2374&posy9=7.123423*

## Start navigation from HTTP URL ##
It is also possible to start a navigation via an HTTP URL, e.g. for launching MapTrip from a Website or SMS.

*http://maptrip.de/navigate?latitude=50.738912&longitude=7.106772*
