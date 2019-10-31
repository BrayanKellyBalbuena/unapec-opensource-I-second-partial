<%--
  Created by IntelliJ IDEA.
  User: scrip
  Date: 9/15/2019
  Time: 2:15 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="head"><Title>Users</Title></jsp:attribute>
    <jsp:attribute name="otherScripst"><script src="js/users.js"></script></jsp:attribute>
    <jsp:body>
        <div id="app">
            <v-app>
                <template>
                    <v-data-table
                            :headers="headers"
                            :items="users"
                            :search="search"
                            sort-by="id"
                            class="elevation-1 mt-4">
                        <template v-slot:item.state="{ item }">
                            <v-icon>
                                {{ item.state ? "mdi-checkbox-marked" : "mdi-checkbox-blank-outline" }}
                            </v-icon>
                        </template>
                        <template v-slot:top>
                            <v-toolbar flat color="white">
                                <v-toolbar-title>Clients</v-toolbar-title>
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
                                <v-dialog v-model="dialog" persistent max-width="500px">
                                    <template v-slot:activator="{ on }">
                                        <v-btn color="green" dark class="mb-2" v-on="on">New Item</v-btn>
                                    </template>
                                    <v-card>
                                        <v-card-title>
                                            <span class="headline blue--text">{{ formTitle }}</span>
                                        </v-card-title>

                                        <v-card-text>
                                            <v-container>
                                                <v-row>
                                                    <v-form ref="form" v-model="registerFormIsValid"
                                                            :lazy-validation='true'>
                                                        <v-container>
                                                            <v-row>
                                                                <v-col sm="12" md="6">
                                                                    <v-text-field :rules="[rules.required]"
                                                                                  v-model="editedUser.firstName"
                                                                                  label="First Name">
                                                                    </v-text-field>
                                                                </v-col>
                                                                <v-col sm="12" md="6">
                                                                    <v-text-field :rules="[rules.required]"
                                                                                  v-model="editedUser.lastName"
                                                                                  label="Last Name">
                                                                    </v-text-field>
                                                                </v-col>
                                                            </v-row>

                                                            <v-row>
                                                                <v-col sm="12" md="6" lg="6">
                                                                    <v-text-field :rules="[rules.required]"
                                                                                  v-model="editedUser.identificationCard"
                                                                                  label="Identification Card">
                                                                    </v-text-field>
                                                                </v-col>
                                                                <v-col sm="12" md="6" lg="6">
                                                                    <v-text-field
                                                                            :rules="[rules.required, rules.validateEmail]"
                                                                            v-model="editedUser.email"
                                                                            label="Email">

                                                                    </v-text-field>
                                                                </v-col>
                                                            </v-row>
                                                            <v-row>
                                                                <v-col sm="12" md="6">
                                                                    <v-text-field v-model="editedUser.password"
                                                                                  :rules="[rules.required]"
                                                                                  :type="'password'"
                                                                                  label="Password"></v-text-field>
                                                                </v-col>
                                                                <v-col sm="12" md="6">
                                                                    <v-checkbox v-model="editedUser.state"
                                                                                label="Status"></v-checkbox>
                                                                </v-col>
                                                            </v-row>
                                                        </v-container>
                                                    </v-form>
                                                </v-row>
                                            </v-container>
                                        </v-card-text>
                                        <v-card-actions>
                                            <div class="flex-grow-1"></div>
                                            <v-btn color="gray" text @click="close">Cancel</v-btn>
                                            <v-btn color="green" text @click="save">Save</v-btn>
                                        </v-card-actions>
                                    </v-card>
                                </v-dialog>
                            </v-toolbar>
                        </template>
                        <template v-slot:item.action="{ item }">
                            <v-icon small class="mr-2" @click="editUser(item)">edit</v-icon>
                            <v-icon small @click="deleteUser(item)">delete</v-icon>
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
    </jsp:body>
</t:genericpage>