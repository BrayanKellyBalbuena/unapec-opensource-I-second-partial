new Vue({
    el: '#app',
    vuetify: new Vuetify(),
    data() {
        return {
            user: {
                email: '',
                password: ''
            },
            dialog: false,
            API_PATH: "./api/locations/",
            rules: {
                required: value => !!value || 'Required.',
                validateEmail: value => /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(value)
                    || "Invalid Email",
            },
            formIsValid: false,
            displaySuccessAlert: false,
            displayErrorAlert: false,
            errorMessage: '',
        }
    },

    methods: {
        login() {
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
            }
        },
    }
});