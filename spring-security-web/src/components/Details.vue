<template>
    <div class="container">
        <img src="../assets/details.png" alt="枫丹廷">
        <button class="exit-button" @click="goBack">返回</button>
    </div>
</template>

<script>
import router from '../router/index.js';
export default {
    name: 'Clorinde',
    methods: {
        goBack: function() {
            router.push({ name: 'Main' });
        }
    },
}
</script>

<style scoped>
.container {
    margin: 10px auto;
    padding: 20px;
    background-color: #FFFFFF;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    font-family: Arial, sans-serif;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    max-width: 100%;
    height: auto;
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
}
</style>