<!-- <template>
    <LoginView />
</template>

<script>
import LoginView from "@/views/LoginView.vue"
import { useRouter } from 'vue-router'
import {ref} from 'vue'

export default {
  setup() {
    const username = ref('')
    const password = ref('')
    const router = useRouter()
    
    
    const login = () => {
        console.log(username, password)
      // 发送登录请求
      const data = {
        username: username.value,
        password: password.value  
      }
      
      fetch('http://localhost:7000/login', { // URL 先留空
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(data) 
      }).then(res => {
        if(res.data.result === "true"){
            router.push('/chat') 
        }
      })
    }
    
    return {
      username,
      password,
      login 
    }
  }
}
</script> -->

<!-- log() {
  var username = this.uname;
  var password = this.pwd;
  const data = JSON.stringify({
    user: username,
    pwd: password,
  });

  axios({
    url: 'http://localhost:7000/login',
    method: 'post',
    data: data,
    headers: {
      'Content-Type': 'application/json',
    },
  }).then(response => {
      if (response.data.result === 'true') {
        alert(5);
        router.push('/chat'); // 使用 router.push
      } else {
        alert(1);
      }
    })
    .catch(error => {
      console.error(error);
      alert(2);
    });
    const uname = ref('');
const pwd = ref('');


 function log(){
    const data = JSON.stringify({
      user: uname.value,
      pwd: pwd.value,
  });
 

  axios({
    url: 'http://localhost:7000/login',
    method: 'post',
    data: data,
    headers: {
      'Content-Type': 'application/json',
    },
  }).then(response => {
      if (response.data.result === 'true') {
        alert(5);
        router.push('/chat'); // 使用 router.push
      } else {
        alert(1);
      }
    })
    .catch(error => {
      console.error(error);
      alert(2);
    });
  }
}, -->