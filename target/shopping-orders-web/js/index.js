new Vue({
    el: "#app",
    vuetify: new Vuetify(),
    data() {
        return {
            currentUser: {firstName: '', lastName: ''}
            , dialog: false,
            API_REPORT_ORDER_USER: './api/shopping-orders/report-orders-user',
            API_REPORT_ORDER_BY_DATE: './api/shopping-orders/report-orders-date',
            dialogReportDate: false
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
        getRandomColor(quantities) {
            let letters = '0123456789ABCDEF';
            let color = '#';
            let colors = [];

            for (let i = 0; i < quantities; i++) {

                colors.push('#' + (Math.random() * 0xFFFFFF << 0).toString(16));
            }
            console.log(colors);

            return colors;
        }
        ,
        showReportUser() {
            this.dialog = true;
            this.dialogReportDate = false;
            axios.get(this.API_REPORT_ORDER_USER)
                .then((resp) => {
                    let ctx = document.getElementById('popChart').getContext('2d');
                    let fullNames = resp.data.map(e => e.fullName);
                    let barChart = new Chart(ctx, {
                        type: 'bar',
                        data: {
                            labels: fullNames,
                            datasets: [{
                                label: 'Total Amount',
                                backgroundColor: this.getRandomColor(fullNames.length),
                                data: resp.data.map(e => e.totalOrder)
                            }]
                        },
                    });
                })
                .catch((err) => {
                    swal('Error', 'loading report data', 'error')
                })
        },

        showReportQuantityOrderByDate() {
            this.dialog = false;
            this.dialogReportDate = true;
            axios.get(this.API_REPORT_ORDER_BY_DATE)
                .then((resp) => {
                    console.log(resp.data.map(e => e));
                    let ctx = document.getElementById('popChartDate').getContext('2d');
                    let orderDates = resp.data.map(e => e.orderDate)
                    let barChart = new Chart(ctx, {
                        type: 'bar',
                        data: {
                            labels: orderDates,
                            datasets: [{
                                label: 'Quantity Orders',
                                backgroundColor: this.getRandomColor(orderDates.length),
                                data: resp.data.map(e => e.quantity)
                            }],
                            options: {
                                scales: {
                                    yAxes: [{
                                        ticks: {
                                            beginAtZero: true
                                        }
                                    }]
                                }
                            }
                        },
                    });
                })
                .catch((err) => {
                    swal('Error', 'loading report data', 'error')
                })
        }

    }
});
