
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
        <button id="refresh" @click="allusers">刷新</button>
        
        <div>
            <button class="bt" @click="group">群聊</button>
        </div>
        <div v-for="user in list" :key="user">
            <button class="bt" :id="user" @click="privatemsg(user)">{{ user }}</button>
        </div>
      </div>
      <div class="right-col">
        <div class="chat-box">
        <div v-for="item in msgList " class="message" id="www">
            <div class="author">{{ item.send }}</div>
            <div class="content">{{ item.message }}</div>
        </div>
            <div  class="msg" id="mm">
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
  import { watch, reactive } from 'vue'
import { useRoute } from 'vue-router';
import { onMounted } from 'vue'
import { computed } from 'vue';

onMounted(()=> {
  setTimeout(() => {
    state.path = route.path
    rou = state.path.slice(1)
    read(rou);
    
  }, 1000)
})


  let list = ref([])
  let count = ref(0)
  let msg = ref('')
  let jsonData = ref({})
  let u = ref("");
  let m = ref("");
  let msgList = ref([])
  let rou = ref("")

  const route = useRoute()
const state = reactive({
  path: ''
})

watch(() => route.params, (params) => {
  state.path = route.path
  rou = state.path.slice(1)
}) //监听路由

watch(msgList, (newVal, oldVal) => {
  // 删除已有的div
  const divList = document.querySelectorAll('.message')
  divList.forEach(div => div.parentNode.removeChild(div))
})

// 生成用于渲染的数据
const renderedMsgList = computed(() => {
  return msgList.value.slice()
})

  
  const username = localStorage.getItem('username')
  const ws = new WebSocket('ws://qqwc.life:7000/chat/'+username)
 
  
  ws.onopen = function() {
        allusers();  //更新注册的所有人
  }
  
  ws.onmessage = (event) => {
      jsonData = JSON.parse(event.data);
      m = jsonData['msg']
      u = jsonData['name']
      
      if(u !== 'all'){
        console.log(u);
      const chatBox = document.getElementById('mm');
      const messageNode = document.createElement('div');
        messageNode.classList.add('message');
        messageNode.innerHTML = '<div class="author">'+u+'</div><div class="content">'+m+'</div>';
        chatBox.appendChild(messageNode);
      }
  }
  const beforeRouteEnter = (to,from,next) => {
  if (ws.open) {
    ws.close;
    next();
  }
}
      

  function privatemsg(value){
    router.push('/'+value);
    read(value)
}
function group(){
    router.push('/chat');
}
function allusers(){
  axios.get('http://qqwc.life:7000/alluser')
      .then(res => {
          list.value = res.data
          count = list.value.length;
    })
}
function send(){
  axios.post('http://qqwc.life:7000/history', {
    send: username,
      receive: rou,
      message: msg.value,
  },{
    headers: {
      'Content-Type': 'application/json',
    }
  })
  ws.send(msg.value)
  msg.value = '';
  }
function read(n){
  document.getElementById('mm').innerHTML = "";
    axios.post('http://qqwc.life:7000/private', {
      send: username,
      receive: n,
      
  },{
    headers: {
      'Content-Type': 'application/json',
    }
  }).then(res => {
          msgList = res.data
      })
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
  overflow-y: scroll; /*滚动条自动显示*/
    height: 90%;
    background-color: #ddd;
  position: absolute; 
  top: 0;
  bottom: 0;
  width: 17%;
 
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
  height: 90%;
  overflow-y: scroll; /*滚动条自动显示*/
  display: flex;
  flex-direction: column;
}
.chat-box {
  height: 405px;
  flex-grow: 1;
  overflow-y: scroll; /*滚动条自动显示*/
  padding: 10px;
  background-color:cornsilk;
  border: 3px solid #ddd;
  border-radius: 4px;
}

.message {
  margin: 5px 0;
  padding: 8px;
}

.msg {
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
  
<!--  
 <script setup>
import { ref } from 'vue';
import router from '../router';
import { useRouter } from 'vue-router';


let name = ref("")
const route = useRouter();
name = route.currentRoute.value.fullPath;
console.log(route.currentRoute);

</script> -->