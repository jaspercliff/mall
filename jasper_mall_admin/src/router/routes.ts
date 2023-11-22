import type {RouteRecordRaw} from "vue-router";
import {Product} from "@vicons/carbon";
import {
    AppGeneric24Regular,
    BorderTopBottomThick20Filled,
    Production24Filled,
    Production24Regular
} from "@vicons/fluent"
import {DashboardOutlined as dashboard} from "@vicons/antd"
import {BrandMedium as brand} from "@vicons/tabler"

const routes: RouteRecordRaw[] = [
    {
        name: 'login',
        path: '/',
        component: () => import("@/views/admin/login.vue"),
        meta: {
            hidden: true,
        }
    },
    {
        name: 'layout',
        path: '/layout',
        component: () => import("@/layout/index.vue"),
        meta: {
            hidden: true,
        },
        children: []
    },
    {
        name: '首页',
        path: '/frontPage',
        component: () => import("@/layout/index.vue"),
        meta: {},
        children: [
            {
                name: 'dashboard',
                path: 'dashboard',
                component: () => import("@/views/admin/dashboard.vue"),
                meta: {
                    icon: dashboard
                }
            },
        ]
    },
    {
        name: '商品管理',
        path: '/goods',
        component: () => import("@/layout/index.vue"),
        meta: {
            icon: Product
        },
        children: [
            {
                name: 'promotion',
                path: 'promotion',
                component:()=> import("@/views/goods/good/promotion.vue"),
                meta:{
                    hidden: true
                }
            },
            {
                name: 'spu',
                path: 'spu',
                component:()=> import("@/views/goods/good/spu.vue"),
                meta:{
                    hidden: true
                }
            },
            {
                name: '添加商品',
                path: 'addGoods',
                component: () => import("@/views/goods/good/addGoods.vue"),
                meta: {
                    icon: Production24Filled
                }
            },

            {
                name: '增加品牌',
                path: 'addBrand',
                component: ()=> import("@/views/goods/brand/addBrand.vue"),
                meta: {
                    hidden: true
                }
            },
            {
                name: '商品列表',
                path: 'goodsList',
                component: () => import("@/views/goods/good/goodsList.vue"),
                meta: {
                    icon: Production24Regular
                }
            },
            {
                name: '品牌管理',
                path: 'brandManage',
                component: () => import("@/views/goods/brand/brandManage.vue"),
                meta: {
                    icon: brand
                },
            },
            {
                name: '商品分类',
                path: 'categoryList',
                component: () => import("@/views/goods/category/categoryList.vue"),
                meta: {
                    icon: Production24Filled
                }
            },
            {
                name: '添加商品分类',
                path: 'addCategory',
                component: () => import("@/views/goods/category/addCategory.vue"),
                meta: {
                    hidden: true
                }
            },
            {
                name: '商品类型',
                path: 'goodsAttribute',
                component: () => import("@/views/goods/attribute/attributeCategory.vue"),
                meta: {
                    icon: Production24Regular
                }
            },
            {
                name: 'attribute',
                path: 'attribute',
                component: () => import("@/views/goods/attribute/attribute.vue"),
                meta: {
                    hidden:true
                },
            },
            {
                name: 'addAttribute',
                path: 'addAttribute',
                component: ()=> import("@/views/goods/attribute/addAttribute.vue"),
                meta:{
                    hidden: true
                }
            }

        ]
    },
    {
        name: 'order',
        path: '/order',
        component: () => import("@/layout/index.vue"),
        meta: {
            icon: BorderTopBottomThick20Filled
        },
        children: [
            {
                name: 'orderList',
                path: 'list',
                component: () => import("@/views/order/list.vue"),
                meta: {
                    icon: AppGeneric24Regular,
                }
            },
            {
                name: 'orderSetting',
                path: "setting",
                component: () => import("@/views/order/setting.vue"),
                meta: {
                    icon: AppGeneric24Regular
                }
            }
        ]
    },
    {
        name: '营销',
        path: '/seckill',
        component: () => import("@/layout/index.vue"),
        meta: {
            icon: Product
        },
    },
    {
        name: '秒杀管理',
        path: '/seck',
        component: () => import("@/layout/index.vue"),
        meta: {
            icon: Product
        },
    },
    {
        name: '权限管理',
        path: '/sads',
        component: () => import("@/layout/index.vue"),
        meta: {
            icon: Product
        },
    },
]
export default routes
