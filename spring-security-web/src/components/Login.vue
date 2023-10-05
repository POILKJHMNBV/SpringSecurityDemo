<template>
    <div class="box">
        <div class="container">
            <label for="username">用户名：</label>
            <input type="text" v-model="username" required>
            <br>
            <label for="password">密码：</label>
            <input type="password" v-model="password" required>
            <br>
            <button type="submit" @click="login">登录</button>
        </div>
    </div>
    
</template>

<script>
import router from '../router/index.js';
export default {
    name: 'Login',
    data() {
        return {
            username: '',
            password: ''
        }
    },
    methods: {
        login: function() {
            let that = this;
            if(!(/^[a-zA-Z0-9]{2,50}$/.test(that.username))) {
                that.$message({
                    message: '用户名格式不正确',
                    type: 'error',
                    duration: 1200
                });
            } else if(!(/^[a-zA-Z0-9]{6,50}$/.test(that.password))) {
                that.$message({
                    message: '密码格式不正确',
                    type: 'error',
                    duration: 1200
                });
            } else {
                let data = { username: that.username, password: that.password };
                that.$post('user/login', data, true, function(resp) {
                    let token = resp.data;
                    console.log("token = ", token);
                    localStorage.setItem('token', token);
                    router.push({ name: 'Main' });
                })
            } 
        }
    }
}
</script>

<style scoped>
.box {
    font-family: Arial, sans-serif;
    background-image: url('../assets/login.png');
    background-size: cover;
    background-position: center;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0;
}

.container {
    background-color: rgba(255, 255, 255, 0.8);
    padding: 20px;
    border-radius: 10px;
    width: 300px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h1 {
    text-align: center;
    color: #333;
}

label {
    display: block;
    margin-bottom: 5px;
}

input {
    width: 100%;
    padding: 5px;
    margin-bottom: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

button {
    width: 100%;
    padding: 10px;
    background-color: #f00;
    color: #fff;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}
</style>