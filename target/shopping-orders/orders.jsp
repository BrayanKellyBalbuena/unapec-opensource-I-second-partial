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
    <jsp:attribute name="head"><Title>Orders</Title></jsp:attribute>
    <jsp:attribute name="otherScripst"><script src="js/orders.js"></script></jsp:attribute>
    <jsp:body>
        <template>
            <v-data-table
                    :headers="headers"
                    :items="orders"
                    :search="search"
                    sort-by="id"
                    class="elevation-1 mt-4">
                <template v-slot:top>
                    <v-toolbar flat color="white">
                        <v-toolbar-title>Orders</v-toolbar-title>
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
                                <v-btn color="green " dark class="mb-2" v-on="on">New Item</v-btn>
                            </template>
                            <v-card>
                                <v-card-title>
                                    <span class="headline blue--text">{{ formTitle }}</span>
                                </v-card-title>

                                <v-card-text>
                                    <v-form ref="form" v-model="registerFormIsValid" :lazy-validation='true'>
                                        <v-container>
                                            <v-row>
                                                <v-col cols="12" sm="6" md="6">
                                                    <v-select :items="products" item-text="name" v-model="selectedProduct"
                                                              label="Select a product" :return-object="true"></v-select>
                                                </v-col>
                                                <v-col cols="12" sm="6" md="6">
                                                    <v-select :items="locations" item-text="name"
                                                              v-model="selectedLocation" item-value="id"
                                                              label="Select a destination"></v-select>
                                                </v-col>
                                                <v-col cols="12" sm="6" md="6">
                                                    <v-text-field v-model="editedOrder.quantity" label="Quantity" type="number"></v-text-field>
                                                </v-col>
                                                <v-col cols="12" sm="6" md="6">
                                                    <v-text-field v-model="selectedProduct.price" label="Price" type="number"></v-text-field>
                                                </v-col>
                                                <v-col cols="12" sm="6" md="6">
                                                    <v-text-field label="Subtotal" v-bind:value="selectedProduct.price * editedOrder.quantity" readonly></v-text-field>
                                                </v-col>
                                            </v-row>
                                        </v-container>
                                    </v-form>
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
                    <v-icon
                            small
                            class="mr-2"
                            @click="editOrder(item)">
                        edit
                    </v-icon>
                    <v-icon
                            small
                            @click="deleteOrder(item)">
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
