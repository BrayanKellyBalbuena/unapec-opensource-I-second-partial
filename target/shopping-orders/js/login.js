new Vue({
    el: "#app",
    vuetify: new Vuetify(),
    data() {
        return {
            userLogin: {
                email: "",
                password: ""
            },
            userRegistration: {
                firstName: "",
                lastName: "",
                identificationCard: "",
                email: "",
                password: "",
                createdDate: ""
            },
            repeatPassword: "",
            displayLogin: true,
            API_PATH: "./api/users/",
            rules: {
                required: value => !!value || "Required.",
                validateEmail: value =>
                    /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(
                        value
                    ) || "Invalid Email",
                validateSamePassword: value =>
                    value === this.userRegistration.password ||
                    "Passwords must be the same",
                validateWithRepeatPassword: value =>
                    value === this.repeatPassword || "Passwords must be the same"
            },
            loginFormIsValid: false,
            registerFormIsValid: false,
            displaySuccessAlert: false,
            displayErrorAlert: false,
            errorMessage: ""
        };
    },

    methods: {
        login() {
            axios
                .post(this.API_PATH + 'login', {
                    email: this.userLogin.email,
                    password: this.userLogin.password
                })
                .then(response => {
                    localStorage.setItem("user", response.data);
                    window.open('./index.jsp', '_blank');
                })
                .catch(error => {
                    console.error(error);
                    this.errorMessage = error;
                    this.diplayErrorAlert = true;
                });
        },
        register() {
            if (this.registerFormIsValid) {
                axios.post(this.API_PATH, {
                    firstName: this.userRegistration.firstName,
                    lastName: this.userRegistration.lastName,
                    identificationCard: this.userRegistration.identificationCard,
                    email: this.userRegistration.email,
                    password: this.userRegistration.password,
                    createdDate: moment().format("D-MM-YYYY H:m:s")
                }).then(data => {
                    alert(data);
                }).catch(err => {
                    alert(err);
                })

            }
        },
        showRegistrationForm() {
            this.displayLogin = !this.displayLogin;
        }
    }
});
