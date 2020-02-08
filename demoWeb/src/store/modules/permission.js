import { routerMap, constantRouterMap } from '@/router'
import Layout from '@/views/layout/Layout'
import axios from 'axios';
import router from '@/router'
import store from '@/store/'
/**
 * 获取模块名称
 */
function getLoginMoudle() {
	return new Promise(function(resolve, reject) {
		axios.get('/obtainevid/system/getMenu', {
				params: {}
			})
			.then(function(response) {
				resolve(response);
			})
			.catch(function(response) {
				return reject(response);
			})
	});
};
const permission = {
	state: {
		routers: constantRouterMap,
		addRouters: [],
		permission: false,
	},
	mutations: {
		SET_ROUTERS: (state, routers) => {
			state.addRouters = routers
			state.routers = constantRouterMap.concat(routers)
		},
		SET_PERMISSION: (state, flag) => {
			state.permission = flag
		}
	},
	actions: {
		GenerateRoutes({
			commit
		}) {
			return new Promise(resolve => {
				let tempRouter = []
				getLoginMoudle().then(function(res) {　　
					let routerList = res.data;
					if(res.data.errorCode == "system_login_noUserInfo") {
						store.dispatch('LogOut');
						location.reload();
					} else {
						if(routerList.length > 0) {
							commit('SET_PERMISSION', true)
							for(let i = 0; i < routerList.length; i++) {
								let moduleRouter = {
									path: '',
									component: Layout,
									redirect: '',
									children: [],
									name: routerList[i].menuId,
									meta: {
										title: routerList[i].menuName
									}
								}
								let flag = false;
								let routerChildren = routerList[i].childMenu;
								if(routerChildren.length == 0 && routerMap[routerList[i].menuId] != undefined) {
									moduleRouter.children.push(routerMap[routerList[i].menuId]);
									flag = true;

								} else {
									for(let j = 0; j < routerChildren.length; j++) {
										let menuId = routerChildren[j].menuId;
										if(routerMap[menuId] != undefined) {
											moduleRouter.children.push(routerMap[routerChildren[j].menuId]);
											flag = true;
										}

									}
								}
								if(flag) {
									tempRouter.push(moduleRouter);
								}

							}
							commit('SET_ROUTERS', tempRouter)
							resolve()
						} else {
							commit('SET_PERMISSION', false)
							router.push({
								path: 'login'
							})
						}
					}

				}).catch(function onRejected(error) {
					console.error(error);
				});

			})
		}
	}
}

export default permission