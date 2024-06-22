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
    redirect: '/login',
    children: [{
      path: 'bookmark',
      name: 'bookmark',
      component: () => import('@/views/bookmark/index'),
      meta: { title: '共享页', icon: 'dashboard' }
    }]
  },

  {
    path: '/manage',
    component: Layout,
    redirect: '/example',
    name: 'manage',
    meta: { title: '管理', icon: 'el-icon-s-tools' },
    children: [
      {
        path: 'manage',
        name: 'manage',
        component: () => import('@/views/manage'),
        meta: { title: '我的书签', icon: 'el-icon-user-solid' }
      }
    ]
  },
  {
    path: '/system',
    component: Layout,
    redirect: '/system',
    name: 'System',
    meta: { title: '信息管理', icon: 'el-icon-s-help' },
    children: [
      {
        path: 'sysinfo',
        name: 'sysinfo',
        component: () => import('@/views/sysinfo'),
        meta: { title: '个人信息', icon: 'el-icon-menu' }
      },
      {
        path: 'system',
        name: 'system',
        component: () => import('@/views/system'),
        meta: { title: '修改信息', icon: 'el-icon-menu' }
      }
    ]
  },
  {
    path: '/comment',
    component: Layout,
    redirect: '/comment',
    name: 'comment',
    meta: { title: '排行榜', icon: 'el-icon-s-tools' },
    children: [
      {
        path: 'comment',
        name: 'comment',
        component: () => import('@/views/comment'),
        meta: { title: '排行榜', icon: 'el-icon-s-data' }
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
