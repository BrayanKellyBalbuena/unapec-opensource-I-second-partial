new Vue({
    el: "#app",
    vuetify: new Vuetify(),
    data() {
        return {
            dialog: false,
            singleSelect: true,
            search: "",
            headers: [
                {text: "Id", value: "id"},
                {text: "Name", value: "firstName"},
                {text: "LastName", value: "lastName"},
                {text: "Email", value: "email"},
                {text: "IdentificationCard", value: "identificationCard"},
                {text: "Created Date", value: "createdDate"},
                {text: 'State', value: 'state'},
                {text: "Actions", value: "action", sortable: false}
            ],
            users: [],
            editedUser: {
                id: 0,
                firstName: '',
                lastName: "",
                identificationCard: "",
                password: '',
                createdDate: "",
                state: true
            },
            defaultUser: {
                id: 0,
                firstName: "",
                lastName: "",
                identificationCard: "",
                password: '',
                createdDate: "",
                state: true
            },
            currentUser: {firstName: '', lastName: ''},
            rules: {
                required: value => !!value || "Required.",
                validateEmail: value =>
                    /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(
                        value
                    ) || "Invalid Email",
                validateSamePassword: value =>
                    value === this.editedUser.password ||
                    "Passwords must be the same"
            },
            editedIndex: -1,
            API_PATH: "./api/users/",
            registerFormIsValid: false,
            repeatPassword: '',
            allSelected: true
        };
    },

    computed: {
        formTitle() {
            return this.editedIndex === -1 ? "New User" : "Edit User";
        }
    },

    watch: {
        dialog(val) {
            val || this.close();
        },
        allSelected: function (val) {
            //Use your source of truth to trigger events!
            this.editedUser.state = val;
            console.log(val)
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
        console.log(this.currentUser);
        let $vm = this;
        this.getAllMembers($vm);
    },

    methods: {
        getAllMembers: function (vm) {
            axios.get(this.API_PATH).then(function (response) {
                vm.users = response.data;
            });
        },

        editUser: function (user) {
            this.editedIndex = this.users.indexOf(user);
            this.editedUser = Object.assign({}, user);
            this.editedUser.password = ''
            this.dialog = true;
        },

        deleteUser: function (dto) {
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
                                swal('Error', ' please contact your system admin', 'error')
                            }
                        });

                } else {

                }
            });

        },
        logout() {
            authenticationService.logout();
        },

        close() {
            this.dialog = false;
            setTimeout(() => {
                this.editedUser = Object.assign({}, this.defaultUser);
                this.editedIndex = -1;
            }, 300);
        }

        , save() {
            if (this.editedIndex > -1) {
                axios
                    .put(this.API_PATH + this.editedUser.id, {
                        id: this.editedUser.id,
                        firstName: this.editedUser.firstName,
                        lastName: this.editedUser.lastName,
                        identificationCard: this.editedUser.identificationCard,
                        email: this.editedUser.email,
                        password: this.editedUser.password,
                        createdDate: moment().format("D-MM-YYYY H:m:s"),
                        state: this.editedUser.state,
                        createdBy: this.currentUser.email
                    })
                    .then(response => {
                        this.getAllMembers(this);
                        this.close()
                        swal('Success', '', 'success');
                    })
                    .catch(error => {
                        if (error.response.status = 404) {
                            swal('Error', error.toString(), 'error')
                        } else {
                            swal('Error', ' please contact your system admin', 'error')
                        }
                    });
            } else {
                axios.post(this.API_PATH, {
                    firstName: this.editedUser.firstName,
                    lastName: this.editedUser.lastName,
                    identificationCard: this.editedUser.identificationCard,
                    email: this.editedUser.email,
                    password: this.editedUser.password,
                    createdDate: moment().format("D-MM-YYYY H:m:s"),
                    createdBy: this.currentUser.email,
                    state: this.editedUser.state
                }).then(data => {
                    this.getAllMembers(this)
                    this.registerFormIsValid = true;
                    this.userRegistration = Object.assign({}, this.userRegistrationDefault);
                    this.close()
                    swal('Success', '', 'success');
                }).catch(err => {
                    if (err.response.status = 404) {
                        swal('Error', err.toString(), 'error')
                    } else {
                        swal('Error', ' please contact your system admin', 'error')
                    }
                });
                this.close();
            }
        }
    }
});
