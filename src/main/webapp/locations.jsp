<%--
  Created by IntelliJ IDEA.
  User: scrip
  Date: 9/15/2019
  Time: 2:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products locations</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Material+Icons" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/@mdi/font@3.x/css/materialdesignicons.min.css" rel="stylesheet">
    <link href="css/vuetify.min.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, minimal-ui">
    <script src="js/libs/vue.js"></script>
</head>
<body>
<div id="app">
    <v-app>
        <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
            <!-- Brand/logo -->
            <a class="navbar-brand" href="index.jsp">Logo</a>

            <!-- Links -->
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="clients.jsp">Clients</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="orders.jsp">Orders</a>
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
            </ul>
        </nav>

        <template>
            <div class="mt-2">
                <v-alert type="success" v-model="displaySuccessAlert" dense
                         border="left" dismissible>
                    Saved Successfully
                </v-alert>
                <v-alert type="error" v-model="displayErrorAlert" dense
                         border="left" dismissible>
                    {{errorMessage}}
                </v-alert>
            </div>
            <v-data-table
                    :headers="headers"
                    :items="locations"
                    :search="search"
                    sort-by="id"
                    class="elevation-1 mt-4"
            >
                <template v-slot:item.state="{ item }">
                    <v-icon>
                        {{ item.state ? "mdi-checkbox-marked" : "mdi-checkbox-blank-outline" }}
                    </v-icon>
                </template>
                <template v-slot:top>
                    <v-toolbar flat color="white">
                        <v-toolbar-title>locations</v-toolbar-title>
                        <v-divider
                                class="mx-4"
                                inset
                                vertical
                        ></v-divider>
                        <div class="flex-grow-1"></div>
                        <v-text-field
                                v-model="search"
                                append-icon="search"
                                label="Search"
                                single-line
                                hide-details
                                class="col-md-4"
                        ></v-text-field>
                        <v-dialog v-model="dialog" max-width="500px">
                            <template v-slot:activator="{ on }">
                                <v-btn color="green" dark class="mb-2" v-on="on">New Item</v-btn>
                            </template>
                            <v-card>
                                <v-card-title>
                                    <span class="headline">{{ formTitle }}</span>
                                </v-card-title>

                                <v-card-text>
                                    <v-form ref="form" v-model="formIsValid" :lazy-validation='true'>
                                        <v-container>
                                            <v-row>
                                                <v-col cols="12" sm="6" md="6" hidden>
                                                    <v-text-field v-model="editedLocation.id"
                                                                  label="Location Id"></v-text-field>
                                                </v-col>
                                                <v-col cols="12" sm="6" md="6">
                                                    <v-text-field :rules="[rules.required]"
                                                                  v-model="editedLocation.name"
                                                                  label="Name"></v-text-field>
                                                </v-col>
                                                <v-col cols="12" sm="6" md="6">
                                                    <v-text-field v-model="editedLocation.latitude"
                                                                  :rules="[rules.required, rules.onlyNumbers]"
                                                                  label="Latitude"></v-text-field>
                                                </v-col>
                                                <v-col cols="12" sm="6" md="6">
                                                    <v-text-field v-model="editedLocation.longitude"
                                                                  :rules="[rules.required, rules.onlyNumbers]"
                                                                  label="Longitude"
                                                                  hint="At least 1 number"></v-text-field>
                                                </v-col>
                                            </v-row>
                                        </v-container>
                                    </v-form>
                                </v-card-text>

                                <v-card-actions>
                                    <div class="flex-grow-1"></div>
                                    <v-btn color="gray" text @click="close">Cancel</v-btn>
                                    <v-btn :disabled="!formIsValid"
                                           color="success"
                                           class="mr-4"
                                           color="green" text @click="save">Save
                                    </v-btn>
                                </v-card-actions>
                            </v-card>
                        </v-dialog>
                    </v-toolbar>
                </template>
                <template v-slot:item.action="{ item }">
                    <v-icon
                            small
                            class="mr-2"
                            @click="editLocation(item)"
                    >
                        edit
                    </v-icon>
                    <v-icon
                            small
                            @click="deleteLocation(item)"
                    >
                        delete
                    </v-icon>
                </template>

            </v-data-table>
        </template>
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
<script src="js/libs/vuetify.js"></script>
<script src="js/libs/moment-locales.min.js"></script>
<script src="js/locations.js"></script>

</body>
</html>
