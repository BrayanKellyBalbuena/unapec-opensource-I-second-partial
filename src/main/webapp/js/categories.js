new Vue({
    el: '#app',
    vuetify: new Vuetify(),
    data() {
        return {
            dialog: false,
            singleSelect: true,
            search: '',
            headers: [
                {text: 'Id', value: 'id'},
                {text: 'Name', value: 'name'},
                {text: 'Descripcion', value: 'description'},
                {text: 'Created Date', value: 'createdDate'},
                {text: 'Modified Date', value: 'modifiedDate'},
                {text: 'State', value: 'state'},
                {text: 'Actions', value: 'action', sortable: false}
            ],
            categories: [],
            editedCategory: {
                id: 0,
                name: '',
                description: '',
                createdDate: '',
                state: true
            },
            defaultCategory: {
                id: 0,
                name: '',
                description: '',
                createdDate: '',
                state: true
            },
            editedIndex: -1,
            API_PATH: "./api/products-category/",
            currentUser: {firstName: '', lastName: ''}
        }
    },

    computed: {
        formTitle() {
            return this.editedIndex === -1 ? 'New Product Category' : 'EditCategory'
        }
    },

    watch: {
        dialog(val) {
            val || this.close()
        }
    },

    beforeCreate() {
        let user = authenticationService.getCurrentUser();

        if (user === null) {
            window.location.href = './login.jsp'
        } else if (JSON.parse(user).email != 'b@b.com') {

            window.location.href = './index.jsp'
        }
    },

    mounted() {
        this.currentUser = JSON.parse(authenticationService.getCurrentUser());
        let $vm = this;
        this.getAllMembers($vm);
    },

    methods: {
        getAllMembers: function (vm) {
            axios.get(this.API_PATH)
                .then(function (response) {
                    vm.categories = response.data;
                });
        },

        logout() {
            authenticationService.logout();
        },

        editCategory: function (category) {
            this.editedIndex = this.categories.indexOf(category);
            this.editedCategory = Object.assign({}, category);
            this.dialog = true;
        },

        deleteCategory: function (dto) {
            swal({
                title: "Are you sure?",
                text: "Once deleted",
                icon: "warning",
                buttons: true,
                dangerMode: true,
            }).then((willDelete) => {
                if (willDelete) {
                    axios
                        .delete(this.API_URL + dto.id)
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

        close() {
            this.dialog = false;
            setTimeout(() => {
                this.editedCategory = Object.assign({}, this.defaultCategory);
                this.editedIndex = -1
            }, 300)
        },

        save() {
            if (this.editedIndex > -1) {
                axios.put(this.API_PATH + this.editedCategory.id, {
                        id: this.editedCategory.id,
                        name: this.editedCategory.name,
                        description: this.editedCategory.description,
                        createdDate: this.editedCategory.createdDate,
                        state: this.editedCategory.state
                    }
                ).then((response) => {
                    this.getAllMembers(this)
                    swal('Success', '', 'success');
                    this.close()
                }).catch((error) => {
                    swal("Error", error.toString(), 'error');
                });

            } else {
                axios.post(this.API_PATH, {
                        id: this.editedCategory.id,
                        name: this.editedCategory.name,
                        lastName: this.editedCategory.lastName,
                        description: this.editedCategory.description,
                    createdBy: this.currentUser.email,
                    createdDate: moment().format("D-MM-YYYY H:m:s")
                    }
                ).then((response) => {
                    this.getAllMembers(this)
                    swal('Success', '', 'success');
                    this.close()
                }).catch((error) => {
                    swal("Error", error.toString(), 'error');
                })

            }
        },
    }
});