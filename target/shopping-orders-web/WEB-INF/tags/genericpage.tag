<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@attribute name="otherScripst" fragment="true" %>
<%@attribute name="head" fragment="true" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Material+Icons" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/@mdi/font@3.x/css/materialdesignicons.min.css" rel="stylesheet">
    <link href="css/vuetify.min.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, minimal-ui">
    <script src="js/libs/vue.js"></script>
    <script src="js/libs/vuetify.js"></script>
    <jsp:invoke fragment="head"/>
</head>
<body>
<div id="app">
    <v-app>
        <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
            <a class="navbar-brand" href="index.jsp">Shopping Orders</a>
            <ul v-if="currentUser.email == 'b@b.com'" class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="users.jsp">Users</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="products.jsp">Products</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="categories.jsp">Categories</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="locations.jsp">Locations</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="orders.jsp">Orders</a>
                </li>
            </ul>

            <ul v-else class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="orders.jsp">Orders</a>
                </li>
            </ul>

            <div v-show="currentUser.firstName != ''" class="dropdown ml-auto">
                <button class="btn btn-info dropdown-toggle" role="button" id="dropdownMenuLink"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    {{currentUser.firstName + ' ' + currentUser.lastName}}
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                    <a class="dropdown-item" @click="logout">Logout</a>
                </div>
            </div>
        </nav>
        <jsp:doBody/>
        <template>
            <v-footer dark absolute>
                <v-card class="flex" flat tile>
                    <v-card-actions class="grey darken-3 justify-center">
                        <strong>Brayan Kelly 20181876</strong> -
                        <strong>Date {{ new Date() }} </strong>
                    </v-card-actions>
                </v-card>
            </v-footer>
        </template>
    </v-app>
</div>
<script src="js/libs/axios.js"></script>
<script src="js/libs/moment-locales.min.js"></script>
<script src="js/libs/sweetalert2.1.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous">
</script>
<script src="js/authentication.js"></script>
<jsp:invoke fragment="otherScripst"/>
</body>
</html>