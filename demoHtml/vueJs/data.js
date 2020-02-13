var vue = new Vue({
    el: "#body",
    data: {
        alertError: "系统错误，请联系管理员！",

        titles: [{
            id: '',
            title: ''
        }],
        alertSuccess: "操作成功",
        image: [],
        iframe_src: "",
        docker_host: ""

    },
    created() {
        this.queryBase();
    },
    methods: {
        queryBase() {
            let self = this;
            axios.post("http://127.0.0.1:8000/demomaster/TTitleController/queryBase", {})
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