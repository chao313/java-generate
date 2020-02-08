import { getToken, setToken, removeToken } from '@/utils/auth'
import api from '../../utils/api.js'
import axios from 'axios';

const user = {
  state: {
    status: '',
    token: getToken(),
    userInfo: {},
    seacherfrom:{
      start: 1,
      end: 10,
      axisNum: "",
      plateNo: ""
    },
  },

  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_STATUS: (state, status) => {
      state.status = status
    },
    SET_USERINFO: (state, info) => {
      state.userInfo = info
    }

  },

  actions: {
    // 用户名登录
    LoginByUsername({ commit }, token) {
          commit('SET_TOKEN', token)
          setToken(token)
    },

    // 获取用户信息
    GetUserInfo({ commit, state }) {
      return new Promise((resolve, reject) => {
           	axios.get(api.getUserInfo, {
							params: {	}
					  }).then(function(response) {
					  	commit('SET_USERINFO', response.data)
							resolve(response);
						}).catch(function(response) {
							return reject(response);
						}).catch(error => {
							reject(error)
						})
           
      })
    },

    // 登出
    LogOut({ commit, state }) {
      return new Promise((resolve, reject) => {
          commit('SET_TOKEN', '')
          removeToken()
          resolve()
      })
    },

    // 前端 登出
    FedLogOut({ commit }) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        removeToken()
        resolve()
      })
    },

  }
}

export default user
