new Vue({
    el: "#app",
    vuetify: new Vuetify(),
    data() {
        return {
            currentUser: {firstName: '', lastName: ''}
            ,dialog: false,
            API_ORDERS: './api/shopping-orders/report-orders-user'
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
        },
         getRandomColor() {
            var letters = '0123456789ABCDEF';
            var color = '#';
            for (var i = 0; i < 6; i++ ) {
                color += letters[Math.floor(Math.random() * 16)];
            }
                 return color;
            }
    ,
        showReportUser() {
            this.dialog = true;
            axios.get(this.API_ORDERS)
                .then((resp) => {
                    console.log(resp.data.map(e => e.fullName))
                    let ctx = document.getElementById('popChart').getContext('2d');
                    console.log(resp.data.map(e => e));
                    let barChart = new Chart(ctx, {
                        type: 'bar',
                        data: {
                            labels: resp.data.map(e => e.fullName),
                            datasets: [{
                                label: 'Total Amount',
                                backgroundColor: this.getRandomColor(),
                                borderColor: this.getRandomColor(),
                                data: resp.data.map(e => e.totalOrder)
                            }]
                        },
                    });
                })
                .catch( (err) => {

                })
        }
    }
});
