import {ref} from 'vue'
import {defineStore} from 'pinia'

export const useCategoryStore = defineStore('category', ()=> {

    const category = ref({
        attributeList:[]
    })
    return { category}
})
