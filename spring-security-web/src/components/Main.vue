<template>
<div class="box">
    <div class="container">
        <h1>欢迎<span>{{nickname}}</span>来到水神的国度！</h1>
        <p>在这里，你可以畅游在各种精彩的海底世界中。</p>
        <img src="../assets/index.png"  alt="原神4.0">
    </div>
    <div>
        <button class="exit-button" @click="details">详情</button>
        <button class="exit-button" @click="enter">进入</button>
        <button class="exit-button" @click="logout">退出</button>
    </div>
</div>

</template>

<script>
import router from '../router/index.js';
export default {
    name: 'Main',
    data() {
        return {
            nickname: ''
        }
    },
    methods: {
        details: function() {
            let that = this;
            that.$get('user/details', null, true, function(resp) {
                router.push({ name: 'Details' });
            })
        },
        enter: function() {
            let that = this;
            that.$get('user/enter', null, true, function(resp) {
                router.push({ name: 'Clorinde' });
            })
        },
        logout: function() {
            let that = this;
            that.$get('user/logout', null, true, function(resp) {
                localStorage.removeItem('token');
                router.push({ name: 'Login' });
            })
        }
    },
    created: function() {
        let that = this;
        that.$get('user/queryCurrentUserNickname', null, true, function(resp) {
            that.nickname = resp.data;
        })
    }
}
</script>

<style scoped>
.box {
    background-color: #F9F9F9;
    font-family: Arial, sans-serif;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    margin: 0;
    padding: 0;
}

.container {
    margin: 10px auto;
    padding: 20px;
    background-color: #FFFFFF;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h1 {
    color: #333333;
    text-align: center;
    margin-top: 20px;
}

p {
    color: #666666;
    text-align: center;
    margin-bottom: 20px;
}

img {
    max-width: 100%;
    height: auto;
    margin-bottom: 20px;
}

.exit-button {
    background-color: #4CAF50;
    border: none;
    color: white;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    margin-right: 2px;
}
</style>