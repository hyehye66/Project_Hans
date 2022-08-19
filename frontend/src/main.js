import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import { BootstrapVue3 } from 'bootstrap-vue-3'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue-3/dist/bootstrap-vue-3.css'
import './index.css'
// import vue3GoogleLogin from 'vue3-google-login'
// import GAuth from 'vue3-google-oauth2'

createApp(App).use(store).use(router).mount('#app')
const app = createApp(App)
app.use(BootstrapVue3)
app.use(router)
app.use(store)
// app.use(vue3GoogleLogin, {
//   clientId: '908845611506-17n1k3isuiniproahm42389k4a381ge1.apps.googleusercontent.com',
//   scope: 'email', prompt: 'consent', fetch_basic_profile: false 
// })
// app.use(GAuth)


