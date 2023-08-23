
<template>
    <div class="container">
        <div class="header">
                <p id="pp">聊天室（v1.5） 当前用户  ({{ username }})</p>
        </div>
      <div class="left-col">
        <div class="online-count">
          <span>当前注册人数：</span>
          <span>{{ count }}</span>
        </div>
        <div></div>
        <button id="refresh" @click="refresh">刷新</button>

        <div>
            <button class="bt" @click="group">群聊</button>
        </div>
        <div v-for="user in list" :key="user">
            <button class="bt" :id="user" @click="privatemsg(user)">{{ user }}</button>
        </div>
      </div>
      <div class="right-col">
        <div class="chat-box">
        <div v-for="item in msgList " class="message">
            <div class="author">{{ item['name'] }}</div>
            <div class="content">{{ item['msg'] }}</div>
        </div>
            <div  class="message" id="mm">
          </div>
        </div>
        <div class="input-box">
          <input v-model="msg" id="ib" type="text" placeholder="输入信息...">
          <input type="submit" id="sumbit" @click="send" value="发送">
        </div>
      </div>
    </div>
  </template>
  <script setup>
  import {ref} from 'vue'
  import axios from 'axios'
  import router from '../router'; // 导入 router
  import { onMounted } from 'vue'
  
  
  onMounted(() => {
    s();
  })
  const beforeRouteEnter = (to,from,next) => {
  if (ws.open) {
    ws.close;
    next();
  }
}
  
  let list = ref([])
  let count = ref(0)
  let msg = ref('')
  let jsonData = ref({})
  let u = ref("");
  let m = ref("");
  let msgList = ref([])
  let user = ref("")
 
  
  
  const username = localStorage.getItem('username')
  const ws = new WebSocket('ws://qqwc.life:7000/chat/'+username)
  
  ws.onopen = function() {
    console.log("群聊");
        alert('登录成功，已连接聊天室');
        refresh();
  }
  
  ws.onmessage = (event) => {
      jsonData = JSON.parse(event.data);
      m = jsonData['msg']
      u = jsonData['name']
      const chatBox = document.getElementById('mm');
      const messageNode = document.createElement('div');
        messageNode.classList.add('message');
        messageNode.innerHTML = '<div class="author">'+u+'</div><div class="content">'+m+'</div>';
        chatBox.appendChild(messageNode);
  }
      

  function send(){
      axios.get('http://qqwc.life:7000/session')
      .then(res => {
      })
      const message = ib.value;
      ws.send(message)
      ib.value = ''
      msg = ""
  }
      
  
  function refresh(){
      axios.get('http://qqwc.life:7000/alluser')
      .then(res => {
        msg = '';
          list.value = res.data
          count = list.value.length;
      })
      
  };
  function s(){
      axios.get('http://qqwc.life:7000/select')
      .then(res => {
          msgList = res.data
      })
  };
  function privatemsg(value){
    router.push('/' + value);
  }
  function group(){
    router.push('/chat');
  }


  </script>
  
  
  <style>
body {
  margin: 0;
  height: 100vh;
  font-family: Arial, Helvetica, sans-serif;
}
.bt{
  background-color: gray;
  /* position:fixed;  */
  margin: 5px;
  width: 90%;
  height: 40px;
  color: #fff;
  border: none;
  border-radius: 3px;
  padding: 8px 12px;
  font-size: 14px;
}
#ib{
    width: 60%;
}

.header {
  height: 30px;
  background-color:  grey;
  width: 85%;
  margin-left: 15.5%;
  display: flex;
  align-items: center; 
  padding: 12px;
  border: 3px solid #ddd;
  border-radius: 4px;
}

.container {
  height: calc(100% - 50px);  
}

/* 左栏样式 */
.left-col {
    height: 90%;
    background-color: #ddd;
  position: absolute; 
  top: 0;
  bottom: 0;
  width: 15%;
}
.online-count {
  font-size: 14px;
}
#sumbit{
  background-color: #007bff;
  margin: 5px;
  width: 60px;
  height: 40px;
  color: #fff;
  border: none;
  border-radius: 5px;
  padding: 8px 12px;
  font-size: 14px;
}
#refresh{
  background-color: #007bff;
  width: 100%;
  height: 40spx;
  color: #fff;
  border: none;
  border-radius: 3px;
  padding: 8px 12px;
  font-size: 14px;
}



.right-col {
  margin-left: 15.5%;  
  width: 90%;
  display: flex;
  flex-direction: column;
}
.chat-box {
    height: 550px;
  flex-grow: 1;
  overflow-y: auto;
  padding: 10px;
  background-color:cornsilk;
  border: 3px solid #ddd;
  border-radius: 4px;
}

.message {
  margin: 5px 0;
  padding: 8px;
}

.author {
  font-weight: bold;
  margin-bottom: 4px;
}

.time {
  font-size: 12px;
  color: #999;
}

/* 输入框固定在底部 */
.input-box {
    height: 40px;
  position: absolute;
  left: 0;
  right: 0;
  bottom: 0;
  /* display: flex; */
  align-items: center;  
  padding: 10px;
  background-color: #f5f5f5;
}

.input-box input {
  flex-grow: 1;
  padding: 8px 10px;
  border: 1px solid #ddd;
  border-radius: 3px;
 
  margin-top: auto; /* 靠底部 */

}

.input-box button {
  margin-left: 10px;
}
.author {
  font-weight: bold;
  color: #409EFF;
}

.content {
  background: #f0f0f0;
  padding: 8px;
  border-radius: 4px; 
}
  </style>
  