new Vue({
    el: '#app',
    vuetify: new Vuetify(),
    data () {
        return {
            dialog:false,
            singleSelect: true,
            search: '',
            headers: [
                { text: 'Id', value: 'id' },
                { text: 'Name', value: 'name' },
                { text: 'Price', value: 'price' },
                {text: 'Category', value: 'category'},
                { text: 'Actions', value: 'action', sortable: false }
            ],
            rules: {
                required: value => !!value || 'Required.',
                validatePrice: value => /^[0-9]+$/.test(value) || 'Invalid number'
            },
            products: [],
            categories: [],
            selectedCategory: {},
            editedProduct: {
                id: 0,
                name: '',
                price: 0.0,
                createdDate: '',
                state: true
            },
            defaultProduct: {
                id: 0,
                name: '',
                price: 0.0,
                createdDate: '',
                state: true
            },
            editedIndex: -1,
            API_URL: "./api/products/",
            CATEGORIES_ENDPOINT: "./api/products-category/",
            formIsValid: false,
            displaySuccessAlert: false,
            displayErrorAlert: false,
            errorMessage: '',
            currentUser: {firstName: '', lastName: ''}
        }
    },

    beforeCreate() {
        if (authenticationService.getCurrentUser() === null) {
            window.location.href = './login.jsp'
        }
    },

    computed: {
        formTitle() {
            return this.editedIndex === -1 ? 'New Product' : 'Edit Product'
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
        this.getAllMembers($vm);
    },

    methods:{
        getAllMembers: function (vm) {
            axios.get(this.API_URL)
                .then(function(response){
                    vm.products = response.data;
                });
            axios.get(this.CATEGORIES_ENDPOINT)
                .then((resp) => {
                    vm.categories = resp.data;
                })
        },

        logout() {
            authenticationService.logout();
        },
        editProduct: function (product) {
            this.editedIndex = this.products.indexOf(product);
            this.editedProduct = Object.assign({}, product);
            this.selectedCategory = product.categoryId;
            this.dialog = true;
        },

        deleteProduct: function(product) {
            const id = this.products.find(c => c.id === product.id).id;
            if(confirm('Are you sure want to delete') ){
                axios.delete(this.API_URL + product.id, {
                } ).then( (response) => {
                    this.diplaySuccessAlert = true;
                    this.getAllMembers(this);
                }).catch( (error) => {
                    this.diplayErrorAlert = true;
                    this.errorMessage = error;
                })
            }
        },

        close () {
            this.dialog = false;
            setTimeout(() => {
                this.editedProduct = Object.assign({}, this.defaultProduct);
                this.editedIndex = -1
            }, 300)
        },

        save () {
            if (this.editedIndex > -1) {
                axios.put(this.API_URL + this.editedProduct.id, {
                    id: this.editedProduct.id,
                        categoryId: this.selectedCategory,
                    name: this.editedProduct.name,
                        price: this.editedProduct.price,
                        createdDate: this.editedProduct.createdDate,
                        state: true
                    },
                ).then( (response) => {
                    this.getAllMembers(this);
                    this.diplaySuccessAlert = true;
                }).catch((error) => {
                    this.diplayErrorAlert = true;
                    this.errorMessage = error;
                });

            } else {
                axios.post(this.API_URL, {
                    name: this.editedProduct.name,
                        categoryId: this.selectedCategory,
                        price: this.editedProduct.price,
                        createdDate: moment().format("D-MM-YYYY H:m:s"),
                        state: true
                    },
                ).then( (response) => {
                    this.getAllMembers(this)
                    this.diplaySuccessAlert = true;
                }).catch((error) => {
                    this.diplayErrorAlert = true;
                    this.errorMessage = error;
                })

            }
            this.close()
        },
    }
});