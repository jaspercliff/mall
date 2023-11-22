import {ref} from 'vue'
import {defineStore} from 'pinia'

export const useBrandStore = defineStore('brand', ()=> {

    const brand = ref({

    })
    return { brand}
})
