let userKey = 'user';
let authenticationService =
    {
        logout: () => {
            localStorage.removeItem(userKey);
            window.location.href = './login.jsp'
        },
        getCurrentUser: () => localStorage.getItem(userKey),

        register: (user) => {
            axios.post(this.API_PATH, {
                firstName: user.firstName,
                lastName: user.lastName,
                identificationCard: user.identificationCard,
                email: user.email,
                password: user.password,
                createdDate: moment().format("D-MM-YYYY H:m:s")
            }).then(data => {
                return true;
            }).catch(err => {
                throw err;
            })
        }
    }
