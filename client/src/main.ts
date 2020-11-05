import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import Antd, { message } from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';
import './styles/index.scss';

const app = createApp(App);

app.config.globalProperties = {
  message,
};

app.use(store)
  .use(router)
  .use(Antd)
  .mount('#app');
