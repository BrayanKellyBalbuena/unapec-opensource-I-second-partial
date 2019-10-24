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
                {text: 'Descripcion', value: 'description'},
                {text: 'Created Date', value: 'createdDate'},
                {text: 'Modified Date', value: 'modifiedDate'},
                {text: 'State', value: 'state'},
                {text: 'Actions', value: 'action', sortable: false}
            ],
            categories: [],
            editedCategory: {
                id: 0,
                name: '',
                description: '',
                createdDate: '',
                state: true
            },
            defaultCategory: {
                id: 0,
                name: '',
                description: '',
                createdDate: '',
                state: true
            },
            editedIndex: -1,
            API_PATH: "./api/products-category/"
        }
    },

    computed: {
        formTitle() {
            return this.editedIndex === -1 ? 'New Product Category' : 'EditCategory'
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
                    vm.categories = response.data;
                });
        },

        editCategory: function (category) {
            this.editedIndex = this.categories.indexOf(category);
            this.editedCategory = Object.assign({}, category);
            this.dialog = true;
        },

        deleteCategory: function (category) {
            const id = this.categories.find(c => c.id === category.id).id;
            if (confirm('Are you sure want to delete')) {
                axios.delete(this.API_PATH + id).then((response) => {
                    this.getAllMembers(this)
                }).catch((error) => {
                    alert(error)
                })
            }
        },

        close() {
            this.dialog = false;
            setTimeout(() => {
                this.editedCategory = Object.assign({}, this.defaultCategory);
                this.editedIndex = -1
            }, 300)
        },

        save() {
            if (this.editedIndex > -1) {
                axios.put(this.API_PATH + this.editedCategory.id, {
                        id: this.editedCategory.id,
                        name: this.editedCategory.name,
                        description: this.editedCategory.description,
                        createdDate: this.editedCategory.createdDate,
                        state: this.editedCategory.state
                    }
                ).then((response) => {
                    this.getAllMembers(this)
                }).catch((error) => {
                    console.error(error)
                });

            } else {
                axios.post(this.API_PATH, {
                        id: this.editedCategory.id,
                        name: this.editedCategory.name,
                        lastName: this.editedCategory.lastName,
                        description: this.editedCategory.description,
                        createdDate: moment().format("D-MM-YYYY H:m:s")
                    }
                ).then((response) => {
                    this.getAllMembers(this)
                }).catch((error) => {
                    console.error(error)
                })

            }
            this.close()
        },
    }
});