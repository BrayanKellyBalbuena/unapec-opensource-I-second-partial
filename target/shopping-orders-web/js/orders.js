new Vue({
    el: '#app',
    vuetify: new Vuetify(),
    data () {
        return {
            dialog: false,
            singleSelect: true,
            search: '',
            headers: [
                {
                    align: 'left',
                    sortable: false,
                    value: 'name',
                },
                {text: 'Id', value: 'id'},
                {text: 'Client Name', value: 'clientName'},
                {text: 'Product', value: 'product.name'},
                {text: 'Price', value: 'product.price'},
                {text: 'Location', value: 'location.name'},
                {text: 'Quantity', value: 'quantity'},
                {text: 'SubTotal', value: 'subTotal'},
                {text: 'OrderDate', value: 'createdDate'},
                {text: 'Actions', value: 'action', sortable: false}
            ],
            products: [],
            orders: [],
            locations: [],
            selectedClient: {},
            selectedProduct: {id: 0, name: '', price: 0},
            selectedLocation: {},
            editedOrder: {
                id: 0,
                clientName: '',
                product: {id: 0, name: '', price: 0.0},
                quantity: 0.0,
                subTotal: 0.0,
                orderDate: ''
            },
            defaultOrder: {
                id: 0,
                clientName: '',
                product: {id: 0, name: '', price: 0.0},
                quantity: 0.0,
                subTotal: 0.0,
                orderDate: ''
            },
            editedIndex: -1,
            API_ORDERS: "./api/shopping-orders/",
            API_ORDERS_BY_USER_ID: "./api/shopping-orders/user/",
            API_PRODUCTS: "./api/products/",
            API_LOCATIONS: "./api/locations/",
            currentUser: {'id': 0, firstName: '', lastName: ''},
            registerFormIsValid: false
        }

    },

    beforeCreate() {
        let user = authenticationService.getCurrentUser();
        if (user === null) {
            window.location.href = './login.jsp'
        }
    },

    computed: {
        formTitle() {
            return this.editedIndex === -1 ? 'New Order' : 'Edit Order'
        }
    },

    watch: {
        dialog (val) {
            val || this.close()
        }
    },

    mounted: function(){
        this.currentUser = JSON.parse(authenticationService.getCurrentUser());
        let $vm = this;
        this.getAllMembers($vm)

    },

    methods:{

        getAllMembers: function (vm) {
            if (this.currentUser.email == "admin@admin.com") {

                axios.get(this.API_ORDERS)
                    .then(function (response) {
                        vm.orders = response.data;
                    }).catch(err => {
                    swal('Error', 'trying load orders', 'error')
                });
            } else {
                axios.get(this.API_ORDERS_BY_USER_ID + this.currentUser.id)
                    .then(function (response) {
                        vm.orders = response.data;
                    }).catch(err => {
                    swal('Error', 'trying load orders', 'error')
                });
            }

            axios.get(this.API_PRODUCTS).then( (resp) => {
                vm.products = resp.data;
            }).catch((error) => {
                swal('Error', 'trying load products', 'error')
            });

            axios.get(this.API_LOCATIONS).then((resp) => {
                vm.locations = resp.data;
            }).catch((error) => {
                swal('Error', 'trying load locations', 'error')
            });
        },

        logout() {
            authenticationService.logout();
        },

        editOrder: function(order) {
            this.editedIndex = this.orders.indexOf(order);
            this.editedOrder = Object.assign({}, order);
            this.selectedProduct = order.product.id;
            this.selectedLocation = order.location.id;
            this.dialog = true;
        },

        deleteOrder: function(order) {
            console.log(order);
            swal({
                title: "Are you sure?",
                text: "Once deleted",
                icon: "warning",
                buttons: true,
                dangerMode: true,
            }).then((willDelete) => {
                if (willDelete) {
                    axios
                        .delete(this.API_ORDERS + order.id)
                        .then(response => {
                            this.getAllMembers(this);
                            swal("Success", {
                                icon: "success",
                            });
                        })
                        .catch(error => {
                            if (error.response.status = 404) {
                                swal('Error', error.toString(), 'error')
                            } else {
                                swal('Error', ' please contact your system admin', 'error')
                            }
                        });

                } else {

                }
            });
        },

        close () {
            this.dialog = false;
            setTimeout(() => {
                this.editedOrder = Object.assign({}, this.defaultOrder);
                this.editedIndex = -1
            }, 300)
        },

        save () {
            if (this.editedIndex > -1) {
                let currentlocation = this.locations.find(l => l.id === this.selectedLocation);
                axios.put(this.API_ORDERS + this.editedOrder.id, {
                    id: this.editedOrder.id,
                    userId: this.currentUser.id,
                    location: currentlocation,
                    product: this.selectedProduct,
                    price: this.selectedProduct.price,
                    quantity: this.editedOrder.quantity,
                    subTotal: this.selectedProduct.price * this.editedOrder.quantity,
                    orderDate: moment().format('D-MM-YYYY H:m:s'),
                    createdDate: this.editedOrder.createdDate,
                    createdBy: this.editedOrder.createdBy,
                    state: true
                }).then((response) => {
                    this.getAllMembers(this)
                    swal('Success', '', 'success');
                    this.close()
                }).catch((error) => {
                    swal('Error', error.toString(), 'error')
                });

            } else {
                let currentlocation = this.locations.find(l => l.id === this.selectedLocation);
                axios.post(this.API_ORDERS,{
                        userId: this.currentUser.id,
                        location: currentlocation,
                    product: this.selectedProduct,
                    price: this.selectedProduct.price,
                    quantity: this.editedOrder.quantity,
                    subTotal: this.selectedProduct.price * this.editedOrder.quantity,
                        orderDate: moment().format('D-MM-YYYY H:m:s'),
                        createdDate: moment().format('D-MM-YYYY H:m:s'),
                        createdBy: this.currentUser.email,
                        state: true
                    }
                ).then( (response) => {
                    this.getAllMembers(this)
                    swal('Success', '', 'success');
                    this.close()

                }).catch((error) => {
                    swal('Error', error.data, 'error');
                })

            }
        },

        viewInMap(item) {
            let encodeUrl = encodeURI(item.name)
            window.open(`https://nominatim.openstreetmap.org/search.php?q={encodeUrl}&polygon_geojson=1`)
        }
    }
});