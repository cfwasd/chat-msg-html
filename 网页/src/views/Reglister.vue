<template>
    <div id="fa">
      <p>注册</p>
      <form @submit.prevent="onSubmit">
        
          <label class="txt" for="username">用户名:</label>
          <input class="t" id="username" v-model="username">
          <br>
          <br>
          <label class="txt" for="password">密&nbsp;&nbsp;&nbsp;码:</label> 
          
          <input class="t" id="password" v-model="password">
          <br>
          <br>
        <button type="submit" id="submit">提交</button>
      </form>
    </div>
  </template>
  
<script>
  import { ref } from 'vue';
  import router from '../router'; // 导入 router
  import axios from 'axios';
  
  export default {
    setup() {
      const username = ref('')
      const password = ref('')
      
      const onSubmit = () => {
        const data = JSON.stringify({
      user: username.value,
      pwd: password.value,
  });
 

  axios({
    url: 'http://qqwc.life:7000/register',
    // url: 'http://localhost:7000/register',
    method: 'post',
    data: data,
    headers: {
      'Content-Type': 'application/json',
    },
  }).then(response => {
      if (response.data.result === 'true') {
        alert("注册成功，即将跳转登录页面");
        router.push('/'); // 使用 router.push
      } else {
        alert("用户名已存在");
      }
    })
    .catch(error => {
      console.error(error);
      alert("发生未知错误");
    });
  
    }
      
      return {
        username,
        password,
        onSubmit
      }
    }
  }
  </script>
<style>

#fa{
  background-image: url(../images/logimg.jpg) ;
  
    height: auto;
    width: 350px;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: #f2f2f2;
    border-radius: 10px;
}
form {
  font-size: 14px;
}
p{
    color: #000;
    font-size: 14px;
    text-align: center;
    border-radius: 5px;
}
.t{
width: 80%;
height: 15px;
}
.txt{
    margin-left: 10px;
    font-weight: bold;
}
#submit{
     margin-top: 20px;
    margin-left: 100px;
    width: 45%;
  margin-top: 20px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
</style>