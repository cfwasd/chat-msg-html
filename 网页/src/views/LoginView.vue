<template class="a">
    <div id="in">
        <form>
            <p id="log">用户登录</p>
            
            <label class="txt">账户：</label>
            <input v-model="uname" type="text" id="user" class="t" placeholder="请输入用户名">
            <br />
            <br />
            <label class="txt">密码：</label>
            <input v-model="pwd" type="text" class="t" id="pwd" placeholder="请输入密码">
            <br />
            <input type="button" id="submit" @click="log" value="登录">
            <br />
            <p>没有账号？<input type="button" id="r" @click="goRegister" value="点击注册"></p>
        </form>
    </div>
</template>

<script setup>
import axios from 'axios';
import router from '../router'; // 导入 router
import {ref} from 'vue';

const uname = ref('');
const pwd = ref('');


 function log(){
    const data = JSON.stringify({
      user: uname.value,
      pwd: pwd.value,
  });
 

  axios({
    url: 'http://qqwc.life:7000/login',
    // url: 'http://localhost:7000/login',
    method: 'post',
    data: data,
    headers: {
      'Content-Type': 'application/json',
    },
  }).then(response => {
      if (response.data.result === 'true') {
        localStorage.setItem('username', uname.value) 
        router.push('/chat'); // 使用 router.push
      } else {
        alert("账号或密码错误");
      }
    })
    .catch(error => {
      console.error(error);
      alert("发生未知错误");
    });
  }
function goRegister() {
  
    router.push('/reglister') 
}
</script>


<style>
body{
  background-image:url(../images/logimg.jpg);
  
}
#in{
  background-image: url(../images/logimg.jpg);
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
#log{
    color: #000;
    font-size: 14px;
    text-align: center;
    border-radius: 5px;
}
.txt{
    margin-left: 10px;
    font-weight: bold;
}
.t{

    width: 80%;
    height: 15px;
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
p {
  text-align: center; 
}

#r{
  color: #008CBA;
}

</style>
