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
                {text: 'Latitude', value: 'latitude'},
                {text: 'Longitude', value: 'longitude'},
                {text: 'Created Date', value: 'createdDate'},
                {text: 'Modified Date', value: 'modifiedDate'},
                {text: 'State', value: 'state'},
                {text: 'Actions', value: 'action', sortable: false}
            ],
            locations: [],
            editedLocation: {
                id: 0,
                name: '',
                latitude: 0.0,
                longitude: 0.0,
                createdDate: '',
                state: true
            },
            defaultLocation: {
                id: 0,
                name: '',
                latitude: 0.0,
                longitude: 0.0,
                createdDate: '',
                state: true
            },
            editedIndex: -1,
            API_PATH: "./api/locations/",
            rules: {
                required: value => !!value || 'Required.',
                onlyNumbers: value => /^[-0-9.,]+$/.test(value) || "Only numbers",
            },
            formIsValid: false,
            displaySuccessAlert: false,
            displayErrorAlert: false,
            errorMessage: '',
            currentUser: {firstName: '', lastName: ''},
        }
    },

    computed: {
        formTitle() {
            return this.editedIndex === -1 ? 'New Product Location' : 'EditLocation'
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
        }else if(JSON.parse(user).email != 'b@b.com') {

            window.location.href = './index.jsp'
        }
    },

    mounted: function () {
        this.currentUser = JSON.parse(authenticationService.getCurrentUser());
        let $vm = this;
        this.getAllMembers($vm);
    },

    methods: {
        getAllMembers: function (vm) {
            axios.get(this.API_PATH)
                .then(function (response) {
                    vm.locations = response.data;
                });
        },

        logout() {
            authenticationService.logout();
        },

        editLocation: function (location) {
            this.editedIndex = this.locations.indexOf(location);
            this.editedLocation = Object.assign({}, location);
            this.dialog = true;
        },

        deleteLocation: function (dto) {
            swal({
                title: "Are you sure?",
                text: "Once deleted",
                icon: "warning",
                buttons: true,
                dangerMode: true,
            }).then((willDelete) => {
                if (willDelete) {
                    axios
                        .delete(this.API_PATH + dto.id)
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
                                swal('Error',' please contact your system admin' , 'error')
                            }
                        });

                } else {

                }
            });
        },

        close() {
            this.dialog = false;
            setTimeout(() => {
                this.editedLocation = Object.assign({}, this.defaultLocation);
                this.editedIndex = -1
            }, 300)
        },

        save() {
            if (this.editedIndex > -1) {
                axios.put(this.API_PATH + this.editedLocation.id, {
                        id: this.editedLocation.id,
                        name: this.editedLocation.name,
                        latitude: this.editedLocation.latitude,
                        longitude: this.editedLocation.longitude,
                        createdDate: this.editedLocation.createdDate,
                        state: this.editedLocation.state
                    }
                ).then((response) => {
                    this.getAllMembers(this)
                    swal('Success', '', 'success');
                    this.close()
                }).catch((error) => {
                    swal('Error', err.toString(), 'error')
                });

            } else {
                axios.post(this.API_PATH, {
                        id: this.editedLocation.id,
                        name: this.editedLocation.name,
                        lastName: this.editedLocation.lastName,
                        description: this.editedLocation.description,
                        createdBy: this.currentUser.email,
                        createdDate: moment().format("D-MM-YYYY H:m:s")
                    }
                ).then((response) => {
                    this.getAllMembers(this)
                    swal('Success', '', 'success');
                    this.close()
                }).catch((error) => {
                    swal('Error', err.toString(), 'error')
                })

            }
        },
    }
});