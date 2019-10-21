<%--
  Created by IntelliJ IDEA.
  User: scrip
  Date: 10/20/2019
  Time: 2:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        /* Always set the map height explicitly to define the size of the div
         * element that contains the map. */
        #map {
            height: 100%;
        }
        /* Optional: Makes the sample page fill the window. */
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
        }
    </style>
</head>
<body>
<div id="app">
        <div id="map"></div>
    <script>
        var map;
        function initMap() {

            let location = JSON.parse(localStorage.getItem("location"));
            map = new google.maps.Map(document.getElementById('map'), {
                center:location,
                zoom: 8
            });

            var marker = new google.maps.Marker({position: location, map: map});
        }
    </script>
</div>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD6SkZKgQ1wA6NQvJmY-qjqFEr8KZMn8Xk&callback=initMap"
        async defer></script>
</body>
</html>
