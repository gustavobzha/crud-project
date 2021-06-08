import Vue from 'vue';
import Router from 'vue-router'

import Home from './views/Home.vue';
import ListaClientes from './views/ListaClientes.vue';
import ListaLinhas from './views/ListaLinhas.vue';
import ListaEstruturas from './views/ListaEstruturas.vue';
import ListaDispositivos from './views/ListaDispositivos.vue';

Vue.use(Router);

export default new Router({
    mode: 'history',
    routes: [
        {
            path: '/home',
            name: 'home',
            component: Home
        },
        {
            path: '/listaClientes',
            name: "listaClientes",
            component: ListaClientes
        },
        {
            path: '/listaLinhas',
            name: "listaLinhas",
            component: ListaLinhas
        },
        {
            path: '/listaEstruturas',
            name: 'listaEstruturas',
            component: ListaEstruturas
        },
        {
            path: '/listaDispositivos',
            name: 'listaDispositivos',
            component: ListaDispositivos
        },
        {
            path: '/',
            redirect: '/home'
        }
    ]
})