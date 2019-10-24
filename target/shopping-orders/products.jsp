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
    <jsp:attribute name="head"><Title>Products</Title></jsp:attribute>
    <jsp:attribute name="otherScripst"><script src="js/products.js"></script></jsp:attribute>
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
                    :items="products"
                    :search="search"
                    sort-by="id"
                    class="elevation-1 mt-4"
            >
                <template v-slot:top>
                    <v-toolbar flat color="white">
                        <v-toolbar-title>Products</v-toolbar-title>
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
                                    <v-container>
                                        <v-row>
                                            <v-col cols="12" sm="6" md="6" hidden>
                                                <v-text-field v-model="editedProduct.id"
                                                              :rules="[rules.required]"
                                                              label="Product Id"></v-text-field>
                                            </v-col>
                                            <v-col cols="12" sm="6" md="6">
                                                <v-text-field v-model="editedProduct.name"
                                                              :rules="[rules.required]"
                                                              label="Name"></v-text-field>
                                            </v-col>
                                            <v-col cols="12" sm="6" md="6">
                                                <v-text-field v-model="editedProduct.price"
                                                              :rules="[rules.required, rules.validatePrice]"
                                                              label="Price"
                                                              type="number"></v-text-field>
                                            </v-col>
                                            <v-col cols="12" sm="6" md="6">
                                                <v-select :items="categories" item-text="name"
                                                          :rules="[rules.required]"
                                                          v-model="selectedCategory" item-value="id"
                                                          label="Select a category">
                                                </v-select>
                                            </v-col>
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
                    <v-icon
                            small
                            class="mr-2"
                            @click="editProduct(item)">
                        edit
                    </v-icon>
                    <v-icon
                            small
                            @click="deleteProduct(item)">
                        delete
                    </v-icon>
                </template>

            </v-data-table>
        </template>
    </jsp:body>
</t:genericpage>
