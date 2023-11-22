import {ref} from 'vue'
import {defineStore} from 'pinia'

export const useStateStore = defineStore('state', ()=> {

    const sidebarCollapsed = ref<boolean>(false)
    const activeKey = ref<string|null>(null)
    function toggle(){
        sidebarCollapsed.value = !sidebarCollapsed.value
    }
    return {sidebarCollapsed,toggle,activeKey}
})
