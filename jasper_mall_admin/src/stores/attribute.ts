import {ref} from 'vue'
import {defineStore} from 'pinia'

export const useAttribute = defineStore('attribute', ()=> {

    const attribute = ref({
            id: ''
    })
    return {attribute}
})
