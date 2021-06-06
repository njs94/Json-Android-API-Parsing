# Json-Android-API-Parsing

## Json API Parsing using the AsyncTask threading mechanism

1. Download the Json API from remote URL in doInBackground() of AsyncTask
2. Parse the data using JSONArray and JSONObject
3. Set the Adapter to the custom adapter in onPostExecute() after the downloading is complete.
4. Inflate the layout in onCreateView of Fragment class (Create your own UI. In the sample it will display the downloaded photos of each album in a gridlayout where the tab name is title of the almbum in JSON API)
5. Usage of Picasso library for downloading the images from URL.

Json Albums API : 

    https://jsonplaceholder.typicode.com/albums/

Json Photos API : 

    https://jsonplaceholder.typicode.com/photos/

External Depdencies: 
    
     implementation 'com.squareup.picasso:picasso:2.5.2'
    
Special Permission: Since the remote URL needs to be accessed the application requires the INTERNET Permission. Ensure to give the same in manifest.xml

    <uses-permission android:name="android.permission.INTERNET" />

PS: Usage of HTTP GET method( You can use whichever is convenient )
