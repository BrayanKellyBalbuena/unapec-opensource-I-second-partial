new Vue({
    el: "#app",
    vuetify: new Vuetify(),
    data() {
        return {
            currentUser: {firstName: '', lastName: ''}
        }
    },

    beforeCreate() {
        if (authenticationService.getCurrentUser() === null) {
            window.location.href = './login.jsp'
        }
    },
    mounted() {
        this.currentUser = JSON.parse(authenticationService.getCurrentUser());
    },

    methods: {
        userIsLogin() {
            if (authenticationService.getCurrentUser(this.userLogin) === null) {
                window.location.href = './login.jsp'
            }
        },
        logout() {
            authenticationService.logout();
        }
    }
});
