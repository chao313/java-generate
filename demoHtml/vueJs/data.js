var queryBase = "http://127.0.0.1:8000/demomaster/TTitleController/queryBase";

var vue = new Vue({
    el: "#body",
    data: {
        alertError: "系统错误，请联系管理员！",
        alertSuccess: "操作成功",

        titles: [{
            id: '',
            title: ''
        }],

    },
    created() {
        this.queryBase();
    },
    methods: {
        queryBase() {
            let self = this;
            axios.post(queryBase, {})
                .then(function (response) {
                    let data = response.data;
                    console.log(data);
                    if (data.code == 0) {
                        self.titles = data.content;
                        console.log(self.titles);
                    } else {
                        alert(data.msg)
                    }
                })
                .catch(function (error) {
                    alert(this.alertErrorValue)
                });

        }

    }
});