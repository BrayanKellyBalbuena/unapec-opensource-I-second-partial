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
    <jsp:attribute name="head"><Title>Locations</Title></jsp:attribute>
    <jsp:attribute name="otherScripst">
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD6SkZKgQ1wA6NQvJmY-qjqFEr8KZMn8Xk&callback">
        </script>
        <script src="js/locations.js"></script>
    </jsp:attribute>
    <jsp:body>
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
                        <v-dialog v-model="dialog" persistent max-width="600px">
                            <template v-slot:activator="{ on }">
                                <v-btn color="green" dark class="mb-2" v-on="on">New Item</v-btn>
                            </template>
                            <v-card>
                                <v-card-title>
                                    <span class="headline blue--text">{{ formTitle }}</span>
                                </v-card-title>

                                <v-card-text>
                                    <v-form ref="form" v-model="formIsValid" :lazy-validation='true'>
                                        <v-container>
                                            <v-row>
                                                <v-col cols="4" sm="4" md="4" hidden>
                                                    <v-text-field v-model="editedLocation.id"
                                                                  label="Location Id"></v-text-field>
                                                </v-col>
                                                <v-col cols="8" sm="8" md="8">
                                                    <v-text-field :rules="[rules.required]"
                                                                  v-model="editedLocation.name"
                                                                  label="Name">
                                                    </v-text-field>
                                                </v-col>
                                                <v-col cols="1">
                                                    <v-progress-circular
                                                            v-if="showGeocodeLoader"
                                                            :size="20"
                                                            indeterminate
                                                            color="green"
                                                    ></v-progress-circular>
                                                </v-col>
                                                <v-col cols="3" sm="3" md="3">
                                                    <v-btn color="blue" text
                                                           @click="getGeocodeLocations(editedLocation.name)">Get
                                                        Geocode
                                                    </v-btn>
                                                </v-col>
                                            </v-row>
                                            <v-row>
                                                <v-col sm="6" md="6">
                                                    <v-text-field v-model="editedLocation.latitude"
                                                                  :rules="[rules.required, rules.onlyNumbers]"
                                                                  label="Latitude"></v-text-field>
                                                </v-col>
                                                <v-col sm="6" md="6">
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
                    <v-icon
                            small
                            @click="viewInMap(item)">
                        mdi-map-marker
                    </v-icon>
                </template>

            </v-data-table>
        </template>
    </jsp:body>
</t:genericpage>