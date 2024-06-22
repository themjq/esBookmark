import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)
import Layout from '@/layout'
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '首页', icon: 'dashboard' }
    }]
  },

  {
    path: '/example',
    component: Layout,
    redirect: '/example',
    name: 'Example',
    meta: { title: '用户管理', icon: 'el-icon-s-tools' },
    children: [
      {
        path: 'user',
        name: 'user',
        component: () => import('@/views/user'),
        meta: { title: '普通用户', icon: 'el-icon-user-solid' }
      },
      {
        path: 'admin',
        name: 'admin',
        component: () => import('@/views/admin'),
        meta: { title: '管理员', icon: 'el-icon-user-solid' }
      }
    ]
  },
  {
    path: '/system',
    component: Layout,
    redirect: '/system',
    name: 'System',
    meta: { title: '网址管理', icon: 'el-icon-s-help' },
    children: [
      {
        path: 'bookmark',
        name: 'bookmark',
        component: () => import('@/views/bookmark'),
        meta: { title: '网址详情', icon: 'el-icon-menu' }
      },
      {
        path: 'bmType',
        name: 'bmType',
        component: () => import('@/views/bmType'),
        meta: { title: '网址类型', icon: 'el-icon-menu' }
      }
    ]
  },
  {
    path: '/comment',
    component: Layout,
    redirect: '/comment',
    name: 'comment',
    meta: { title: '消息管理', icon: 'el-icon-s-tools' },
    children: [
      {
        path: 'comment',
        name: 'comment',
        component: () => import('@/views/comment'),
        meta: { title: '消息推送', icon: 'el-icon-s-data' }
      }
    ]
  },
  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]
const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})
const router = createRouter()
// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}
export default router
