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
<style>
    .login {
        margin: 10% 30%;
    }

    body
</style>
</head>
<body>
<div id="app">
    <v-app>
        <div class="login">
            <v-card>
                <v-card-title>
                    <span class="headline">Login</span>
                </v-card-title>

                <v-card-text>
                    <v-form ref="form" v-model="formIsValid" :lazy-validation='true'>
                        <v-container>
                            <v-row>
                                <v-col cols="12" sm="6" md="12">
                                    <v-text-field :rules="[rules.required, rules.validateEmail]"
                                                  v-model="user.email"
                                                  label="Email"></v-text-field>
                                </v-col>
                                <v-col cols="12" sm="6" md="12">
                                    <v-text-field v-model="user.password"
                                                  :rules="[rules.required]"
                                                  label="Password"></v-text-field>
                                </v-col>
                                <v-col cols="12" sm="6" md="6">

                                </v-col>
                            </v-row>
                        </v-container>
                    </v-form>
                </v-card-text>

                <v-card-actions>
                    <div class="flex-grow-1"></div>
                    <v-btn color="gray">Register</v-btn>
                    <v-btn :disabled="!formIsValid"
                           color="success"
                           class="mr-4"
                           color="green" text @click="login">Login
                    </v-btn>
                </v-card-actions>
            </v-card>
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
        </div>
    </v-app>
</div>

<script src="js/libs/axios.js"></script>
<script src="js/libs/vuetify.js"></script>
<script src="js/login.js"></script>
</body>
</html>