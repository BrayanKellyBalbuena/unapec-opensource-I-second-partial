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
            diplaySuccessAlert: false,
            diplayErrorAlert: false,
            errorMessage: '',
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

    mounted: function () {
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

        editLocation: function (location) {
            this.editedIndex = this.locations.indexOf(location);
            this.editedLocation = Object.assign({}, location);
            this.dialog = true;
        },

        deleteLocation: function (location) {
            const id = this.locations.find(c => c.id === location.id).id;
            if (confirm('Are you sure want to delete')) {
                axios.delete(this.API_PATH + id).then((response) => {
                    this.getAllMembers(this)
                }).catch((error) => {
                    this.errorMessage = error
                })
            }
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
                    this.diplaySuccessAlert = true;
                }).catch((error) => {
                    console.error(error)
                    this.errorMessage = error
                    this.diplayErrorAlert = true;
                });

            } else {
                axios.post(this.API_PATH, {
                        id: this.editedLocation.id,
                        name: this.editedLocation.name,
                        lastName: this.editedLocation.lastName,
                        description: this.editedLocation.description,
                        createdDate: moment(new Date().toDateString()).format("D-MM-YYYY H:m:s")
                    }
                ).then((response) => {
                    this.getAllMembers(this)
                }).catch((error) => {
                    this.errorMessage = error;
                    this.diplayErrorAlert = true;
                    console.error(error)
                })

            }
            this.close()
        },
    }
});