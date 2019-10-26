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
            userRegistrationDefault: {
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
            errorMessage: "",
            showLoading: false,
            showLoadingRegistration: false,
        };
    },

    mounted() {
        this.userIsLogin();
    },

    methods: {
        userIsLogin() {
            let user = authenticationService.getCurrentUser(this.userLogin)
            if (user !== null) {
                window.location.href = './index.jsp';
            }
            return;
        },

        login() {
            if (this.loginFormIsValid) {

                this.showLoading = true;
                this.formIsValid = false;

                axios
                    .post('./api/users/login', {
                        email: this.userLogin.email,
                        password: this.userLogin.password
                    })
                    .then(response => {
                        localStorage.setItem(userKey, JSON.stringify(response.data));
                        window.location.href = './index.jsp';
                    })
                    .catch(error => {
                        console.log({date: error})
                        if (error.response.status = 404) {
                            swal('Error', 'User or password are invalid', 'error')
                        } else {
                            swal('Error', error.response.data)
                        }

                        this.showLoading = false;
                        this.formIsValid = true;
                    });

            } else {
                swal("Info", 'Form is not valid', 'error');
            }
        },
        register() {
            if (this.registerFormIsValid) {
                this.showLoadingRegistration = true;
                this.registerFormIsValid = false;

                axios.post(this.API_PATH, {
                    firstName: this.userRegistration.firstName,
                    lastName: this.userRegistration.lastName,
                    identificationCard: this.userRegistration.identificationCard,
                    email: this.userRegistration.email,
                    password: this.userRegistration.password,
                    createdDate: moment().format("D-MM-YYYY H:m:s")
                }).then(data => {
                    this.showLoadingRegistration = false;
                    this.registerFormIsValid = true;
                    this.userRegistration = Object.assign({}, this.userRegistrationDefault);
                    this.displayLogin = true;
                    swal('Success', 'registration was successfully.please go to login page', 'success');
                }).catch(err => {
                    if (err.response.status = 404) {
                        swal('Error', err.response.data, 'error')
                    } else {
                        swal('Error', err.toString())
                    }

                    this.showLoadingRegistration = false;
                    this.registerFormIsValid = true;

                })
            } else {
                swal('Error', 'Form is not valid', 'error')

                return;
            }
        },
        showRegistrationForm() {
            this.displayLogin = !this.displayLogin;
        }
    }
});
