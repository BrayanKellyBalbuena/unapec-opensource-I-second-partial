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
    <jsp:attribute name="head"><Title>Products Categories</Title></jsp:attribute>
    <jsp:attribute name="otherScripst"><script src="js/categories.js"></script></jsp:attribute>
    <jsp:body>
        <template>
            <v-data-table
                    :headers="headers"
                    :items="categories"
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
                        <v-toolbar-title>Products Categories</v-toolbar-title>
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
                                                <v-text-field v-model="editedCategory.id"
                                                              label="Category Id"></v-text-field>
                                            </v-col>
                                            <v-col cols="12" sm="6" md="6">
                                                <v-text-field v-model="editedCategory.name" label="Name"></v-text-field>
                                            </v-col>
                                            <v-col cols="12" sm="6" md="6">
                                                <v-text-field v-model="editedCategory.description"
                                                              label="Description"></v-text-field>
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
                            @click="editCategory(item)"

                    >
                        edit
                    </v-icon>
                    <v-icon
                            small
                            @click="deleteCategory(item)"
                    >
                        delete
                    </v-icon>
                </template>

            </v-data-table>
        </template>
    </jsp:body>
</t:genericpage>
